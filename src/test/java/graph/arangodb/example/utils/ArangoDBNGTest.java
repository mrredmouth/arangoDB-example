/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.arangodb.example.utils;

import com.arangodb.ArangoDriver;
import com.arangodb.ArangoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ranjeet
 */
public class ArangoDBNGTest {

    private final static Logger logger = LoggerFactory.getLogger(ArangoDBNGTest.class);
    private final static String dbName = "ArangoDBNGTest";

    public ArangoDBNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        ArangoDB arangoDB = new ArangoDB();
        ArangoDriver result = arangoDB.getArangoDriver();
        result.deleteDatabase(dbName);
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getArangoDriver method, of class ArangoDB.
     */
    @Test(priority = 0)
    public void testGetArangoDriver() {
        logger.info("MESSAGE", "getArangoDriver");
        ArangoDB instance = new ArangoDB();
        ArangoDriver expResult = null;
        ArangoDriver result = instance.getArangoDriver();
        assertNotEquals(result, expResult);

    }

    /**
     * Test of createDB method, of class ArangoDB.
     */
    @Test(priority = 1)
    public void testCreateDB() {
        try {
            logger.info("MESSAGE", "createDB");
            ArangoDB instance = new ArangoDB();
            ArangoDriver arangoDriver = instance.getArangoDriver();
            instance.createDB(dbName, arangoDriver);
        } catch (ArangoException ex) {
            logger.error("EXCEPTION", ex);
            fail(ex.getMessage());
        }

    }

    /**
     * Test of useDB method, of class ArangoDB.
     */
    @Test(priority = 2)
    public void testUseDB() {
        logger.info("MESSAGE", "useDB");
        ArangoDB instance = new ArangoDB();
        ArangoDriver arangoDriver = instance.getArangoDriver();
        instance.useDB(dbName, arangoDriver);

    }

}
