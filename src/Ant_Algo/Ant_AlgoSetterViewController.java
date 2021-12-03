
package Ant_Algo;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Ant_Pack.MysqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Ant_AlgoSetterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView WEBui;

    @FXML
    private TextField SAPid;

    @FXML
    private TextField fkt;

    @FXML
    private TextField fktSAP;

    @FXML
    private TextField ph1;

    @FXML
    private TextField ph2;

    @FXML
    private TextField ph3;

    @FXML
    private TextField ph4;
    
    java.sql.PreparedStatement pst;
    WebEngine webEngine= null;
    @FXML
    void Action1(ActionEvent event) throws IOException {
    	Desktop desktop = Desktop.getDesktop();
        File dirToOpen = null;
        try {
            dirToOpen = new File("D:\\ANT COLONY OPTIMISATION PROJECT\\download");
            desktop.open(dirToOpen);
        } catch (IllegalArgumentException iae) {
            System.out.println("File Not Found");
        }
    }

    @FXML
    void Action2(ActionEvent event) {
    	webEngine.load("https://docs.google.com/forms/u/0/");
    }

    @FXML
    void Action3(ActionEvent event) {
    	webEngine.load("http://localhost/phpmyadmin/index.php?route=/database/structure&db=tables");

    }

    @FXML
    void Action4(ActionEvent event) {
    	algo3fun ObJ=new algo3fun();
    	ObJ.savefm1();
    	showMsge("Data Cleaned!");
    }

    @FXML
    void DoSaveSAP(ActionEvent event) {
    	algo2 OBJ=new algo2();
    	OBJ.getter(Integer.parseInt(SAPid.getText()));
    	
    	algo2 obj=new algo2();
		try {
			obj.algo2();
			obj.algo2getter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	
       showMsg("SAP received!");
    }

    @FXML
    void exiter(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void getResult(ActionEvent event) {
    	phero5 ObJ=new phero5();
    	phero5.getPhero();
    	float arr[]=phero5.localphero;
    	ph1.setText(arr[0]+"");
    	ph2.setText(arr[1]+"");
    	ph3.setText(arr[2]+"");
    	ph4.setText(arr[3]+"");
    	try {
	    	java.sql.Connection con;
			 con=MysqlConnection.getConnection();
			 java.sql.PreparedStatement pst;
			 pst=con.prepareStatement("select * from algo3 order by fkt DESC");
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true;		 
				 String sap=res.getString("SAP");		
				 Float fktT=res.getFloat("fkt");
				 System.out.println(sap+" and "+fktT);
			    	fktSAP.setText(sap);
			    	fkt.setText(fktT+"");
				 //res.close();
				 break;
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
    }
    void showMsg(String msg) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("SAP received!");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    
    void showMsge(String msg) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("Mysql algo3 cleaned!");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    Connection con;

    @FXML
    void initialize() {
    	webEngine =WEBui.getEngine();
    	con=MysqlConnector.getConnection();
    }
}

