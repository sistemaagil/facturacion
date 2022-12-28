package bff.bweb.factura;


import java.math.BigDecimal;
import lombok.Data;

@Data
public class FacturaLineaDTO {

    private Long id;

    private ProductoDTO producto;
    private BigDecimal cantidad;
    private BigDecimal precio;

}
