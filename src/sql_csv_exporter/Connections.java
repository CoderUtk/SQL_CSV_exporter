package sql_csv_exporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class Connections extends Exporter_FX_UI{

   

    public void set_connections() throws FileNotFoundException, IOException, ParseException {
        json_object = (JSONObject) parser.parse(new FileReader("Connections.json"));
        JSONObject database = (JSONObject) json_object.get(current_db);
        host = (String) database.get("Host");
        port = (String) database.get("Port");
        sid = (String) database.get("sid");
        username = (String) database.get("Username");
        password = (String) database.get("Password");
    }

    public void get_connections() throws FileNotFoundException, IOException, ParseException {
        json_object = (JSONObject) parser.parse(new FileReader("Connections.json"));
        Object[] keys = json_object.keySet().toArray();
        db_names = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            db_names[i] = keys[i].toString();
        }
    }

    void add_connection(String db_name, String host, String port, String sid, String username, String password) throws IOException {
        this.db_name = db_name;
        this.host = host;
        this.port = port;
        this.sid = sid;
        this.username = username;
        this.password = password;
        System.out.println(this.db_name);
        JSONObject newConnection = new JSONObject();
        newConnection.put("Host", host);
        newConnection.put("Port", port);
        newConnection.put("sid", sid);
        newConnection.put("Username", username);
        newConnection.put("Password", password);
        json_object.put(db_name, newConnection);
        FileWriter writer = new FileWriter("Connections.json");
        writer.write(json_object.toString());
        writer.flush();
        writer.close();
    }

    void read_query_from_file(File file) throws FileNotFoundException, IOException {
        System.out.println(file.toString());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line, temp = "";
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("--")) {
                    query += line;
                }
            }
        }
    }
}
