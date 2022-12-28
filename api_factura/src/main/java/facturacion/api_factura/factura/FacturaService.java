package facturacion.api_factura.factura;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class FacturaService {
    @Autowired FacturaRepository facturaRepository;

    @Transactional
    public Factura save(Factura entity){
        return facturaRepository.save(entity);
    }

    public Factura findById( Long id){
        return facturaRepository.findById(id).orElse(new Factura());
    }

    public void deleteById(Long id){
        facturaRepository.deleteById(id);
    }

    public List<Factura> findAll(){
        return facturaRepository.findAll();
    }
}