package DAO;


import Model.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDAO {
    Transaction transaction = new Transaction();
    public void addTransaction(int accountId, String type, double amount) {
        String sql = "INSERT INTO Transactions (account_id, operation_type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DB_Util.getConnection3(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setString(2, type);
            stmt.setDouble(3, amount);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Error adding: " + e.getMessage());
        }
    }

    public List<Transaction> getTransactionsByAccount(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions WHERE account_id = ?";
        try (Connection conn = DB_Util.getConnection3();PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt("id"));
                t.setAccount_id(rs.getInt("account_id"));
                t.setOperation_type(rs.getString("operation_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setCreated_at(rs.getTimestamp("created_at"));

                transactions.add(t);
                System.out.println(t);
            }

        }
        catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
        return transactions;
    }

}
