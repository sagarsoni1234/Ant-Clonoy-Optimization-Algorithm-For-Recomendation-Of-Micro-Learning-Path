
package Ant_Pack;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginFormViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label showLabel;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSAP;

    @FXML
    private TextField TxtkLevel;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    void Display(MouseEvent event) {
    	String display=comboBox.getSelectionModel().getSelectedItem();
        int index=comboBox.getSelectionModel().getSelectedIndex();
        showLabel.setText("Course: "+display +"   Course Id:"+index);
    }
    
    java.sql.PreparedStatement pst;
    @FXML
    void doSave(ActionEvent event) {
    	try {
			pst=con.prepareStatement("insert into login values(?,?,?,?)");
			pst.setInt(1,Integer.parseInt(txtSAP.getText()));
			pst.setString(2, txtName.getText());
			pst.setString(3, comboBox.getEditor().getText());
			pst.setInt(4, Integer.parseInt(TxtkLevel.getText()));
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
        assert showLabel != null : "fx:id=\"showLabel\" was not injected: check your FXML file 'LoginFormView.fxml'.";
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'LoginFormView.fxml'.";
        assert txtSAP != null : "fx:id=\"txtSAP\" was not injected: check your FXML file 'LoginFormView.fxml'.";
        assert TxtkLevel != null : "fx:id=\"TxtkLevel\" was not injected: check your FXML file 'LoginFormView.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'LoginFormView.fxml'.";
        ArrayList<String> ary=new ArrayList<String>(Arrays.asList("Java","Python","C","C++","Node Js","MySql","Data Structures","Cloud","Networking","CryptoGraphy","MVC-framework","BlockChain","WebDevelopment"));
    	comboBox.getItems().addAll(ary);
    	con=MysqlConnection.getConnection();
    }
}
