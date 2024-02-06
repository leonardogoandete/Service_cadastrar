package cadastro.services;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import org.eclipse.microprofile.metrics.Histogram;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.*;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.ws.rs.core.Response;
import cadastro.enumerados.Role;
import cadastro.model.*;
import cadastro.dto.*;
import cadastro.validadores.*;
import cadastro.repository.PFRepository;
import cadastro.repository.PJRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.quarkus.elytron.security.common.BcryptUtil;

@Path("/registrar")
public class Cadastro {
    
    //Para uso do persist do Hibernate em banco
    @Inject
    PJRepository pjRepository;
   
    //métrica para testar o tempo de serviço
    @Inject
    @Metric(name = "histogram", absolute = true)
    Histogram histogramPj;
    
    //métrica para testar o maior tempo de serviço
    private long maiorExecucaoPj;
    
    //logger para obter informações sobre o serviço, seja erro ou sucesso
    final Logger logger = Logger.getLogger(Cadastro.class.getName());
    
    //mapa para armazenar as mensagens de erro
    Map<String, String> response = new HashMap<>();
    
    //Serviço de cadastro de pessoa jurídica
    @POST
    @Path("/instituicao")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "contador", description = "o número de execuções do serviço cadastro") //métrica para contar o número de execuções
    @Timed(name = "timer", description = "tempo que leva para executar o serviço", unit = MetricUnits.MICROSECONDS) //métrica para testar o tempo de serviço
    @JsonPropertyOrder({"cnpj", "nome", "endereco", "email", "senha"})
    public Response cadastroPJ(CadastroDTO cadastroDTO){
        
        //Captura o tempo inicial de execução
        long startTime = System.currentTimeMillis();
        
        //Hash de senha
        String senha = BcryptUtil.bcryptHash(cadastroDTO.senha());
        
        //Validações de cadastro, em caso de erro retorna a mensagem de erro
        if(pjRepository.findCnpj(cadastroDTO.documento()) != null){
            logger.log(Level.WARNING,"CNPJ existente");
            response.put("error", "CNPJ existente");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();    
        }else if(!ValidaDoc.validaCnpj(cadastroDTO.documento())){
             logger.log(Level.WARNING,"CNPJ inválido");
            response.put("error", "CNPJ inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaNome.validarNome(cadastroDTO.nome())){
             logger.log(Level.WARNING,"Formato de nome inválido: use apenas letras");
            response.put("error", "Formato de nome inválido: use apenas letras");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaEndereco.validarEndereco(cadastroDTO.endereco())){
             logger.log(Level.WARNING,"Formato de endereço inválido: use apenas letras e números");
            response.put("error", "Formato de endereço inválido: use apenas letras e números");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaEmail.validarEmail(cadastroDTO.email())){
            logger.log(Level.WARNING,"Formato de email inválido");
            response.put("error", "Formato de email inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else{
        //Caso não haja erro, realiza o cadastro
        CadastroPJ cadastroPJ = new CadastroPJ(cadastroDTO.nome(), cadastroDTO.endereco(), cadastroDTO.email(), 
        senha, cadastroDTO.documento(), Role.INSTITUICAO);
        pjRepository.persist(cadastroPJ);
        }
        
        //Verifica o tempo de execução do bloco de código e atualiza no histograma
        long executionTime = System.currentTimeMillis() - startTime;
        histogramPj.update(executionTime);
        
        //Verifica se o tempo de execução é maior que o maior tempo de execução
        if (executionTime> maiorExecucaoPj) {
            this.maiorExecucaoPj = executionTime;
        }
        
        //Retorna a mensagem de sucesso
        logger.log(Level.INFO,"Cadastro Realizado");
        return Response.status(Response.Status.ACCEPTED).entity("Cadastro Realizado").build();
    }
    
    //Métrica para testar o maior tempo de execução(bloco)
    @Gauge(name = "maiorExecucaoPj", unit = MetricUnits.NONE, description = "Maior tempo de execução para cadastro de pessoa jurídica")
    public Long maiorExecucaoPj() {
        return this.maiorExecucaoPj();
    }
    
    //Para uso do persist do Hibernate em banco
    @Inject
    PFRepository pfRepository;
    
    //Métrica para testar o tempo de serviço
    @Inject
    @Metric(name = "histogram", absolute = true)
    Histogram histogramPf;
    
    //Métrica para testar o maior tempo de serviço
    private long maiorExecucaoPf;
    
    //Serviço de cadastro de pessoa física
    @POST
    @Path("/usuario")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "contador", description = "o número de execuções do serviço cadastro") //métrica para contar o número de execuções
    @Timed(name = "timer", description = "tempo que leva para executar o serviço", unit = MetricUnits.MICROSECONDS) //métrica para testar o tempo de serviço
    @JsonPropertyOrder({"cnpj", "nome", "endereco", "email", "senha"})
    public Response cadastroPF(CadastroDTO cadastroDTO){
        
        //Captura o tempo inicial de execução
        long startTime = System.currentTimeMillis();
        //Hash de senha
        String senha = BcryptUtil.bcryptHash(cadastroDTO.senha());
        
         //Validações de cadastro, em caso de erro retorna a mensagem de erro
        if(pfRepository.findCpf(cadastroDTO.documento()) != null){
             logger.log(Level.WARNING,"CPF existente");
            response.put("error", "CPF existente");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaDoc.validaCpf(cadastroDTO.documento())){
            logger.log(Level.WARNING,"CPF inválido");
            response.put("error", "CPF inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaNome.validarNome(cadastroDTO.nome())){
            logger.log(Level.WARNING,"Formato de nome inválido: use apenas letras");
            response.put("error", "Formato de nome inválido: use apenas letras");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaEndereco.validarEndereco(cadastroDTO.endereco())){
            logger.log(Level.WARNING,"Formato de endereço inválido: use apenas letras e números");
            response.put("error", "Formato de endereço inválido: use apenas letras e números");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(!ValidaEmail.validarEmail(cadastroDTO.email())){
            logger.log(Level.WARNING,"Formato de email inválido");
            response.put("error", "Formato de email inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else{
        //Caso não haja erro, realiza o cadastro
        CadastroPF cadastroPF = new CadastroPF(cadastroDTO.nome(), cadastroDTO.endereco(), cadastroDTO.email(), 
        senha, cadastroDTO.documento(), Role.USUARIO);
        pfRepository.persist(cadastroPF);
        }
        
        //Verifica o tempo de execução do bloco de código e atualiza no histograma
        long executionTime = System.currentTimeMillis() - startTime;
        histogramPf.update(executionTime);
        
         //Verifica se o tempo de execução é maior que o maior tempo de execução
        if (executionTime> maiorExecucaoPf) {
            this.maiorExecucaoPf = executionTime;
        }
        
        //Retorna a mensagem de sucesso
        logger.log(Level.INFO,"Cadastro Realizado");
        return Response.status(Response.Status.ACCEPTED).entity("Cadastro Realizado").build();
    }
    
    //Métrica para testar o maior tempo de execução(bloco)
    @Gauge(name = "maiorExecucaoPf", unit = MetricUnits.NONE, description = "Maior tempo de execução para cadastro de pessoa física")
    public Long maiorExecucaoPf() {
        return this.maiorExecucaoPf();
    }

}
