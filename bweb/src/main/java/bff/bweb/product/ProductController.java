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

    @GetMapping("/")
    public List<ProductDTO> findAll() {
        return client.findAll();
    }
    
    @GetMapping("/{id}/")
    public ProductDTO findById(@PathVariable Long id){
        return client.findProductById(id);
    }

    @PostMapping("/")
    public ProductDTO save(@RequestBody ProductDTO entity){
        return client.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        client.deleteById(id);
    }

    @PutMapping("/{id}/")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO entity){
        return client.update(id, entity);
    }
    
    @PatchMapping("/{id}/")
    public ProductDTO partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields){

        ProductDTO ProductDTO = client.findProductById(id);

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
        return client.update(id, ProductDTO);
    }
}
