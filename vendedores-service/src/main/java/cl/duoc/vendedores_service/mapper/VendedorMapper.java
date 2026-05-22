package cl.duoc.vendedores_service.mapper;

import cl.duoc.vendedores_service.dto.VendedorDTO;
import cl.duoc.vendedores_service.model.Vendedor;
import org.springframework.stereotype.Component;

@Component
public class VendedorMapper {

    public VendedorDTO toDTO(Vendedor vendedor, String sucursal) {
        if (vendedor == null) return null;

        VendedorDTO dto = new VendedorDTO();

        dto.setIdVendedor(vendedor.getIdVendedor());
        dto.setNombreVendedor(vendedor.getNombreVendedor());
        dto.setSueldoVendedor(vendedor.getSueldoVendedor());
        dto.setIdSucursal(sucursal);

        return dto;
    }
}