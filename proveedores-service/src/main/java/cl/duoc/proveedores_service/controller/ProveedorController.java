package cl.duoc.proveedores_service.controller;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.service.ProveedorService;
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

@Tag(name = "Proveedores", description = "Operaciones disponibles para la gestión de proveedores")
@RestController
@RequestMapping("api/v1/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Listar proveedores", description = "Obtiene el listado completo de proveedores registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Proveedores listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Proveedor.class))))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @Operation(summary = "Buscar proveedor por ID", description = "Obtiene un proveedor específico según su identificador.")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class)))
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del proveedor", example = "1")
            @PathVariable Long id){
        Proveedor proveedor = proveedorService.findById(id);
        if (proveedor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedor);
    }

    @Operation(summary = "Registrar proveedor", description = "Registra un nuevo proveedor en el sistema.")
    @ApiResponse(responseCode = "201", description = "Proveedor creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Proveedor proveedor){
        Proveedor proveedorNuevo = proveedorService.save(proveedor);
        return new ResponseEntity<>(proveedorNuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar proveedor", description = "Actualiza un proveedor existente según su identificador.")
    @ApiResponse(responseCode = "200", description = "Proveedor actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class)))
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del proveedor", example = "1")
            @PathVariable Long id, @Valid @RequestBody Proveedor proveedor){
        Proveedor proveedorActualizado = proveedorService.update(id, proveedor);
        if (proveedorActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedorActualizado);
    }

    @Operation(summary = "Eliminar proveedor", description = "Elimina un proveedor existente según su identificador.")
    @ApiResponse(responseCode = "204", description = "Proveedor eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(
            @Parameter(description = "ID del proveedor", example = "1")
            @PathVariable Long id){
        proveedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar proveedor por ID (DTO)", description = "Obtiene un proveedor específico en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProveedorDTO.class)))
    @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID del proveedor", example = "1")
            @PathVariable Long id){
        ProveedorDTO proveedor = proveedorService.findDTO(id);
        if(proveedor==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(proveedor);
    }

    @Operation(summary = "Listar proveedores (DTO)", description = "Obtiene el listado completo de proveedores en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Proveedores listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProveedorDTO.class))))
    @GetMapping("listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(proveedorService.findDTOList());
    }

}