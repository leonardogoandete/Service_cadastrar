package cadastro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import cadastro.enumerados.Role;

@Entity
public class CadastroPJ extends Cadastro{
    
    @NotEmpty
    @NotBlank
    private String cnpj;

    @NotEmpty
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;

    public CadastroPJ() {
    }

    public CadastroPJ(String nome, String endereco, String email, String senha, String cnpj, Role role) {
        super(nome, endereco, email, senha);
        this.cnpj = cnpj;
        this.role = role;
    }

    private String getCnpj() {
        return cnpj;
    }

    private void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    private Role getRole() {
        return role;
    }

    private void setRole(Role role) {
        this.role = role;
    }
    
}
