package facturacion.bff.authz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin({"*"})
public class UserController {

    @Autowired UserClient userClient;

    @GetMapping("/")
    public List<User> findAll(@RequestHeader("Authorization") String authorizationHeader){
        return userClient.findAll(authorizationHeader);
    }
    
}
