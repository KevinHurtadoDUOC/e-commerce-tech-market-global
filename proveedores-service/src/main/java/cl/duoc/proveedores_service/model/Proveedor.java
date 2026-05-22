package cl.duoc.proveedores_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @NotNull(message = "Rut del proveedor no puede estar vacio.")
    @Size(max = 12, message = "Rut debe tener un maximo de 12 caracteres")
    @Column(name = "rut_proveedor")
    private Long rutProveedor;

    @NotBlank(message = "Dv del cliente no puede estar vacio.")
    @Size(max = 1, message = "Dv debe tener un maximo de 1 caracter")
    @Column(name = "dv_proveedor")
    private String dvProveedor;

    @NotBlank(message = "Nombre del proveedor no puede estar vacio.")
    @Size(max = 100, message = "Nombre debe tener un maximo de 100 caracteres")
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

    @Email
    @NotBlank(message = "Correo del proveedor no puede estar vacio.")
    @Size(max = 100, message = "Correo debe tener un maximo de 100 caracteres")
    @Column(name = "correo_proveedor")
    private String correoProveedor;

    @NotNull(message = "Telefono del proveedor no puede estar vacio")
    @Column(name = "telefono_proveedor")
    private Integer telefonoProveedor;
}
