package fr.inalco.studia.gui.modules;

import fr.inalco.studia.entity.Langage;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

public class LangageSelectionToggle extends HBox {

	private ToggleGroup group = new ToggleGroup();
	
	//private Langage selected = Langage.ANGLAIS;
	
	public LangageSelectionToggle(Langage langage)
	{
		setSpacing(10);
		setPadding(new Insets(10));
		for (Langage l: Langage.values())
		{
			RadioButton rb = new RadioButton(l.getLangage().toString());
			rb.setToggleGroup(group);
			getChildren().add(rb);
			if (l == langage)
			{
				rb.setSelected(true);
			}
		}
	}
	
	private Toggle getSelected()
	{
		Toggle l = group.getSelectedToggle();
		return l;
	}
	
	private int getSelectedIndex()
	{
		Toggle selected = this.getSelected();
		int index = selected.getToggleGroup().getToggles().indexOf(selected);
		return index;
	}
	
	public Langage getSelectedLangage()
	{
		return Langage.values()[getSelectedIndex()];
		//return this.selected;
	}

}
