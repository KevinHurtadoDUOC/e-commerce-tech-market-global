package cl.duoc.sucursales_service.model;

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
@Table(name = "sucursal")
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @NotBlank(message = "Nombre de la sucursal no puede estar vacio.")
    @Size(max = 100, message = "Nombre debe tener un maximo de 100 caracteres")
    @Column(name = "nombre_sucursal")
    private String nombreSucursal;

    @NotNull(message = "Telefono de la sucursal no puede estar vacio")
    @Column(name = "fono_sucursal")
    private Integer telefonoSucursal;

    @NotBlank(message = "Direccion de la sucursal no puede estar vacia.")
    @Size(max = 255, message = "Direccion debe tener un maximo de 255 caracteres")
    @Column(name = "direccion_sucursal")
    private String direccionSucursal;
}
