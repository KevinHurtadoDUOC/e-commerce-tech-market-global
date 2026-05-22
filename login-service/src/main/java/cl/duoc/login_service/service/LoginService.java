package cl.duoc.login_service.service;

import cl.duoc.login_service.dto.LoginDTO;
import cl.duoc.login_service.mapper.LoginMapper;
import cl.duoc.login_service.model.Login;
import cl.duoc.login_service.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private LoginRepository loginRepository;

    public List<Login> findAll(){
        return loginRepository.findAll();
    }

    public Login findById(Long id){
        return loginRepository.findById(id).orElse(null);
    }

    public Login save(Login login){
        return loginRepository.save(login);
    }

    public Login update(Long id, Login login){
        Login loginActualizar = loginRepository.findById(id).orElse(null);

        if (loginActualizar == null) return null;

        loginActualizar.setUsuario(login.getUsuario());
        loginActualizar.setContrasena(login.getContrasena());
        loginActualizar.setUltimoAcceso(login.getUltimoAcceso());

        return loginRepository.save(loginActualizar);
    }

    public void delete(Long id){
        loginRepository.deleteById(id);
    }

    public LoginDTO findDTO(Long id){
        return loginMapper.toDTO(findById(id));
    }

    public List<LoginDTO> findDTOList(){
        return loginMapper.toDTOList(findAll());
    }
}