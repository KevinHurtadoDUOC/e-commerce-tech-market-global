package cl.duoc.clientes_service.controller;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Cliente cliente){
        Cliente clienteNuevo = clienteService.save(cliente);
        return new ResponseEntity<>(clienteNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        Cliente clienteActualizado = clienteService.update(id, cliente);
        if (clienteActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<ClienteDTO> buscarPorIdDTO(@PathVariable Long id){
        ClienteDTO cliente = clienteService.findDTO(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(clienteService.findDTOList());
    }
}
