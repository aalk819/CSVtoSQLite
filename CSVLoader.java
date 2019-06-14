package com.csvtosqlite;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.util.StringUtils;

public class CSVLoader {

	private static String JDBC_CONNECTION_URL = 
			"jdbc:sqlite:C:/sqlite/test.db";

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String csvFile = "c:/sqlite/tryout.csv";
	        BufferedReader br = null;
	        String line = "";
	       String cvsSplitBy = ",";
	        boolean HeadRowExists = true;
	        int AcceptedNumberofColumns = 10;
	        int IncorrectRecords = 0;
	        String[] csvtosqlite = null;
	      //Create List for holding csvtosqlite objects
            List<CSVtosqlite> CsvList = new ArrayList<CSVtosqlite>();
	        
	        try {
				br = new BufferedReader(new FileReader(csvFile));
				
				           
	            if(HeadRowExists) {
	            	String HeadRow = br.readLine();
	         
	            	if(HeadRow==null || HeadRow.isEmpty()) {
	            		throw new FileNotFoundException(
	        					"No columns defined in given CSV file." +
	        					"Please check the CSV file format.");
	            	}
	            }
	            
				 while ((line = br.readLine()) != null) {

		                // use comma as separator
		                csvtosqlite = line.split(cvsSplitBy);
		                
		                if(csvtosqlite.length > 0 && csvtosqlite.length == AcceptedNumberofColumns)
		                {
		                    //Save the csvtosqlite details in CSVtosqlite object
		                    CSVtosqlite csv = new CSVtosqlite(csvtosqlite[0],
		                    		csvtosqlite[1],csvtosqlite[2],csvtosqlite[3],
		                            csvtosqlite[4],csvtosqlite[5],csvtosqlite[6],csvtosqlite[7],csvtosqlite[8],csvtosqlite[9]);
		                    CsvList.add(csv);
		                }
		                else {
		                	
		                	IncorrectRecords++;
		                }   
				 }
	                
	                LoadCSVintoDatabase(CsvList);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	        System.out.println("Following are the statistics :\n#"+
	        		csvtosqlite.length+"Number of records received.\n#"+
	        		CsvList.size()+" Number of records processed.\n#"+
	        		IncorrectRecords+"Number of records failed.");
	}

	private static void LoadCSVintoDatabase(List<CSVtosqlite> CsvList) {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		boolean tableExists = true;
		boolean truncateTable = true;
		
		try {
			//Class.forName("org.sqlite.JDBC");
			
			connection = DriverManager.getConnection(JDBC_CONNECTION_URL);
			
			if(tableExists != true) {
				connection.createStatement().execute("CREATE TABLE x (A, B, C, D, E, F, G, H, I, J)");
			}
			
			if(truncateTable == true) {
				connection.createStatement().execute("DELETE FROM x");
			}
				
			
			PreparedStatement stmt =
					connection.prepareStatement("INSERT INTO x (A, B, C, D, E, F, G, H, I, J)
								    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			for(CSVtosqlite c : CsvList)
            {
				
				stmt.setString(1, c.getA());
				stmt.setString(2, c.getB());
				stmt.setString(3, c.getC());
				stmt.setString(4, c.getD());
				stmt.setString(5, c.getE());
				stmt.setString(6, c.getF());
				stmt.setString(7, c.getG());
				stmt.setString(8, c.getH());
                                stmt.setString(9, c.getI());
                                stmt.setString(10, c.getJ());
				
				stmt.executeUpdate();
            }
			
			System.out.println("Result of SELECT\n");
			
			ResultSet rs = connection.createStatement().executeQuery("select * from x");
			
			while(rs.next()) {
				String A = rs.getString(1);
				String B = rs.getString(2);
				String C = rs.getString(3);
				String D = rs.getString(4);
				String E = rs.getString(5);
				String F = rs.getString(6);
				String G = rs.getString(7);
				String H = rs.getString(8);
                                String I = rs.getString(9);
                                String J = rs.getString(10);
				
				System.out.println(A+"\t"+B+"\t"+C+"\t"+D+"\t"+E+"\t"
				+F+"\t"+G+"\t"+H+"\t"+I+"\t"+J);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
