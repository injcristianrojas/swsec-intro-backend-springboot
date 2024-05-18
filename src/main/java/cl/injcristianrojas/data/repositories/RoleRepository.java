package cl.injcristianrojas.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.RoleJPA;


@Repository
public interface RoleRepository extends JpaRepository<RoleJPA, Long> {
	RoleJPA findByRolename(String rolename);
}
