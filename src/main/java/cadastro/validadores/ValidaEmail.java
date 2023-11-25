package cadastro.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidaEmail {

    private ValidaEmail(){

    }

     public static boolean validarEmail(String email) {

        // Define comprimento mínimo e máximo permitido para o nome
        int comprimentoMinimo = 10;
        int comprimentoMaximo = 254;

        // Cria um objeto Pattern e um objeto Matcher para a expressão regular
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
        Matcher matcher = pattern.matcher(email);

        // Verifica se o nome atende a todas as condições e retorna o resultado diretamente
        return matcher.matches() && email.length() >= comprimentoMinimo && email.length() <= comprimentoMaximo;
    }
    
}
