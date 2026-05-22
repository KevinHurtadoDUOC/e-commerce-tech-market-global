package cl.duoc.productos_service.mapper;

import cl.duoc.productos_service.dto.ProductoDTO;
import cl.duoc.productos_service.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto, String sucursal, String bodega, String proveedor) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();

        dto.setNombreProducto(producto.getNombreProducto());
        dto.setPrecioProducto(producto.getPrecioProducto());
        dto.setDescripcionProducto(producto.getDescripcionProducto());
        dto.setNombreSucursal(sucursal);
        dto.setDireccionBodega(bodega);
        dto.setNombreProveedor(proveedor);

        return dto;
    }
}