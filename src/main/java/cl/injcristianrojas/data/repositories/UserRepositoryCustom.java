package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.User;

@Repository
public interface UserRepositoryCustom {
  List<User> getUsersByUsername(String username);
  List<User> getUsersByType(Long type);
}
