package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.UserDisplay;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserDisplayRepositoryImpl implements UserDisplayRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<UserDisplay> findUsers() {
    Query q = entityManager.createQuery("SELECT u.username FROM UserJPA u");
    return q.getResultList();
  }

}
