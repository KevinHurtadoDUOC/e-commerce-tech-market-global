package cl.duoc.ventas_service.controller;

import cl.duoc.ventas_service.model.DetalleVenta;
import cl.duoc.ventas_service.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/detalles-venta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(detalleVentaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        DetalleVenta detalleVenta = detalleVentaService.findById(id);
        if (detalleVenta == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detalleVenta);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody DetalleVenta detalleVenta){
        DetalleVenta detalleVentaNueva = detalleVentaService.save(detalleVenta);
        return new ResponseEntity<>(detalleVentaNueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleVenta detalleVenta){
        DetalleVenta detalleVentaActualizada = detalleVentaService.update(id, detalleVenta);
        if (detalleVentaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(detalleVentaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        detalleVentaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/informacion-completa/{id}")
    public ResponseEntity<?> buscarDTO(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.detalleVentaDTO(id));
    }

    @GetMapping("/informacion-completa")
    public ResponseEntity<?> buscarDTO(){
        return ResponseEntity.ok(detalleVentaService.listarDetalleVentaDTO());
    }
}
