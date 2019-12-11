import java.sql.Connection;
import dataBase.DBConnectionException;
import dataBase.DBS;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TestConnectionManager {

    private DBS dbc;

    @BeforeTest
    public void beforeTest() {
        dbc = new DBS();
    }

    @Test
    public void testConnection() throws DBConnectionException {
        Connection connection = dbc.createConnection();
        Assert.assertNotNull(connection);
    }

}
