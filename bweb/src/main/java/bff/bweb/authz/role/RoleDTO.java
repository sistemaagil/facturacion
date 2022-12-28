package bff.bweb.authz.role;

import java.util.List;

import bff.bweb.authz.AuthorityDTO;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name;
    private boolean enabled;
    private List<AuthorityDTO> authorities;
}
