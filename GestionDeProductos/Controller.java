package EjerciciosEnClase.GestionDeProductos;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TableView<Producto> tabla;

    @FXML
    private TableColumn<Producto,String> colNombre;

    @FXML
    private TableColumn<Producto,Double> colPrecio;

    @FXML
    private TableColumn<Producto,Integer> colCantidad;

    @FXML
    private Label lblError;

    ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize(){

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tabla.setItems(productos);

        productos.add(new Producto("Laptop",850.00,5));
        productos.add(new Producto("Mouse",12.50,30));

        //formato de precio
        colPrecio.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double valor, boolean empty) {
                super.updateItem(valor, empty);
                setText(empty || valor == null ? null : String.format("%.2f", valor));
            }
        });
    }

    @FXML
    private void agregarProducto(){

        lblError.setText("");

        String nombre = txtNombre.getText().trim();

        try{

            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());

            Producto p = new Producto(nombre,precio,cantidad);

            productos.add(p);

            txtNombre.clear();
            txtPrecio.clear();
            txtCantidad.clear();

        }catch(NumberFormatException e){
            lblError.setText("Precio y cantidad deben ser numeros");
        }
    }

    @FXML
    private void eliminarProducto(){

        Producto seleccionado = tabla.getSelectionModel().getSelectedItem();

        if(seleccionado != null){
            productos.remove(seleccionado);
        }
    }
}