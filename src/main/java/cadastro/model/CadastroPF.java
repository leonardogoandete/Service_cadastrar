package cadastro.model;

import javax.management.relation.Role;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Instituições")

public class CadastroPF extends PanacheEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @NotBlank
    private String nome;
    @NotEmpty
    @NotBlank
    private String endereco;
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotEmpty
    @NotBlank
    private String senha;

    public CadastroPF(String nome, String endereco, String email, String senha) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }
    
}
