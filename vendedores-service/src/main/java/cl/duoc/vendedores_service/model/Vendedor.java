package cl.duoc.vendedores_service.model;

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
@Table(name = "vendedor")
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vendedor")
    private Long idVendedor;

    @NotBlank(message = "Nombre del vendedor no puede estar vacio.")
    @Size(max = 100, message = "Nombre debe tener un maximo de 100 caracteres")
    @Column(name = "nombre_vendedor")
    private String nombreVendedor;

    @NotNull(message = "Sueldo del vendedor no puede estar vacio")
    @Column(name = "sueldo_vendedor")
    private Integer sueldoVendedor;

    @NotNull(message = "Id de sucursal no puede estar vacio")
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @NotNull(message = "Id de login no puede estar vacio")
    @Column(name = "id_login")
    private Long idLogin;
}
