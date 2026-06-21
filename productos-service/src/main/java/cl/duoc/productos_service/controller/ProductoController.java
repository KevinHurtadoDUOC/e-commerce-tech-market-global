package cl.duoc.productos_service.controller;

import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.service.ProductoService;
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

@Tag(name = "Productos", description = "Operaciones disponibles para la gestión de productos")
@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Listar productos", description = "Obtiene el listado completo de productos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Productos listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Producto.class))))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @Operation(summary = "Buscar producto por ID", description = "Obtiene un producto específico según su identificador.")
    @ApiResponse(responseCode = "200", description = "Producto encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id){
        Producto producto = productoService.findById(id);
        if (producto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(producto);
    }

    @Operation(summary = "Registrar producto", description = "Registra un nuevo producto en el sistema.")
    @ApiResponse(responseCode = "201", description = "Producto creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Producto producto){
        Producto productoNuevo = productoService.save(producto);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente según su identificador.")
    @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)))
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id, @Valid @RequestBody Producto producto){
        Producto productoActualizado = productoService.update(id, producto);
        if (productoActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productoActualizado);
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto existente según su identificador.")
    @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id){
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar producto con información completa", description = "Obtiene un producto con información de sucursal, bodega y proveedor.")
    @ApiResponse(responseCode = "200", description = "Producto encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoDTO.class)))
    @GetMapping("/informacion-completa/{id}")
    public ResponseEntity<?> buscarDTO(
            @Parameter(description = "ID del producto", example = "1")
            @PathVariable Long id){
       return ResponseEntity.ok(productoService.productoDTOCompleto(id));
    }

    @Operation(summary = "Listar productos con información completa", description = "Obtiene el listado de productos con información de sucursal, bodega y proveedor.")
    @ApiResponse(responseCode = "200", description = "Productos listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductoDTO.class))))
    @GetMapping("/informacion-completa")
    public ResponseEntity<?> buscarDTO(){
        return ResponseEntity.ok(productoService.listarProductoDTOCompleto());
    }
}