package cl.duoc.proveedores_service;

import cl.duoc.proveedores_service.dto.ProveedorDTO;
import cl.duoc.proveedores_service.mapper.ProveedorMapper;
import cl.duoc.proveedores_service.model.Proveedor;
import cl.duoc.proveedores_service.repository.ProveedorRepository;
import cl.duoc.proveedores_service.service.ProveedorService;

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
@DisplayName("Pruebas unitarias para ProveedorService")
public class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepository;

    @Mock
    private ProveedorMapper proveedorMapper;

    @InjectMocks
    private ProveedorService proveedorService;

    private Proveedor proveedor;
    private ProveedorDTO proveedorDTO;

    @BeforeEach
    public void setUp() {
        proveedor = new Proveedor(12345678L, "5", "TechSupply", "tech@supply.com", 987654321);

        proveedorDTO = new ProveedorDTO();
        proveedorDTO.setRutProveedor("12345678-5");
        proveedorDTO.setNombreProveedor("TechSupply");
    }

    @Test
    @DisplayName("Debe listar todos los proveedores correctamente")
    public void findAll_deberiaRetornarListaDeProveedores() {
        when(proveedorRepository.findAll()).thenReturn(List.of(proveedor));
        List<Proveedor> resultado = proveedorService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("TechSupply", resultado.get(0).getNombreProveedor());
        verify(proveedorRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar un proveedor por ID cuando existe")
    public void findById_cuandoExiste_deberiaRetornarProveedor() {
        when(proveedorRepository.findById(12345678L)).thenReturn(Optional.of(proveedor));
        Proveedor resultado = proveedorService.findById(12345678L);
        assertNotNull(resultado);
        assertEquals("TechSupply", resultado.getNombreProveedor());
        verify(proveedorRepository).findById(12345678L);
    }

    @Test
    @DisplayName("Debe retornar null cuando el proveedor no existe por ID")
    public void findById_cuandoNoExiste_deberiaRetornarNull() {
        when(proveedorRepository.findById(99L)).thenReturn(Optional.empty());
        Proveedor resultado = proveedorService.findById(99L);
        assertNull(resultado);
        verify(proveedorRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un proveedor correctamente")
    public void save_deberiaGuardarYRetornarProveedor() {
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);
        Proveedor resultado = proveedorService.save(proveedor);
        assertNotNull(resultado);
        assertEquals("TechSupply", resultado.getNombreProveedor());
        verify(proveedorRepository).save(proveedor);
    }

    @Test
    @DisplayName("Debe actualizar un proveedor correctamente cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarProveedor() {
        Proveedor proveedorMod = new Proveedor(12345678L, "5", "NewSupply", "new@supply.com", 111222333);
        when(proveedorRepository.findById(12345678L)).thenReturn(Optional.of(proveedor));
        when(proveedorRepository.save(any(Proveedor.class))).thenReturn(proveedorMod);
        Proveedor resultado = proveedorService.update(12345678L, proveedorMod);
        assertNotNull(resultado);
        assertEquals("NewSupply", resultado.getNombreProveedor());
        verify(proveedorRepository).findById(12345678L);
        verify(proveedorRepository).save(any(Proveedor.class));
    }

    @Test
    @DisplayName("Debe retornar null cuando se intenta actualizar un proveedor que no existe")
    public void update_cuandoNoExiste_deberiaRetornarNull() {
        when(proveedorRepository.findById(99L)).thenReturn(Optional.empty());
        Proveedor resultado = proveedorService.update(99L, proveedor);
        assertNull(resultado);
        verify(proveedorRepository).findById(99L);
        verify(proveedorRepository, never()).save(any(Proveedor.class));
    }

    @Test
    @DisplayName("Debe eliminar un proveedor por ID")
    public void delete_deberiaEliminarProveedorPorId() {
        proveedorService.delete(12345678L);
        verify(proveedorRepository).deleteById(12345678L);
    }

    @Test
    @DisplayName("Debe retornar un DTO de proveedor cuando existe")
    public void findDTO_deberiaRetornarProveedorDTO() {
        when(proveedorRepository.findById(12345678L)).thenReturn(Optional.of(proveedor));
        when(proveedorMapper.toDTO(proveedor)).thenReturn(proveedorDTO);
        ProveedorDTO resultado = proveedorService.findDTO(12345678L);
        assertNotNull(resultado);
        assertEquals("TechSupply", resultado.getNombreProveedor());
        verify(proveedorRepository).findById(12345678L);
        verify(proveedorMapper).toDTO(proveedor);
    }

    @Test
    @DisplayName("Debe retornar la lista de DTOs de proveedores")
    public void findDTOList_deberiaRetornarListaDeProveedoresDTO() {
        List<Proveedor> proveedores = List.of(proveedor);
        when(proveedorRepository.findAll()).thenReturn(proveedores);
        when(proveedorMapper.toDTOList(proveedores)).thenReturn(List.of(proveedorDTO));
        List<ProveedorDTO> resultado = proveedorService.findDTOList();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(proveedorRepository).findAll();
        verify(proveedorMapper).toDTOList(proveedores);
    }
}
