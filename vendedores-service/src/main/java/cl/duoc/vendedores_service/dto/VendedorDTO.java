package cl.duoc.vendedores_service.dto;

import lombok.Data;

@Data
public class VendedorDTO {
    private Long idVendedor;
    private String nombreVendedor;
    private Integer sueldoVendedor;
    private String idSucursal;
}