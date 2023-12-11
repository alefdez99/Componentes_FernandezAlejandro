package es.ieslosmontecillos.componentes_fernandezalejandro;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class CampoTextoBoton extends HBox
{
    @javafx.fxml.FXML
    private TextField tfControl;
    @javafx.fxml.FXML
    private Button btControl;


    // Constructor
    public CampoTextoBoton() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("campotextoboton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return tfControl.textProperty();
    }

    @javafx.fxml.FXML
    public void doSomething(ActionEvent actionEvent) {
        System.out.println("The button was clicked!");
    }

    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>()
    {
        @Override
        protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }

        @Override
        public Object getBean() {
            return CampoTextoBoton.this;
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty(){return onAction;}
    public final void setOnAction(EventHandler<ActionEvent> handler){onActionProperty().set(handler);}
    public final EventHandler<ActionEvent> getOnAction(){return onActionProperty().get();}
}
