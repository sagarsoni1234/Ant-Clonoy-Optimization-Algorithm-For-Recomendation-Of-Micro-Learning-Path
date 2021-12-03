package Ant_Algo;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import Ant_Pack.MysqlConnection;

class Student{
	/* 1. */String Learning_Goal;// from table login 
	/* 2. */int SAP;//from table login            
	/* 3. */String Name;//from table login
	/* 4. */int Knolwedge_Level;//value 1,2,3
	/* 5. */String[] path=new String[4];//path=COURSE1,COURSE2,COURSE3,COURSE4 from table pathselection
	/* 6. */String[] CourseLevel=new String[4];//from table Courses Level
	/* 7. */String[] TestRes=new String[4];//from CSV google form
	/* 8. */String[] Phero_Courses=new String[4];//from MODULE=5
	/* 9. */Float fkt;//from MODULE 3
	Student (String Lg,int Sap,String name,int KnowledgeLvL,String arr1[],String arr2[],String arr3[],String arr4[],float getFKT){
		Learning_Goal=Lg;
		SAP=Sap;
		Name=name;
		Knolwedge_Level=KnowledgeLvL;
		path=arr1;
		CourseLevel=arr2;
		TestRes=arr3;
		Phero_Courses=arr4;
		fkt=getFKT;
	}
}
 public class algo2{
	public final static int alpha=1; //parameter 1
	public final static int beta=3;  //parameter 2
	public final static double evapo_const=0.5; //parameter 3
	/* 1. */public static String argu1; //goal rec!
	/* 2. */public static int argu2;//sap rec !     
	/* 3. */public static String argu3;//name rec!
	/* 4. */public static int argu4;//knowledge level rec!
	/* 5. */public static String argu5[]=new String[4];//courses rec!
	/* 6. */public static String argu6[]=new String[4];//course level rec!
	/* 7. */public static String[] argu7=new String[4];//Test Result rec! CONVERT THEM INTO float FOR USE!
	/* 8. */ArrayList<Float> argu8=new ArrayList<Float>();// fkt 
	/* 9. */
	static int saap;
	//order to RUN--> 
		
	algo3fun obj1=new algo3fun();
	void algo2getter() throws IOException{
		 
			
		
		obj1.getSAPandCOURSE();
		obj1.compareGoal();
		obj1.getweightfactorr();		
		obj1.getTestSet();	
		phero5 OBJ=new phero5();
		OBJ.getPhero();
	}
	void algo2() throws IOException {
		
		getSAP();
		getDetail1();
		getDetails2();
		getDetails3();
		getDetails4();
		algo3();
		algo5();
	}
	//**********************************************************************//
	 void getDetail1() {
		 java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 try {
			 pst=con.prepareStatement("select * from login where SAP=?");
			 pst.setInt(1,argu2);
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true;
				 String name=res.getString("NAME");
				 String goal=res.getString("GOAL");
				 int klevel=res.getInt("KLEVEL");
				 System.out.println(name+" have learning goal as: "+goal+" and its level is: "+klevel);
				 argu1=goal;
				 argu3=name;
				 argu4=klevel;
				 //res.close();
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	 }
	 
	 void getter(int sap) {
		 saap=sap;
	 }
	 //********************************************************************//
	 void getDetails2() {
		 java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 try {
				pst=con.prepareStatement("select * from pathselection where SAP=?");
				pst.setInt(1,argu2);
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
					setArgu5(arr);
					//rset.close();
				}
				if(count==false) {
					System.out.println("\nINVALID, data not fenched ERROR @PATHSELECTOION\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	 }
	 //*******************************************************************//
	 void getSAP() {
		// int SAP;
		/*
		 *  System.out.println("\n Enter your SAP id:\n");
		 Scanner sc=new Scanner(System.in);
		 SAP=sc.nextInt();
		 */
		 argu2=saap;
		 
	 }
	 //******************************************************************//
	 void getDetails3() {
		 java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 String array[]=new String[getArgu5().length];
		 int j=0;
		 for(int i=0;i<getArgu5().length;i++) {
     		try {
     			pst=con.prepareStatement("select * from courses where NAME=?");
     			pst.setString(1,getArgu5()[i]);
     			
     			ResultSet rsetd=pst.executeQuery();
     			System.out.print(getArgu5()[i]+" ");
     			boolean count=false;
     			while(rsetd.next()) {
     				count=true;
     				String LEVEL=rsetd.getString("LEVEL");
     				System.out.print(LEVEL);
     				array[j]=LEVEL;
     				j++;
     				if(count==false) {
     					System.out.println("\nINVALID! data not fenched!\n");
     				}
     				
     			}
     			
     			} catch (Exception e) {
     				e.printStackTrace();
     				//System.out.println("error");
     			}
     		
     		     argu6=array;
     }
		 System.out.println();
		 for(int k=0;k<array.length;k++) {
  			System.out.println(array[k]);
  			
  		}
	 }
	 //***************************************************************//
	 @SuppressWarnings("resource")
	void getDetails4() throws IOException {
		
		 String ary[]=getArgu5();
		String ScoreAry[]=new String[getArgu5().length];
		java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 int counter=1;
		 System.out.println("--------------------------------------------------------------------------------------");
		 for(int j=0;j<4;j++) {
			
			if(j==0) {
				 try {
					 	
					 	pst=con.prepareStatement("select * from csvgetter1 where SAP=?");
						pst.setInt(1,argu2);
						ResultSet rset=pst.executeQuery();
						boolean count=false;
						while(rset.next()) {
							count=true;
							String COL1=rset.getString("SAP");
							String COL2=rset.getString("SCORE");
							String COL3=rset.getString("COL3");
							String COL4=rset.getString("COL4");
							String COL5=rset.getString("COL5");
							String COL6=rset.getString("COL6");
							String COL7=rset.getString("COL7");
							String COL8=rset.getString("COL8");
							System.out.println(COL1+","+COL2+","+COL3+","+COL4+","+COL5+","+COL6+","+COL7+","+COL8);
							String[] arr=new String[] {COL1,COL2,COL3,COL4,COL5,COL6,COL7,COL8};
							System.out.println("\nEnter Score coloumn no. for COURSE: "+getArgu5()[j]+"\n");
							String score;
							score=COL2;
							ScoreAry[j]=score.substring(0, 2);
							System.out.println(score);
						}
						if(count==false) {
							System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
				 if(j==1) {
					 try {
						 	
						 	pst=con.prepareStatement("select * from csvgetter2 where SAP=?");
							pst.setInt(1,argu2);
							ResultSet rset=pst.executeQuery();
							boolean count=false;
							while(rset.next()) {
								count=true;
								String COL1=rset.getString("SAP");
								String COL2=rset.getString("SCORE");
								String COL3=rset.getString("COL3");
								String COL4=rset.getString("COL4");
								String COL5=rset.getString("COL5");
								String COL6=rset.getString("COL6");
								String COL7=rset.getString("COL7");
								String COL8=rset.getString("COL8");
								System.out.println(COL1+","+COL2+","+COL3+","+COL4+","+COL5+","+COL6+","+COL7+","+COL8);
								String[] arr=new String[] {COL1,COL2,COL3,COL4,COL5,COL6,COL7,COL8};
								System.out.println("\nEnter Score coloumn no. for COURSE: "+getArgu5()[j]+"\n");
								String score;
								score=COL2;
								ScoreAry[j]=score.substring(0, 2);
								System.out.println(score);	
							}
							if(count==false) {
								System.out.println("\nINVALID, data not fenched ERROR @getDetails2\n");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					 
				 }
					 if(j==2) {
						 try {
							 	pst=con.prepareStatement("select * from csvgetter3 where SAP=?");
								pst.setInt(1,argu2);
								ResultSet rset=pst.executeQuery();
								boolean count=false;
								while(rset.next()) {
									count=true;
									String COL1=rset.getString("SAP");
									String COL2=rset.getString("SCORE");
									String COL3=rset.getString("COL3");
									String COL4=rset.getString("COL4");
									String COL5=rset.getString("COL5");
									String COL6=rset.getString("COL6");
									String COL7=rset.getString("COL7");
									String COL8=rset.getString("COL8");
									System.out.println(COL1+","+COL2+","+COL3+","+COL4+","+COL5+","+COL6+","+COL7+","+COL8);
									String[] arr=new String[] {COL1,COL2,COL3,COL4,COL5,COL6,COL7,COL8};
									System.out.println("\nEnter Score coloumn no. for COURSE: "+getArgu5()[j]+"\n");
									String score;
									score=COL2;
									ScoreAry[j]=score.substring(0, 2);
									System.out.println(score);		
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
								 	
								 	pst=con.prepareStatement("select * from csvgetter4 where SAP=?");
									pst.setInt(1,argu2);
									ResultSet rset=pst.executeQuery();
									boolean count=false;
									while(rset.next()) {
										count=true;
										String COL1=rset.getString("SAP");
										String COL2=rset.getString("SCORE");
										String COL3=rset.getString("COL3");
										String COL4=rset.getString("COL4");
										String COL5=rset.getString("COL5");
										String COL6=rset.getString("COL6");
										String COL7=rset.getString("COL7");
										String COL8=rset.getString("COL8");
										System.out.println(COL1+","+COL2+","+COL3+","+COL4+","+COL5+","+COL6+","+COL7+","+COL8);
										String[] arr=new String[] {COL1,COL2,COL3,COL4,COL5,COL6,COL7,COL8};
										System.out.println("\nEnter Score coloumn no. for COURSE: "+getArgu5()[j]+"\n");
										String score;
										score=COL2;
										ScoreAry[j]=score.substring(0, 2);
										System.out.println(score);
										
										}
									if(count==false) {
										System.out.println("\nINVALID, data not fenched ERROR @getDetails4\n");
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							
						 }
					 }
				 
		 System.out.println("--------------------------------------------------------------------------------------");
		 
		 argu7=ScoreAry;
		 for(int k=0;k<4;k++) {
		 System.out.print(ScoreAry[k]+" ");
		 }
		 
	 }
	 //**************************|ALGORITHM-5|***********************//
	 void algo5() {
		 //algorithm 5
		 
	 }
	 //**************************|ALGORITHM-3|**********************//
	 void algo3() {
		 //algorithm3
		//NOTE:get all data from 1-7 args!!
		 java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 try {
			 pst=con.prepareStatement("select SAP from login");
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true;
				 String SAP=res.getString("SAP");
				 System.out.println(SAP);
			
				 //res.close();
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		
		 try {
			 pst=con.prepareStatement("select fkt from algo3 where SAP='argu2'");
			 ResultSet resr=pst.executeQuery();
			 //pst.setInt(1,argu2);
			 boolean count=false;
			 while(resr.next()) {
				 count=true;
				 Float fkt=resr.getFloat("fkt");
				 System.out.println(fkt);
			     argu8.add(fkt);
			     
				 //res.close();
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
			 for(int i=0;i<argu8.size();i++) {
				 System.out.print(argu8.get(i));
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	 }
	public static String[] getArgu5() {
		return argu5;
	}
	public static void setArgu5(String argu5[]) {
		algo2.argu5 = argu5;
	}
	public static void main(String args[]) throws IOException {
		System.out.println("all fine!");
		algo3fun OBJ=new algo3fun();
    	algo2 obj=new algo2();
		obj.algo2();
		obj.algo2getter();
    	OBJ.savefm1();
		}	
}


