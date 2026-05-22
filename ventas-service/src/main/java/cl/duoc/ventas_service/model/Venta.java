package cl.duoc.ventas_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @NotNull
    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @NotNull
    @Column (name = "monto_total")
    private Integer montoTotal;

    @NotNull
    @Column (name = "id_vendedor")
    private Long idVendedor;

    @NotNull
    @Column(name = "id_cliente")
    private Long rutCliente;

    @NotNull
    @Column(name = "id_sucursal")
    private Long idSucursal;

}
