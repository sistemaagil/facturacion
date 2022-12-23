package facturacion.bff.authz;

import lombok.Data;

@Data
public class User {

    private long id;
    private String password;
    private String name;
    private String username;
    private boolean looked = false;
    private boolean expired = false;
    private boolean enabled = true;
    
}
