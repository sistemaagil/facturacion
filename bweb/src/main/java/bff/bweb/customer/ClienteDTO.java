package bff.bweb.customer;

import java.sql.Date;

import lombok.Data;

@Data
public class ClienteDTO {
    
    private Long id;
    private String razon_social;
    private String nro_identificacion;
    private Date fecha_nacimiento;
    private double monto_primera_compra;

}
