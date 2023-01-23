package facturacion.api_factura.factura;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Long>{

    List<Producto> findAll();

}