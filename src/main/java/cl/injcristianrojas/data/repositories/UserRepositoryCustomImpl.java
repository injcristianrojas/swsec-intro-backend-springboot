package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.User;
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
    public List<User> getUsersByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("from User where username = '" + username + "'", User.class);
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByType(Long type) {
        TypedQuery<User> query = entityManager.createQuery("from User where role.id = "+ type, User.class);
        return query.getResultList();
    }
}
