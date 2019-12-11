import dataBase.DBConnectionException;
import dataBase.DataBaseStructure;
import org.testng.annotations.Test;
import java.sql.SQLException;

public class TestDataBaseStructure {

    @Test
    public void testCreateTables() throws DBConnectionException, SQLException {
        try { DataBaseStructure.dropTables(); } catch (Exception ignored) { }
        DataBaseStructure.createTables();
    }

    @Test
    public void testDropTables() throws SQLException, DBConnectionException {
        try { DataBaseStructure.createTables(); } catch (Exception ignored) { }
        DataBaseStructure.dropTables();
    }


}