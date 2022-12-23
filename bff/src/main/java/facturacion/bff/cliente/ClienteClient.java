package facturacion.bff.cliente;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "clientefacturacion.bff.cliente", url = "http://localhost:8000/api/Cliente")
public interface ClienteClient {

    @GetMapping("/{id}/")
    ClienteDTO findClienteById(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id);

    @GetMapping("/")
    List<ClienteDTO> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/") 
    ClienteDTO save(@RequestHeader("Authorization") String authorizationHeader, ClienteDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id")  Long id);
}
