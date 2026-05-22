package cl.duoc.bodegas_service.model;

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
@Table(name = "bodega")
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bodega")
    private Long idBodega;

    @NotBlank(message = "Direccion de la bodega no puede estar vacia.")
    @Size(max = 255, message = "Direccion debe tener un maximo de 255 caracteres")
    @Column(name = "direccion_bodega")
    private String direccionBodega;

    @NotNull(message = "Telefono de la bodega no puede estar vacio")
    @Column(name = "telefono_bodega")
    private Integer telefonoBodega;

    @NotNull(message = "Temperatura de la bodega no puede estar vacia")
    @Column(name = "temperatura_bodega")
    private Integer temperaturaBodega;

    @NotNull(message = "Capacidad de la bodega no puede estar vacia")
    @Column(name = "capacidad_bodega")
    private Integer capacidadBodega;
}
