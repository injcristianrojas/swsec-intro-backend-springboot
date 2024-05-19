package cl.injcristianrojas.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rolename;

	public String getName() {
		return rolename;
	
	}
	public String toString() {
		return rolename;
	}

	public void setName(String name) {
		this.rolename = name;
	}
}
