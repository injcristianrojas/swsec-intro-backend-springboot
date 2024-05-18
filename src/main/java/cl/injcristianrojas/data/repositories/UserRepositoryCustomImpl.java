package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.UserJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
  @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserJPA> getUsersByUsername(String username) {
        TypedQuery<UserJPA> query = entityManager.createQuery("from UserJPA where username = '" + username + "'", UserJPA.class);
        return query.getResultList();
    }

    @Override
    public List<UserJPA> getUsersByType(Long type) {
        TypedQuery<UserJPA> query = entityManager.createQuery("from UserJPA where role_id = '" + type + "'", UserJPA.class);
        return query.getResultList();
    }
}
