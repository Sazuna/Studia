package fr.inalco.Studia.entity.reponses;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="reponse_a_cocher")
public class ReponseACocher extends Reponse {
	
	@Column(name="reponse")
	private Boolean reponse;
	
	@Column(name="reponseString")
	private String reponseString;

	public ReponseACocher(Boolean reponse, String reponseString) {
		super();
		this.reponse = reponse;
		this.reponseString = reponseString;
	}

	public String getReponseString() {
		return reponseString;
	}

	public void setReponseString(String reponseString) {
		this.reponseString = reponseString;
	}

	public void setReponse(Boolean reponse) {
		this.reponse = reponse;
	}

	public Boolean getReponse()
	{
		return reponse;
	}
	
	public boolean correcte(Object reponse)
	{
		return reponse.equals(this.reponse);
	}
}
