package facturacion.api_factura.factura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/factura")
@CrossOrigin({"*"})
public class FacturaController {
    @Autowired FacturaService facturaService;

    @GetMapping("/")
    public List<Factura> findAll(){
        return facturaService.findAll();
    }

    @GetMapping("/{id}/")
    public Factura findById(@PathVariable Long id){
        return facturaService.findById(id);
    }

    @PostMapping("/")
    public Factura save(@RequestBody Factura entity){
        return facturaService.save(entity);
    }

    @PutMapping("/{id}/")
    public Factura update(@RequestBody Factura entity){
        return facturaService.save(entity);
    }

    @DeleteMapping("/{id}/")
    public void deleteById(@PathVariable Long id){
        facturaService.deleteById(id);
    }
}