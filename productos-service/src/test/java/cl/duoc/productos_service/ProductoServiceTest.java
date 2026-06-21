package cl.duoc.productos_service;

import cl.duoc.productos_service.clientsFeign.BodegaFeign;
import cl.duoc.productos_service.clientsFeign.ProveedorFeign;
import cl.duoc.productos_service.clientsFeign.SucursalFeign;
import cl.duoc.productos_service.mapper.ProductoMapper;
import cl.duoc.productos_service.model.Producto;
import cl.duoc.productos_service.repository.ProductoRepository;
import cl.duoc.productos_service.service.ProductoService;

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
@DisplayName("Pruebas unitarias para ProductoService")
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoMapper productoMapper;

    @Mock
    private SucursalFeign sucursalFeign;

    @Mock
    private BodegaFeign bodegaFeign;

    @Mock
    private ProveedorFeign proveedorFeign;

    @InjectMocks
    private ProductoService productoService;

    private Producto producto;

    @BeforeEach
    public void setUp() {
        producto = new Producto(1L, "Laptop HP", 599990, "Laptop HP 15 pulgadas", 1L, 1L, 12345678L);
    }

    @Test
    @DisplayName("Debe listar todos los productos correctamente")
    public void findAll_deberiaRetornarListaDeProductos() {
        when(productoRepository.findAll()).thenReturn(List.of(producto));
        List<Producto> resultado = productoService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Laptop HP", resultado.get(0).getNombreProducto());
        verify(productoRepository).findAll();
    }

    @Test
    @DisplayName("Debe buscar un producto por ID cuando existe")
    public void findById_cuandoExiste_deberiaRetornarProducto() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        Producto resultado = productoService.findById(1L);
        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProducto());
        verify(productoRepository).findById(1L);
    }

    @Test
    @DisplayName("Debe retornar null cuando el producto no existe por ID")
    public void findById_cuandoNoExiste_deberiaRetornarNull() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        Producto resultado = productoService.findById(99L);
        assertNull(resultado);
        verify(productoRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un producto correctamente")
    public void save_deberiaGuardarYRetornarProducto() {
        when(productoRepository.save(producto)).thenReturn(producto);
        Producto resultado = productoService.save(producto);
        assertNotNull(resultado);
        assertEquals("Laptop HP", resultado.getNombreProducto());
        verify(productoRepository).save(producto);
    }

    @Test
    @DisplayName("Debe actualizar un producto correctamente cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarProducto() {
        Producto productoMod = new Producto(1L, "Laptop Dell", 699990, "Laptop Dell 17 pulgadas", 2L, 2L, 87654321L);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoMod);
        Producto resultado = productoService.update(1L, productoMod);
        assertNotNull(resultado);
        assertEquals("Laptop Dell", resultado.getNombreProducto());
        verify(productoRepository).findById(1L);
        verify(productoRepository).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe retornar null cuando se intenta actualizar un producto que no existe")
    public void update_cuandoNoExiste_deberiaRetornarNull() {
        when(productoRepository.findById(99L)).thenReturn(Optional.empty());
        Producto resultado = productoService.update(99L, producto);
        assertNull(resultado);
        verify(productoRepository).findById(99L);
        verify(productoRepository, never()).save(any(Producto.class));
    }

    @Test
    @DisplayName("Debe eliminar un producto por ID")
    public void delete_deberiaEliminarProductoPorId() {
        productoService.delete(1L);
        verify(productoRepository).deleteById(1L);
    }
}
