package es.ieslosmontecillos.componentes_fernandezalejandro;

import javafx.animation.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;

public class Temporizador extends Label
{
    // Atributos de la clase Temporizador
    private IntegerProperty time = new SimpleIntegerProperty();
    private EventHandler<ActionEvent> onFinished;

    // Getter  y Setters
    public int getTime() {return time.get();}
    public IntegerProperty timeProperty() {return time;}
    public void setTime(int time) {this.time.set(time);}

    public EventHandler<ActionEvent> getOnFinished() {return onFinished;}

    public void setOnFinished(EventHandler<ActionEvent> onFinished) {this.onFinished = onFinished;}

    // Constructor
    public Temporizador()
    {
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

        final Timeline timeline = new Timeline();
        //timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

        System.out.println("Duración del temporizador: " + time.get());

        final KeyValue kv = new KeyValue(time, 0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(time.get()), kv);

        setText(time.get() + " segundos restantes");

        timeline.getKeyFrames().add(kf);
        timeline.play();

        timeline.setOnFinished(e -> {
           timeline.stop();
           //timeline.playFromStart();
            setText("Tiempo acabado");
            System.out.println("Tiempo acabado");

        });

        //textProperty().bind(time.asString(" %d segundos "));

        /*
        time.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1)
            {
                //textProperty().bind(t1.intValue(" %d segundos "));
                setText(time.get() + " segundos restantes");
                System.out.println("Segundos restantes: " + time.get());
                System.out.println(number.intValue() + " ->  number");
                System.out.println(t1.intValue() + " ->  t1");
            }
        });

         */

        //int currentTime = getTime();

        //this.setText(currentTime + " segundos restantes");


        // Evento de finalización




        /*
        // Calcular horas, munutos y segundos a partir del tiempo total en segundos
        IntegerProperty horas = new SimpleIntegerProperty();
        IntegerProperty minutos = new SimpleIntegerProperty();
        IntegerProperty segundos = new SimpleIntegerProperty();

        horas.bind(time.divide(3600));
        minutos.bind(time.divide(60).subtract(horas.multiply(60)));
        segundos.bind(time.subtract(horas.multiply(3600)).subtract(minutos.multiply(60)));

        textProperty().bind(horas.asString(" %d horas ").concat(minutos.asString(" %d minutos ")).concat(segundos.asString(" %d segundos")));

         */
    }
}
