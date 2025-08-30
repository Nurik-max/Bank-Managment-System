package Model;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Transaction {
    private int account_id;
    private int id;
    private String operation_type;
    private double amount;
    private Timestamp created_at;
    //Method for taking from table

//Getters

    public int getAccount_id() {
        return account_id;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getCreated_at(){
        return created_at;
    }
    //setters


    public void setId(int id) {
        this.id = id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setAmount(double amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public void setOperation_type(String operation_type) {
        this.operation_type = operation_type;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void validateAmount(Double amount1){
        if(amount1 == null){
            throw new IllegalArgumentException("Invalid amount");
        }

    }
    @Override
    public String toString(){
        return "Transaction data for: " + created_at +
                "\t|operation type = " + operation_type +
                "\t| amount = " + amount + "\n";

    }
}
