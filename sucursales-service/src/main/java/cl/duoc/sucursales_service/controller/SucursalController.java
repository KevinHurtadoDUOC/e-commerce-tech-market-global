package cl.duoc.sucursales_service.controller;

import cl.duoc.sucursales_service.dto.SucursalDTO;
import cl.duoc.sucursales_service.model.Sucursal;
import cl.duoc.sucursales_service.service.SucursalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/sucursales")
public class SucursalController {
    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(sucursalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Sucursal sucursal) {
        Sucursal sucursalNueva = sucursalService.save(sucursal);
        return new ResponseEntity<>(sucursalNueva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Sucursal sucursal) {
        Sucursal sucursalActualizada = sucursalService.update(id, sucursal);
        if (sucursalActualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursalActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        sucursalService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listado/{id}")
    public ResponseEntity<?> buscarPorIdDTO(@PathVariable Long id){
        SucursalDTO sucursal = sucursalService.findDTO(id);
        if (sucursal==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(sucursal);
    }

    @GetMapping("/listado")
    public ResponseEntity<?> listarDTO(){
        return ResponseEntity.ok(sucursalService.findDTOList());
    }


}

