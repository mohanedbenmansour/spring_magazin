package tn.esprit.spring.repository;

import javax.management.relation.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer> {
	Role findByRole(String role);
}
