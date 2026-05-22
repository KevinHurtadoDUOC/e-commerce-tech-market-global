package cl.duoc.sucursales_service.mapper;

import cl.duoc.sucursales_service.dto.SucursalDTO;
import cl.duoc.sucursales_service.model.Sucursal;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SucursalMapper {

    public SucursalDTO toDTO(Sucursal sucursal){
        if(sucursal==null) return null;
        SucursalDTO dto = new SucursalDTO();
        dto.setNombreSucursal(sucursal.getNombreSucursal());
        dto.setTelefonoSucursal(sucursal.getTelefonoSucursal());
        dto.setDireccionSucursal(sucursal.getDireccionSucursal());
        return dto;
    }

    public List<SucursalDTO> toDTOList(List<Sucursal> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}