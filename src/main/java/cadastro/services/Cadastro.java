package cadastro.services;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import cadastro.model.*;
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

    @GET
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public CadastroPJ CadastroPJ(
        @FormParam("cnpj") String cnpj, 
        @FormParam("nome") String nome, 
        @FormParam("endereco") String endereco,
        @FormParam("email") String email,
        @FormParam("senha") String senha) {
        CadastroPJ cadastroPJ = new CadastroPJ(cnpj, nome, endereco, email, senha);
        cadastroPJ.persist();
        return cadastroPJ;
    }

    @GET
    @Transactional
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public CadastroPF CadastroPF(
        @FormParam("nome") String nome, 
        @FormParam("endereco") String endereco,
        @FormParam("email") String email,
        @FormParam("senha") String senha) {
        CadastroPF cadastroPF = new CadastroPF(nome, endereco, email, senha);
        cadastroPF.persist();
        return cadastroPF;
    }
}
