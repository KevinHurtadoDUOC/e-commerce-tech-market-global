package cl.duoc.vendedores_service.controller;

import cl.duoc.vendedores_service.dto.VendedorDTO;
import cl.duoc.vendedores_service.model.Vendedor;
import cl.duoc.vendedores_service.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(vendedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Vendedor vendedor = vendedorService.findById(id);
        if (vendedor == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(vendedor);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Vendedor vendedor){
        Vendedor vendedorNuevo = vendedorService.save(vendedor);
        return new ResponseEntity<>(vendedorNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Vendedor vendedor){
        Vendedor vendedorActualizado = vendedorService.update(id, vendedor);
        if (vendedorActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(vendedorActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        vendedorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/informacion-completa/{id}")
    public ResponseEntity<?> buscarDTO(@PathVariable Long id){
        return ResponseEntity.ok(vendedorService.productoDTOCompleto(id));
    }

    @GetMapping("/informacion-completa")
    public ResponseEntity<?> buscarDTO(){
        return ResponseEntity.ok(vendedorService.listarVendedorDTO());
    }
}
