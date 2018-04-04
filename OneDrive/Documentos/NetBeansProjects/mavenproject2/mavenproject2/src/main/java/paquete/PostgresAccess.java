package paquete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresAccess {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            connect = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/pruebaDF",
                            "postgres", "Samsung4660");

            connect.setAutoCommit(false);

            //connect = DriverManager.getConnection("jdbc:mysql://localhost/rlt?" + "user=root&password=Sony4660");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostgresAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connect;
    }

    public ArrayList<DocentePAAE> getAllDocentes_BD() throws Exception {
        ArrayList<DocentePAAE> docentesPAAE = new ArrayList<>();

        try {
            // Setup the connection with the DB
            connect = getConnection();
            System.out.println("bbbb");
            preparedStatement = connect.prepareStatement("SELECT * FROM prueba.\"DocentePAAE\";");

            resultSet = preparedStatement.executeQuery();
            docentesPAAE = writeResultSetDocente(resultSet);

        } catch (SQLException e) {
            throw e;
        } finally {
            close();
        }

        return docentesPAAE;
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private ArrayList<DocentePAAE> writeResultSetDocente(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        ArrayList<DocentePAAE> arr = new ArrayList<>();
        while (resultSet.next()) {
            // It is possible to get the columns via name also possible to get the columns via the column number which starts at 1
            // e.g. resultSet.getSTring(2);
            String nombre = resultSet.getString("nombre");
            String rfc = resultSet.getString("rfc");
            String apPaterno = resultSet.getString("apPaterno");
            String numTrabajador = resultSet.getString("numTrabajador");

            arr.add(new DocentePAAE(nombre, apPaterno, numTrabajador, rfc));
        }
        return arr;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {

        }
    }

}
