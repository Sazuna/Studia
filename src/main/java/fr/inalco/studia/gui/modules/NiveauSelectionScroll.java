package fr.inalco.studia.gui.modules;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollBar;
import javafx.scene.text.Text;

public class NiveauSelectionScroll extends ScrollBar {
	
	//private int niveau;
	
	private Text niveauTxt;
	
	private int niveau;

	public NiveauSelectionScroll(int niveau)
	{
		if (niveau == 0)
		{
			niveau = 1;
		}
		this.niveauTxt = new Text(""+niveau);
		this.setMin(1);
		this.setMax(8);
		this.setValue(niveau);
		this.setUnitIncrement(1);
		this.setBlockIncrement(1);
		this.setWidth(200);
		this.setMaxWidth(200);
		//this.setContextMenu(new ContextMenu(new MenuItem(""+niveau)));
		this.valueProperty().addListener((ObservableValue<? extends Number> ov, 
	            Number oldVal, Number newVal) -> {
	                //this.niveau = newVal.intValue();
	            	this.niveau = newVal.intValue();
	                this.niveauTxt.setText(""+this.niveau);
	        });          
	}
	
	public Text getNiveauTxt()
	{
		return this.niveauTxt;
	}
	
	public int getNiveau()
	{
		return this.niveau;
	}

}
