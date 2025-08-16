package Model;

import java.time.LocalDate;

public class Account {

    private int account_id;
    private int client_id;
    private String account_number;
    private double balance;
    private LocalDate created_at;

    public Account(int clientId, String accountNumber, double balance1, LocalDate createdAt){
validateAccountNumber(accountNumber);
validateBalance(balance1);

this.account_id = -1;
this.client_id = clientId;
this.account_number = accountNumber;
this.balance = balance1;
this.created_at = createdAt;
    }
    public Account(int accountId, int clientId, String accountNumber1, double balance2, LocalDate createdAt1){

if(accountId <= 0) {
    throw new IllegalArgumentException("Invalid ID");
}
    validateAccountNumber(accountNumber1);
    validateBalance(balance2);
    this.account_id = accountId;
    this.client_id = clientId;
    this.account_number = accountNumber1;
    this.balance = balance2;
    this.created_at = createdAt1;


        //Getters
    }
    public int getAccountID(){
        return account_id;
    }

    public int getClient_id(){
        return client_id;
    }

    public String getAccount_number(){
        return account_number;
    }

    public double getBalance(){
        return balance;
    }

    public LocalDate getCreated_at(){
        return created_at;

    }

    //Setters

    public void setAccount_id(int accountId){
        this.account_id = accountId;

    }

    public void setClient_id(int client_id1){
        this.client_id = client_id1;
    }

    public void setAccount_number(String accountNumber1){
        validateAccountNumber(accountNumber1);
        this.account_number = accountNumber1;
    }

    public void setBalance(double balance2){
validateBalance(balance2);
        this.balance = balance2;
    }

    public void setCreated_at(LocalDate createdAt){
        this.created_at = createdAt;
    }

    public void validateAccountNumber(String account_num){

        if(account_num == null || account_num.length() < 10){
            throw new IllegalArgumentException("Invalid account number");
        }
    }
    public void validateBalance(Double balance){
        if (balance == null || balance < 0){
            throw new IllegalArgumentException("Invalid balance");
        }
    }
}
