package cl.injcristianrojas.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.UserJPA;

@Repository
public interface UserRepository extends JpaRepository<UserJPA, Long>, UserRepositoryCustom {
    UserJPA findByUsername(String username);
}
