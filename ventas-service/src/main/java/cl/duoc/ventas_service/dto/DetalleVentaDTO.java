package cl.duoc.ventas_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DetalleVentaDTO {
    private Long nroBoleta;
    private Date fechaVenta;
    private String nombreSucursal;
    private String nombreVendedor;
    private String nombreCliente;
    private String producto;
    private Integer cantidad;
    private Integer precioProdcuto;
    private Integer total;
}
