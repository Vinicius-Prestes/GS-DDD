package br.com.fiap.api.dto;

import br.com.fiap.domain.Assignment;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentDTO {
    private Long id;

    @NotNull(message = "O ID do usuario e obrigatorio")
    private Long userId;

    @NotNull(message = "O ID da trilha e obrigatorio")
    private Long trailId;

    private String status;

    public AssignmentDTO(Assignment assignment){
        this.id=assignment.getId();
        this.userId=assignment.getUser().getId();
        this.trailId=assignment.getTrail().getId();
        this.status=assignment.getStatus().toString();
    }
}
