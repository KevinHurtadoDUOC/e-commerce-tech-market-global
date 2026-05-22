package cl.duoc.ventas_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta")
    private Long idDetalleVenta;

    @NotNull
    @Column(name = "id_producto")
    private Long idProducto;

    @NotNull
    @Column(name = "cantidad")
    private Integer cantidad;
   @NotNull
    @Column(name = "precio_unitario")
    private Integer precioUnitario;

    @NotNull
    @Column(name = "subtotal")
    private Integer subtotal;

    @ManyToOne
    @JoinColumn (name = "id_venta")
    private Venta venta;
}