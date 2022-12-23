package facturacion.bff.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/cliente")
@CrossOrigin({"*"})
public class ClienteController {

    @Autowired ClienteClient client;

    @GetMapping("/")
    public List<ClienteDTO> findAll(@RequestHeader("Authorization") String authHeader) {
        return client.findAll(authHeader);
    }
    
    @GetMapping("/{id}/")
    public ClienteDTO findById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        System.out.println(id);
        return client.findClienteById(authHeader, id);
    }

    @PostMapping("/")
    public ClienteDTO save(@RequestHeader("Authorization") String authHeader, @RequestBody ClienteDTO entity){
        return client.save(authHeader, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        client.deleteById(authHeader, id);
    }
    
}
