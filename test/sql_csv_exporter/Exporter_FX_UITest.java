/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql_csv_exporter;

import javafx.stage.Stage;
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
public class Exporter_FX_UITest {
    
    public Exporter_FX_UITest() {
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
     * Test of launch_fxml method, of class Exporter_FX_UI.
     */
    @Test
    public void testLaunch_fxml() {
        System.out.println("launch_fxml");
        Exporter_FX_UI instance = new Exporter_FX_UI();
        instance.launch_fxml();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class Exporter_FX_UI.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        Stage primaryStage = null;
        Exporter_FX_UI instance = new Exporter_FX_UI();
        instance.start(primaryStage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
