package cl.duoc.ventas_service.clientsFeign;

import cl.duoc.ventas_service.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "clientes-service", path="api/v1/clientes")
public interface ClienteFeign {

    @GetMapping("/listado/{id}")
    public ClienteDTO buscarPorIdDTO(@PathVariable Long id);
}
