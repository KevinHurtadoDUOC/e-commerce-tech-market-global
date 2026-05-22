package cl.duoc.ventas_service.service;

import cl.duoc.ventas_service.clientsFeign.ClienteFeign;
import cl.duoc.ventas_service.clientsFeign.ProductoFeign;
import cl.duoc.ventas_service.clientsFeign.SucursalFeign;
import cl.duoc.ventas_service.clientsFeign.VendedorFeign;
import cl.duoc.ventas_service.dto.*;
import cl.duoc.ventas_service.mapper.DetalleVentaMapper;
import cl.duoc.ventas_service.model.DetalleVenta;
import cl.duoc.ventas_service.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private SucursalFeign sucursalFeign;

    @Autowired
    private ClienteFeign clienteFeign;

    @Autowired
    private ProductoFeign productoFeign;

    @Autowired
    private VendedorFeign vendedorFeign;

    @Autowired
    private DetalleVentaMapper detalleVentaMapper;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    public List<DetalleVenta> findAll(){
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta findById(Long id){
        return detalleVentaRepository.findById(id).orElse(null);
    }

    public DetalleVenta save(DetalleVenta DetalleVenta){
        return detalleVentaRepository.save(DetalleVenta);
    }

    public DetalleVenta update(Long id, DetalleVenta detalleVenta){
        DetalleVenta detalleVentaActualizar = detalleVentaRepository.findById(id).orElse(null);
        if (detalleVentaActualizar == null) return null;

        detalleVentaActualizar.setIdProducto(detalleVenta.getIdProducto());
        detalleVentaActualizar.setCantidad(detalleVenta.getCantidad());
        detalleVentaActualizar.setPrecioUnitario(detalleVenta.getPrecioUnitario());
        detalleVentaActualizar.setSubtotal(detalleVenta.getSubtotal());
        detalleVentaActualizar.setVenta(detalleVenta.getVenta());

        return detalleVentaRepository.save(detalleVentaActualizar);
    }

    public void delete(Long id){
        detalleVentaRepository.deleteById(id);
    }

    public DetalleVentaDTO detalleVentaDTO(Long id){
        DetalleVenta dv = detalleVentaRepository.findById(id).orElse(null);

        SucursalDTO sucursalDTO = sucursalFeign.buscarSucursal(dv.getVenta().getIdSucursal());
        ClienteDTO clienteDTO = clienteFeign.buscarPorIdDTO(dv.getVenta().getRutCliente());
        ProductoDTO productoDTO = productoFeign.buscarDTO(dv.getIdProducto());
        VendedorDTO vendedorDTO = vendedorFeign.buscarDTO(dv.getVenta().getIdVendedor());

        return detalleVentaMapper.toDTO(dv, sucursalDTO.getNombreSucursal(), vendedorDTO.getNombreVendedor(),
                clienteDTO.getNombreCliente(), productoDTO.getNombreProducto());
    }
    
    public List<DetalleVentaDTO> listarDetalleVentaDTO(){

        List<DetalleVenta> lista = detalleVentaRepository.findAll();
        List<DetalleVentaDTO> nuevos = new ArrayList<>();

        for (DetalleVenta dv : lista) {
            SucursalDTO sucursalDTO = sucursalFeign.buscarSucursal(dv.getVenta().getIdSucursal());
            ClienteDTO clienteDTO = clienteFeign.buscarPorIdDTO(dv.getVenta().getRutCliente());
            ProductoDTO productoDTO = productoFeign.buscarDTO(dv.getIdProducto());
            VendedorDTO vendedorDTO = vendedorFeign.buscarDTO(dv.getVenta().getIdVendedor());


            nuevos.add(detalleVentaMapper.toDTO(dv, sucursalDTO.getNombreSucursal(), vendedorDTO.getNombreVendedor(),
                    clienteDTO.getNombreCliente(), productoDTO.getNombreProducto()));
        }
        return nuevos;
    }
}