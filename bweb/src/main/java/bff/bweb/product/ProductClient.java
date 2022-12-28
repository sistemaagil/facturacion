package bff.bweb.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "bff.product", url = "http://localhost:8082/api/product")
public interface ProductClient {

    @GetMapping("/{id}/")
    ProductDTO findProductById(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id);

    @GetMapping("/")
    List<ProductDTO> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/") 
    ProductDTO save(@RequestHeader("Authorization") String authorizationHeader, ProductDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    ProductDTO update(@RequestHeader("Authorization") String authorizationHeader, @PathVariable("id")  Long id, ProductDTO entity);

}