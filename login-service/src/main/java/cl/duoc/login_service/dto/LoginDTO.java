package cl.duoc.login_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoginDTO {
    private String usuario;
    private Date ultimoAcceso;
}
