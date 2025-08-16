package Service;

import DAO.AccountDAO;
import Model.Account;

import java.time.LocalDate;

public class AccountService {
    AccountDAO accountDAO = new AccountDAO();
    public void addAccount(int clientID, String accountNum, double balance, LocalDate createdAt){
        Account account = new Account(clientID, accountNum, balance, createdAt);
        accountDAO.addAccount(account);
    }

    public void updateAccount(int updID, String acc_number, Double balance2){
        Account account = accountDAO.getAccount(updID);
        if (account == null){
            System.out.println("Account not found");
            return;
        }

        if(acc_number.isEmpty()) acc_number = account.getAccount_number();
        if(balance2 == null) balance2 = account.getBalance();

        account.setAccount_number(acc_number);
        account.setBalance(balance2);
        accountDAO.updateAccount(account);
    }
    public Account getAccount(int getId){
       return accountDAO.getAccount(getId);
    }

    public void deleteAccount(int accId){
        accountDAO.deleteAccount(accId);
    }
}
