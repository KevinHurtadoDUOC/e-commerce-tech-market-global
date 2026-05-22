package cl.duoc.productos_service.clientsFeign;

import cl.duoc.productos_service.dto.BodegaDTO;
import cl.duoc.productos_service.dto.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "bodegas-service", path="api/v1/bodegas")
public interface BodegaFeign {

    @GetMapping("/listado/{id}")
    public BodegaDTO buscarBodega(@PathVariable Long id);
}
