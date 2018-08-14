/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_csv_exporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class SQL_exporter extends Connections {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        new Exporter_FX_UI().launch_fxml();
    }
    
    public void export() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con;
        String connection = "jdbc:oracle:thin:@";
        connection += host + ":" + port + ":" + sid;
        System.out.println(connection);
        System.out.println("SQL connection: " + connection);
        con = DriverManager.getConnection(connection, username, password);
        Statement statement;
        ResultSet rs;
        DateFormat df = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
        String formattedDate = df.format(new java.util.Date());
        DateFormat df2 = new SimpleDateFormat("ddMMMhhmm");
        String formattedDate2 = df2.format(new java.util.Date());
        String[] sqlStatements = query.split(";");
        statement = con.createStatement();
        String temp = "";
        for (int i = 0; i < sqlStatements.length; i++) {
            try {
                temp = "" + formattedDate2;
                sqlStatements[i] = sqlStatements[i].toUpperCase().replaceFirst("_BK", temp);
                System.out.println(sqlStatements[i]);
                statement.execute(sqlStatements[i]);
                rs = statement.getResultSet();
                if (sqlStatements[i].toUpperCase().startsWith("SELECT")) {
                    statement.execute(sqlStatements[i]);
                    rs = statement.getResultSet();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    String outputCSVfile = "Output/" + "CSV_export" + "_" + formattedDate + ".csv";
                    File file = new File(outputCSVfile);
                    FileWriter fwCSV;
                    fwCSV = new FileWriter(file);
                    int columnsNumber = rsmd.getColumnCount();
                    for (int j = 1; j < columnsNumber; j++) {
                        fwCSV.write(rsmd.getColumnName(j) + delimeter);
                    }
                    fwCSV.write(rsmd.getColumnName(columnsNumber));
                    fwCSV.write("\n");
                    while (rs.next()) {
                        for (int j = 1; j <columnsNumber; j++) {
                            if(rs.getString(j)== null)
                                fwCSV.write("" + delimeter);
                            else
                                fwCSV.write(rs.getString(j) + delimeter);  
                        }
                       if(rs.getString(columnsNumber)== null)
                            fwCSV.write("");
                        else
                            fwCSV.write(rs.getString(columnsNumber));
                        fwCSV.write("\n");
                    }
                    fwCSV.flush();
                    fwCSV.close();
                    JOptionPane.showMessageDialog(null,"FINISHED", "SQL CSV EXPORT", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    System.out.println(sqlStatements[i] + "\n");
                    if (rs == null) {
                        System.out.println("-----------------\nSUCCESSFUL\n-----------------\n");
                        JOptionPane.showMessageDialog(null,"SUCCESSFUL", "SQL CSV EXPORT", JOptionPane.INFORMATION_MESSAGE );
                    }
                }
                
            } catch (IOException | SQLException e) {
                String error = "\n" + sqlStatements[i] + "\n" + "------------------------------------" + "\n" + e ;
                JOptionPane.showMessageDialog(null,error, "SQL CSV EXPORT", JOptionPane.ERROR_MESSAGE );
                System.out.println(error);
            }
        }
    }
}
