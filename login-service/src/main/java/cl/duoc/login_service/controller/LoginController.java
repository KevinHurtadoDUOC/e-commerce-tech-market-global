package cl.duoc.login_service.controller;

import cl.duoc.login_service.dto.LoginDTO;
import cl.duoc.login_service.model.Login;
import cl.duoc.login_service.service.LoginService;
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

@Tag(name = "Login", description = "Operaciones disponibles para la gestión de credenciales de acceso")
@RestController
@RequestMapping("api/v1/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Operation(summary = "Listar logins", description = "Obtiene el listado completo de credenciales registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Logins listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Login.class))))
    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(loginService.findAll());
    }

    @Operation(summary = "Buscar login por ID", description = "Obtiene un login específico según su identificador.")
    @ApiResponse(responseCode = "200", description = "Login encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Login.class)))
    @ApiResponse(responseCode = "404", description = "Login no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID del login", example = "1")
            @PathVariable Long id){
        Login login = loginService.findById(id);
        if (login == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(login);
    }

    @Operation(summary = "Registrar login", description = "Registra un nuevo login en el sistema.")
    @ApiResponse(responseCode = "201", description = "Login creado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Login.class)))
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Login login){
        Login loginNuevo = loginService.save(login);
        return new ResponseEntity<>(loginNuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar login", description = "Actualiza un login existente según su identificador.")
    @ApiResponse(responseCode = "200", description = "Login actualizado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Login.class)))
    @ApiResponse(responseCode = "404", description = "Login no encontrado")
    @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID del login", example = "1")
            @PathVariable Long id, @Valid @RequestBody Login login){
        Login loginActualizado = loginService.update(id, login);
        if (loginActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(loginActualizado);
    }

    @Operation(summary = "Eliminar login", description = "Elimina un login existente según su identificador.")
    @ApiResponse(responseCode = "204", description = "Login eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Login no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(
            @Parameter(description = "ID del login", example = "1")
            @PathVariable Long id){
        loginService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar login por ID (DTO)", description = "Obtiene un login específico en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Login encontrado correctamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginDTO.class)))
    @ApiResponse(responseCode = "404", description = "Login no encontrado")
    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(
            @Parameter(description = "ID del login", example = "1")
            @PathVariable Long id){
        LoginDTO login = loginService.findDTO(id);
        if (login == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(login);
    }

    @Operation(summary = "Listar logins (DTO)", description = "Obtiene el listado completo de logins en formato DTO.")
    @ApiResponse(responseCode = "200", description = "Logins listados correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = LoginDTO.class))))
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(loginService.findDTOList());
    }
}
