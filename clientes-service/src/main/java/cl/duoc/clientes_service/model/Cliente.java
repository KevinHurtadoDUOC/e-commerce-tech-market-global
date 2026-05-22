package cl.duoc.clientes_service.model;

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
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "rut_cliente")
    private Long rutCliente;

    @NotBlank(message = "Dv del cliente no puede estar vacio.")
    @Size(max = 1, message = "Dv debe tener un maximo de 1 caracter")
    @Column(name = "dv_cliente")
    private String dvCliente;

    @NotBlank(message = "Nombre del cliente no puede estar vacio.")
    @Size(max = 100, message = "Nombre debe tener un maximo de 100 caracteres")
    @Column(name = "nombre_cliente")
    private String nombreCliente;

    @NotBlank(message = "Direccion del cliente no puede estar vacia.")
    @Size(max = 255, message = "Direccion debe tener un maximo de 255 caracteres")
    @Column(name = "direccion_cliente")
    private String direccionCliente;

    @NotNull(message = "Telefono del cliente no puede estar vacio")
    @Column(name = "telefono_cliente")
    private Integer telefonoCliente;

    @NotNull(message = "Id de login no puede estar vacio")
    @Column(name = "id_login")
    private Long idLogin;
}
