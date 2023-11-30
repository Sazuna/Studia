package fr.inalco.Studia.entity.reponses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name="Reponse")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Reponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reponse_id")
	private Long id;

	public abstract boolean correcte(Object reponse);
}
