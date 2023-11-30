package fr.inalco.Studia.entity.exercices;

import fr.inalco.Studia.entity.Langage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 * Superclasse de tous les exercices. Ne pas utiliser.
 */
@Entity
@Table(name="exercice")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Exercice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="exercice_id")
	private Long id;
	
	@Column(name="niveau")
	private byte niveau; // 0-128
	
	@Column(name="language")
	private Langage langage;
	
	@Column(name="consigne")
	private String consigne;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte getNiveau() {
		return niveau;
	}

	public void setNiveau(byte niveau) {
		this.niveau = niveau;
	}

	public Langage getLangage() {
		return langage;
	}

	public void setLangage(Langage langage) {
		this.langage = langage;
	}

	public String getConsigne() {
		return consigne;
	}

	public void setConsigne(String consigne) {
		this.consigne = consigne;
	}
	
}
