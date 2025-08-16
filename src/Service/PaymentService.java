package Service;

import DAO.PaymentDAO;
import Model.Payment;

import java.time.LocalDate;

public class PaymentService {

    private PaymentDAO paymentDAO = new PaymentDAO();
    private double amount;

    public void addPayment(int client_id, double amount, LocalDate payment_date, String description){

        Payment payment = new Payment(client_id, amount, payment_date, description);
        paymentDAO.addPayment(payment);
    }

    public void updatePayment(int paymentID, Double amount, String description){
        Payment payment = paymentDAO.getPayment(paymentID);
        if(payment == null){
            System.out.println("Payment not found");
            return;
        }

         //amount = payment.getAmount();
        if(description.isEmpty()) description = payment.getDescription();


        payment.setAmount(amount);
        payment.setDescription(description);

        paymentDAO.updatePayment(payment);
    }

    public Payment getPayment(int id){


        return paymentDAO.getPayment(id);
    }

    public void deletePay(int del_ID){
        paymentDAO.deletePayment(del_ID);
    }

    public void getAllPay(){
        var payments = paymentDAO.getAllPayments();
        for(Payment payment: payments){
            System.out.println(payment.getId() + " | " + payment.getClientId() + " | " + payment.getAmount() + " | " + payment.getPaymentDate() + " | " + payment.getDescription() + " | ");
        }
    }
}
