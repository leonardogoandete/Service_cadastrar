package cadastro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@Entity
@Table(name = "Instituições")
public class CadastroPJ extends PanacheEntity{

    @NotEmpty
    @NotBlank
    private String cnpj;
    @NotEmpty
    @NotBlank
    private String nome;
    @NotEmpty
    @NotBlank
    private String endereco;
    @NotEmpty
    @NotBlank
    private String email;
    @NotEmpty
    @NotBlank
    private String senha;

    public CadastroPJ(String cnpj, String nome, String endereco, String email, String senha) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }
    
}
