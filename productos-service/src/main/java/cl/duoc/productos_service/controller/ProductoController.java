package cl.duoc.productos_service.controller;

import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Producto producto = productoService.findById(id);
        if (producto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Producto producto){
        Producto productoNuevo = productoService.save(producto);
        return new ResponseEntity<>(productoNuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Producto producto){
        Producto productoActualizado = productoService.update(id, producto);
        if (productoActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/informacion-completa/{id}")
    public ResponseEntity<?> buscarDTO(@PathVariable Long id){
       return ResponseEntity.ok(productoService.productoDTOCompleto(id));
    }

    @GetMapping("/informacion-completa")
    public ResponseEntity<?> buscarDTO(){
        return ResponseEntity.ok(productoService.listarProductoDTOCompleto());
    }
}