package security.api_authz.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import security.api_authz.entity.Authority;



public interface AuthorityRepository extends CrudRepository<Authority, Long> {
 
    List<Authority> findAll();

    @Query(
    value = "select a.* from auth.authorities a " +
    "join auth.roles_authorities ra on (a.id = ra.authority_id) "+
    "where ra.role_id = ?1",
    nativeQuery = true
    )
    List<Authority> findByRoleId(long rolid);

    List<Authority> findByNameIgnoreCaseLike(String term);

}

