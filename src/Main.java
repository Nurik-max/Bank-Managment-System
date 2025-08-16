import DAO.PaymentDAO;
import Model.Account;
import Model.Client;
import DAO.ClientDAO;
import Model.Payment;
import Service.AccountService;
import Service.ClientService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import Service.PaymentService;

public class Main {
    public static void main(String[] args) {

        DAO.ClientDAO clientDAO = new DAO.ClientDAO();
        ClientService clientService = new ClientService();

        PaymentService paymentService = new PaymentService();
        AccountService accountService = new AccountService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose with what you want work. (Clients, Payments, Accounts)");
        String generalChoice = scanner.nextLine();
while (true) {
    if (generalChoice.equalsIgnoreCase("Clients")) {
        while (true) {

            System.out.println("---Client Manager---");
            System.out.println("1. Add client");
            System.out.println("2. Show all client");
            System.out.println("3. Update data about client");
            System.out.println("4. Delete data about client");
            System.out.println("5. Get data about client");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Passport: ");
                    String passport = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Phone: ");
                    String phone = scanner.nextLine();

                    clientService.addClient(name, passport, phone, email);


                    break;

                case 2:
                    clientService.getAllClients();
                    break;

                case 3:

                    System.out.print("Enter client ID which you want update: ");
                    int updID = scanner.nextInt();
                    scanner.nextLine();
                    Client existingClient = clientService.findClientByiD(updID);

                    if (existingClient == null) {
                        System.out.println("Client not found");
                        return;
                    }
                    System.out.println(existingClient);
                    System.out.print("Enter new name (or press Enter to keep): ");
                    String name1 = scanner.nextLine();

                    System.out.print("Enter new passport (or press Enter to keep): ");
                    String passport_1 = scanner.nextLine();

                    System.out.print("Enter new phone (or press Enter to keep): ");
                    String phone1 = scanner.nextLine();


                    System.out.print("Enter new email (or press Enter to keep): ");
                    String email1 = scanner.nextLine();


                    // Обновляем клиента
                    clientService.updateClient(updID, name1, passport_1, phone1, email1);

                    break;

                case 4:
                    System.out.println("Enter client ID which you want delete: ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    Client deletingClient = clientDAO.getClient(clientId);

                    if (deletingClient == null) {
                        System.out.println("Client not found");
                        return;
                    }

                    clientService.deleteClient(clientId);
                    System.out.println(deletingClient);

                    break;

                case 5:
                    System.out.println("Enter client ID which you want get: ");
                    int client_Id = scanner.nextInt();
                    scanner.nextLine();

                    Client getingClient = clientService.findClientByiD(client_Id);

                    if (getingClient == null) {
                        System.out.println("Client not found");
                        return;
                    }
                    System.out.println(getingClient);
                    break;
                case 0:
                    System.out.println("Program closed");
                    return;
            }
        }


    } else if (generalChoice.equalsIgnoreCase("Payments")) {

        while (true) {
            System.out.println("---Payments Manager");
            System.out.println("1. Add payment");
            System.out.println("2. Update payment");
            System.out.println("3. Get data about payment");
            System.out.println("4. Get date all payments");
            System.out.println("5. Delete payment");
            System.out.println("0. Exit");


            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter client ID: ");
                    int clientID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter amount for that client: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("If you want you can enter payment description: ");
                    String payDescription = scanner.nextLine();

                    System.out.println("Enter payment date (yyyy-MM-dd) or press Enter for today:");
                    String dateInput = scanner.nextLine();

                    LocalDate paymentDate;
                    if (dateInput.trim().isEmpty()) {
                        // Если пользователь ничего не ввёл — ставим текущую дату
                        paymentDate = LocalDate.now();
                    } else {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            paymentDate = LocalDate.parse(dateInput, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format! Using today's date.");
                            paymentDate = LocalDate.now();
                        }
                    }
                    paymentService.addPayment(clientID, amount, paymentDate, payDescription);


                    break;

                case 2:
                    System.out.print("Enter payment ID which you want update: ");
                    int updID = scanner.nextInt();
                    scanner.nextLine();
                    Payment existingPayment = paymentService.getPayment(updID);

                    if (existingPayment == null) {
                        System.out.println("Payment not found");
                        return;
                    }
                    System.out.println(existingPayment);
                    System.out.print("Enter new amount (or press Enter to keep): ");
                    String updAmount = scanner.nextLine();
                    Double amount1 = null;
                    if(updAmount.isEmpty()){
                        amount1 = existingPayment.getAmount();

                    }else {
                        amount1 = Double.parseDouble(updAmount);
                    }

                    System.out.print("Enter new description (or press Enter to keep): ");
                    String description = scanner.nextLine();

                    paymentService.updatePayment(updID, amount1, description);

                    break;

                case 3:
                    System.out.println("Enter the client ID which you want get date: ");
                    int getID = scanner.nextInt();

                    Payment gettingPay = paymentService.getPayment(getID);
                    if (gettingPay == null) {
                        System.out.println("Client not found");
                        return;
                    }
                    System.out.println(gettingPay);

                    break;

                case 4:
                    paymentService.getAllPay();
                    break;

                case 5:
                    System.out.println("Enter client ID which you want delete payment: ");
                    int client_Id = scanner.nextInt();
                    scanner.nextLine();
                    Payment deletingPayment = paymentService.getPayment(client_Id);

                    if (deletingPayment == null) {
                        System.out.println("Client not found");
                        return;
                    }

                    paymentService.deletePay(client_Id);
                    System.out.println(deletingPayment);
                    break;

                case 0:
                    System.out.println("Program closed");
                    return;

            }
        }
    } else if (generalChoice.equalsIgnoreCase("Accounts")){
        while (true) {
            System.out.println("---Account Manager");
            System.out.println("1. Add account");
            System.out.println("2. Update account");
            System.out.println("3. Get data about account");
            System.out.println("4. Get date all account");
            System.out.println("5. Delete account");
            System.out.println("0. Exit");


            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){

                case 1:
                    System.out.println("Enter client ID: ");
                    int clientID = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter amount for that client: ");
                    double amount1 = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Enter account_number: ");
                    String accountNumber = scanner.nextLine();

                    System.out.println("Enter payment date (yyyy-MM-dd) or press Enter for today:");
                    String dateInput = scanner.nextLine();

                    LocalDate createdDate;
                    if (dateInput.trim().isEmpty()) {
                        // Если пользователь ничего не ввёл — ставим текущую дату
                        createdDate = LocalDate.now();
                    } else {
                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            createdDate = LocalDate.parse(dateInput, formatter);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format! Using today's date.");
                            createdDate = LocalDate.now();
                        }
                    }
                    accountService.addAccount(clientID, accountNumber,amount1, createdDate);
                    break;

                case 2:
                    System.out.print("Enter account ID which you want update: ");
                    int updID = scanner.nextInt();
                    scanner.nextLine();
                    Account existingAccount = accountService.getAccount(updID);

                    if (existingAccount== null) {
                        System.out.println("Account not found");
                        return;
                    }
                    System.out.println(existingAccount);
                    System.out.print("Enter new account number (or press Enter to keep): ");
                    String accountNum = scanner.nextLine();

                    System.out.println("Enter new balance (or press Enter to keep): ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    accountService.updateAccount(updID,accountNum,balance);

                case 5:
                    System.out.println("Enter account ID which you want delete: ");
                    int delId = scanner.nextInt();
                    scanner.nextLine();
                    accountService.deleteAccount(delId);

            }
        }
    }

}
    }
}

