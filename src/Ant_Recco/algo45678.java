package Ant_Recco;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Ant_Algo.algo2;
import Ant_Algo.algo3fun;
import Ant_Algo.phero5;
import Ant_Pack.MysqlConnection;

public class algo45678 {
	public final static int alpha=1; //parameter 1
	public final static int beta=3;  //parameter 2
	public final static double evapo_const=0.5; //parameter 3
	public final static float omega=5;
	static String[] array=new String[4];
	 static String sap=null;
	public static void main(String args[]) {
		
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
					//**  Fentch-1 array[0]
			 if(counter==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		}
		}
			 catch(Exception e) {
				 e.printStackTrace();
			 }
		//**give test of array[0]**//
		java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		try {
			pst=con.prepareStatement("select * from courses where NAME=?");
			pst.setString(1,array[0]);
			//** send to web view and show link in lable view of test!**//
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  //** getres() **//
		float result=getres();
		System.out.println(result);
		 //** get heuristic **//
		 float[] heuristic=heruistic();
		 //** get phero() **//
		int[] phero=getphero();
		//get individual phero**//
		float microphero=getmicrophero();
		//get probability and course2**//
		getprob(result,heuristic,microphero,phero);
		//get course 3**//
		//getcourse2(result,heuristic,microphero,phero);
		//get course 4**//
		//getcourse3(result,heuristic,microphero,phero);
	}
	static float getres() {
		//**upload csv in resultfetcher (go to backend page==Ant_Algo main class**//
		//fench res**//
		java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 float scr=0;
		try {
			 pst=con.prepareStatement("select * from resultfetch where SAP=?");
			 pst.setString(1,sap+"");
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

	static float[] heruistic() {
		java.sql.Connection con;
	    con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 String[] coursess=new String[4];
		 try {
				pst=con.prepareStatement("select * from pathselection where SAP=?");
				pst.setString(1,sap); // sap
				ResultSet rset=pst.executeQuery();
				boolean count=false;
				while(rset.next()) {
					count=true;
					String COURSE1=rset.getString("COURSE1");
					String COURSE2=rset.getString("COURSE2");
					String COURSE3=rset.getString("COURSE3");
					String COURSE4=rset.getString("COURSE4");
					System.out.println(COURSE1+","+COURSE2+","+COURSE3+","+COURSE4);
					coursess[0]=COURSE1;
					coursess[1]=COURSE2;
					coursess[2]=COURSE3;
					coursess[3]=COURSE4;
					//rset.close();
				}
				if(count==false) {
					System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		 char arr[]=new char[coursess.length];
		 System.out.println("#########################");
		
		 float[] getter=new float[4];
		 float thitter[]=new float[4];
		 float thita1[]=new float[4];
		
		for(int k=0;k<4;k++) {
		 try {
				pst=con.prepareStatement("select * from courses where NAME=?");
				coursess[k].replaceAll("\\s", "");
				pst.setString(1,coursess[k]);
				ResultSet rsetd=pst.executeQuery();
				//System.out.print(courses[k]+" ");
				boolean count=false;
				while(rsetd.next()) {
					count=true;
					String LEVEL=rsetd.getString("LEVEL");
					LEVEL.replaceAll("\\s", "");
					arr[k]=LEVEL.charAt(0);
				
					if(count==false) {
						System.out.println("\nINVALID! data not fenched!\n");
					}	
				}
				
				} catch (Exception e) {
					e.printStackTrace();
					//System.out.println("error");
				}
		   
		
		
			 System.out.println(arr[k]);
			 if(arr[k]=='E') {
				 getter[k]=1;
			 }
			 if(arr[k]=='M') {
				 getter[k]=2;
			 }
			 if(arr[k]=='H') {
				 getter[k]=3;
			 }
		 }
		 for(int i=0;i<arr.length;i++) {
			 System.out.println(arr[i]);
		 }
		thita1[0]=getter[0]+getter[1];
	    thita1[1]=getter[1]+getter[2];
	    thita1[2]=getter[2]+getter[3];
	    thita1[3]=getter[3];
	  
		
		  float thita2=0;// if learning goal mapped
		  algo2 obj=new algo2();
		  String newGoal=obj.argu1;
		  algo3fun objj=new algo3fun();
		  for(int i1=0;i1<objj.argu1.size();i1++) {
			  if(newGoal.equals(objj.argu1.get(i1))==true) {
				 thita2=1;
		  }
			  else
				  thita2=0;
		  }
		float[] heuristic=new float[4];
		System.out.print("heuristic: \n");
		
		
		for(int m=0;m<4;m++) {
		    heuristic[m]=(omega*thita1[m])+(1-omega)*thita2;
		    
		    System.out.print(heuristic[m]+" ");
		}
			 
			
			return heuristic;
		}
	// algorithm 5 is used for finding pheromone!
	static int[] getphero() {
		java.sql.Connection con;
	    con=MysqlConnection.getConnection();
		java.sql.PreparedStatement pst;
		ArrayList<String> testres = new ArrayList<String>();

	String tst1[]=new String[4];
	String tst2[]=new String[4];
	String tst3[]=new String[4];
	String tst4[]=new String[4];
		
		int[] finaltestset=new int[4];
					for(int j=0;j<4;j++) {		
						if(j==0) {
							 try {					 	
								 	pst=con.prepareStatement("select SAP,SCORE from csvgetter1");
									ResultSet rset=pst.executeQuery();
									boolean count=false;
									int i=0;
									while(rset.next()) {
										count=true;
										String COL1=rset.getString("SAP");
										String COL2=rset.getString("SCORE");
										String score;
										score=COL2;
										testres.add(j,score.substring(0, 2));
										//System.out.println(COL1+":-->"+score+"\n");
										String str=score.substring(0,2);
										str=str.replaceAll("\\s", "");
										tst1[i]=str;
										i++;
										//System.out.print(tst1[j]+" , ");
										
									}
									System.out.println();
									if(count==false) {
										System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
						}
			
							 if(j==1) {
								 try {					 	
									 	pst=con.prepareStatement("select SAP,SCORE from csvgetter2");
										ResultSet rset=pst.executeQuery();
										boolean count=false;
										int ii=0;
										while(rset.next()) {
											count=true;
											String COL1=rset.getString("SAP");
											String COL2=rset.getString("SCORE");
											String score;
											score=COL2;
											testres.add(j,score.substring(0, 2));
						
											//System.out.println(COL1+":-->"+score+"\n");	
											String str=score.substring(0,2);
											str=str.replaceAll("\\s", "");
											tst2[ii]=str;
											ii++;
											//int inttst2=Integer.parseInt(tst2);
											//System.out.print(tst2[j]+" , ");
											
										}
										System.out.println();
										if(count==false) {
											System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
										}
									} catch (Exception e) {
										e.printStackTrace();
									}				 
							 }
							
								 if(j==2) {
									 try {
										 	pst=con.prepareStatement("select SAP,SCORE from csvgetter3");
											ResultSet rset=pst.executeQuery();
											boolean count=false;
											int iii=0;
											while(rset.next()) {
												count=true;
												String COL1=rset.getString("SAP");
												String COL2=rset.getString("SCORE");
											    String score;
												score=COL2;
												testres.add(j,score.substring(0, 2));
											
												//System.out.println(COL1+":-->"+score+"\n");
												String str=score.substring(0,2);
												str=str.replaceAll("\\s", "");
												tst3[iii]=str;
												iii++;
											//	System.out.print(tst3[j]+" , ");
											
											}
											if(count==false) {
												System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
											}
										} catch (Exception e) {
											e.printStackTrace();
										}	
								 }
					
									 if(j==3) {
										 try {	 	
											 	pst=con.prepareStatement("select SAP,SCORE from csvgetter4 ");
												ResultSet rset=pst.executeQuery();
												boolean count=false;
												int iiii=0;
												while(rset.next()) {
													count=true;
													String COL1=rset.getString("SAP");
													String COL2=rset.getString("SCORE");
													String score;
													score=COL2;
													testres.add(j,score.substring(0, 2));
													//System.out.println(COL1+":-->"+score+"\n");	
													String str=score.substring(0,2);
													str=str.replaceAll("\\s", "");
													tst4[iiii]=str;
													iiii++;
												//	System.out.print(tst4[j]+" , ");
									
													}
												if(count==false) {
													System.out.println("\nINVALID, data not fenched ERROR @getDetails4\n");
												}
											} catch (Exception e) {
												e.printStackTrace();
											}
									 }
								 }
					int algo5array[]=new int[4];
					for(int m=0;m<4;m++) {
						algo5array[m]=Integer.parseInt(tst1[m])+Integer.parseInt(tst2[m])+Integer.parseInt(tst3[m])+Integer.parseInt(tst4[m]);
					}
					for(int mm=0;mm<4;mm++) {
						System.out.print(algo5array[mm]+"--->");
					}
					return algo5array;
	}
	//static float holdres=getres();
	static int count=0;
    static float getmicrophero() {  
	    float holdres=getres();
		float PHEROij,PHEROi,PHEROj;
		PHEROi=holdres;
		float[] array=new float[4];
		if(count==0) {
			PHEROj=holdres;
		}
		else
			array[count]=holdres;
			PHEROj=array[count--];
			count++;
		
		System.out.println("values: "+PHEROi+" "+PHEROj);
		PHEROij=(float) (0.5*PHEROi+(PHEROj-PHEROi));
		return PHEROij;
	}
    static String getprob(float result, float[] heruistic, float microphero, int[] phero) {
    	System.out.println("inside -1   ---------------------------------------------------");
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
         		result(1);
         		getcourse2(result,heruistic,microphero,phero,1,PROBij);
         		return("REFFERED COURSE NO: 1 and pos is :" );
           }
           else if(PROBij[1] > PROBij[2])
           {
        	   System.out.println("REFFERED COURSE NO: 2" );
         		result(2);
         		getcourse2(result,heruistic,microphero,phero,2,PROBij);
         	    return("REFFERED COURSE NO: 2" );
           }
           else
           {
        	   System.out.println("REFFERED COURSE NO: 3" );
           	   result(3);
           	getcourse2(result,heruistic,microphero,phero,3,PROBij);
           	return("REFFERED COURSE NO: 3" );
           }
    }
    static double denominator(int num,float[] heruistic,int[] phero) {
    	double deno=0; 
    	for(int j=0;j<num;j++) {
       	  deno=deno+(Math.pow(heruistic[j], alpha) * Math.pow(phero[j], beta));
         }
    	 return deno;
    }
    static void result(int num) {
    	System.out.println(num);
    	String[] arr=new String[4];
    	try {
    		java.sql.Connection con;
   		    con=MysqlConnection.getConnection();
   	    	 java.sql.PreparedStatement pst;
			pst=con.prepareStatement("select * from pathselection where SAP=?");
			pst.setString(1,sap);
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
    	}
		if(num==1) {
			
			System.out.println(arr[1]);	
			k=getdecission();
		}
		if(num==2) {
			System.out.println(arr[2]);
			k=getdecission();
		}
		if(num==3) {
			System.out.println(arr[3]);
			k=getdecission();
		}
    }
    static void getcourse2(float result, float[] heruistic, float microphero, int[] phero,int del,double[] PROBij) {
    	System.out.println("inside -2   ---------------------------------------------------");
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
        	   result(2);
        	   PROBij = removeTheElement(PROBij, 2);
        	   var2=2;
           }   
           else if(var==2 && PROBij[0]>PROBij[1]) {
        	   result(1);
        	   PROBij = removeTheElement(PROBij, 1);
        	   var2=1;
           }
           else if(var==3 && PROBij[0]>PROBij[1]){
        	   result(2);
        	   PROBij = removeTheElement(PROBij,2);
        	   var2=2;
           }
           
           else  if(var!=3 &&PROBij[1]>PROBij[0]) {
        	   result(1);
        	   PROBij = removeTheElement(PROBij, 1);
        	   var2=1;
           }
           else
           {
        	   result(3);
        	   PROBij = removeTheElement(PROBij, 3);
        	   var2=3;
           }
          for(int i=0;i<PROBij.length;i++) {
        	  System.out.print("|"+PROBij[i]+"|"+"--->");
          }
          getcourse3(phero,var,var2);
           System.out.println("******************************************");
    }
    static void getcourse3(int[] phero,int var1,int var2) {
    	//for loop use!

			if(var1==1 && var2==2) {
				result(3);
			}
			if(var1==1 && var2==3) {
				result(2);
			}
			if(var1==2 && var2==1) {
				result(3);
			}
			if(var1==2 && var2==3) {
				result(1);
			}
			if(var1==3 && var2==1) {
				result(2);
			}
			if(var1==3 && var2==2) {
				result(1);
			}
			//updatephero();
           System.out.println("******************************************");
  
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
}