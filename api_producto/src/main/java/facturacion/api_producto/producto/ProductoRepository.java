package facturacion.api_producto.producto;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    public List<Producto> findAll();
    Page<Producto> findAll(Pageable pageable);
    
}
