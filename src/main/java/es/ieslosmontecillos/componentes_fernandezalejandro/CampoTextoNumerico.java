package es.ieslosmontecillos.componentes_fernandezalejandro;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CampoTextoNumerico extends TextField
{
    public CampoTextoNumerico()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("campotextonumerico.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    // Override replaceText and replaceSelection methods
    @Override
    public void replaceText(int start, int end, String text)
    {
        if (!text.matches("[a-z, A-Z]"))
            super.replaceText(start, end, text);
        else
            this.setText("Enter a numeric value");
    }

    @Override
    public void replaceSelection(String text)
    {
        if (!text.matches("[a-z, A-Z]"))
            super.replaceSelection(text);
        else
            this.setText("Enter a numeric value");
    }
}
