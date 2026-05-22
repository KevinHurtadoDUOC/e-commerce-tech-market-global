package cl.duoc.ventas_service.clientsFeign;

import cl.duoc.ventas_service.dto.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name= "sucursales-service", path="api/v1/sucursales")
public interface SucursalFeign {

    @GetMapping("/listado/{id}")
    public SucursalDTO buscarSucursal(@PathVariable Long id);
}