package cl.duoc.login_service;

import cl.duoc.login_service.dto.LoginDTO;
import cl.duoc.login_service.mapper.LoginMapper;
import cl.duoc.login_service.model.Login;
import cl.duoc.login_service.repository.LoginRepository;
import cl.duoc.login_service.service.LoginService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoginService")
public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private LoginMapper loginMapper;

    @InjectMocks
    private LoginService loginService;

    private Login login;
    private LoginDTO loginDTO;

    @BeforeEach
    public void setUp() {
        login = new Login(1L, "admin", "password123", new Date());

        loginDTO = new LoginDTO();
        loginDTO.setUsuario("admin");
        loginDTO.setUltimoAcceso(login.getUltimoAcceso());
    }

    @Test
    @DisplayName("Debe listar todos los logins correctamente")
    public void findAll_deberiaRetornarListaDeLogins() {
        when(loginRepository.findAll()).thenReturn(List.of(login));
        List<Login> resultado = loginService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("admin", resultado.get(0).getUsuario());
        verify(loginRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar un login por ID cuando existe")
    public void findById_cuandoExiste_deberiaRetornarLogin() {
        when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        Login resultado = loginService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdLogin());
        verify(loginRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar null cuando el login no existe por ID")
    public void findById_cuandoNoExiste_deberiaRetornarNull() {
        when(loginRepository.findById(99L)).thenReturn(Optional.empty());
        Login resultado = loginService.findById(99L);
        assertNull(resultado);
        verify(loginRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un login correctamente")
    public void save_deberiaGuardarYRetornarLogin() {
        when(loginRepository.save(login)).thenReturn(login);
        Login resultado = loginService.save(login);
        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsuario());
        verify(loginRepository).save(login);
    }

    @Test
    @DisplayName("Debe actualizar un login correctamente cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarLogin() {
        Login loginModificado = new Login(1L, "newuser", "newpass", new Date());
        when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        when(loginRepository.save(any(Login.class))).thenReturn(loginModificado);
        Login resultado = loginService.update(1L, loginModificado);
        assertNotNull(resultado);
        assertEquals("newuser", resultado.getUsuario());
        verify(loginRepository).findById(1L);
        verify(loginRepository).save(any(Login.class));
    }

    @Test
    @DisplayName("Debe retornar null cuando se intenta actualizar un login que no existe")
    public void update_cuandoNoExiste_deberiaRetornarNull() {
        when(loginRepository.findById(99L)).thenReturn(Optional.empty());
        Login resultado = loginService.update(99L, login);
        assertNull(resultado);
        verify(loginRepository).findById(99L);
        verify(loginRepository, never()).save(any(Login.class));
    }

    @Test
    @DisplayName("Debe eliminar un login por ID")
    public void delete_deberiaEliminarLoginPorId() {
        loginService.delete(1L);
        verify(loginRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar un DTO de login cuando existe")
    public void findDTO_deberiaRetornarLoginDTO() {
        when(loginRepository.findById(1L)).thenReturn(Optional.of(login));
        when(loginMapper.toDTO(login)).thenReturn(loginDTO);
        LoginDTO resultado = loginService.findDTO(1L);
        assertNotNull(resultado);
        assertEquals("admin", resultado.getUsuario());
        verify(loginRepository).findById(1L);
        verify(loginMapper).toDTO(login);
    }

    @Test
    @DisplayName("Debe retornar la lista de DTOs de logins")
    public void findDTOList_deberiaRetornarListaDeLoginsDTO() {
        List<Login> logins = List.of(login);
        when(loginRepository.findAll()).thenReturn(logins);
        when(loginMapper.toDTOList(logins)).thenReturn(List.of(loginDTO));
        List<LoginDTO> resultado = loginService.findDTOList();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(loginRepository).findAll();
        verify(loginMapper).toDTOList(logins);
    }
}
