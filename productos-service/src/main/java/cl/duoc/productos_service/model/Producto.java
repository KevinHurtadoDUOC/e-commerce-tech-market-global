package cl.duoc.productos_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @NotBlank(message = "Nombre del producto no puede estar vacio.")
    @Size(max = 100, message = "Nombre debe tener un maximo de 100 caracteres")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotNull(message = "Precio del producto no puede estar vacio")
    @Column(name = "precio_producto")
    private Integer precioProducto;

    @NotBlank(message = "Descripcion del producto no puede estar vacia.")
    @Size(max = 255, message = "Descripcion debe tener un maximo de 255 caracteres")
    @Column(name = "descripcion_producto")
    private String descripcionProducto;

    @NotNull(message = "Id de sucursal no puede estar vacio")
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @NotNull(message = "Id de bodega no puede estar vacio")
    @Column(name = "id_bodega")
    private Long idBodega;

    @NotNull(message = "Rut del proveedor no puede estar vacio.")
    @Column(name = "rut_proveedor")
    private Long rutProveedor;
}
