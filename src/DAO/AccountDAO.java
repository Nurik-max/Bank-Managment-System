package DAO;

import Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AccountDAO {

    public void addAccount(Account account){
        String addAccountSQL = "INSERT INTO Accounts(client_id, account_number, balance, created_at) VALUES ( ?, ?, ?, ?)";

        try(Connection connection = DB_Util.getConnection2();
            PreparedStatement addAccount = connection.prepareStatement(addAccountSQL)) {

            addAccount.setInt(1, account.getClient_id());
            addAccount.setString(2, account.getAccount_number());
            addAccount.setDouble(3, account.getBalance());
            addAccount.setDate(4,java.sql.Date.valueOf(account.getCreated_at()));
            addAccount.executeUpdate();

            System.out.println("Account added successfully");

        }catch (SQLException e){
            System.out.println("Error adding new account" + e.getMessage());
        }
    }
public void updateAccount(Account account){

        String updateAccountSQL = "UPDATE Accounts SET account_number = ?, balance = ? WHERE account_id = ?";
        try(Connection connection = DB_Util.getConnection2();
        PreparedStatement updateAccount = connection.prepareStatement(updateAccountSQL)){

            updateAccount.setString(1,account.getAccount_number());
            updateAccount.setDouble(2,account.getBalance());
            updateAccount.executeUpdate();

            int rows = updateAccount.executeUpdate();
            if(rows > 0){
                System.out.println("Account updated successfully!");
            }else {
                System.out.println("Account not found");
            }

        }catch (SQLException e){
            System.out.println("Error account updating" + e.getMessage());
        }

}
public Account getAccount(int getDataById){
        String getAccountSQL = "SELECT * FROM Accounts WHERE account_id = ?";
        try(Connection connection = DB_Util.getConnection2();
        PreparedStatement getAccount = connection.prepareStatement(getAccountSQL)){

            getAccount.setInt(1,getDataById);
            ResultSet rs = getAccount.executeQuery();

            if(rs.next()){
                String accountNum = rs.getString("account_number");
                double balance = rs.getDouble("balance");
                LocalDate created_at = rs.getDate("created_at").toLocalDate();

                return new Account(getDataById, accountNum, balance, created_at);
            }
            else {
                return null;
            }
        }catch (SQLException e){
            System.out.println("Error getting data about account" + e.getMessage());
            return null;
        }

}
    public void deleteAccount(int delID){

        String deleteAccountSQL = "DELETE FROM Accounts WHERE account_id = ?";
        try(Connection connection = DB_Util.getConnection2();
        PreparedStatement deleteAccount = connection.prepareStatement(deleteAccountSQL)) {

            deleteAccount.setInt(1,delID);
            deleteAccount.executeUpdate();
            System.out.println("Account deleted successfully");

        }catch (SQLException e){
            System.out.println("Error deleting account" + e.getMessage());
        }

    }
}
