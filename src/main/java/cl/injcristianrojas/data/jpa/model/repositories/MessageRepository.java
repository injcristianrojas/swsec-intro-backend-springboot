package cl.injcristianrojas.data.jpa.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.injcristianrojas.data.jpa.model.MessageJPA;

@Repository
public interface MessageRepository extends JpaRepository<MessageJPA, Long> {
  List<MessageJPA> findAll();
  <S extends MessageJPA> S save(S s);
}
