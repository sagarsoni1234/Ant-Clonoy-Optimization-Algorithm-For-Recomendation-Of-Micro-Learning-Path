package RunBackend;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Ant_Pack.MysqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class BackViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtPASS;

    @FXML
    private Label label;

    @FXML
    void exit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void login(ActionEvent event) {
    	 java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 try {
			 pst=con.prepareStatement("select * from backend where ID=?");
			 pst.setInt(1,Integer.parseInt(txtID.getText()));
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true;
				 String PASS=res.getString("PASS");
				 System.out.println("user pass: "+txtPASS.getText() +" and correct pass is :"+ PASS);
				 if(PASS.equals(txtPASS.getText())) {
				 System.out.println("LOGIN SUCCESSFUL");
				 javafx.scene.Parent root=FXMLLoader.load(getClass().getResource("/Ant_Algo/Ant_AlgoSetterView.fxml")); 
					//OR
					//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
					javafx.scene.Scene scene = new javafx.scene.Scene(root);
					javafx.stage.Stage stage=new javafx.stage.Stage();
					stage.setScene(scene);
					stage.show();
				 }
				 else {
					 System.out.println("Incorrect credientials\n");
					 showGOmsg("Incorrect details");
				 }
				 
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	
    void showGOmsg(String msg) {
 	   Alert alert=new Alert(AlertType.INFORMATION);
     	alert.setTitle("Ant Colony Algo Says:");
     	alert.setHeaderText("Incorrect Details Try again !");
     	alert.setContentText(msg);
     	alert.showAndWait();
     	
    }

    @FXML
    void initialize() {
        assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'BackView.fxml'.";
        assert txtPASS != null : "fx:id=\"txtPASS\" was not injected: check your FXML file 'BackView.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'BackView.fxml'.";

    }
}
