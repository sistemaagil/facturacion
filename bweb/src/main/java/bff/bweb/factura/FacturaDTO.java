package bff.bweb.factura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class FacturaDTO {
    private Long id;
    private String numeroFactura;
    private LocalDate fecha;
    private Long clienteId;
    private double total;
    private List<FacturaLineaDTO> lineas = new ArrayList<>();
}
