package cl.duoc.ventas_service.clientsFeign;


import cl.duoc.ventas_service.dto.VendedorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "vendedores-service", path="api/v1/vendedores")
public interface VendedorFeign {

    @GetMapping("/informacion-completa/{id}")
    public VendedorDTO buscarDTO(@PathVariable Long id);
}
