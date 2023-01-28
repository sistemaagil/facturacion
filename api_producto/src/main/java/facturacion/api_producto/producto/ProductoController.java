package facturacion.api_producto.producto;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("api/product")
@CrossOrigin({"*"})
/**
* Controlador del producto
*/
public class ProductoController {
    @Autowired ProductoService productoService;


    @GetMapping(value= "", params={"page","size","sort"})
    public List<Producto> findAll(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(defaultValue = "id") String sort){
        return productoService.findAllWithPageAndSizeAndSort(page, size, sort);
    }

    @GetMapping("/")
    public List<Producto> findAll(){
        return productoService.findAll();
    }

    @GetMapping("/count/")
    public Long count(){
        return productoService.count();
    }

    @Operation(summary = "Entrega un producto por su ID")
    @GetMapping("/{id}/")
    public Producto findById(@PathVariable long id){
        return productoService.findById(id);
    }

    @PostMapping("/")
    public Producto save(@RequestBody Producto entity){
        return productoService.save(entity);
    }

    @Operation(summary = "Actualización de un producto por su ID")
    @PutMapping("/{id}/")
    public Producto update(@RequestBody Producto entity){
        return productoService.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        productoService.deleteById(id);
    }

    @PatchMapping("/{id}/")
    public ResponseEntity<Producto> patch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Producto product = productoService.findById(id);
  
        // itera sobre los campos que se desean actualizar
        for (Map.Entry<String, Object> field : fields.entrySet()) {
            String fieldName = field.getKey();
            Object fieldValue = field.getValue();
            
            // utiliza reflection para establecer el valor del campo en la entidad
            try {
                Field campoEntidad = Producto.class.getDeclaredField(fieldName);
                campoEntidad.setAccessible(true);
                campoEntidad.set(product, fieldValue);
            } catch (NoSuchFieldException | IllegalAccessException ex) {
                // maneja la excepción si ocurre algún error al acceder al campo
            }
        }
    
        // actualiza la entidad
        Producto entidadActualizada = productoService.save(product);
        return ResponseEntity.ok(entidadActualizada);
    }


}
