package cl.duoc.bodegas_service.controller;

import cl.duoc.bodegas_service.dto.BodegaDTO;
import cl.duoc.bodegas_service.exception.ErrorResponse;
import cl.duoc.bodegas_service.model.Bodega;
import cl.duoc.bodegas_service.service.BodegaService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Bodegas",
        description = "Operaciones disponibles para la gestión de bodegas"
)
@RestController
@RequestMapping("api/v1/bodegas")
public class BodegaController {
    @Autowired
    private BodegaService bodegaService;

    @Operation(
            summary = "Listar bodegas",
            description = "Obtiene el listado completo de bodegas registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Bodegas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Bodega.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(bodegaService.findAll());
    }

    @Operation(
            summary = "Buscar bodega por ID",
            description = "Obtiene una bodega específica según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Bodega encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class)))
    @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID de la bodega", example = "1")
            @PathVariable Long id){
        Bodega bodega = bodegaService.findById(id);
        if (bodega == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bodega);
    }

    @Operation(
            summary = "Registrar bodega",
            description = "Registra una nueva bodega en el sistema."
    )
    @ApiResponse(responseCode = "201", description = "Bodega creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Bodega bodega){
        Bodega bodegaNueva = bodegaService.save(bodega);
        return new ResponseEntity<>(bodegaNueva, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar bodega",
            description = "Actualiza una bodega existente según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Bodega actualizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Bodega.class)))
    @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID de la bodega", example = "1")
            @PathVariable Long id, @Valid @RequestBody Bodega bodega){
        Bodega bodegaActualizada = bodegaService.update(id, bodega);
        if (bodegaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bodegaActualizada);
    }

    @Operation(
            summary = "Eliminar bodega",
            description = "Elimina una bodega existente según su identificador."
    )
    @ApiResponse(responseCode = "204", description = "Bodega eliminada correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(
            @Parameter(description = "ID de la bodega", example = "1")
            @PathVariable Long id){
        bodegaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Listar bodegas (DTO)",
            description = "Obtiene el listado completo de bodegas en formato DTO."
    )
    @ApiResponse(responseCode = "200", description = "Bodegas listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = BodegaDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(bodegaService.findDTOList());
    }

    @Operation(
            summary = "Buscar bodega por ID (DTO)",
            description = "Obtiene una bodega específica en formato DTO según su identificador."
    )
    @ApiResponse(responseCode = "200", description = "Bodega encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BodegaDTO.class)))
    @ApiResponse(responseCode = "404", description = "Bodega no encontrada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID de la bodega", example = "1")
            @PathVariable Long id){
        BodegaDTO bodegaDTO = bodegaService.findDTO(id);
        if (bodegaDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bodegaDTO);
    }

    @Operation(
            summary = "Endpoint de prueba",
            description = "Endpoint abierto para verificar que el servicio está activo."
    )
    @ApiResponse(responseCode = "200", description = "Servicio activo", content = @Content(mediaType = "text/plain"))
    @GetMapping("/open")
    public String hola(){
        return "HOLA MUNDO";
    }
}
