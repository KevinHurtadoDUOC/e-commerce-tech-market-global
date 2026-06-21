package cl.duoc.sucursales_service.controller;

import cl.duoc.sucursales_service.dto.SucursalDTO;
import cl.duoc.sucursales_service.model.Sucursal;
import cl.duoc.sucursales_service.service.SucursalService;
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

@Tag(name = "Sucursales", description = "Operaciones disponibles para la gestión de sucursales")
@RestController
@RequestMapping("api/v1/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @Operation(summary = "Listar sucursales", description = "Obtiene el listado completo de sucursales registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Sucursales listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Sucursal.class))))
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @Operation(summary = "Buscar sucursal por ID", description = "Obtiene una sucursal específica según su identificador.")
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID de la sucursal", example = "1")
            @PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }

    @Operation(summary = "Registrar sucursal", description = "Registra una nueva sucursal en el sistema.")
    @ApiResponse(responseCode = "201", description = "Sucursal creada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Sucursal sucursal) {
        Sucursal sucursalNueva = sucursalService.save(sucursal);
        return new ResponseEntity<>(sucursalNueva, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar sucursal", description = "Actualiza una sucursal existente según su identificador.")
    @ApiResponse(responseCode = "200", description = "Sucursal actualizada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Sucursal.class)))
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID de la sucursal", example = "1")
            @PathVariable Long id, @Valid @RequestBody Sucursal sucursal) {
        Sucursal sucursalActualizada = sucursalService.update(id, sucursal);
        if (sucursalActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursalActualizada);
    }

    @Operation(summary = "Eliminar sucursal", description = "Elimina una sucursal existente según su identificador.")
    @ApiResponse(responseCode = "204", description = "Sucursal eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(
            @Parameter(description = "ID de la sucursal", example = "1")
            @PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar sucursal por ID (DTO)", description = "Obtiene una sucursal específica en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Sucursal encontrada correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SucursalDTO.class)))
    @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID de la sucursal", example = "1")
            @PathVariable Long id){
        SucursalDTO sucursal = sucursalService.findDTO(id);
        if (sucursal==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }

    @Operation(summary = "Listar sucursales (DTO)", description = "Obtiene el listado completo de sucursales en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Sucursales listadas correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SucursalDTO.class))))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(sucursalService.findDTOList());
    }
}
