package EjerciciosEnClase.EjercicioDragon;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Aplicacion extends Application {

    private int filaActual = 1; 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registro de Personajes DBZ");

        

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.setPadding(new Insets(20));

        TextField txtNombre = new TextField();
        ComboBox<String> cbRaza = new ComboBox<>();
        cbRaza.getItems().addAll("Saiyajin", "Humano", "Namekiano", "Androide", "Majin", "Freezer Race");
        TextField txtPoder = new TextField();
        TextField txtPlaneta = new TextField();
        TextField txtTecnica = new TextField();
        TextField txtEdad = new TextField();
        Button btnAgregar = new Button("Agregar Personaje");

        formulario.addRow(0, new Label("Nombre:"), txtNombre);
        formulario.addRow(1, new Label("Raza:"), cbRaza);
        formulario.addRow(2, new Label("Nivel de Poder:"), txtPoder);
        formulario.addRow(3, new Label("Planeta:"), txtPlaneta);
        formulario.addRow(4, new Label("Tecnica:"), txtTecnica);
        formulario.addRow(5, new Label("Edad:"), txtEdad);
        formulario.add(btnAgregar, 1, 6);

      
        GridPane gridLista = new GridPane();
        gridLista.setHgap(20);
        gridLista.setVgap(10);
        gridLista.setPadding(new Insets(20));
        gridLista.setStyle("-border-color: black; -border-width: 1; -border-style: solid;");

       
        String[] encabezados = {"Nombre", "Raza", "Poder", "Planeta", "Tecnica", "Edad"};
        for (int i = 0; i < encabezados.length; i++) {
            Label label = new Label(encabezados[i]);
            label.setStyle("-font-weight: bold; -font-size: 14px;");
            gridLista.add(label, i, 0);
        }

        
        btnAgregar.setOnAction(e -> {
            try {
                
                if (txtNombre.getText().isEmpty() || cbRaza.getValue() == null || 
                    txtPlaneta.getText().isEmpty() || txtTecnica.getText().isEmpty()) {
                    mostrarAlerta("Error", "Todos los campos son obligatorios.");
                    return;
                }

                int poder = Integer.parseInt(txtPoder.getText());
                int edad = Integer.parseInt(txtEdad.getText());

                if (poder <= 0 || edad <= 0) {
                    mostrarAlerta("Error", "El poder y la edad deben ser mayores a 0.");
                    return;
                }

               
                Personaje p = new Personaje(
                    txtNombre.getText(),
                    cbRaza.getValue(),
                    poder,
                    txtPlaneta.getText(),
                    txtTecnica.getText(),
                    edad
                );

                
                gridLista.add(new Label(p.getNombre()), 0, filaActual);
                gridLista.add(new Label(p.getRaza()), 1, filaActual);
                gridLista.add(new Label(String.valueOf(p.getNivelPoder())), 2, filaActual);
                gridLista.add(new Label(p.getPlanetaOrigen()), 3, filaActual);
                gridLista.add(new Label(p.getTecnicaEspecial()), 4, filaActual);
                gridLista.add(new Label(String.valueOf(p.getEdad())), 5, filaActual);

                filaActual++;

                
                txtNombre.clear();
                txtPoder.clear();
                txtPlaneta.clear();
                txtTecnica.clear();
                txtEdad.clear();
                cbRaza.getSelectionModel().clearSelection();

            } catch (NumberFormatException ex) {
                mostrarAlerta("Error de Formato", "Poder y Edad deben ser numeros enteros.");
            }
        });

        
        VBox root = new VBox(15);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
            new Label("REGISTRO DE PERSONAJES DRAGON BALL Z"),
            formulario,
            new Separator(),
            new Label("PERSONAJES REGISTRADOS"),
            gridLista
        );

        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}