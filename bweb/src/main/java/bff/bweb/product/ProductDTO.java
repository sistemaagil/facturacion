package bff.bweb.product;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String nombre;
    private String codigo;
    private double precio;
    
}
