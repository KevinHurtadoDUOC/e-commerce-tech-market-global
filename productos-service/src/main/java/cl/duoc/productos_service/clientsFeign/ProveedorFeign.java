package cl.duoc.productos_service.clientsFeign;

import cl.duoc.productos_service.dto.BodegaDTO;
import cl.duoc.productos_service.dto.ProveedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "proveedores-service", path="api/v1/proveedores")
public interface ProveedorFeign {

    @GetMapping("/listado/{id}")
    public ProveedorDTO buscarProveedor(@PathVariable Long id);
}