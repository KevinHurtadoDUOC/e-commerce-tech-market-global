package cl.duoc.bodegas_service.service;

import cl.duoc.bodegas_service.dto.BodegaDTO;
import cl.duoc.bodegas_service.mapper.BodegaMapper;
import cl.duoc.bodegas_service.model.Bodega;
import cl.duoc.bodegas_service.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodegaService {

    @Autowired
    private BodegaRepository bodegaRepository;

    @Autowired
    private BodegaMapper bodegaMapper;

    public List<Bodega> findAll(){
        return bodegaRepository.findAll();
    }
    public Bodega findById(Long id){
        return bodegaRepository.findById(id).orElse(null);
    }
    public Bodega save(Bodega bodega){
        return bodegaRepository.save(bodega);
    }
    public Bodega update(Long id, Bodega bodega){

        Bodega bodegaActualizar = bodegaRepository.findById(id).orElse(null);

        if (bodegaActualizar == null) return null;

        bodegaActualizar.setDireccionBodega(bodega.getDireccionBodega());
        bodegaActualizar.setTelefonoBodega(bodega.getTelefonoBodega());
        bodegaActualizar.setTemperaturaBodega(bodega.getTemperaturaBodega());
        bodegaActualizar.setCapacidadBodega(bodega.getCapacidadBodega());

        return bodegaRepository.save(bodegaActualizar);
    }
    public void delete(Long id){
        bodegaRepository.deleteById(id);
    }

    public BodegaDTO findDTO(Long id){
        return bodegaMapper.toDTO(findById(id));
    }

    public List<BodegaDTO> findDTOList(){
        return bodegaMapper.toDTOList(findAll());
    }


}
