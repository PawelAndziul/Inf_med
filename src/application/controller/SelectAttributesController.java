package application.controller;

import java.util.Enumeration;

import javax.swing.JOptionPane;

import application.Context;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import weka.core.Attribute;
import weka.core.Instances;

public class SelectAttributesController {

    @FXML
    private Button buttonAddAttribute;

    @FXML
    private Button buttonDeleteAttribute;

    @FXML
    private TextArea textAttributes;

	@FXML
	private void initialize()
	{
	    textAttributes.setEditable(false);
	    showAttributes();
	}

	public SelectAttributesController()	{}

	private void showAttributes()
    {
        try
        {
            Instances instances = Context.getLoadedInstance();

            String text = "";
            Enumeration<Attribute> attributes = instances.enumerateAttributes();
            while (attributes.hasMoreElements() == true)
            {
                Attribute attribute = attributes.nextElement();
                text += attribute.name() + "\n";
            }

            textAttributes.setText(text);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Najpierw zaladuj plik z danymi.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	@FXML
	private void buttonAddAttributeClicked()
	{

	}

	@FXML
    private void buttonDeleteAttributeClicked()
    {

    }
}
