package cadastro;

import io.quarkus.test.junit.QuarkusTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastroTest{

    //Teste - 1 - Verifica se a classe ValidaDoc é final - não pode ser instanciada
    @Test
    @Order(1)
    public void testClasseFinalDoc() throws NoSuchMethodException, SecurityException{
        
        // Obtém o construtor privado da classe
        Constructor<ValidaDoc> construtor = ValidaDoc.class.getDeclaredConstructor();
        
        // Torna o construtor acessível para permitir que o teste o invoque
        construtor.setAccessible(true);
        
        // Verifica se o construtor é privado (private)
        assertTrue(Modifier.isPrivate(construtor.getModifiers()));
        //Verifica se a classe é final
        assertTrue(Modifier.isFinal(ValidaDoc.class.getModifiers()));
       
    }
    
    //Teste - 2 - Verifica se a classe ValidaNome é final - não pode ser instanciada
    @Test
    @Order(2)
    public void testClasseFinalNome() throws NoSuchMethodException, SecurityException{
        
        // Obtém o construtor privado da classe
        Constructor<ValidaNome> construtor = ValidaNome.class.getDeclaredConstructor();
        
        // Torna o construtor acessível para permitir que o teste o invoque
        construtor.setAccessible(true);
        
        // Verifica se o construtor é privado (private)
        assertTrue(Modifier.isPrivate(construtor.getModifiers()));
        // Verifica se a classe é final
        assertTrue(Modifier.isFinal(ValidaNome.class.getModifiers()));
       
    }

    //Teste - 3 - Verifica se a classe ValidaEndereco é final - não pode ser instanciada
    @Test
    @Order(3)
    public void testClasseFinalEndereco() throws NoSuchMethodException, SecurityException{
        
        // Obtém o construtor privado da classe
        Constructor<ValidaEndereco> construtor = ValidaEndereco.class.getDeclaredConstructor();
        
        // Torna o construtor acessível para permitir que o teste o invoque
        construtor.setAccessible(true);
        
        // Verifica se o construtor é privado (private)
        assertTrue(Modifier.isPrivate(construtor.getModifiers()));
        //Verifica se a classe é final
        assertTrue(Modifier.isFinal(ValidaEndereco.class.getModifiers()));
       
    }

    //Teste - 4 - Verifica se a classe ValidaEndereco é final - não pode ser instanciada
    @Test
    @Order(4)
    public void testClasseFinalEmail() throws NoSuchMethodException, SecurityException{
        
        // Obtém o construtor privado da classe
        Constructor<ValidaEmail> construtor = ValidaEmail.class.getDeclaredConstructor();
        
        // Torna o construtor acessível para permitir que o teste o invoque
        construtor.setAccessible(true);
        
        // Verifica se o construtor é privado (private)
        assertTrue(Modifier.isPrivate(construtor.getModifiers()));
        //Verifica se a classe é final
        assertTrue(Modifier.isFinal(ValidaEmail.class.getModifiers()));
       
    }
   
    //Teste - 5 - Verifica se a classe ValidaDoc valida corretamente CPF e CNPJ
    @Test
    @Order(5)
    public void testValidaDoc(){
        //Retorno válido de CPF E CNPJ
        assertEquals(true, ValidaDoc.validaCpf("70399622098"));
        assertEquals(true, ValidaDoc.validaCnpj("78322050000182"));
         //Retorno inválido de CPF E CNPJ
        assertEquals(false, ValidaDoc.validaCpf("12345678901"));
        assertEquals(false, ValidaDoc.validaCnpj("12345678901234"));
    }
    
    //Teste - 6 - Verifica se a classe ValidaEmail valida corretamente o email
    @Test 
    @Order(6)
    public void testValidaEmail(){
        //Retorno válido de email
        assertEquals(true, ValidaEmail.validarEmail("teste@gmail.com"));
        //Retorno inválido de email
        assertEquals(false, ValidaEmail.validarEmail("testegmail"));
    }
    
    //Teste - 7 - Verifica se a classe ValidaEndereco valida corretamente o endereço
    @Test 
    @Order(7)
    public void testValidaEndereco(){
        //Retorno válido de endereço
        assertEquals(true, ValidaEndereco.validarEndereco("Av Nilo Pecanha 3539"));
        //Retorno inválido de endereço
        assertEquals(false, ValidaEndereco.validarEndereco("Av Nilo Pecanha 3539_1"));
    }
    
    //Teste - 8 - Verifica se a classe ValidaNome valida corretamente o nome
    @Test
    @Order(8)
    public void testValidaNome(){
        //Retorno válido de nome
        assertEquals(true, ValidaNome.validarNome("Jose Alberto"));
        //Retorno inválido de nome
        assertEquals(false, ValidaNome.validarNome(""));
        assertEquals(false, ValidaNome.validarNome("Jose_Beto86"));
    }
    
    //VALIDAÇÕES DE PJ
    //Teste - 9 - Verifica se o cadastro de PJ é realizado com sucesso
    @Test
    @Order(9)
    public void testCadastroPJSucesso() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "55079581000190")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();
        
        //Vai até o caminho e envia os dados para cadastro
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());
    }

    //Teste - 10 - Verifica um CNPJ já existente em banco - não deixará cadastrar documento repetido
    @Test
    @Order(10)
    public void testCadastroPJCNPJExistente() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "78322050000182") //CNPJ que será inserido
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitalteste@hotmail.com")
        .add("senha", "123456")
        .build();
        
        //Inserção dos dados
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());
        //Inserção repetida dos dados para testar o erro
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/instituicao")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("CNPJ existente"));
    }

    //Teste - 11 - Verifica a inserção de um CNPJ inválido
    @Test
    @Order(11)
    public void testCadastroPJCNPJInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "12345678901234") // CNPJ inválido
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

    //Teste - 12 - Verifica a inserção de um nome inválido na PJ - apenas letras
    @Test
    @Order(12)
    public void testCadastroPJNomeInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "16707700000150")
        .add("nome", "Hospital Conceicao_14#") // nome inválido
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
    
    //Teste - 13 - Verifica a inserção de um endereço inválido - apenas letras e números
    @Test
    @Order(13)
    public void testCadastroPJEnderecoInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "03287472000165")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 1234*") // endereço inválido
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

    //Teste - 14 - Verifica a inserção de um email inválido
    @Test
    @Order(14)
    public void testCadastroPJEmailInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "50630504000163")
        .add("nome", "Hospital Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "hospitaltestehotmail.com") // email inválido
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
    //Teste - 15 - Verifica se o cadastro de PF é realizado com sucesso
    @Test
    @Order(15)
    public void testCadastroPFSucesso() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "70399622098")
        .add("nome", "Usuario Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "usuarioteste@hotmail.com")
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
    
    //Teste - 10 - Verifica um CPF já existente em banco - não deixará cadastrar documento repetido
    @Test
    @Order(16)
    public void testCadastroPFExistente() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "18157310016") // CPF que será inserido
        .add("nome", "Usuario Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "usuarioteste@hotmail.com")
        .add("senha", "123456")
        .build();
        
        //Inserção de dados
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.ACCEPTED.getStatusCode());
        //Inserção repetida dos dados para testar o erro
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(cadastro.toString())
        .when()
            .post("/registrar/usuario")
        .then()
            .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
            .body("error", is("CPF existente"));
    }
    
    //Teste - 17 - Verifica a inserção de um CPF inválido
    @Test
    @Order(17)
    public void testCadastroPJPFInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "12345678901") // CPF inválido
        .add("nome", "Usuario Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "usuarioteste@hotmail.com")
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

    //Teste - 18 - Verifica a inserção de um nome inválido na PJ - apenas letras
    @Test
    @Order(18)
    public void testCadastroPFNomeInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "60546547052")
        .add("nome", "Jose-Alberto20") //Nome inválido
        .add("endereco", "Rua Teste 123")
        .add("email", "usuarioteste@hotmail.com")
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
    
    //Teste - 19 - Verifica a inserção de um endereço inválido na PF - apenas letras e números
    @Test
    @Order(19)
    public void testCadastroPFEnderecoInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "34761531002")
        .add("nome", "Usuario Teste")
        .add("endereco", "Rua Teste_8193")//Endereço inválido
        .add("email", "usuarioteste@hotmail.com")
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
    
    //Teste - 20 - Verifica a inserção de um email inválido na PF
    @Test
    @Order(20)
    public void testCadastroPFEmailInvalido() {

        // Dados do cadastro
        JsonObject cadastro = Json.createObjectBuilder()
        .add("documento", "57157459000")
        .add("nome", "Usuario Teste")
        .add("endereco", "Rua Teste 123")
        .add("email", "usuariotestehotmail.com") //email inválido
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