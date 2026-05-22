package cl.duoc.clientes_service.mapper;

import cl.duoc.clientes_service.dto.ClienteDTO;
import cl.duoc.clientes_service.model.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente){
        if(cliente==null) return null;
        ClienteDTO dto = new ClienteDTO();
        dto.setRutCompletoCliente(cliente.getRutCliente().toString().concat(cliente.getDvCliente()));
        dto.setNombreCliente(cliente.getNombreCliente());
        dto.setTelefonoCliente(cliente.getTelefonoCliente());
        return dto;
    }

    public List<ClienteDTO> toDTOlist(List<Cliente> listado){
        return listado.stream()
                .map(this::toDTO)
                .toList();
    }
}
