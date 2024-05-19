package cl.injcristianrojas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import cl.injcristianrojas.data.model.Message;
import cl.injcristianrojas.data.model.Role;
import cl.injcristianrojas.data.model.UserJPA;
import cl.injcristianrojas.data.repositories.MessageRepository;
import cl.injcristianrojas.data.repositories.RoleRepository;
import cl.injcristianrojas.data.repositories.UserRepository;
import jakarta.transaction.Transactional;

@Component
public class InitialDataProvisioner implements ApplicationListener<ContextRefreshedEvent> {

  private boolean alreadySetup = false;
  private static final String ROLE_ADMIN = "ROLE_ADMIN";
	private static final String ROLE_USER = "ROLE_USER";

  @Autowired
	private MessageRepository messageRepository;
  @Autowired
	private RoleRepository roleRepository;
  @Autowired
	private PasswordEncoder passwordEncoder;
  @Autowired
	private UserRepository userRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    if (alreadySetup)
			return;
    createRoleIfNotFound(ROLE_ADMIN);
		createRoleIfNotFound(ROLE_USER);

    createUser("zorzal", "fio", ROLE_USER);
    createUser("admin", "123", ROLE_ADMIN);
    createUser("chincol", "fiofio", ROLE_USER);
    createUser("tiuque", "pah", ROLE_USER);
    createUser("loica", "roji", ROLE_USER);

    createPost("Bienvenidos a Fans de las Aves Chilenas. Soy el administrador.");
  }

  private void createUser(String username, String password, String rolename) {
    UserJPA user = new UserJPA();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(roleRepository.findByRolename(rolename));
		userRepository.save(user);
		alreadySetup = true;
  }

  @Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByRolename(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}

  private void createPost(String message) {
		Message post = new Message();
		post.setMessage(message);
		messageRepository.save(post);
	}

}