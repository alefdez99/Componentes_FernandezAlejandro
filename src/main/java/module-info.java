module es.ieslosmontecillos.componentes_fernandezalejandro {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.componentes_fernandezalejandro to javafx.fxml;
    exports es.ieslosmontecillos.componentes_fernandezalejandro;
}