package ru.netology.travel.Date;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static String url= System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    public static void clearTables() {
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val deleteCreditEntity = "DELETE FROM credit_request_entity";
        val deleteOrderEntity = "DELETE FROM order_entity";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(
                url, user, password)
        ) {
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditEntity);
            runner.update(conn, deleteOrderEntity);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    public static String getPayStatus() throws SQLException {
        String statusSQL = "SELECT status FROM payment_entity";
        return getStatus(statusSQL);
    }

    public static String getCreditStatus() throws SQLException {
        String statusSQL = "SELECT status FROM credit_request_entity";
        return getStatus(statusSQL);
    }

    private static String getStatus(String query) throws SQLException {
        String result = "";
        val runner = new QueryRunner();
        try
                (val conn = DriverManager.getConnection(
                        url, user, password)
                ) {

             result = runner.query(conn, query, new ScalarHandler<String>());
            System.out.println(result);
            return result;
        }

    }
}

