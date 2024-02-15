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

/**
 * Esta clase es un temporizador gráfico que realiza una cuenta atrás con las siguientes características:
 * <ul>
 *     <li>Se basa en una etiqueta que dispone de una propiedad llamada time, de tipo int, que representa los segundos que van a transcurrir desde su creación hasta que llega a cero.</li>
 *     <li>Cada segundo disminuye en uno el valor de tiempo, que visualizamos en el texto de la etiqueta.</li>
 *     <li>Al finalizar la cuenta atrás se lanza un evento de finalización de cuenta que puede ser recogido por la aplicación en la que se incluya el componente.</li>
 * </ul>
 *
 *
 * @author Alejandro Fernández Barrionuevo
 * @version 1.2
 * @since 1.2
 *
 *
 */

public class Temporizador extends Label
{
    // Atributos de la clase Temporizador
    /**
     * Indica el tiempo en segundos para el temporizador.
     *
     */
    private static IntegerProperty time;

    /**
     * onFinished es el manejador para el evento de la finalización del temporizador.
     */
    private EventHandler<ActionEvent> onFinished;

    /**
     * Timeline timeline será el objeto encargado de manejar el segmento del tiempo.
     */
    private static Timeline timeline;


    /**
     * Carga la vista y establece un formato y un escuchador para la propiedad time.
     *
     */

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
    }

    /**
     * Inicializa el temporizador según el valor del atributo time.
     */
    public void start()
    {
        timeline = new Timeline();
        timeline.setAutoReverse(false);

        //System.out.println("Duración del temporizador (antes de KeyValue y KeyFrame): " + time.get());
        final KeyValue kv = new KeyValue(time, 0);
        final KeyFrame kf = new KeyFrame(Duration.seconds(time.get()), onFinished,kv);


        timeline.getKeyFrames().add(kf);
        timeline.play();
    }

    // Getters  y Setters
    /**
     * Devuelve el número actual que gestiona el temporizador.
     * @return Número entero que representa el tiempo en segundos.
     */
    public int getTime() {return time.get();}
    public IntegerProperty timeProperty() {return time;}

    /**
     * Establece el valor para la propiedad time
     * @param time Valor para la propiedad time
     *
     */
    public void setTime(int time) {this.time.set(time);}

    public EventHandler<ActionEvent> getOnFinished() {return onFinishedProperty().get();}

    /**
     * Devuelve el objeto manejador del evento de finalización.
     * @return Objeto manejador.
     */
    public final ObjectProperty<EventHandler<ActionEvent>> onFinishedProperty() {return (ObjectProperty<EventHandler<ActionEvent>>) onFinished;}

    /**
     * Establece el valor para la propiedad onFinished.
     * @param onFinished Valor para la propiedad onFinished.
     *
     */
    public void setOnFinished(EventHandler<ActionEvent> onFinished) {this.onFinished = onFinished;}
}