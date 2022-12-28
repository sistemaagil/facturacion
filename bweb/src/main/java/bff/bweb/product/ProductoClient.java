package bff.bweb.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "producto", url = "http://localhost:8000/api/product")
public interface ProductoClient {

    @GetMapping("/{id}/")
    ProductoDTO findProductoById(@RequestHeader("Authorization") String authHeader, @PathVariable("id") Long id);

    @GetMapping("/")
    List<ProductoDTO> findAll(@RequestHeader("Authorization") String authorizationHeader);

    @PostMapping("/") 
    ProductoDTO save(@RequestHeader("Authorization") String authorizationHeader, ProductoDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable("id")  Long id);
}
