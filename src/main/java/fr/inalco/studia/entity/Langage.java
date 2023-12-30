package fr.inalco.studia.entity;

public enum Langage {
	CHINOIS("Chinois"),
	FRANCAIS("Français"),
	ANGLAIS("Anglais"),
	ESPAGNOL("Espagnol");
	
	private String string;

	Langage(String string)
	{
		this.string = string;
	}
	
	public String getLangage()
	{
		return string;
	}
	
	//public String getValue
}