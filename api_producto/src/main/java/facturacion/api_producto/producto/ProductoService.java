package facturacion.api_producto.producto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired ProductoRepository productoRepository;

    public Producto save(Producto entity){
        return productoRepository.save(entity);
    }

    public Producto findById( Long id){
        return productoRepository.findById(id).orElse(new Producto());
    }

    public void deleteById(Long id){
        productoRepository.deleteById(id);
    }

    public List<Producto> findAll(){
        return productoRepository.findAll();
    }
}
