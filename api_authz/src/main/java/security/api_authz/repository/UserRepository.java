package security.api_authz.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import security.api_authz.entity.User;


public interface UserRepository extends CrudRepository <User, Long>{
    
    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
    
}