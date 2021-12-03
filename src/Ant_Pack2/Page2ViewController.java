
package Ant_Pack2;
import java.util.ArrayList;
import java.util.Arrays;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Ant_Pack.MysqlConnection;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Page2ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> Combo1;

    @FXML
    private ComboBox<String> Combo2;

    @FXML
    private ComboBox<String> Combo3;

    @FXML
    private ComboBox<String> Combo4;

    @FXML
    private TextField txtSAP;

    @FXML
    void doClear(ActionEvent event) {

    }
    java.sql.PreparedStatement pst;
    @FXML
    void doSubmit(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into pathselection values(?,?,?,?,?)");
			pst.setInt(1,Integer.parseInt(txtSAP.getText()));
			pst.setString(2,Combo1.getEditor().getText() );
			pst.setString(3,Combo2.getEditor().getText() );
			pst.setString(4,Combo3.getEditor().getText() );
			pst.setString(5,Combo4.getEditor().getText() );
			pst.executeUpdate();
			showMsg("Saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    void showMsg(String msg) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("Result Updated");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    java.sql.Connection con;
    @FXML
    void initialize() {
    	ArrayList<String> ary=new ArrayList<String>(Arrays.asList("Keywords","Classes and Object","Polymorphism","Access Specifier","Package","MySql","Interface","File Handling","Inheritance","Stack and Heap","Array of Objects","Array of Object algo","LinkedList","LinkedListALgo","Doubly Linkedlist","aws","MVC Framework","Frontend","AWT","RAID","Hashing","API","Cloud Deployment Models","Crypto"));
        assert Combo1 != null : "fx:id=\"Combo1\" was not injected: check your FXML file 'Page2View.fxml'.";
        assert Combo2 != null : "fx:id=\"Combo2\" was not injected: check your FXML file 'Page2View.fxml'.";
        assert Combo3 != null : "fx:id=\"Combo3\" was not injected: check your FXML file 'Page2View.fxml'.";
        assert Combo4 != null : "fx:id=\"Combo4\" was not injected: check your FXML file 'Page2View.fxml'.";
        assert txtSAP != null : "fx:id=\"txtSAP\" was not injected: check your FXML file 'Page2View.fxml'.";
        Combo1.getItems().addAll(ary);
        Combo2.getItems().addAll(ary);
        Combo3.getItems().addAll(ary);
        Combo4.getItems().addAll(ary);
    	con=MysqlConnection.getConnection();
    }
}
