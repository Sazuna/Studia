package fr.inalco.studia.entity.reponses;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/*
@Entity
@Table(name="Reponse")
@Inheritance(strategy = InheritanceType.JOINED)
*/

@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Crée une table par entité héritant de la classe
public abstract class Reponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reponse_id")
	private Long id;

	public Long getId() {
		return id;
	}

	public abstract boolean correcte(Object reponse);
}
