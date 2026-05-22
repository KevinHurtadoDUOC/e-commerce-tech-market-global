package cl.duoc.login_service.controller;

import cl.duoc.login_service.dto.LoginDTO;
import cl.duoc.login_service.model.Login;
import cl.duoc.login_service.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(loginService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Login login = loginService.findById(id);
        if (login == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(login);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Login login){
        Login loginNuevo = loginService.save(login);
        return new ResponseEntity<>(loginNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Login login){
        Login loginActualizado = loginService.update(id, login);
        if (loginActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(loginActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        loginService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        LoginDTO login = loginService.findDTO(id);
        if (login == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(login);
    }
    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO() {
        return ResponseEntity.ok(loginService.findDTOList());
    }
}
