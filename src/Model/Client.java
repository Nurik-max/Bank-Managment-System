package Model;

public class Client {

    private final int id;
    private String name;
    private String passport;
    private String phone;
    private String email;

        // Constructor for creating new client
public Client(String name, String passport, String phone, String email){

    validateName(name);
    validateEmail(email);
    validatePhone(phone);

    this.id = -1; //special value for "not have in Data_Base"
    this.name = name;
    this.passport = passport;
    this.email = email;
    this.phone = phone;
}

public Client(int id,String name, String passport, String phone, String email){

    if(id <= 0){
        throw new IllegalArgumentException("Invalid ID");
    }

    validateName(name);
    validatePassport(passport);
    validateEmail(email);
    validatePhone(phone);

    this.id = id;
    this.name =name;
    this.passport = passport;
    this.phone =  phone;
    this.email = email;
}
// Only 'getters' for get data
public int getId(){
    return id;
    }

public String getName(){
    return name;
}

public String getPassport(){
    return passport;
}

public String getPhone(){
    return phone;
}

public String getEmail(){
    return email;
}

//safely 'setters' for entering data
// we not have 'setter' for ID because
// he already entered in table DB(AUTO_INCREMENT)
public void setName(String name){
    validateName(name);
    this.name = name;
}

public void setPassport(String passport){
    validatePassport(passport);
    this.passport = passport;
}
public void setPhone(String phone){
    validatePhone(phone);
    this.phone = phone;
}

public void setEmail(String email){
    validateEmail(email);
    this.email =email;
}
public void updateClient(String newPhone, String newEmail){
    validatePhone(newPhone);
    validateEmail(newEmail);

    this.phone = newPhone;
    this.email = newEmail;
}

        //Private methods for value validation
private void validateName(String name){
    if(name == null || name.trim().isEmpty()){

        throw new IllegalArgumentException("Name cannot be is empty!");
    }

}

private  void validatePassport(String passport){
    if(passport == null || passport.trim().isEmpty()){
        throw new IllegalArgumentException("Passport cannot be is empty!");
    }
}
private void validateEmail(String email){
    if(email == null || !email.contains("@")){
        throw new IllegalArgumentException("Invalid email address!");
    }

}
private void validatePhone(String phone){
    if(phone == null || phone.length() < 5){

        throw new IllegalArgumentException("Invalid phone number!");
    }
}
    @Override
    public String toString() {
        return "Client data :" +
                "\nid = " + id +
                "\nname = '" + name + '\'' +
                "\npassport = '" + passport + '\'' +
                "\nphone = '" + phone + '\'' +
                "\nemail = '" + email + '\'' +
                "\n ";
    }



}
