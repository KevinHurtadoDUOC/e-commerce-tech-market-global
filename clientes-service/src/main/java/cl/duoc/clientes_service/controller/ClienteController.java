package cl.duoc.clientes_service.controller;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.exception.ErrorResponse;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.service.ClienteService;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
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

@OpenAPIDefinition(
        info = @Info(
                title = "API de Bodegas",
                version = "1.0",
                description = "Operaciones disponibles para la gestión de bodegas",
                contact = @Contact(
                        name = "Sofia Muñoz",
                        email = "tu.correo@duocuc.cl"
                )
        )
)

@Tag(
        name = "Clientes",
        description = "Operaciones disponibles para la gestión de clientes"
)
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Listar clientes", description = "Obtiene el listado completo de clientes registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Clientes listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @Operation(summary = "Buscar cliente por ID", description = "Obtiene un cliente específico según su identificador.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Registrar cliente", description = "Registra un nuevo cliente en el sistema.")
    @ApiResponse(responseCode = "201", description = "Cliente creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.save(cliente);
        return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza un cliente existente según su identificador.")
    @ApiResponse(responseCode = "200", description = "Cliente actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id, @Valid @RequestBody Cliente cliente){
        Cliente clienteActualizado = clienteService.update(id, cliente);
        if (clienteActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteActualizado);
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente existente según su identificador.")
    @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar cliente por ID (DTO)", description = "Obtiene un cliente específico en formato DTO según su identificador.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)))
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/listado/{id}")
    public ResponseEntity<ClienteDTO> buscarPorIdDTO(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Long id){
        ClienteDTO cliente = clienteService.findDTO(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @Operation(summary = "Listar clientes (DTO)", description = "Obtiene el listado completo de clientes en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Clientes listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteDTO.class))))
    @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(clienteService.findDTOList());
    }
}
