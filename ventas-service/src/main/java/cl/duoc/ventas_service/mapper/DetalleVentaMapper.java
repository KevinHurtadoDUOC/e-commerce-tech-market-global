package cl.duoc.ventas_service.mapper;

import cl.duoc.ventas_service.dto.DetalleVentaDTO;
import cl.duoc.ventas_service.model.DetalleVenta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {

    public DetalleVentaDTO toDTO(DetalleVenta detalleVenta, String sucursal, String vendedor, String cliente, String producto) {
        if (vendedor == null) return null;

        DetalleVentaDTO dto = new DetalleVentaDTO();

        dto.setNroBoleta(100000+detalleVenta.getIdDetalleVenta());
        dto.setFechaVenta(detalleVenta.getVenta().getFechaVenta());
        dto.setNombreSucursal(sucursal);
        dto.setNombreVendedor(vendedor);
        dto.setNombreCliente(cliente);
        dto.setProducto(producto);
        dto.setCantidad(detalleVenta.getCantidad());
        dto.setPrecioProdcuto(detalleVenta.getPrecioUnitario());
        dto.setTotal(dto.getCantidad()*dto.getPrecioProdcuto());

        return dto;
    }
}
