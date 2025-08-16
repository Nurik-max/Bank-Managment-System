package Service;
import DAO.ClientDAO;
import Model.Client;

public class ClientService {
    private ClientDAO clientDAO = new ClientDAO();

    public void addClient(String name, String passport, String phone, String email) {
        Client client = new Client(name, passport, phone, email);
        clientDAO.addClient(client);
    }

    public void updateClient(int id, String newName, String newPassport, String newPhone, String newEmail) {
        Client client = clientDAO.getClient(id);

        if (client == null) {
            System.out.println("Client not found");
            return;

        }
        //Entering old data if new value the empty
        if (newName.isEmpty()) newName = client.getName();
        if(newPassport.isEmpty()) newPassport = client.getPassport();
        if (newEmail.isEmpty()) newEmail = client.getEmail();
        if (newPhone.isEmpty()) newPhone = client.getPhone();

        //updating client
        client.setName(newName);
        client.setPassport(newPassport);
        client.setEmail(newEmail);
        client.setPhone(newPhone);
        clientDAO.updateClient(client);

    }

    public void deleteClient(int id){
        clientDAO.deleteClient(id);
    }

    public void getAllClients(){
        var clients = clientDAO.getAllClient();
        for(Client client: clients){

            System.out.println(client.getId() + " | " + client.getName() + " | " + client.getPassport() + " | " +  client.getEmail() + " | " + client.getPhone() );
        }
    }

    public Client findClientByiD(int id){

        return clientDAO.getClient(id);
    }
}
