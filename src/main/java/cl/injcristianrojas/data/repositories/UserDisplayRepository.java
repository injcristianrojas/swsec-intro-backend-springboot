package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.UserDisplay;

@Repository
public interface UserDisplayRepository {
  List<UserDisplay> findUsers();
}
