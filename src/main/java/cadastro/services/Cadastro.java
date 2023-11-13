package cadastro.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import cadastro.enumerados.Role;
import cadastro.model.*;
import cadastro.repository.PFRepository;
import cadastro.repository.PJRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/registrar")
public class Cadastro {

    @Inject
    PJRepository pjRepository;

    @GET
    @Path("/instituicao")
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public CadastroPJ CadastroPJ(
        @FormParam("cnpj") String cnpj, 
        @FormParam("nome") String nome, 
        @FormParam("endereco") String endereco,
        @FormParam("email") String email,
        @FormParam("senha") String senha) {
        CadastroPJ cadastroPJ = new CadastroPJ(nome, endereco, email, senha, cnpj, Role.INSTITUICAO);
        pjRepository.persist(cadastroPJ);
        return cadastroPJ;
    }

    @Inject
    PFRepository pfRepository;

    @GET
     @Path("/usuario")
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public CadastroPF CadastroPF(
        @FormParam("cpf") String cpf,
        @FormParam("nome") String nome, 
        @FormParam("endereco") String endereco,
        @FormParam("email") String email,
        @FormParam("senha") String senha) {
        CadastroPF cadastroPF = new CadastroPF(nome, endereco, email, senha, cpf, Role.USUARIO);
        pfRepository.persist(cadastroPF);
        return cadastroPF;
    }

    @GET
    @Path("Hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }
}
