package bff.bweb.authz;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("api/authority")
@CrossOrigin({"*"})
public class AuthorityController {

    @Autowired AuthorityClient client;

    @GetMapping("/")
    public List<AuthorityDTO> findAll(@RequestHeader("Authorization") String authHeader) {
        return client.findAll(authHeader);
    }
    
    @GetMapping("/{id}/")
    public AuthorityDTO findById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        return client.findAuthorityById(authHeader, id);
    }

    @PostMapping("/")
    public AuthorityDTO save(@RequestHeader("Authorization") String authHeader, @RequestBody AuthorityDTO entity){
        return client.save(authHeader, entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@RequestHeader("Authorization") String authHeader, @PathVariable Long id){
        client.deleteById(authHeader, id);
    }

    @PutMapping("/{id}/")
    public AuthorityDTO update(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody AuthorityDTO entity){
        return client.update(authHeader, id, entity);
    }
    
    @PatchMapping("/{id}/")
    public AuthorityDTO partialUpdate(@RequestHeader("Authorization") String authHeader, @PathVariable Long id, @RequestBody Map<String, Object> fields){

        AuthorityDTO AuthorityDTO = client.findAuthorityById(authHeader, id);

        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = AuthorityDTO.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(AuthorityDTO, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
        return client.update(authHeader, id, AuthorityDTO);
    }
}