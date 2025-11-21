package br.com.fiap.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotBlank(message = "O nome e obrigatorio")
    @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "O email e obrigatorio")
    @Email(message = "Email invalido")
    @Size(max = 100, message = "O email deve ter no maximo 100 caracteres")
    @Column(name = "email")
    private String email;

    @PastOrPresent
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Size(max = 100, message = "A area de atuacao deve ter no maximo 100 caracteres")
    @Column(name = "field")
    private String field;
}
