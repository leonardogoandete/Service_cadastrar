package cadastro.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidaNome {

    private ValidaNome(){

    }

    public static boolean validarNome(String nome) {
 
        // Define uma expressão regular para permitir apenas letras e espaços

        // Define comprimento mínimo e máximo permitido para o nome
        int comprimentoMinimo = 2;
        int comprimentoMaximo = 50;

        // Cria um objeto Pattern e um objeto Matcher para a expressão regular
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(nome);

        // Verifica se o nome atende a todas as condições e retorna o resultado diretamente
        return matcher.matches() && nome.length() >= comprimentoMinimo && nome.length() <= comprimentoMaximo;
    }
    
}
