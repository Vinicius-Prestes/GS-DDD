package br.com.fiap.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "trail")
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trail")
    private Long id;

    @NotBlank(message = "O nome e obrigatorio")
    @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "A area de atuacao e obrigatoria")
    @Size(max = 100, message = "A area de atuacao deve ter no maximo 100 caracteres")
    @Column(name = "field")
    private String field;

    @Size(max = 500, message = "O descricao deve ter no maximo 500 caracteres")
    @Column(name = "description")
    private String description;
}
