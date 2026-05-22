package cl.duoc.proveedores_service.mapper;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.model.Proveedor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProveedorMapper {

    public ProveedorDTO toDTO(Proveedor proveedor){
        if(proveedor==null) return null;

        ProveedorDTO dto = new ProveedorDTO();

        dto.setRutProveedor(proveedor.getRutProveedor().toString().concat("-").concat(proveedor.getDvProveedor()));
        dto.setNombreProveedor(proveedor.getNombreProveedor());

        return dto;

    }

    public List<ProveedorDTO> toDTOList(List<Proveedor> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}

