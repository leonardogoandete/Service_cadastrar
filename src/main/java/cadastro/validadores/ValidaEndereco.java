package cadastro.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ValidaEndereco{

    private ValidaEndereco(){

    }

     public static boolean validarEndereco(String endereco) {

        // Define comprimento mínimo e máximo permitido para o nome
        int comprimentoMinimo = 10;
        int comprimentoMaximo = 120;

        // Cria um objeto Pattern e um objeto Matcher para a expressão regular
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s]+$");
        Matcher matcher = pattern.matcher(endereco);

        // Verifica se o nome atende a todas as condições e retorna o resultado diretamente
        return matcher.matches() && endereco.length() >= comprimentoMinimo && endereco.length() <= comprimentoMaximo;
    }

}