/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_csv_exporter;

import java.io.IOException;
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
     * Test of read_connections method, of class Connections.
     */
    @Test
    public void testRead_connections() throws Exception {
        System.out.println("read_connections");
        Connections instance = new Connections();
        instance.read_connections();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_input method, of class Connections.
     */
    @Test
    public void testGet_input() throws IOException {
        System.out.println("get_input");
        Connections instance = new Connections();
        instance.get_input();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
