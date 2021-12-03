package Ant_Algo;

import java.sql.ResultSet;
import java.util.ArrayList;

import Ant_Pack.MysqlConnection;

public class phero5 {
//1. GET PHEROMONE
//2. GET HERUISTIC INFORMATION
//3. PATH FOLLOWED______T1->T2->T3->T4
//4. VALUES ALPHA,BETA,EVA. CONSTANT
	public final static int alpha=1; //parameter 1
	public final static int beta=3;  //parameter 2
	public final static double evapo_const=0.5; //parameter 3
	public final static float omega=2;//parameter 4
	public static  float localphero[]=new float[4];
  
	public static float[] getPhero() {
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
				
				float[] a=new float[tst1.length];
				float[] b=new float[tst1.length];
				float[] c=new float[tst1.length];
				float[] d=new float[tst1.length];
					for(int i=0;i<tst1.length;i++) {
						a[i]=(float)Integer.parseInt(tst1[i]);
								b[i]=(float)Integer.parseInt(tst2[i]);
									c[i]=(float)Integer.parseInt(tst3[i]);
										d[i]=(float)Integer.parseInt(tst4[i]);
						
					}
					float scr1 = 0,scr2 = 0,scr3 = 0,scr4 = 0;
					for(int i=0;i<tst1.length;i++) {
						
						 scr1=scr1+a[i]/10;
				    	 scr2=scr2+b[i]/10;
						 scr3=scr3+c[i]/10;
				    	 scr4=scr4+d[i]/10;   	 
						
					}
					float[] pheroRR=new float[4];
					pheroRR[0]=(float) scr1;
					pheroRR[1]=(scr1+scr2)/2;
				    pheroRR[2]=(scr1+scr2+scr3)/3;
					pheroRR[3]=(scr1+scr2+scr3+scr4)/4;
					System.out.println("\n----------------------------------------------------------------\n");
					System.out.println(pheroRR[0]+"--->"+pheroRR[1]+"--->"+pheroRR[2]+"--->"+pheroRR[3]); //at time t=0
					float[] huristic= heuristic();
					System.out.println("\n####################################\n");
					System.out.println(huristic[0]+"--->"+huristic[1]+"--->"+huristic[2]+"--->"+huristic[3]);
					float numerator[]=new float[4];
					for(int m=0;m<4;m++) {
						numerator[m]=(float) ((float) Math.pow(huristic[m], alpha) * Math.pow(pheroRR[m], beta));
					}
					float denominator=0;
					for(int m1=0;m1<4;m1++) {
						denominator=denominator+(float) ((float) Math.pow(huristic[m1], alpha) * Math.pow(pheroRR[m1], beta));
					}
					System.out.println("\n****************\n"+denominator);
					for(int m2=0;m2<4;m2++) {
						localphero[m2]=numerator[m2]/denominator;
						System.out.println(localphero[m2]);
					}
					//data kha send kre yeh??
					algo3fun objj=new algo3fun();
					 java.sql.PreparedStatement pstd;
					 
					 
						 try {
							 pstd=con.prepareStatement("insert into phero values(?,?,?,?,?,?)");
							 pstd.setInt(1,algo3fun.SAPID);
							 pstd.setFloat(2,localphero[0]);
							 pstd.setFloat(3,localphero[1]);
							 pstd.setFloat(4,localphero[2]);
							 pstd.setFloat(5,localphero[3]);
							 	float getans=localphero[0]+localphero[1]+localphero[2]+localphero[3];
							 	pstd.setFloat(6, getans);
							 	pstd.executeUpdate();
								 //res.close();
						 }
						 catch(Exception e) {
							 e.printStackTrace();
						 }
					 
					return localphero;
					
	}
	
	
	static float[] heuristic() {
	java.sql.Connection con;
    con=MysqlConnection.getConnection();
	 java.sql.PreparedStatement pst;
	
	 algo2 OBJ=new algo2();
	 String courses[]=OBJ.argu5;
	 char arr[]=new char[courses.length];
	 System.out.println("#########################");
	
	 float[] getter=new float[4];
	 float thitter[]=new float[4];
	 float thita1[]=new float[4];
	
	for(int k=0;k<4;k++) {
	 try {
			pst=con.prepareStatement("select * from courses where NAME=?");
			courses[k].replaceAll("\\s", "");
			pst.setString(1,courses[k]);
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
	for(int m=0;m<4;m++) {
	    heuristic[m]=(omega*thita1[m])+(1-omega)*thita2;
	    System.out.println(heuristic[m]);
	}
		 
		
		return heuristic;
	}
	
	
}
