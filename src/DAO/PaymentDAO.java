package DAO;
import Model.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    public void addPayment(Payment payment) {
        String addPaymentSQL = "INSERT INTO Payments ( client_id, amount, payment_date, description) VALUES ( ?, ?, ?, ?)";
        try (Connection connection = DB_Util.getConnection1();
             PreparedStatement addPay = connection.prepareStatement(addPaymentSQL)) {

            addPay.setInt(1, payment.getClientId());
            addPay.setDouble(2, payment.getAmount());
            addPay.setDate(3, java.sql.Date.valueOf(payment.getPaymentDate()));
            addPay.setString(4, payment.getDescription());

            addPay.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error adding payment!" + e.getMessage());
        }

    }

    public void updatePayment(Payment payment) {

        String updatePaySQL = "UPDATE Payments SET amount = ?, description = ? WHERE id = ?";
        try (Connection connection = DB_Util.getConnection1();
             PreparedStatement updPay = connection.prepareStatement(updatePaySQL)) {

            updPay.setDouble(1, payment.getAmount());
            updPay.setString(2, payment.getDescription());
            updPay.setInt(3, payment.getId());
            updPay.executeUpdate();

            int rows = updPay.executeUpdate();
            if (rows > 0) {
                System.out.println("Payment updated!");
            } else {
                System.out.println("Payment not found");
            }

        } catch (SQLException e) {
            System.out.println("Error updating payment!" + e.getMessage());
        }
    }

    public Payment getPayment(int Id) {
        String getPaySQL = "SELECT * FROM Payments WHERE id = ?";

        try (Connection connection = DB_Util.getConnection1();
             PreparedStatement getPay = connection.prepareStatement(getPaySQL)) {

            getPay.setInt(1, Id);
            ResultSet rs = getPay.executeQuery();

            if (rs.next()) {
                int getID = rs.getInt("id");
                int getClientID = rs.getInt("client_id");
                double getAmount = rs.getDouble("amount");
                LocalDate pay_date = rs.getDate("payment_date").toLocalDate();
                String description = rs.getString("description");

                return new Payment(getID, getClientID, getAmount, pay_date, description);


            } else {
                return null;
            }

        } catch (SQLException e) {

            System.out.println("Payment not found: " + e.getMessage());
            return null;
        }
    }

    public void deletePayment(int delID) {
        String deletePaySQL = "DELETE FROM Payments WHERE id = ?";
        try (Connection connection = DB_Util.getConnection1();
             PreparedStatement deletePay = connection.prepareStatement(deletePaySQL)) {

            deletePay.setInt(1, delID);
            deletePay.executeUpdate();
            System.out.println("Payment with id: " + delID + " deleted!");


        } catch (SQLException e) {
            System.out.println("Error deleting payment: " + e.getMessage());
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String getALlPaySQL = "SELECT * FROM Payments";

        try (Connection connection = DB_Util.getConnection1();
             Statement getAllPay = connection.createStatement(); ResultSet rs = getAllPay.executeQuery(getALlPaySQL)) {

            while (rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("id"),
                        rs.getInt("client_id"),
                        rs.getDouble("amount"),
                        rs.getDate("payment_date").toLocalDate(),
                        rs.getString("description")
                );


                payments.add(payment);
            }

        } catch (SQLException e) {
            System.out.println("Error getting all payments: " + e.getMessage());
        }
return payments;
    }

}
