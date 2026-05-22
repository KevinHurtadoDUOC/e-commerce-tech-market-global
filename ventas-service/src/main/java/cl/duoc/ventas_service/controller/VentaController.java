package cl.duoc.ventas_service.controller;

import cl.duoc.ventas_service.model.Venta;
import cl.duoc.ventas_service.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Venta venta = ventaService.findById(id);
        if (venta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(venta);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Venta venta){
        Venta ventaNueva = ventaService.save(venta);
        return new ResponseEntity<>(ventaNueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Venta venta){
        Venta ventaActualizada = ventaService.update(id, venta);
        if (ventaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ventaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
