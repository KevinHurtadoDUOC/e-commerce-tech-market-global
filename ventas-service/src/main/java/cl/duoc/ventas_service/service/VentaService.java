package cl.duoc.ventas_service.service;

import cl.duoc.ventas_service.model.Venta;
import cl.duoc.ventas_service.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll(){
        return ventaRepository.findAll();
    }

    public Venta findById(Long id){
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta save(Venta venta){
        return ventaRepository.save(venta);
    }

    public Venta update(Long id, Venta venta){
        Venta ventaActualizar = ventaRepository.findById(id).orElse(null);
        if (ventaActualizar == null) return null;

        ventaActualizar.setFechaVenta(venta.getFechaVenta());
        ventaActualizar.setMontoTotal(venta.getMontoTotal());
        ventaActualizar.setIdVendedor(venta.getIdVendedor());
        ventaActualizar.setRutCliente(venta.getRutCliente());
        ventaActualizar.setIdSucursal(venta.getIdSucursal());

        return ventaRepository.save(ventaActualizar);
    }

    public void delete(Long id){
        ventaRepository.deleteById(id);
    }
}
