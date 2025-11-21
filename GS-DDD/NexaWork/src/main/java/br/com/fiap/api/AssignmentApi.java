package br.com.fiap.api;

import br.com.fiap.api.dto.AssignmentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/assignment")
@Tag(name = "Assignment", description = "Assignment management between users and trails")
public interface AssignmentApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new assignment",
            description = "Creates a new assignment linking a user to a trail",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Assignment object that needs to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AssignmentDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Assignment created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    )
            }
    )
    ResponseEntity<Void> createAssignment(@RequestBody AssignmentDTO request);

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update assignment by ID",
            description = "Updates an existing assignment identified by its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID of the assignment",
                            example = "98765",
                            schema = @Schema(type = "string")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated assignment object",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AssignmentDTO.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Assignment updated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Assignment not found"
                    )
            }
    )
    ResponseEntity<Void> updateAssignment(@PathVariable("id") String id, @RequestBody AssignmentDTO request);

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Find user assignments",
            description = "Finds all trails assigned to a user",
            parameters = {
                    @Parameter(
                            name = "userId",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID of the user",
                            example = "98765",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User assignments retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AssignmentDTO[].class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found"
                    )
            }
    )
    ResponseEntity<List<AssignmentDTO>> findtUserAssignment(@PathVariable("userId") String id);

    @GetMapping("/trail/{trailId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Find trail assignments",
            description = "Finds all users assigned to a trail",
            parameters = {
                    @Parameter(
                            name = "trailId",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID of the trail",
                            example = "98765",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Trail assignments retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AssignmentDTO[].class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Trail not found"
                    )
            }
    )
    ResponseEntity<List<AssignmentDTO>> findTrailAssignment(@PathVariable("trailId") String id);
}