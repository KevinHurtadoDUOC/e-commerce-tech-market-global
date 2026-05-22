package cl.duoc.ventas_service.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private String nombreProducto;
    private Integer precioProducto;
    private String descripcionProducto;
    private String nombreSucursal;
    private String direccionBodega;
    private String nombreProveedor;
}
