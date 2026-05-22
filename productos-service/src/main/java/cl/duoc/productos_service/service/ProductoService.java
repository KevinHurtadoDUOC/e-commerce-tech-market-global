package cl.duoc.productos_service.service;

import cl.duoc.productos_service.clientsFeign.BodegaFeign;
import cl.duoc.productos_service.clientsFeign.ProveedorFeign;
import cl.duoc.productos_service.clientsFeign.SucursalFeign;
import cl.duoc.productos_service.dto.*;
import cl.duoc.productos_service.mapper.ProductoMapper;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoMapper productoMapper;

    @Autowired
    private SucursalFeign sucursalFeign;

    @Autowired
    private BodegaFeign bodegaFeign;

    @Autowired
    private ProveedorFeign proveedorFeign;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }

    public Producto findById(Long id){
        return productoRepository.findById(id).orElse(null);
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto update(Long id, Producto producto){
        Producto productoActualizar = productoRepository.findById(id).orElse(null);
        if (productoActualizar == null) return null;
        productoActualizar.setNombreProducto(producto.getNombreProducto());
        productoActualizar.setPrecioProducto(producto.getPrecioProducto());
        productoActualizar.setDescripcionProducto(producto.getDescripcionProducto());
        productoActualizar.setIdSucursal(producto.getIdSucursal());
        productoActualizar.setIdBodega(producto.getIdBodega());
        productoActualizar.setRutProveedor(producto.getRutProveedor());
        return productoRepository.save(productoActualizar);
    }

    public void delete(Long id){
        productoRepository.deleteById(id);
    }

    public ProductoDTO productoDTOCompleto(Long id){
        Producto p = productoRepository.findById(id).orElse(null);
        SucursalDTO sucursalDTO = sucursalFeign.buscarSucursal(p.getIdSucursal());
        BodegaDTO bodegaDTO = bodegaFeign.buscarBodega(p.getIdBodega());
        ProveedorDTO proveedorDTO = proveedorFeign.buscarProveedor(p.getRutProveedor());

        return productoMapper.toDTO(p, sucursalDTO.getNombreSucursal(), bodegaDTO.getDireccionBodega(), proveedorDTO.getNombreProveedor());
    }

    public List<ProductoDTO> listarProductoDTOCompleto(){

        List<Producto> lista = productoRepository.findAll();
        List<ProductoDTO> nuevos = new ArrayList<>();

        for (Producto p : lista) {
            SucursalDTO sucursalDTO = sucursalFeign.buscarSucursal(p.getIdSucursal());
            BodegaDTO bodegaDTO = bodegaFeign.buscarBodega(p.getIdBodega());
            ProveedorDTO proveedorDTO = proveedorFeign.buscarProveedor(p.getRutProveedor());

            nuevos.add(productoMapper.toDTO(p, sucursalDTO.getNombreSucursal(), bodegaDTO.getDireccionBodega(), proveedorDTO.getNombreProveedor()));
        }
        return nuevos;
    }

}
