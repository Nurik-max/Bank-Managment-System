package Model;
import java.sql.Date;
import java.time.LocalDate;
public class Payment {

    private int id;
    private int clientId;
    private double amount;
    private LocalDate paymentDate;
    private String description;


    public Payment(int clientId, double amount, LocalDate paymentDate, String description){

        validateAmount(amount);
        this.id = -1;
        this.clientId = clientId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.description = description;

    }
    public Payment(int id, int client_Id, double amount1, LocalDate date, String description){
        validateAmount(amount1);

        if(id <= 0) throw new IllegalArgumentException("Invalid ID");
        this.id = id;
        this.clientId = client_Id;
        this.amount = amount1;
        this.paymentDate = date;
        this.description =description;
    }
//Getters
    public int getId(){return id;}
    public int getClientId(){return clientId;}
    public double getAmount(){return amount;}
    public LocalDate getPaymentDate(){return paymentDate;}
    public String getDescription(){
        return description;
    }

//Setters
   /* public void setId(int id1){

        this.id = id1;
    }*/

    public void setClientId(int clientId1){
        this.clientId = clientId1;
    }

    public void setAmount(double amount1){
        validateAmount(amount1);
        this.amount = amount1;
    }
    public void setPaymentDate(LocalDate paymentDate1){
        this.paymentDate =paymentDate1;
    }
    public void setDescription(String description1){
        this.description = description1;
    }
//Validation
    private void validateAmount(double amount1){
        if(amount1 <= 0){
            throw new IllegalArgumentException("Amount cannot be less zero!");
        }
    }

    @Override
    public String toString(){
        return "Payment data :" +
                "\nid = " + id +
                "\nclient_id = '" + clientId + '\'' +
                "\namount = '" + amount + '\'' +
                "\npayment_date = '" + paymentDate + '\'' +
                "\ndescription = '" + description + '\'' +
                "\n ";
    }
}

