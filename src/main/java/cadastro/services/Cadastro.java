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

    @Inject
    PJRepository pjRepository;

    @Inject
    @Metric(name = "histogram", absolute = true)
    Histogram histogramPj;

    private long maiorExecucaoPj;

    final Logger logger = Logger.getLogger(Cadastro.class.getName());

    @POST
    @Path("/instituicao")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "contador", description = "o número de execuções do serviço cadastro")
    @Timed(name = "timer", description = "tempo que leva para executar o serviço", unit = MetricUnits.MICROSECONDS)
    @JsonPropertyOrder({"cnpj", "nome", "endereco", "email", "senha"})
    public Response cadastroPJ(CadastroDTO cadastroDTO){
      
        long startTime = System.currentTimeMillis();

        String senha = BcryptUtil.bcryptHash(cadastroDTO.senha());

        if(pjRepository.findCnpj(cadastroDTO.documento()) != null){
            Map<String, String> response = new HashMap<>();
            logger.log(Level.WARNING,"CNPJ existente");
            response.put("error", "CNPJ existente");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();    
        }else if(ValidaDoc.validaCnpj(cadastroDTO.documento())){
            Map<String, String> response = new HashMap<>();
             logger.log(Level.WARNING,"CNPJ inválido");
            response.put("error", "CNPJ inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaNome.validarNome(cadastroDTO.nome())){
            Map<String, String> response = new HashMap<>();
             logger.log(Level.WARNING,"Formato de nome inválido: use apenas letras");
            response.put("error", "Formato de nome inválido: use apenas letras");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaEndereco.validarEndereco(cadastroDTO.endereco())){
            Map<String, String> response = new HashMap<>();
             logger.log(Level.WARNING,"Formato de endereço inválido: use apenas letras e números");
            response.put("error", "Formato de endereço inválido: use apenas letras e números");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaEmail.validarEmail(cadastroDTO.email())){
            Map<String, String> response = new HashMap<>();
            logger.log(Level.WARNING,"Formato de email inválido");
            response.put("error", "Formato de email inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else{
         CadastroPJ cadastroPJ = new CadastroPJ(cadastroDTO.nome(), cadastroDTO.endereco(), cadastroDTO.email(), 
        senha, cadastroDTO.documento(), Role.INSTITUICAO);
        pjRepository.persist(cadastroPJ);
        }

        long executionTime = System.currentTimeMillis() - startTime;
        histogramPj.update(executionTime);

        if (executionTime> maiorExecucaoPj) {
            this.maiorExecucaoPj = executionTime;
        }
        
        return Response.status(Response.Status.ACCEPTED).entity("Cadastro Realizado").build();
    }

    @Gauge(name = "maiorExecucaoPj", unit = MetricUnits.NONE, description = "Maior tempo de execução para cadastro de pessoa jurídica")
    public Long maiorExecucaoPj() {
        return this.maiorExecucaoPj();
    }
    
    @Inject
    PFRepository pfRepository;

    @Inject
    @Metric(name = "histogram", absolute = true)
    Histogram histogramPf;

    private long maiorExecucaoPf;

    @POST
    @Path("/usuario")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(name = "contador", description = "o número de execuções do serviço cadastro")
    @Timed(name = "timer", description = "tempo que leva para executar o serviço", unit = MetricUnits.MICROSECONDS)
    @JsonPropertyOrder({"cnpj", "nome", "endereco", "email", "senha"})
    public Response cadastroPF(CadastroDTO cadastroDTO){

        long startTime = System.currentTimeMillis();

        String senha = BcryptUtil.bcryptHash(cadastroDTO.senha());

        if(pfRepository.findCpf(cadastroDTO.documento()) != null){
            Map<String, String> response = new HashMap<>();
             logger.log(Level.WARNING,"CPF existente");
            response.put("error", "CPF existente");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaDoc.validaCpf(cadastroDTO.documento())){
            Map<String, String> response = new HashMap<>();
            logger.log(Level.WARNING,"CPF inválido");
            response.put("error", "CPF inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaNome.validarNome(cadastroDTO.nome())){
            Map<String, String> response = new HashMap<>();
            logger.log(Level.WARNING,"Formato de nome inválido: use apenas letras");
            response.put("error", "Formato de nome inválido: use apenas letras");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaEndereco.validarEndereco(cadastroDTO.endereco())){
            Map<String, String> response = new HashMap<>();
            logger.log(Level.WARNING,"Formato de endereço inválido: use apenas letras e números");
            response.put("error", "Formato de endereço inválido: use apenas letras e números");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else if(ValidaEmail.validarEmail(cadastroDTO.email())){
            Map<String, String> response = new HashMap<>();
            logger.log(Level.WARNING,"Formato de email inválido");
            response.put("error", "Formato de email inválido");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response)
                    .build();
        }else{
         CadastroPF cadastroPF = new CadastroPF(cadastroDTO.nome(), cadastroDTO.endereco(), cadastroDTO.email(), 
        senha, cadastroDTO.documento(), Role.USUARIO);
        pfRepository.persist(cadastroPF);
        }

        long executionTime = System.currentTimeMillis() - startTime;
        histogramPf.update(executionTime);

        if (executionTime> maiorExecucaoPf) {
            this.maiorExecucaoPf = executionTime;
        }

        return Response.status(Response.Status.ACCEPTED).entity("Cadastro Realizado").build();
    }

    @Gauge(name = "maiorExecucaoPf", unit = MetricUnits.NONE, description = "Maior tempo de execução para cadastro de pessoa física")
    public Long maiorExecucaoPf() {
        return this.maiorExecucaoPf();
    }

}
