package cl.duoc.login_service.mapper;

import cl.duoc.login_service.dto.LoginDTO;
import cl.duoc.login_service.model.Login;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginMapper {

    public LoginDTO toDTO(Login login){
        if(login==null) return null;
        LoginDTO dto = new LoginDTO();
        dto.setUsuario(login.getUsuario());
        dto.setUltimoAcceso(login.getUltimoAcceso());
        return dto;
    }

    public List<LoginDTO> toDTOList(List<Login> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
