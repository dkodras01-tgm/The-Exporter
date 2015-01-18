package kodras;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Start {
	
	public static void main(String[] args) {
		MyCLI cli = new MyCLI(args);
		Connect con = new Connect(cli.getHost(), cli.getDB(), cli.getUser(), cli.getPasswd());
		String query = "";
		if(cli.getFelder().equals(".classpath") || cli.getFelder().equals("\\*")) {
			query = "SELECT * FROM " + cli.getTabelle();
		} else {
			query = "SELECT " + cli.getFelder() + " FROM " + cli.getTabelle();
		}
		
		if(!cli.getBedinnung().equals("")) {
			query += " WHERE " + cli.getBedinnung();
		}
		
		if(!cli.getSort().equals("")) {
			query += " ORDER BY " + cli.getSort() + " " + cli.getSortierrichtung();
		}
		
		query += ";";
		
		Statement st;
		if(cli.getAusgabe()==null) {
			try {
				st = Connect.conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				int col = rsmd.getColumnCount();
				System.out.println("\n\n");
				for(int i = 1; i <= col; i++) {
					System.out.print(rsmd.getColumnName(i) + cli.getTrennzeichen());
				}
				System.out.println("\n");
				
				while(rs.next()) {
					
					for(int i = 1; i <= col; i++) {
						System.out.print(rs.getString(i) + cli.getTrennzeichen());
					}
					System.out.println();
				}
				rs.close();
				rs = null;
			} catch (SQLException e) {
				System.out.println("Fehler: " + e.getMessage());
			}
		} else {
			try {
				
				File file = new File(cli.getAusgabe() + ".txt");
				file.createNewFile();
				PrintWriter fw = new PrintWriter(file, "UTF-8");
				
				st = Connect.conn.createStatement();
				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				
				int count = rsmd.getColumnCount();
				
				for(int i = 1; i <= count; i++) {
					fw.print(rsmd.getColumnName(i) + cli.getTrennzeichen());
				}
				fw.println();
				
				while(rs.next()) {
					
					for(int i = 1; i <= count; i++) {
						
						fw.print(rs.getString(i) + cli.getTrennzeichen());
					}
					fw.println();
				}
				
				fw.close();
				rs.close();
				rs = null;
				
				System.out.println("\n\nEs wurde " + file.getAbsolutePath() + " erfollgreich erstellt.");
			} catch (IOException e) {
				System.out.println("Fehler: " + e.getMessage());
			} catch (SQLException e) {
				System.out.println("Fehler: " + e.getMessage());
			}
		}
	}
}