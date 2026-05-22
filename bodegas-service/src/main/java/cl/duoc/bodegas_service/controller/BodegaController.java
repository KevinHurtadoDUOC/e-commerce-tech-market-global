package cl.duoc.bodegas_service.controller;

import cl.duoc.bodegas_service.dto.BodegaDTO;
import cl.duoc.bodegas_service.model.Bodega;
import cl.duoc.bodegas_service.service.BodegaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bodegas")
public class BodegaController {
    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(bodegaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Bodega bodega = bodegaService.findById(id);
        if (bodega == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bodega);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Bodega bodega){
        Bodega bodegaNueva = bodegaService.save(bodega);
        return new ResponseEntity<>(bodegaNueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Bodega bodega){
        Bodega bodegaActualizada = bodegaService.update(id, bodega);
        if (bodegaActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bodegaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id){
        bodegaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(bodegaService.findDTOList());
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        BodegaDTO bodegaDTO = bodegaService.findDTO(id);
        if (bodegaDTO==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bodegaDTO);
    }

    @GetMapping("/open")
    public String hola(){
        return "HOLA MUNDO";
    }
}














