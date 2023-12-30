package fr.inalco.studia.gui.modules;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PasswordEditorHBox extends HBox {
	
	private PasswordField pf = new PasswordField();
	private TextField tf = new TextField("");

	public PasswordEditorHBox()
	{
		setSpacing(10);
		setPadding(new Insets(10));
		
		getChildren().add(pf);
		tf.setVisible(false);
		
		CheckBox cb = new CheckBox("Montrer");
		cb.setOnAction(e -> {
			if (pf.isVisible())
			{
				tf.setText(pf.getText());
				getChildren().set(0, tf);
			}
			else
			{
				pf.setText(tf.getText());
				getChildren().set(0, pf);
			}
			pf.setVisible(!pf.isVisible());
			tf.setVisible(!tf.isVisible());
		});
		
		getChildren().addAll(cb);
	}
	
	public String getPassword()
	{
		if (pf.isVisible())
			return pf.getText();
		else
			return tf.getText();
	}
}
