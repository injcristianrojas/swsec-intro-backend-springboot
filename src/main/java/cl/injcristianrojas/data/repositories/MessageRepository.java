package cl.injcristianrojas.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
  List<Message> findAll();
  <S extends Message> S save(S s);
}
