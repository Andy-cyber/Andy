package dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseStructure {

    private static final String CREATE_SHOPS1 = "CREATE TABLE shops1(\n"+
            "sh_id integer PRIMARY KEY,\n" +
            " name VARCHAR(20) ,\n " +
            "address VARCHAR(30)\n"+");";

    private static String DROP_SHOPS1 = "DROP TABLE shops1";

    public static void createTables() throws DBConnectionException, SQLException {
        DBS connectionManager = new DBS();
        try (Connection connection = connectionManager.createConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_SHOPS1);

        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public static void dropTables() throws SQLException, DBConnectionException {
        DBS connectionManager = new DBS();
        try (Connection connection = connectionManager.createConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(DROP_SHOPS1);
        }
        catch(SQLException | DBConnectionException ex){
            throw new SQLException(ex.getMessage());
        }
    }

}