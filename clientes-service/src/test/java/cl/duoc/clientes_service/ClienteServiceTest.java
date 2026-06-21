package cl.duoc.clientes_service;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.exception.DvIncorrectoException;
import cl.duoc.clientes_service.mapper.ClienteMapper;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.repository.ClienteRepository;
import cl.duoc.clientes_service.service.ClienteService;

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
@DisplayName("Pruebas unitarias para ClienteService")
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(12345678L, "5", "Juan Pérez", "Av. Las Condes 100", 987654321, 1L);

        clienteDTO = new ClienteDTO();
        clienteDTO.setRutCompletoCliente("123456785");
        clienteDTO.setNombreCliente("Juan Pérez");
        clienteDTO.setTelefonoCliente(987654321);
    }

    @Test
    @DisplayName("Debe listar todos los clientes correctamente")
    public void findAll_deberiaRetornarListaDeClientes() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> resultado = clienteService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombreCliente());

        verify(clienteRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar un cliente por ID cuando existe")
    public void findById_cuandoExiste_deberiaRetornarCliente() {
        when(clienteRepository.findById(12345678L)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.findById(12345678L);

        assertNotNull(resultado);
        assertEquals(12345678L, resultado.getRutCliente());
        assertEquals("Juan Pérez", resultado.getNombreCliente());

        verify(clienteRepository).findById(12345678L);
    }

    @Test
    @DisplayName("Debe retornar null cuando el cliente no existe por ID")
    public void findById_cuandoNoExiste_deberiaRetornarNull() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.findById(99L);

        assertNull(resultado);

        verify(clienteRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un cliente correctamente con DV válido")
    public void save_conDvValido_deberiaGuardarYRetornarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.save(cliente);

        assertNotNull(resultado);
        assertEquals(12345678L, resultado.getRutCliente());

        verify(clienteRepository).save(cliente);
    }

    @Test
    @DisplayName("Debe lanzar DvIncorrectoException con DV inválido")
    public void save_conDvInvalido_deberiaLanzarExcepcion() {
        Cliente clienteInvalido = new Cliente(12345678L, "X", "Juan", "Dir", 123, 1L);

        assertThrows(DvIncorrectoException.class, () -> clienteService.save(clienteInvalido));

        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe actualizar un cliente correctamente cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarCliente() {
        Cliente clienteModificado = new Cliente(12345678L, "5", "Pedro López", "Nueva Dir", 111222333, 2L);

        when(clienteRepository.findById(12345678L)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteModificado);

        Cliente resultado = clienteService.update(12345678L, clienteModificado);

        assertNotNull(resultado);
        assertEquals("Pedro López", resultado.getNombreCliente());

        verify(clienteRepository).findById(12345678L);
        verify(clienteRepository).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe retornar null cuando se intenta actualizar un cliente que no existe")
    public void update_cuandoNoExiste_deberiaRetornarNull() {
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        Cliente resultado = clienteService.update(99L, cliente);

        assertNull(resultado);

        verify(clienteRepository).findById(99L);
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    @DisplayName("Debe eliminar un cliente por ID")
    public void delete_deberiaEliminarClientePorId() {
        Long id = 12345678L;

        clienteService.delete(id);

        verify(clienteRepository).deleteById(id);
    }

    @Test
    @DisplayName("Debe retornar un DTO de cliente cuando existe")
    public void findDTO_deberiaRetornarClienteDTO() {
        when(clienteRepository.findById(12345678L)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toDTO(cliente)).thenReturn(clienteDTO);

        ClienteDTO resultado = clienteService.findDTO(12345678L);

        assertNotNull(resultado);
        assertEquals("Juan Pérez", resultado.getNombreCliente());

        verify(clienteRepository).findById(12345678L);
        verify(clienteMapper).toDTO(cliente);
    }

    @Test
    @DisplayName("Debe retornar la lista de DTOs de clientes")
    public void findDTOList_deberiaRetornarListaDeClientesDTO() {
        List<Cliente> clientes = List.of(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);
        when(clienteMapper.toDTOlist(clientes)).thenReturn(List.of(clienteDTO));

        List<ClienteDTO> resultado = clienteService.findDTOList();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Pérez", resultado.get(0).getNombreCliente());

        verify(clienteRepository).findAll();
        verify(clienteMapper).toDTOlist(clientes);
    }
}
