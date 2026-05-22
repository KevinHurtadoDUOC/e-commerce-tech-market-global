package cl.duoc.ventas_service.clientsFeign;

import cl.duoc.ventas_service.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "productos-service", path="api/v1/productos")
public interface ProductoFeign {

    @GetMapping("/informacion-completa/{id}")
    public ProductoDTO buscarDTO(@PathVariable Long id);

}
