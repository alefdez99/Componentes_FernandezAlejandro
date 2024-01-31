package es.ieslosmontecillos.componentes_fernandezalejandro;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class Temporizador extends Label
{
    // Atributos de la clase Temporizador
    private static IntegerProperty time;
    private EventHandler<ActionEvent> onFinished;
    private static Timeline timeline;


    // Constructor
    public Temporizador()
    {
        time = new SimpleIntegerProperty();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try
        {
            fxmlLoader.load();
        }
        catch (IOException exception)
        {
            throw new RuntimeException(exception);
        }

        System.out.println("Duración del temporizador: " + time.get());
        time.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number previousNumber, Number newNumber)
            {
                Temporizador.this.setText("|" + newNumber + " segundos restantes");
            }
        });



        /*
        timeline.setOnFinished(e -> {
           timeline.stop();
           //timeline.playFromStart();
            setText("Tiempo acabado");
            System.out.println("Tiempo acabado");
        });

         */
    }

    public void start()
    {
        timeline = new Timeline();
        timeline.setAutoReverse(false);

        //System.out.println("Duración del temporizador (antes de KeyValue y KeyFrame): " + time.get());
        final KeyValue kv = new KeyValue(time, 0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(time.get()), onFinished,kv);

        /*
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Temporizador.this.setText("Tiempo acabado");
                //System.out.println("Tiempo acabado");

                // Disparamos el evento personalizado cuando lo cuenta haya terminado
                //fireEvent(new ActionEvent());
            }
        });

         */

        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    // Getter  y Setters
    public int getTime() {return time.get();}
    public IntegerProperty timeProperty() {return time;}
    public void setTime(int time) {this.time.set(time);}

    public EventHandler<ActionEvent> getOnFinished() {return onFinishedProperty().get();}
    public final ObjectProperty<EventHandler<ActionEvent>> onFinishedProperty() {return (ObjectProperty<EventHandler<ActionEvent>>) onFinished;}

    public void setOnFinished(EventHandler<ActionEvent> onFinished) {this.onFinished = onFinished;}
}
