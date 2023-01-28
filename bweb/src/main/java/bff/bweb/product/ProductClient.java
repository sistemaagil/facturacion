package bff.bweb.product;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "bff.product", url = "http://localhost:8082/api/product")
public interface ProductClient {

    @GetMapping("/{id}/")
    ProductDTO findProductById(@PathVariable("id") Long id);

    @GetMapping("/")
    List<ProductDTO> findAll();

    @PostMapping("/") 
    ProductDTO save(ProductDTO entity);

    @DeleteMapping("/{id}/")
    void deleteById(@PathVariable("id")  Long id);

    @PutMapping("/{id}/")
    ProductDTO update(@PathVariable("id")  Long id, ProductDTO entity);

}