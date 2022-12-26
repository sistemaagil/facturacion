package security.api_authz.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/api/product")
public class ProductController {
    
    @PreAuthorize("hasAuthority('Product_Read')")
    @GetMapping("/{id}/")
    public ResponseEntity<String> findById(@PathVariable long id){
        return ResponseEntity.ok("ok");
    }

    @PreAuthorize("hasAuthority('Product_Read')")
    @GetMapping("/")
    public ResponseEntity<String> findAll(){
        return ResponseEntity.ok("ok");
    }
    
    @PreAuthorize("hasAuthority('Product_Write')")
    @PostMapping("/")
    public ResponseEntity<String> save(){
        return ResponseEntity.ok("ok");
    }

    @PreAuthorize("hasAuthority('Product_Write')")
    @PutMapping("/")
    public ResponseEntity<String> update(){
        return ResponseEntity.ok("ok");
    }

    @PreAuthorize("hasAuthority('Product_Delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return ResponseEntity.ok("ok");
    }
}
