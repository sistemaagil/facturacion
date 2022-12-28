package bff.bweb.product;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bff.bweb.authz.UserClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/product")
@CrossOrigin({"*"})
public class ProductController {

    @Autowired ProductClient client;
    @Autowired UserClient userClient;

    @GetMapping("/")
    public List<ProductDTO> findAll(@RequestHeader("Authorization") String authHeader) {
        return client.findAll(authHeader);
    }
    
    @GetMapping("/{id}/")
    public ProductDTO findById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        return client.findProductById(authHeader, id);
    }

    @PostMapping("/")
    public ProductDTO save(@RequestHeader("Authorization") String authHeader, @RequestBody ProductDTO entity){
        return client.save(authHeader, entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        client.deleteById(authHeader, id);
    }

    @PutMapping("/{id}/")
    public ProductDTO update(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody ProductDTO entity){
        return client.update(authHeader, id, entity);
    }
    
    @PatchMapping("/{id}/")
    public ProductDTO partialUpdate(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody Map<String, Object> fields){

        ProductDTO ProductDTO = client.findProductById(authHeader, id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = ProductDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(ProductDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return client.update(authHeader, id, ProductDTO);
    }
}
