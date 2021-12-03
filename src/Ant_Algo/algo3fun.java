package Ant_Algo;

import static Ant_Algo.algo2.argu6;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import Ant_Pack.MysqlConnection;

//import Ant_Algo.MysqlConnection;



public class  algo3fun {

	static int SAPID;
	static float fktt;
	public ArrayList<String> argu1=new ArrayList<String>();
	public ArrayList<Integer> sap=new ArrayList<Integer>();
	public ArrayList<Integer> klvl=new ArrayList<Integer>();
	
	int i=0;
	void getSAPandCOURSE() {
		java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		 try {
			 pst=con.prepareStatement("select SAP,GOAL,KLEVEL from login");
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true; 
				 int SAP=res.getInt("SAP");
				 String GOAL=res.getString("GOAL");
				 int kl=res.getInt("KLEVEL");
				 System.out.println(SAP +" : "+GOAL+" LEVEL: "+kl);
			     argu1.add(i,GOAL);
			     sap.add(i,SAP);
			     klvl.add(i,kl);
			     i++;
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
	int[] getTestSet() {
		java.sql.Connection con;
	    con=MysqlConnection.getConnection();
		java.sql.PreparedStatement pst;
		ArrayList<String> testres = new ArrayList<String>();
		/*
		 * int[] tst1=new int[4];
		int[] tst2=new int[4];
		int[] tst3=new int[4];
		int[] tst4=new int[4];
		 */
	String tst1[]=new String[4];
	String tst2[]=new String[4];
	String tst3[]=new String[4];
	String tst4[]=new String[4];
		
		int[] finaltestset=new int[4];
					for(int j=0;j<4;j++) {		
						if(j==0) {
							 try {		
								 System.out.println("Triyingggggggggggggggggggggggggggggg");
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
									System.out.println("zattuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
									if(count==false) {
										System.out.println("\nINVALID, data not fenched ERROR @csvgetter1\n");
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
											System.out.println("\nINVALID, data not fenched ERROR @csvgetter2\n");
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
					//NOW ADD ALL THE TESTSET OF COURSES AND PUT THEM WITH HASHMAPS [INCORRECT]
					//NOW DO ONE THING CREATE ARRAY OF TEST SETS AND BY INDEXING ADD THE RES. SET
					/*
					 * 
					 * for(int j=0;j<4;j++) {
						
						finaltestset[j]=tst1[j]+tst2[j]+tst3[j]+tst4[j];
					}
					 */
				
					int returner[]=new int[tst4.length];
					for(int i=0;i<tst4.length;i++) {
						returner[i]=Integer.parseInt(tst1[i])+Integer.parseInt(tst2[i])+Integer.parseInt(tst3[i])+Integer.parseInt(tst4[i]);
					}
					return returner;
}

    float[] getweightfactorr() {//new user weight factors e1 ad e2
    	//return 2 floats
    	System.out.println("\n*********strater*********\n");
    algo2 ObJ=new algo2();
    String[] getres=algo2.argu7;
  for(int i=0;i<getres.length;i++) {
	  System.out.println(getres[i]+"-->");
  }
    int returner[]=new int[getres.length];
    int sum=0;
    String[] Stringer=new String[getres.length];
	for(int i=0;i<getres.length;i++) {
		Stringer[i]=getres[i].substring(0,2);
		  
		Stringer[i]=Stringer[i].replaceAll("\\s", "");
		returner[i]=Integer.parseInt(Stringer[i]);
		sum=sum+returner[i];
	}
	float sumper=(float)sum/80*100; //net percentage
	sumper=sumper/2;// weight factor weight=50%
	System.out.println("\n@@"+sumper);
	//******* knowledge level ********//
	float knowledgeper=(float) 0.0;
	int klvl=ObJ.argu4;
	if(klvl==1) {
		knowledgeper=(float) 20.00;
	}
	else if(klvl==2) {
		knowledgeper=(float) 30.00;
	}
	else if(klvl==3) {
		knowledgeper=(float) 50.00;
	}
	else {
		knowledgeper=(float) 0.00;
	}
	//course lvl ::------::
	String[] arr=argu6;
	char[] a=new char[arr.length];
	double[] wf2=new double[arr.length];

	for(int k=0;k<arr.length;k++) {
		//String a=arr[k++].substring(0);
	
	     String check=arr[k];   
	     
		if("E".equals(check)) {
		   wf2[k]=20.00;
		}
		if("M".equals(check)) {
			wf2[k]=30.00;
		}
		if("H".equals(check)) {
			wf2[k]=50.00;
		}
		else
			wf2[k]= 0.00;
	}
	float sum2=0;
	for(int kk=0;kk<wf2.length;kk++) {
		sum2=(float) (sum2+wf2[kk]);
	}
	float wf22=sum2/200*100;
	System.out.println("@@"+wf22);
	
	float returner1=sumper+knowledgeper;
	returner1=returner1/100;
	System.out.println("\n#"+returner1+"\n");
	float returner2=sum2+sumper;
	returner2=returner2/100;
	System.out.println("\n#"+returner2+"\n");
	float[] arrayy= {returner1,returner2};
    return arrayy;
    }
	@SuppressWarnings("null")
	void formulaP1(float fm1,int SAP,int klvl,int klvl2,int part32,float e1,float e2) {
		//pic sap id
		algo2 obj=new algo2();
		
	    //got 1
		String GOAL=obj.argu1;
		//got 2
	    String getgoal = null;
	    try {
	    	java.sql.Connection con;
			 con=MysqlConnection.getConnection();
			 java.sql.PreparedStatement pst;
			 pst=con.prepareStatement("select * from login where SAP=?");
			 pst.setInt(1,SAP);
			 ResultSet res=pst.executeQuery();
			 boolean count=false;
			 while(res.next()) {
				 count=true;		 
				 String goal=res.getString("GOAL");		
				 getgoal=goal;
			
				 //res.close();
			 }
			 if(count==false) {
				 System.out.println("\nERROR @ getDetails1! DATA NOT FENCHED!!\n");
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 int part3[]=getTestSet();
		 //SIMILAR COURSE fkt
		 if(obj.argu1.equals(getgoal) ) {
			 System.out.println( "COURSE MAPPED! ##############################");
				System.out.println("info of SAP- "+SAP+" is :"+fm1+" ,knowledge level- "+klvl+" / "+klvl2+" ,and test score is: "+part32+" , weightfactor 1,2: "+e1+ " and "+e2);
				float part1=(float)SAP;
				float part2=(float)Math.abs(klvl2-klvl2);
				float fkt=e1*(fm1+part2)+e2*(part32);
					System.out.println(fkt);
					int array[]=new int[2];
					array[0]=SAP;
					SAPID=array[0];
					array[1]=(int)fkt;
					fktt=array[1];
					java.sql.PreparedStatement pst;
					java.sql.Connection con;
					con=MysqlConnection.getConnection();
					try {
						pst=con.prepareStatement("insert into algo3 values(?,?)");
						pst.setInt(1, SAPID);
						pst.setFloat(2, fktt);
						pst.executeUpdate();
					} catch (Exception e) {
						e.printStackTrace();
					}
		 }
		 else {
			 System.out.println("Course not mapped!");
		 }
		
	}

	void savefm1() {
		java.sql.PreparedStatement pst;
		java.sql.Connection con;
		con=MysqlConnection.getConnection();
		try {
			pst=con.prepareStatement("TRUNCATE algo3");
			
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	void compareGoal() throws IOException {
		//System.out.println("INSIDE COMPAREGOAL");
		algo2 obj=new algo2();
		String newGoal=obj.argu1; //NEW USER GOAL
		System.out.println(newGoal);
		int newSap=obj.argu2; //NEW USER SAP
		System.out.println(newSap);
		int newLevel=obj.argu4;//NEW USER LEARNING GOAL LEVEL
		System.out.println(newLevel);
		int[] part3=getTestSet();
		float[] weightfactor=new float[2];
		weightfactor=getweightfactorr();
		float E1=weightfactor[0];
		float E2=weightfactor[1];
		 java.sql.Connection con;
		 con=MysqlConnection.getConnection();
		 java.sql.PreparedStatement pst;
		
		for(int i=0;i<argu1.size();i++) { //ALL USERS GOAL
			//comparing GOAL
			if(newGoal.equals(argu1.get(i))==true && newSap==sap.get(i) &&newLevel==klvl.get(i)) {
				
				formulaP1(1,sap.get(i),newLevel,klvl.get(i),part3[i],E1,E2);//SK-ST(0<-->1)
				}
			else
				formulaP1(0,sap.get(i),newLevel,klvl.get(i),part3[i],E1,E2);
			}
		 
		}
 	}
