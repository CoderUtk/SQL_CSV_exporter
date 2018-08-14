/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_csv_exporter;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author umagrawal
 */
public class ConnectionsTest {
    
    public ConnectionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of set_connections method, of class Connections.
     */
    @Test
    public void testSet_connections() throws IOException, FileNotFoundException, ParseException {
        System.out.println("set_connections");
        Connections instance = new Connections();
        instance.set_connections();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_connections method, of class Connections.
     */
    @Test
    public void testGet_connections() throws IOException, FileNotFoundException, ParseException {
        System.out.println("get_connections");
        Connections instance = new Connections();
        instance.get_connections();
    }
    
}
