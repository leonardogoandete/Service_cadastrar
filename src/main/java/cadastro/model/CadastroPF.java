package cadastro.model;

import cadastro.enumerados.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class CadastroPF extends Cadastro {

    @NotEmpty
    @NotBlank
    private String cpf;

    @NotEmpty
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;

    public CadastroPF() {
    }

    public CadastroPF(String nome, String endereco, String email, String senha, String cpf, Role role) {
        super(nome, endereco, email, senha);
        this.cpf = cpf;
        this.role = role;
    }

    private String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    private Role getRole() {
        return role;
    }

    private void setRole(Role role) {
        this.role = role;
    }
    
}
