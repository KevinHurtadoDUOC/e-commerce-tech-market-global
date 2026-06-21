package cl.duoc.bodegas_service;

import cl.duoc.bodegas_service.dto.BodegaDTO;
import cl.duoc.bodegas_service.mapper.BodegaMapper;
import cl.duoc.bodegas_service.model.Bodega;
import cl.duoc.bodegas_service.repository.BodegaRepository;
import cl.duoc.bodegas_service.service.BodegaService;

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
@DisplayName("Pruebas unitarias para BodegaService")
public class BodegaServiceTest {

    @Mock
    private BodegaRepository bodegaRepository;

    @Mock
    private BodegaMapper bodegaMapper;

    @InjectMocks
    private BodegaService bodegaService;

    private Bodega bodega;
    private Bodega bodegaSinId;
    private BodegaDTO bodegaDTO;

    @BeforeEach
    public void setUp() {
        bodega = new Bodega(
                1L,
                "Av. Las Condes 123",
                987654321,
                15,
                1000
        );

        bodegaSinId = new Bodega(
                null,
                "Av. Las Condes 123",
                987654321,
                15,
                1000
        );

        bodegaDTO = new BodegaDTO();
        bodegaDTO.setDireccionBodega("Av. Las Condes 123");
        bodegaDTO.setTelefonoBodega(987654321);
        bodegaDTO.setCapacidadBodega(1000);
    }

    @Test
    @DisplayName("Debe listar todas las bodegas correctamente")
    public void findAll_deberiaRetornarListaDeBodegas() {

        when(bodegaRepository.findAll()).thenReturn(List.of(bodega));

        List<Bodega> resultado = bodegaService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Av. Las Condes 123", resultado.get(0).getDireccionBodega());

        verify(bodegaRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar una bodega por ID cuando existe")
    public void findById_cuandoExiste_deberiaRetornarBodega() {

        when(bodegaRepository.findById(1L)).thenReturn(Optional.of(bodega));

        Bodega resultado = bodegaService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBodega());
        assertEquals("Av. Las Condes 123", resultado.getDireccionBodega());

        verify(bodegaRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar null cuando la bodega no existe por ID")
    public void findById_cuandoNoExiste_deberiaRetornarNull() {

        when(bodegaRepository.findById(99L)).thenReturn(Optional.empty());

        Bodega resultado = bodegaService.findById(99L);

        assertNull(resultado);

        verify(bodegaRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar una bodega y retornarla correctamente")
    public void save_deberiaGuardarYRetornarBodega() {

        when(bodegaRepository.save(bodegaSinId)).thenReturn(bodega);

        Bodega resultado = bodegaService.save(bodegaSinId);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBodega());
        assertEquals("Av. Las Condes 123", resultado.getDireccionBodega());

        verify(bodegaRepository).save(bodegaSinId);
    }

    @Test
    @DisplayName("Debe actualizar una bodega correctamente cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarBodega() {

        Bodega bodegaModificada = new Bodega(
                null,
                "Nueva Direccion 456",
                123456789,
                10,
                2000
        );

        Bodega bodegaActualizada = new Bodega(
                1L,
                "Nueva Direccion 456",
                123456789,
                10,
                2000
        );

        when(bodegaRepository.findById(1L)).thenReturn(Optional.of(bodega));
        when(bodegaRepository.save(any(Bodega.class))).thenReturn(bodegaActualizada);

        Bodega resultado = bodegaService.update(1L, bodegaModificada);

        assertNotNull(resultado);
        assertEquals("Nueva Direccion 456", resultado.getDireccionBodega());
        assertEquals(123456789, resultado.getTelefonoBodega());
        assertEquals(10, resultado.getTemperaturaBodega());
        assertEquals(2000, resultado.getCapacidadBodega());

        verify(bodegaRepository).findById(1L);
        verify(bodegaRepository).save(any(Bodega.class));
    }

    @Test
    @DisplayName("Debe retornar null cuando se intenta actualizar una bodega que no existe")
    public void update_cuandoNoExiste_deberiaRetornarNull() {

        when(bodegaRepository.findById(99L)).thenReturn(Optional.empty());

        Bodega resultado = bodegaService.update(99L, bodega);

        assertNull(resultado);

        verify(bodegaRepository).findById(99L);
        verify(bodegaRepository, never()).save(any(Bodega.class));
    }

    @Test
    @DisplayName("Debe eliminar una bodega por ID")
    public void delete_deberiaEliminarBodegaPorId() {

        Long id = 1L;

        bodegaService.delete(id);

        verify(bodegaRepository).deleteById(id);
    }

    @Test
    @DisplayName("Debe retornar un DTO de bodega cuando existe")
    public void findDTO_deberiaRetornarBodegaDTO() {

        when(bodegaRepository.findById(1L)).thenReturn(Optional.of(bodega));
        when(bodegaMapper.toDTO(bodega)).thenReturn(bodegaDTO);

        BodegaDTO resultado = bodegaService.findDTO(1L);

        assertNotNull(resultado);
        assertEquals("Av. Las Condes 123", resultado.getDireccionBodega());
        assertEquals(987654321, resultado.getTelefonoBodega());
        assertEquals(1000, resultado.getCapacidadBodega());

        verify(bodegaRepository).findById(1L);
        verify(bodegaMapper).toDTO(bodega);
    }

    @Test
    @DisplayName("Debe retornar la lista de DTOs de bodegas")
    public void findDTOList_deberiaRetornarListaDeBodegasDTO() {

        List<Bodega> bodegas = List.of(bodega);

        when(bodegaRepository.findAll()).thenReturn(bodegas);
        when(bodegaMapper.toDTOList(bodegas)).thenReturn(List.of(bodegaDTO));

        List<BodegaDTO> resultado = bodegaService.findDTOList();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Av. Las Condes 123", resultado.get(0).getDireccionBodega());

        verify(bodegaRepository).findAll();
        verify(bodegaMapper).toDTOList(bodegas);
    }
}
