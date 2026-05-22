package cl.duoc.sucursales_service.service;

import cl.duoc.sucursales_service.dto.SucursalDTO;
import cl.duoc.sucursales_service.mapper.SucursalMapper;
import cl.duoc.sucursales_service.model.Sucursal;
import cl.duoc.sucursales_service.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalMapper sucursalMapper;

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> findAll(){
        return sucursalRepository.findAll();
    }
    public Sucursal findById(Long id){
        return sucursalRepository.findById(id).orElse(null);
    }
    public Sucursal save(Sucursal sucursal){
        return sucursalRepository.save(sucursal);
    }
    public Sucursal update(Long id, Sucursal sucursal){
        Sucursal sucursalActualizar = sucursalRepository.findById(id).orElse(null);
        if (sucursalActualizar == null) return null;
        sucursalActualizar.setNombreSucursal(sucursal.getNombreSucursal());
        sucursalActualizar.setDireccionSucursal(sucursal.getDireccionSucursal());
        sucursalActualizar.setTelefonoSucursal(sucursal.getTelefonoSucursal());
        return sucursalRepository.save(sucursalActualizar);
    }
    public void delete(Long id){
        sucursalRepository.deleteById(id);
    }

    public SucursalDTO findDTO(Long id){
        return sucursalMapper.toDTO(findById(id));
    }

    public List<SucursalDTO> findDTOList(){
        return sucursalMapper.toDTOList(findAll());
    }
}
