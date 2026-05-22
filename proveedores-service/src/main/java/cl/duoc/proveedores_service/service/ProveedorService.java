package cl.duoc.proveedores_service.service;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.mapper.ProveedorMapper;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.repository.ProveedorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorMapper proveedorMapper;

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor findById(Long id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public Proveedor save(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor update(Long id, Proveedor proveedor) {
        Proveedor proveedorActualizar = proveedorRepository.findById(id).orElse(null);
        if (proveedorActualizar == null) return null;
        proveedorActualizar.setDvProveedor(proveedor.getDvProveedor());
        proveedorActualizar.setNombreProveedor(proveedor.getNombreProveedor());
        proveedorActualizar.setCorreoProveedor(proveedor.getCorreoProveedor());
        proveedorActualizar.setTelefonoProveedor(proveedor.getTelefonoProveedor());
        return proveedorRepository.save(proveedorActualizar);
    }

    public void delete(Long id) {
        proveedorRepository.deleteById(id);
    }

    public ProveedorDTO findDTO(Long id) {
        return proveedorMapper.toDTO(findById(id));
    }

    public List<ProveedorDTO> findDTOList() {
        return proveedorMapper.toDTOList(findAll());
            }
}
