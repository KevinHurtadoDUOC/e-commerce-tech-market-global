package cl.duoc.clientes_service.service;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.mapper.ClienteMapper;
import cl.duoc.clientes_service.model.Cliente;
import cl.duoc.clientes_service.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente){
        Cliente clienteActualizar = clienteRepository.findById(id).orElse(null);
        if (clienteActualizar == null) return null;
        clienteActualizar.setDvCliente(cliente.getDvCliente());
        clienteActualizar.setNombreCliente(cliente.getNombreCliente());
        clienteActualizar.setDireccionCliente(cliente.getDireccionCliente());
        clienteActualizar.setTelefonoCliente(cliente.getTelefonoCliente());
        clienteActualizar.setIdLogin(cliente.getIdLogin());
        return clienteRepository.save(clienteActualizar);
    }

    public void delete(Long id){
        clienteRepository.deleteById(id);
    }

    public ClienteDTO findDTO(Long id){
        return clienteMapper.toDTO(findById(id));
    }

    public List<ClienteDTO> findDTOList(){
        return clienteMapper.toDTOlist(findAll());
    }
}