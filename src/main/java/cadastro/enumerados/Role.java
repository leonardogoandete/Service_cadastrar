package cadastro.enumerados;

public enum Role {
    USUARIO(1,"Usuario"),INSTITUICAO(2,"Instituicao");
    private final int cod;
    private final String tipo;
    Role(int cod, String tipo){
        this.tipo = tipo;
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public String getTipo() {
        return tipo;
    }
}