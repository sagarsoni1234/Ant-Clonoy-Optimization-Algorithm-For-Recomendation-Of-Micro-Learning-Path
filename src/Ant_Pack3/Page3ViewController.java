package Ant_Pack3;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import Ant_Pack.MysqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Page3ViewController {
	public static String strURL1;
	public static String strURL2;
	public static String strURL3;
	public static String strURL4;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView webDisplay;

    @FXML
    private Label finalLabel;

    @FXML
    private TextField txtSAP;
    
    java.sql.PreparedStatement pst;
    WebEngine webEngine= null;
    @FXML
    void btn1(ActionEvent event) {
    	
    	webEngine.load(strURL1);
    	SHOWMSG("COURSE -started!");
    }

    @FXML
    void btn2(ActionEvent event) {
    	webEngine.load(strURL2);
    }

    @FXML
    void btn3(ActionEvent event) {
    	
    	webEngine.load(strURL3);
    }

    @FXML
    void btn4(ActionEvent event) {
    	//Page3ViewController OBJ=new Page3ViewController();
    	System.out.println(strURL4);
    	webEngine.load(strURL4);
    }
    
    @FXML
    void btnExit(ActionEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void doSubmit(ActionEvent event) {
    	getdata();
    	showsubmit("SAP receive");
    }
    //get data for particular SAP ID!
    void getdata() {
    	try {
			pst=con.prepareStatement("select * from pathselection where SAP=?");
			pst.setInt(1,Integer.parseInt(txtSAP.getText()));
			ResultSet rset=pst.executeQuery();
			boolean count=false;
			while(rset.next()) {
				count=true;
				String COURSE1=rset.getString("COURSE1");
				String COURSE2=rset.getString("COURSE2");
				String COURSE3=rset.getString("COURSE3");
				String COURSE4=rset.getString("COURSE4");
				System.out.println(COURSE1+","+COURSE2+","+COURSE3+","+COURSE4);
				String[] arr=new String[] {COURSE1,COURSE2,COURSE3,COURSE4};

				getURL(arr);
				//rset.close();
			}
			if(count==false) {
				System.out.println("INVALID");
				showMsg("Data not Fenched!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 
    }
    
    public String getURL(String[] arr) throws SQLException {
    	String str=null;
        for(int i=0;i<arr.length;i++) {
        		try {
        			pst=con.prepareStatement("select * from courses where NAME=?");
        			pst.setString(1,arr[i]);
        			ResultSet rsetd=pst.executeQuery();
        			System.out.println(arr[i]);
        			boolean count=false;
        			
        			while(rsetd.next()) {
        				count=true;
        				String LINK=rsetd.getString("LINK");
        				String LEVEL=rsetd.getString("LEVEL");
        				
        				System.out.println(LINK+","+LEVEL);
        				
        				if(count==false) {
        					System.out.println("INVALID");
        					showMsg("Data Fenched!");
        				}
        				if(i==0) {
        					 strURL1=LINK;
        				}
        				if(i==1) {
        					strURL2=LINK;
        				}
        				if(i==2) {
        					strURL3=LINK;
        				}
        				if(i==3) {
        					strURL4=LINK;
        				}
        			}
        			} catch (Exception e) {
        				e.printStackTrace();
        				//System.out.println("error");
        			}
        		
        	System.out.println("loop"+i+" completed!");
        }
    	return str;
    }
   
    void showMsg(String msg) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("Sap id not Found");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    void SHOWMSG(String msg) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("started!");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    void showsubmit(String msg) {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("SAP ID received");
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
   
  Connection con;
    @FXML
    void initialize() {
    	webEngine =webDisplay.getEngine();
    	con=MysqlConnection.getConnection();
    	
    }
}

