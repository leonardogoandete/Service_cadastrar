package cadastro.validadores;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

public final class ValidaDoc {

    private ValidaDoc(){

    }
    
    //Teste de CPF
    public static boolean validaCpf(String cpf) { 
        CPFValidator cpfValidator = new CPFValidator(); 
        //Valida e retorna true ou false
        try{ cpfValidator.assertValid(cpf); 
        return true; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            return false; 
        } 
    }
    
    //Teste de CNPJ
    public static boolean validaCnpj(String cnpj){
        CNPJValidator cnpjValidator = new CNPJValidator(); 
        //Valida e retorna true ou false
        try{ cnpjValidator.assertValid(cnpj); 
        return true; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            return false; 
        } 
    }

}
