package security.api_authz.entity;

import lombok.Data;

@Data
public class UserAuthority {
    
    private String username;
    private String authority;
}
