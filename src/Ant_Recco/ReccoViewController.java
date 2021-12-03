/*
 * 
package Ant_Recco;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ReccoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fktDisplay1;

    @FXML
    private TextField fktDisplay2;

    @FXML
    private TextField fktDisplay4;

    @FXML
    private TextField fktDislay3;

    @FXML
    private Label fktsap;

    @FXML
    void fktExiter(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void fktSTART(ActionEvent event) {
    	String[] array=new String[4];
    	try {
	    	java.sql.Connection con;
			 con=MysqlConnection.getConnection();
			 java.sql.PreparedStatement pst;
			 pst=con.prepareStatement("select * from algo3 order by fkt DESC");
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 String sap=null;
			 while(res.next()) {
				 count=true;		 
				  sap=res.getString("SAP");		
				 Float fktT=res.getFloat("fkt");
				 System.out.println(sap+" and "+fktT);
			    	fktsap.setText(sap);
				 //res.close();
				 break;
			 }
		   //***********************************************************************************************

			 pst=con.prepareStatement("select * from pathselection where SAP=?");
				pst.setInt(1, Integer.parseInt(sap));
				ResultSet rset=pst.executeQuery();
				boolean counter=false;
				while(rset.next()) {
					count=true;
					String COURSE1=rset.getString("COURSE1");
					String COURSE2=rset.getString("COURSE2");
					String COURSE3=rset.getString("COURSE3");
					String COURSE4=rset.getString("COURSE4");
					System.out.println(COURSE1+","+COURSE2+","+COURSE3+","+COURSE4);
					String[] arr=new String[] {COURSE1,COURSE2,COURSE3,COURSE4};
					array=arr;
			 if(counter==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
			 
		 }
	
    }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
    	for(int i=0;i<4;i++) {
    		System.out.println(array[i]+"--->");
    	}
    	
    	fktDisplay1.setText(array[0]);
  
    	
    	fktDisplay2.setText(array[1]);
    	fktDislay3.setText(array[2]);
    	fktDisplay4.setText(array[3]);
    }

    @FXML
    void initialize() {
        assert fktDisplay1 != null : "fx:id=\"fktDisplay1\" was not injected: check your FXML file 'ReccoView.fxml'.";
        assert fktDisplay2 != null : "fx:id=\"fktDisplay2\" was not injected: check your FXML file 'ReccoView.fxml'.";
        assert fktDisplay4 != null : "fx:id=\"fktDisplay4\" was not injected: check your FXML file 'ReccoView.fxml'.";
        assert fktDislay3 != null : "fx:id=\"fktDislay3\" was not injected: check your FXML file 'ReccoView.fxml'.";
        assert fktsap != null : "fx:id=\"fktsap\" was not injected: check your FXML file 'ReccoView.fxml'.";

    }
}

*/
package Ant_Recco;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

import Ant_Algo.MysqlConnector;
import Ant_Algo.phero5;
import Ant_Pack.MysqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.util.ArrayList;
public class ReccoViewController {
	public final static int alpha=1; //parameter 1
	public final static int beta=3;  //parameter 2
	static String sap=null;
	static double[] argu2b;
	static int argu2=0;
	static int variable1=0;
	static int variable2=0;
	public static String strURL1;
	public static String strURL2;
	public static String strURL3;
	public static String strURL4;
	static ArrayList<String> arrr = new ArrayList<String>();
	WebEngine webEngine= null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private WebView WebViewer;

    @FXML
    private TextField txtRecco;

    @FXML
    private TextField txtStatus;
    @FXML
    private TextField txtRecco2;

    @FXML
    private TextField txtRecco3;

    @FXML
    private TextField txtRecco4;
    @FXML
    void end(ActionEvent event) {
        System.exit(0);
        ShowEndMsg("Successfully Logged Out!");
        
    }
    
    @FXML
    void end2(ActionEvent event) {
    	 System.exit(0);
         ShowEndMsg("Successfully Logged Out!");
    }

    @FXML
    void end3(ActionEvent event) {
    	 System.exit(0);
         ShowEndMsg("Successfully Logged Out!");
    }

    @FXML
    void end4(ActionEvent event) {
    	 System.exit(0);
         ShowEndMsg("Successfully Logged Out!");
    }
    @FXML
    void go(ActionEvent event) {
    algo45678 OBJ=new algo45678();
    OBJ.main(null);
    /*
	    * ALGORITHM -6 and 7
	    * 2: STATIONARY STATUS: ABOVE 65%
	    * 3: ACTIVE STATUS: ABOVE 80%
	    * 4: DEGRADATION STATUS: BELOW 50%
	    * 5: INITIAL STATUS: BELOW 35%
	    */
    //NOTE DATA NEED TO BE UPDATED AFTER EACH USE!
	   float result=OBJ.getres();
	   result=(result/20)*100;
	   if(result>=65 ) {
		  
			   System.out.println("status: STATIONARY and score is"+result);
		    txtStatus.setText("STATIONARY and score is " +result);
		   
	   }
	   else if(result>=80) {
		
			  System.out.println("status: ACTIVE and score is "+result);
			    txtStatus.setText("ACTIVE and score is "+result);

	   }
	   else if(result<=50) {
		  
			   System.out.println("status: DEGRADATION and score is "+result);
			    txtStatus.setText(" DEGRADATION and score is "+result);

		   
	   }
	   else if(result<35) {
		   
			   System.out.println("status: INITIAL and score is "+result);
			    txtStatus.setText(" INITIAL and score is "+result);

		   
	   }
	   else {
		   System.out.println("****");
		   
	   }
	   showGOmsg("Wait for 10-15 min data need to be updated at backend!");
    }

   void showGOmsg(String msg) {
	   Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ant Colony Algo Says:");
    	alert.setHeaderText("Wait for next unit till backend is updating !");
    	alert.setContentText(msg);
    	alert.showAndWait();
   }
    
    @FXML
    void start(ActionEvent event) {
    	String COURSE1=null;
    	try {
	    	java.sql.Connection con;
			 con=MysqlConnection.getConnection();
			 java.sql.PreparedStatement pst;
			 pst=con.prepareStatement("select * from algo3 order by fkt DESC");
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			
			 while(res.next()) {
				 count=true;		 
				 sap=res.getString("SAP");		
				 Float fktT=res.getFloat("fkt");
				 System.out.println(sap+" and "+fktT);
			    	
				 //res.close();
				 break;
			 }
			 pst=con.prepareStatement("select * from pathselection where SAP=?");
				pst.setInt(1,Integer.parseInt(sap));
				ResultSet rset=pst.executeQuery();
				boolean counter=false;
				while(rset.next()) {
					count=true;
					COURSE1=rset.getString("COURSE1");
					String COURSE2=rset.getString("COURSE2");
					String COURSE3=rset.getString("COURSE3");
					String COURSE4=rset.getString("COURSE4");
					System.out.println(COURSE1+","+COURSE2+","+COURSE3+","+COURSE4);
					String[] arr=new String[] {COURSE1,COURSE2,COURSE3,COURSE4};
					
					//**  Fentch-1 array[0]
					txtRecco.setText(COURSE1);
					
					 arrr.add(0,COURSE1);
				      getURL(arrr);
					System.out.println(strURL1+"and arraylist length is  "+arrr.size());
					webEngine.load(strURL1);
					 
			 if(counter==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		}
		}
			 catch(Exception e) {
				 e.printStackTrace();
			 }
    	showCOURSE1(COURSE1);
		
    }
    void showCOURSE1(String msg) {
    	 Alert alert=new Alert(AlertType.INFORMATION);
      	alert.setTitle("Ant Colony Algo Says:");
      	alert.setHeaderText(msg+": COURSE started!");
      	alert.setContentText("Complete the course and give the test after that\nnew unit will be recommended.");
      	alert.showAndWait();
    }
    @FXML
    void start2(ActionEvent event) throws SQLException {
    	algo45678 OBJ=new algo45678();
    	algo45678.main(null);
    	float result=OBJ.getres();
		System.out.println(result);
		 
		 float[] heuristic=OBJ.heruistic();
		
		int[] phero=OBJ.getphero();
		
		float microphero=OBJ.getmicrophero();
		
		String pass1=getprob(result,heuristic,microphero,phero);
		System.out.println(pass1+"******************************");
		txtRecco2.setText(pass1);
		
		  arrr.add(1,pass1);
					getURL(arrr);
					System.out.println(strURL1);
					webEngine.load(strURL2); 
					showCOURSE1(pass1);
 }
		 

    @FXML
    void start3(ActionEvent event) throws SQLException {
    	algo45678 OBJ=new algo45678();
    	algo45678.main(null);
    	float result=OBJ.getres();
		System.out.println(result);
		 
		 float[] heuristic=OBJ.heruistic();
		
		int[] phero=OBJ.getphero();
		
		float microphero=OBJ.getmicrophero();
		getprob(result,heuristic,microphero,phero);
		System.out.println(argu2+", "+argu2b+ "******************************");
		for(int ni=0;ni<argu2b.length;ni++) {
			System.out.print(argu2b[ni]+"-->");
		}
		String pass1=getcourse2(result,heuristic,microphero,phero,argu2,argu2b);
		System.out.println(pass1+"******************************");
		txtRecco3.setText(pass1);
		arrr.add(2,pass1);
	    getURL(arrr);
		webEngine.load(strURL3);
		showCOURSE1(pass1);
    }

    @FXML
    void start4(ActionEvent event) throws SQLException {
    	algo45678 OBJ=new algo45678();
    	algo45678.main(null);
    	float result=OBJ.getres();
		System.out.println(result);
		 
		 float[] heuristic=OBJ.heruistic();
		
		int[] phero=OBJ.getphero();
		
		float microphero=OBJ.getmicrophero();
		getprob(result,heuristic,microphero,phero);
		System.out.println(argu2+", "+argu2b+ "******************************");
		for(int ni=0;ni<argu2b.length;ni++) {
			System.out.print(argu2b[ni]+"-->");
		}
		getcourse2(result,heuristic,microphero,phero,argu2,argu2b);
		String pass1=getcourse3(variable1,variable2);
		System.out.println(pass1+"******************************");
		txtRecco4.setText(pass1);
		
		  arrr.add(3,pass1);
					getURL(arrr);
					
					webEngine.load(strURL4);
					showCOURSE1(pass1);
    }
    
    public  String getURL(ArrayList<String> arrr) throws SQLException {
    	String str=null;
    	
        for(int i=0;i<arrr.size();i++) {
        		try {
        			
        			PreparedStatement pst = con.prepareStatement("select * from courses where NAME=?");
        			pst.setString(1,arrr.get(i));
        			ResultSet rsetd=pst.executeQuery();
        			System.out.println(arrr.get(i));
        			boolean count=false;
        			
        			while(rsetd.next()) {
        				count=true;
        				String LINK=rsetd.getString("LINK");
        				String LEVEL=rsetd.getString("LEVEL");
        				
        				System.out.println(LINK+","+LEVEL);
        				
        				if(count==false) {
        					System.out.println("INVALID");
        					//showMsg("Data Fenched!");
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
   

    static String getcourse2(float result, float[] heruistic, float microphero, int[] phero,int del,double[] PROBij) {
    	System.out.println("inside -2   ---------------------------------------------------");
    	String course2;
    	for(int i=0;i<PROBij.length;i++) {
     	   System.out.print(PROBij[i]+"--->");
        }
    	double num=0;
		double deno=0;
    	//convert int phero-->float phero
    	 final float[] totalphero = new float[4];
         int index = 0;
         for (final Integer value: phero) {
            totalphero[index++] = value;
         }
          Arrays.sort(PROBij);
          int var=del;
          int var2=0;
          System.out.println("variable is "+var);
           PROBij = removeTheElement(PROBij, var);
           
           
           System.out.println("**************************");
           for(int i=0;i<PROBij.length;i++) {
        	   System.out.print(PROBij[i]+"--->");
           }
           System.out.println("length is :"+PROBij.length);
           if(var==1 && PROBij[0]>PROBij[1]) {
        	   course2=result(2);
        	   PROBij = removeTheElement(PROBij, 2);
        	   var2=2;
        	   getvar1(var);
        	   getvar2(var2);
        	   return course2;
           }   
           else if(var==2 && PROBij[0]>PROBij[1]) {
        	   course2=result(1);
        	   PROBij = removeTheElement(PROBij, 1);
        	   var2=1;
        	   getvar1(var);
        	   getvar2(var2);
        	   return course2;
           }
           else if(var==3 && PROBij[0]>PROBij[1]){
        	  course2= result(2);
        	   PROBij = removeTheElement(PROBij,2);
        	   var2=2;
        	   getvar1(var);
        	   getvar2(var2);
        	   return course2;
           }
           
           else  if(var!=3 &&PROBij[1]>PROBij[0]) {
        	  course2= result(1);
        	   PROBij = removeTheElement(PROBij, 1);
        	   var2=1;
        	   getvar1(var);
        	   getvar2(var2);
        	   return course2;
           }
           else
           {
        	   course2 =result(3);
        	   PROBij = removeTheElement(PROBij, 3);
        	   var2=3;
        	   getvar1(var);
        	   getvar2(var2);
        	   return course2;
           }
          
          //getcourse3(phero,var,var2);
         
    }
    static String getprob(float result, float[] heruistic, float microphero, int[] phero) {
    	System.out.println("inside -1   ---------------------------------------------------");
    	String course2;
    	//result: single float number
    	//heuristic : array of 4 indices
    	//pheroij :microphero
    	//phero: net pheromone
    	double num=0;
		double deno=0;
    	//convert int phero-->float phero
    	 final float[] totalphero = new float[4];
         int index = 0;
         for (final Integer value: phero) {
            totalphero[index++] = value;
         }
         //appling algorithm4
         //calculating numerator , denominator and probability of algorithm 4:
          
          double[] PROBij=new double[4];
          //PROBij = removeTheElement(PROBij, 0);
           for(int k=0;k<=3;k++) {
        	   num=(Math.pow(heruistic[k], alpha) * Math.pow(microphero, beta));
        	   deno=denominator(k,heruistic,phero);
        	   PROBij[k]=num/deno;
               PROBij[k]=PROBij[k]*1000;
               PROBij[k]=Math.abs(PROBij[k]);
              
              	System.out.println(num+","+deno+",||"+PROBij[k]);
           }
           PROBij = removeTheElement(PROBij, 0);
           //getting maximum probability course name!
       
           for(int i=0;i<PROBij.length;i++) {
        	   System.out.println(+PROBij[i]+"-->");
           }
           
           if(PROBij[0] > PROBij[1] && PROBij[0] > PROBij[2])
           {
        	   System.out.println("REFFERED COURSE NO: 1 and pos is :" );
         		course2=result(1);
         		//getcourse2(result,heruistic,microphero,phero,1,PROBij);
         		getno(1);
         		getarr(PROBij);
         		return course2;
         		
           }
           else if(PROBij[1] > PROBij[2])
           {
        	   System.out.println("REFFERED COURSE NO: 2" );
         		course2=result(2);
         		//getcourse2(result,heruistic,microphero,phero,2,PROBij);
         		getno(2);
         		getarr(PROBij);
         	    return course2;
           }
           else
           {
        	   System.out.println("REFFERED COURSE NO: 3" );
           	   course2=result(3);
           	//getcourse2(result,heruistic,microphero,phero,3,PROBij);
           	getno(3);
           	getarr(PROBij);
           	return course2;
           }
    }
    static void getno(int number) {
    	argu2=number;
    }
    static void getarr(double[] array) {
    argu2b=array;
    }
    static String getcourse3(int var1,int var2) {
    	//for loop use!
    	String course3=null;
			if(var1==1 && var2==2) {
				course3=result(3);
				return course3;
			}
			else if(var1==1 && var2==3) {
				course3=result(2);
				return course3;
			}
			else if(var1==2 && var2==1) {
				course3=result(3);
				return course3;
			}
			else if(var1==2 && var2==3) {
				course3=result(1);
				return course3;
			}
			else if(var1==3 && var2==1) {
				course3=result(2);
				return course3;
			}
			else if (var1==3 && var2==2) {
				course3=result(1);
				return course3;
			}
			else {
			return null;}
			
    }
    public static double[] removeTheElement(double[] pROBij, int index)
    {
        if (pROBij == null || index < 0
            || index >= pROBij.length) {
 
            return pROBij;
        }
        double[] anotherArray = new double[pROBij.length - 1];
        for (int i = 0, k = 0; i < pROBij.length; i++) {

            if (i == index) {
                continue;
            }

            anotherArray[k++] = pROBij[i];
        }

        return anotherArray;
    }
    static int getdecission() {
	   /*
	    * ALGORITHM -6 and 7
	    * 2: STATIONARY STATUS: ABOVE 65%
	    * 3: ACTIVE STATUS: ABOVE 80%
	    * 4: DEGRADATION STATUS: BELOW 50%
	    * 5: INITIAL STATUS: BELOW 35%
	    */
	   float result=getres();
	   result=(result/20)*100;
	   if(result>=65 ) {
		  
			   System.out.println("status: STATIONARY and score is"+result);
			   return 2;
		   
		   
	   }
	   else if(result>=80) {
		
			  System.out.println("status: ACTIVE and score is "+result);
			   return 3;
		  
	   }
	   else if(result<=50) {
		  
			   System.out.println("status: DEGRADATION and score is "+result);
			   return 4;
		   
		   
	   }
	   else if(result<35) {
		   
			   System.out.println("status: INITIAL and score is "+result);
			   return 5;
		       
		   
	   }
	   else {
		   System.out.println("****");
	   }
	   return 1;
   }
    static void updatephero() {
    	//ALGORITHM -8
    	phero5 OBJ=new phero5();
    	OBJ.getPhero();
    }
    static String result(int num) {
    	algo45678 OBJ=new algo45678();
    	System.out.println(num);
    	String[] arr=new String[4];
    	try {
    		java.sql.Connection con;
   		    con=MysqlConnection.getConnection();
   	    	 java.sql.PreparedStatement pst;
			pst=con.prepareStatement("select * from pathselection where SAP=?");
			pst.setString(1,OBJ.sap);
			ResultSet rset=pst.executeQuery();
			boolean count=false;
			while(rset.next()) {
				count=true;
				String COURSE1=rset.getString("COURSE1");
				String COURSE2=rset.getString("COURSE2");
				String COURSE3=rset.getString("COURSE3");
				String COURSE4=rset.getString("COURSE4");
				System.out.println(COURSE1+","+COURSE2+","+COURSE3+","+COURSE4);
				arr[0]= COURSE1;
				arr[1]=COURSE2;
				arr[2]=COURSE3;
				arr[3]=COURSE4;
				
				//rset.close();
			}
			if(count==false) {
				System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	int k=0;
    	if(num==0) {
    	    
    		System.out.println(arr[0]);
    	    
    		k=getdecission();
    		return arr[0];
    	}
		if(num==1) {
			
			System.out.println(arr[1]);	
			k=getdecission();
			return arr[1];
		}
		if(num==2) {
			System.out.println(arr[2]);
			k=getdecission();
			return arr[2];
		}
		if(num==3) {
			System.out.println(arr[3]);
			k=getdecission();
			return arr[3];
		}
	
		return null;
    }
    static float getres() {
    	algo45678 OBJ=new algo45678();
		//**upload csv in resultfetcher (go to backend page==Ant_Algo main class**//
		//fench res**//
		java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 float scr=0;
		try {
			 pst=con.prepareStatement("select * from resultfetch where SAP=?");
			 pst.setString(1,OBJ.sap+"");
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true;
				 String name=res.getString("SCORE");
				 name=name.substring(0,2);
				  scr=Float.parseFloat(name);
				  System.out.println("RESULT FETCHED IS :"+scr);
				 //res.close();
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		return scr;
	}
    static double denominator(int num,float[] heruistic,int[] phero) {
    	double deno=0; 
    	for(int j=0;j<num;j++) {
       	  deno=deno+(Math.pow(heruistic[j], alpha) * Math.pow(phero[j], beta));
         }
    	 return deno;
    }
   static void getvar1(int number) {
	   variable1=number;
   }
   static void getvar2(int number) {
	   variable2=number;
   }
    Connection con;
     void ShowEndMsg(String msg) {
        Alert alert=new Alert(AlertType.INFORMATION);
     	alert.setTitle("Ant Colony Algo Says:");
     	alert.setHeaderText("Go to Next Course!");
     	alert.setContentText(msg);
     	alert.showAndWait();
     }
    @FXML
    void initialize() {
        assert WebViewer != null : "fx:id=\"WebViewer\" was not injected: check your FXML file 'ReccoView.fxml'.";
        assert txtRecco != null : "fx:id=\"txtRecco\" was not injected: check your FXML file 'ReccoView.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'ReccoView.fxml'.";
        //WebEngine webEngine =WebViewer.getEngine();
        webEngine =WebViewer.getEngine();
    	con=MysqlConnector.getConnection();

    }
}
