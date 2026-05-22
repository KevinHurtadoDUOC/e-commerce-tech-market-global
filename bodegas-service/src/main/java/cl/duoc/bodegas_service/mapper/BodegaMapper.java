package cl.duoc.bodegas_service.mapper;

import cl.duoc.bodegas_service.dto.BodegaDTO;
import cl.duoc.bodegas_service.model.Bodega;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BodegaMapper {

    public BodegaDTO toDTO(Bodega bodega){
        if (bodega==null)return null;

        BodegaDTO dto = new BodegaDTO();

        dto.setDireccionBodega(bodega.getDireccionBodega());
        dto.setTelefonoBodega(bodega.getTelefonoBodega());
        dto.setCapacidadBodega(bodega.getCapacidadBodega());

        return dto;
    }

    public List<BodegaDTO> toDTOList(List<Bodega> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}