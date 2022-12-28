package bff.bweb.authz;

import java.util.List;

import bff.bweb.authz.role.RoleDTO;
import lombok.Data;

@Data
public class UserDTO {

    private long id;
    private String password;
    private String name;
    private String username;
    private boolean looked = false;
    private boolean expired = false;
    private boolean enabled = true;
    private List<RoleDTO> roles;
    
}
