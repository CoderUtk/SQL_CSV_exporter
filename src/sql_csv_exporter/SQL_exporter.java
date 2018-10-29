package sql_csv_exporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SQL_exporter extends Connections {

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
                    String outputCSVfile = "Output/" + fileName + "_" + formattedDate + ".csv";
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
                        for (int j = 1; j < columnsNumber; j++) {
                            if (rs.getString(j) == null) {
                                fwCSV.write("" + delimeter);
                            } else {
                                fwCSV.write(rs.getString(j) + delimeter);
                            }
                        }
                        if (rs.getString(columnsNumber) == null) {
                            fwCSV.write("");
                        } else {
                            fwCSV.write(rs.getString(columnsNumber));
                        }
                        fwCSV.write("\n");
                    }
                    fwCSV.flush();
                    fwCSV.close();
                    isComplete = true;
                    //JOptionPane.showMessageDialog(null,"FINISHED", "SQL CSV EXPORT", JOptionPane.INFORMATION_MESSAGE );
                } else {
                    System.out.println(sqlStatements[i] + "\n");
                    if (rs == null) {
                        System.out.println("-----------------\nSUCCESSFUL\n-----------------\n");
                        JOptionPane.showMessageDialog(null, "SUCCESSFUL", "SQL CSV EXPORT", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            } catch (IOException | SQLException e) {
                String error = "\n" + sqlStatements[i] + "\n" + "------------------------------------" + "\n" + e;
                JOptionPane.showMessageDialog(null, error, "SQL CSV EXPORT", JOptionPane.ERROR_MESSAGE);
                System.out.println(error);
            }
        }
    }

    public void export_to_excel() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con;
        String connection = "jdbc:oracle:thin:@";
        connection += host + ":" + port + ":" + sid;
        System.out.println(connection);
        System.out.println("SQL connection: " + connection);
        con = DriverManager.getConnection(connection, username, password);
        //Creating new Workbook
        DateFormat df = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
        String formattedDate = df.format(new java.util.Date());
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream out = new FileOutputStream(new File("Output/" + fileName + "_" + formattedDate + ".xlsx"));
        //query = "select * from SYSTEM_FEATURE_CONFIG;select * from SOLUTIONFLAGMASTER;";
        Statement statement;
        ResultSet rs;
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
                if (sqlStatements[i].toUpperCase().startsWith("SELECT")) {
                    statement.execute(sqlStatements[i]);
                    rs = statement.getResultSet();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    XSSFSheet spreadsheet = workbook.createSheet("Export " + i);
                    XSSFRow row = spreadsheet.createRow(0);
                    XSSFCell cell;
                    int columnsNumber = rsmd.getColumnCount();
                    for (int j = 1; j <= columnsNumber; j++) {
                        cell = row.createCell(j - 1);
                        cell.setCellValue(rsmd.getColumnName(j));
                    }
                    int row_counter = 1;
                    while (rs.next()) {
                        row = spreadsheet.createRow(row_counter);
                        for (int j = 1; j <= columnsNumber; j++) {
                            cell = row.createCell(j - 1);
                            if (rs.getString(j) == null) {
                                cell.setCellValue("");
                            } else {
                                cell.setCellValue(rs.getString(j));
                            }
                        }
                        row_counter++;
                    }
                }
            } catch (SQLException e) {

            }
        }
        workbook.write(out);
        out.close();
    }
}
