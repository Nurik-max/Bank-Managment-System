package DAO;
import Model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientDAO {


    Scanner scanner = new Scanner(System.in);

    public void addClient(Client client){
        String sql = "INSERT INTO Clients (full_name, passport_number, phone, email) VALUES(?, ?, ?, ?)";
            try(Connection connection = DB_Util.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql)){

                pstmt.setString(1, client.getName());
                pstmt.setString(2, client.getPassport());
                pstmt.setString(3, client.getPhone());
                pstmt.setString(4, client.getEmail());
                pstmt.executeUpdate();

                System.out.println("Client added successfully");

            }catch (SQLException e){
                System.out.print("Error updating client: " + e.getMessage());
            }


    }

    public void updateClient(Client client){
        String sql = "UPDATE Clients SET full_name = ?, passport_number = ?, phone = ?, email = ? WHERE id = ?";

        try (Connection conn = DB_Util.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPassport());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getEmail());
            stmt.setInt(5, client.getId());
            stmt.executeUpdate();

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Client updated");
            } else {
                System.out.println("⚠️ Client not found");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error updating client: " + e.getMessage());
        }
    }

    public List<Client> getAllClient(){
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Clients";

        try(Connection connection = DB_Util.getConnection();
        Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){

                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("passport_number"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                clients.add(client);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return clients;
    }
    public void deleteClient(int id){
        try {
            String deleteSQL = "DELETE FROM Clients WHERE id = ?";
            try (Connection conn = DB_Util.getConnection();
                 PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL)) {


                deleteStmt.setInt(1, id);
                deleteStmt.executeUpdate();
                System.out.println("Client with id: " + id + " deleted!");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error adding contact: " + e.getMessage());
        }

    }
    public Client getClient(int id){
        String getData = "SELECT * FROM Clients WHERE id = ?";
        try(Connection connection = DB_Util.getConnection();
        PreparedStatement getPstmt  = connection.prepareStatement(getData);) {

            getPstmt.setInt(1, id);
            ResultSet rs = getPstmt.executeQuery();

            if(rs.next()){

                String name = rs.getString("full_name");
                String passport = rs.getString("passport_number");
                String email = rs.getString("email");
                String phone = rs.getString("phone");


                return new Client(id, name, passport, phone, email);
            } else{
                return null;
            }

        }catch (SQLException e){

            System.out.println("Error getting data about client" + e.getMessage());
            return null;
        }

    }

    }
