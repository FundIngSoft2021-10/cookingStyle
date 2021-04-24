package presentacion.perfiles;

import entidades.dto.DTOAgendaChef;
import entidades.dto.DTOSesion;
import entidades.modelo.Bloque;
import entidades.modelo.Chef;
import entidades.modelo.Dia;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logica_negocio.citas.ControladorCitas;
import logica_negocio.citas.IControladorCitas;
import presentacion.IControladorPantalla;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorChGUI011 implements IControladorPantalla {
    private IControladorCitas controlCitas;
    private DTOSesion sesion;
    private DTOAgendaChef agenda;
    private int horaInicial = 6;

    @FXML
    public GridPane gridAgenda;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void inicializar(DTOSesion sesion) {
        this.controlCitas = new ControladorCitas();
        this.sesion = sesion;

        this.agenda = this.controlCitas.consultarAgendaChef((Chef) this.sesion.getUsuario());
        if (!this.agenda.isEstado())
            this.crearAlerta(Alert.AlertType.ERROR, this.agenda.getMensaje());
        else
            this.crearAgenda();
    }

    private int traducirHora(int hora) {
        return hora - this.horaInicial;
    }

    private int traducirDia(Dia dia) {
        return dia.getValor() - 1;
    }

    private void crearAgenda() {
        List<Bloque> bloques = this.agenda.getCalendario().getBloques();

        for (Bloque bloque : bloques) {
            int fila = this.traducirHora(bloque.getHora());
            int col = this.traducirDia(bloque.getDia());

            Pane bloqueFX = new Pane();
            bloqueFX.setPrefSize(200, 200);
            bloqueFX.setStyle("-fx-background-color: green");

            this.gridAgenda.setConstraints(bloqueFX, col, fila);
            this.gridAgenda.getChildren().add(bloqueFX);
        }
    }

    @FXML
    public void clickServicioCliente(MouseEvent mouseEvent) {
        try {
            this.irServicioCliente((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clickVolver(MouseEvent mouseEvent) {
        try {
            this.volverPantalla((Event) mouseEvent, this.sesion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
