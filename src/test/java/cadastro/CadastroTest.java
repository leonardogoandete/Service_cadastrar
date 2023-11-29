package cadastro;

import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import cadastro.validadores.ValidaDoc;
import cadastro.validadores.ValidaEmail;
import cadastro.validadores.ValidaEndereco;
import cadastro.validadores.ValidaNome;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class CadastroTest{

    @Test
    public void testValidaDoc(){
        //Retorno válido de CPF E CNPJ
        assertEquals(true, ValidaDoc.validaCpf("70399622098"));
        assertEquals(true, ValidaDoc.validaCnpj("78322050000182"));
         //Retorno inválido de CPF E CNPJ
        assertEquals(false, ValidaDoc.validaCpf("12345678901"));
        assertEquals(false, ValidaDoc.validaCnpj("12345678901234"));
    }

    @Test 
    public void testValidaEmail(){
        //Retorno válido de email
        assertEquals(true, ValidaEmail.validarEmail("teste@gmail.com"));
        //Retorno inválido de email
        assertEquals(false, ValidaEmail.validarEmail("teste468413.´d-"));
    }

    @Test 
    public void testValidaEndereco(){
        //Retorno válido de endereço
        assertEquals(true, ValidaEndereco.validarEndereco("Av Nilo Pecanha 3539"));
        //Retorno inválido de endereço
        assertEquals(false, ValidaEndereco.validarEndereco("Av Nilo Pecanha *#4_"));
    }

    @Test
    public void testValidaNome(){
        //Retorno válido de nome
        assertEquals(true, ValidaNome.validarNome("Jose Alberto"));
        //Retorno inválido de nome
        assertEquals(false, ValidaNome.validarNome(""));
        assertEquals(false, ValidaNome.validarNome("*******"));
    }

    @Test
    public void testCadastroPJSucesso() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "55079581000190")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());
    }

     @Test
    public void testCadastroPJCNPJExistente() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "78322050000182")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("CNPJ existente"));
    }

    @Test
    public void testCadastroPJCNPJInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "12345678901234")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("CNPJ inválido"));
    }

    @Test
    public void testCadastroPJNomeInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "78322050000182")
        .add("nome", "***///@")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("Formato de nome inválido: use apenas letras"));
    }

    @Test
    public void testCadastroPJEnderecoInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "78322050000182")
        .add("nome", "Hospital Teste")
        .add("endereco", "%7$8Rua Teste*")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("Formato de endereço inválido: use apenas letras e números"));
    }

    @Test
    public void testCadastroPJEmailInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "78322050000182")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitaltestehotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("Formato de email inválido"));
    }
    

    //VALIDAÇÕES DE PF

    @Test
    public void testCadastroPFSucesso() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "70399622098")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());
    }

     @Test
    public void testCadastroPFExistente() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "18157310016")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("CPF existente"));
    }

    @Test
    public void testCadastroPJPFInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "12345678901")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("CPF inválido"));
    }

    @Test
    public void testCadastroPFNomeInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "70399622098")
        .add("nome", "***///@")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("Formato de nome inválido: use apenas letras"));
    }

    @Test
    public void testCadastroPFEnderecoInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "70399622098")
        .add("nome", "Hospital Teste")
        .add("endereco", "%7$8Rua Teste*")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("Formato de endereço inválido: use apenas letras e números"));
    }

    @Test
    public void testCadastroPFEmailInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "70399622098")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitaltestehotmail.com")
        .add("senha", "123456")
        .build();

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("Formato de email inválido"));
    }

    

}