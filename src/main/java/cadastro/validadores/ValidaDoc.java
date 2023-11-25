package cadastro.validadores;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

public final class ValidaDoc {

    private ValidaDoc(){

    }
    
    public static boolean validaCpf(String cpf) { 
        CPFValidator cpfValidator = new CPFValidator(); 
        try{ cpfValidator.assertValid(cpf); 
        return true; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            return false; 
        } 
    }

    public static boolean validaCnpj(String cnpj){
        CNPJValidator cnpjValidator = new CNPJValidator(); 
        try{ cnpjValidator.assertValid(cnpj); 
        return true; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            return false; 
        } 
    }

}
