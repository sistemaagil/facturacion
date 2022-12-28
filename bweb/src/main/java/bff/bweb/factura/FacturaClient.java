package bff.bweb.factura;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "bff.factura", url = "http://localhost:8083/api/factura")
public interface FacturaClient {

    @GetMapping("/{id}/")
    FacturaDTO findFacturaById(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id);

    @GetMapping("/")
    List<FacturaDTO> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/") 
    FacturaDTO save(@RequestHeader("Authorization") String authorizationHeader, FacturaDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    FacturaDTO update(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("id")  Long id, FacturaDTO entity);

}