package cl.duoc.sucursales_service;

import cl.duoc.sucursales_service.dto.SucursalDTO;
import cl.duoc.sucursales_service.mapper.SucursalMapper;
import cl.duoc.sucursales_service.model.Sucursal;
import cl.duoc.sucursales_service.repository.SucursalRepository;
import cl.duoc.sucursales_service.service.SucursalService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para SucursalService")
public class SucursalServiceTest {

    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private SucursalMapper sucursalMapper;

    @InjectMocks
    private SucursalService sucursalService;

    private Sucursal sucursal;
    private SucursalDTO sucursalDTO;

    @BeforeEach
    public void setUp() {
        sucursal = new Sucursal(1L, "Sucursal Central", 912345678, "Av. Providencia 200");

        sucursalDTO = new SucursalDTO();
        sucursalDTO.setNombreSucursal("Sucursal Central");
        sucursalDTO.setTelefonoSucursal(912345678);
        sucursalDTO.setDireccionSucursal("Av. Providencia 200");
    }

    @Test
    @DisplayName("Debe listar todas las sucursales correctamente")
    public void findAll_deberiaRetornarListaDeSucursales() {
        when(sucursalRepository.findAll()).thenReturn(List.of(sucursal));
        List<Sucursal> resultado = sucursalService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Sucursal Central", resultado.get(0).getNombreSucursal());
        verify(sucursalRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar una sucursal por ID cuando existe")
    public void findById_cuandoExiste_deberiaRetornarSucursal() {
        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));
        Sucursal resultado = sucursalService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdSucursal());
        verify(sucursalRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar null cuando la sucursal no existe por ID")
    public void findById_cuandoNoExiste_deberiaRetornarNull() {
        when(sucursalRepository.findById(99L)).thenReturn(Optional.empty());
        Sucursal resultado = sucursalService.findById(99L);
        assertNull(resultado);
        verify(sucursalRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar una sucursal correctamente")
    public void save_deberiaGuardarYRetornarSucursal() {
        when(sucursalRepository.save(sucursal)).thenReturn(sucursal);
        Sucursal resultado = sucursalService.save(sucursal);
        assertNotNull(resultado);
        assertEquals("Sucursal Central", resultado.getNombreSucursal());
        verify(sucursalRepository).save(sucursal);
    }

    @Test
    @DisplayName("Debe actualizar una sucursal correctamente cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarSucursal() {
        Sucursal sucursalMod = new Sucursal(1L, "Sucursal Norte", 911111111, "Av. Norte 500");
        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalMod);
        Sucursal resultado = sucursalService.update(1L, sucursalMod);
        assertNotNull(resultado);
        assertEquals("Sucursal Norte", resultado.getNombreSucursal());
        verify(sucursalRepository).findById(1L);
        verify(sucursalRepository).save(any(Sucursal.class));
    }

    @Test
    @DisplayName("Debe retornar null cuando se intenta actualizar una sucursal que no existe")
    public void update_cuandoNoExiste_deberiaRetornarNull() {
        when(sucursalRepository.findById(99L)).thenReturn(Optional.empty());
        Sucursal resultado = sucursalService.update(99L, sucursal);
        assertNull(resultado);
        verify(sucursalRepository).findById(99L);
        verify(sucursalRepository, never()).save(any(Sucursal.class));
    }

    @Test
    @DisplayName("Debe eliminar una sucursal por ID")
    public void delete_deberiaEliminarSucursalPorId() {
        sucursalService.delete(1L);
        verify(sucursalRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar un DTO de sucursal cuando existe")
    public void findDTO_deberiaRetornarSucursalDTO() {
        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));
        when(sucursalMapper.toDTO(sucursal)).thenReturn(sucursalDTO);
        SucursalDTO resultado = sucursalService.findDTO(1L);
        assertNotNull(resultado);
        assertEquals("Sucursal Central", resultado.getNombreSucursal());
        verify(sucursalRepository).findById(1L);
        verify(sucursalMapper).toDTO(sucursal);
    }

    @Test
    @DisplayName("Debe retornar la lista de DTOs de sucursales")
    public void findDTOList_deberiaRetornarListaDeSucursalesDTO() {
        List<Sucursal> sucursales = List.of(sucursal);
        when(sucursalRepository.findAll()).thenReturn(sucursales);
        when(sucursalMapper.toDTOList(sucursales)).thenReturn(List.of(sucursalDTO));
        List<SucursalDTO> resultado = sucursalService.findDTOList();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(sucursalRepository).findAll();
        verify(sucursalMapper).toDTOList(sucursales);
    }
}
