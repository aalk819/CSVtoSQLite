package app.test.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.CSVReader;

public class CSVLoader {
	public static void main(String[] args) throws ClassNotFoundException {
		Connection connection = null;
		try {
			// load the sqlite-JDBC driver using the current class loader
			Class.forName("org.sqlite.JDBC");
			
			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			
			CSVLoader csvReader = new CSVLoader();
			
			BufferedReader br = new BufferedReader(new FileReader(csvReader.getFileFromResources("ms3Interview.csv")));
			CSVReader reader = new CSVReader(br);
			
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists Interview");
			statement.executeUpdate("create table Interview(A string, B string, C string, D string, E string, F string, G string, H string, I string, J string)");
			
			
			PreparedStatement pstmt = connection.prepareStatement("insert into Interview(A, B, C, D, E, F, G, H, I, J) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			int receivedCount = 0;
			int successCount = 0;
			int failureCount = 0;
			String[] nextRow;
			
			while ((nextRow = reader.readNext()) != null) {
				receivedCount++;
				
				if(nextRow.length == 10){
					successCount++;
					pstmt.setString(1, nextRow[0]);//A
					pstmt.setString(2, nextRow[1]);//B
					pstmt.setString(3, nextRow[2]);//C
					pstmt.setString(4, nextRow[3]);//D
					pstmt.setString(5, nextRow[4]);//E
					pstmt.setString(6, nextRow[5]);//F
					pstmt.setString(7, nextRow[6]);//G
					pstmt.setString(8, nextRow[7]);//H
					pstmt.setString(9, nextRow[8]);//I
					pstmt.setString(10, nextRow[9]);//J
					
					pstmt.execute();
				}else{
					failureCount++;
				}
			}
			
			
			System.out.println("# of records received : "+receivedCount);
			System.out.println("# of records successful : "+successCount);
			System.out.println("# of records failed : "+failureCount);
			
						
			pstmt.close();
			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}		
	}
	
    // get file from classpath, resources folder
    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
