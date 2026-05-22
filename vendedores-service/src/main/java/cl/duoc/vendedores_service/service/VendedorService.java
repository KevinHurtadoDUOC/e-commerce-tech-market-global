package cl.duoc.vendedores_service.service;

import cl.duoc.vendedores_service.clientsFeign.SucursalFeign;
import cl.duoc.vendedores_service.dto.SucursalDTO;
import cl.duoc.vendedores_service.dto.VendedorDTO;
import cl.duoc.vendedores_service.mapper.VendedorMapper;
import cl.duoc.vendedores_service.model.Vendedor;
import cl.duoc.vendedores_service.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendedorService {

    @Autowired
    private VendedorMapper vendedorMapper;

    @Autowired
    private SucursalFeign sucursalFeign;

    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> findAll(){
        return vendedorRepository.findAll();
    }

    public Vendedor findById(Long id){
        return vendedorRepository.findById(id).orElse(null);
    }

    public Vendedor save(Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

    public Vendedor update(Long id, Vendedor vendedor){
        Vendedor vendedorActualizar = vendedorRepository.findById(id).orElse(null);
        if (vendedorActualizar == null) return null;
        vendedorActualizar.setNombreVendedor(vendedor.getNombreVendedor());
        vendedorActualizar.setSueldoVendedor(vendedor.getSueldoVendedor());
        vendedorActualizar.setIdSucursal(vendedor.getIdSucursal());
        vendedorActualizar.setIdLogin(vendedor.getIdLogin());
        return vendedorRepository.save(vendedorActualizar);
    }

    public void delete(Long id){
        vendedorRepository.deleteById(id);
    }

    public VendedorDTO productoDTOCompleto(Long id){
        Vendedor v = vendedorRepository.findById(id).orElse(null);
        SucursalDTO sucursalDTO = sucursalFeign.buscarSucursal(v.getIdSucursal());

        return vendedorMapper.toDTO(v, sucursalDTO.getNombreSucursal());
    }

    public List<VendedorDTO> listarVendedorDTO(){

        List<Vendedor> lista = vendedorRepository.findAll();
        List<VendedorDTO> nuevos = new ArrayList<>();

        for (Vendedor v : lista) {
            SucursalDTO sucursalDTO = sucursalFeign.buscarSucursal(v.getIdSucursal());

            nuevos.add(vendedorMapper.toDTO(v, sucursalDTO.getNombreSucursal()));
        }
        return nuevos;
    }

}
