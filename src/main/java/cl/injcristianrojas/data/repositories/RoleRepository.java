package cl.injcristianrojas.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRolename(String rolename);
}
