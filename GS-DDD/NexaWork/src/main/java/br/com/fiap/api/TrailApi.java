package br.com.fiap.api;

import br.com.fiap.domain.Trail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/trail")
@Tag(name = "Trails", description = "Management Trail")
public interface TrailApi {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a new trail",
            description = "Creates a new learning trail with the provided information",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Trail object that needs to be created",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Trail.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Trail created successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    ResponseEntity<Void> createTrail(@RequestBody Trail request);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get trail by ID",
            description = "Returns a single learning trail identified by its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID of the trail to retrieve",
                            example = "98765",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Trail found successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Trail.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Trail not found"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid ID format"
                    )
            }
    )
    ResponseEntity<Trail> findTrail(@Valid @PathVariable("id") String id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Get all trails",
            description = "Returns a list of all registered learning trails",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of trails retrieved successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Trail[].class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error"
                    )
            }
    )
    ResponseEntity<List<Trail>> findAllTrail();

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Update trail by ID",
            description = "Updates an existing learning trail identified by its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID of the trail to update",
                            example = "98765",
                            schema = @Schema(type = "string")
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated trail object",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Trail.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Trail updated successfully"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input data or ID mismatch"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Trail not found"
                    )
            }
    )
    ResponseEntity<Void> updateTrail(@Valid @PathVariable("id") String id, @RequestBody Trail request);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete trail by ID",
            description = "Deletes a learning trail identified by its ID",
            parameters = {
                    @Parameter(
                            name = "id",
                            in = ParameterIn.PATH,
                            required = true,
                            description = "ID of the trail to delete",
                            example = "98765",
                            schema = @Schema(type = "string")
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Trail deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Trail not found"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid ID format"
                    )
            }
    )
    ResponseEntity<Void> deleteTrail(@Valid @PathVariable("id") String id);
}
