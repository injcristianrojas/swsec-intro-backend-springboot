package cl.injcristianrojas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cl.injcristianrojas.data.model.MessageJPA;
import cl.injcristianrojas.data.repositories.MessageRepository;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadySetup = false;

  @Autowired
	private MessageRepository messageRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadySetup)
			return;
    createPost("Bienvenidos a Fans de las Aves Chilenas. Soy el administrador.");
  }

  private void createPost(String message) {
		MessageJPA post = new MessageJPA();
		post.setMessage(message);
		messageRepository.save(post);
	}

}