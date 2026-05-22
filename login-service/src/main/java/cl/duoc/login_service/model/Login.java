package cl.duoc.login_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login")
    private Long idLogin;

    @NotBlank(message = "Usuario no puede estar vacio.")
    @Size(max = 50, message = "Usuario debe tener un maximo de 50 caracteres")
    @Column(name = "usuario")
    private String usuario;

    @NotBlank(message = "Contrasena no puede estar vacia.")
    @Size(max = 255, message = "Contrasena debe tener un maximo de 255 caracteres")
    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.DATE)
    private Date ultimoAcceso;
}
