package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.UserJPA;

@Repository
public interface UserRepositoryCustom {
  List<UserJPA> getUsersByUsername(String username);
  List<UserJPA> getUsersByType(Long type);
}
