package entity;

public class Admin extends Person {

    private final String position;
    public Admin(String account,String password,String position) {
        this.accountName = account;
        this.password= password;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "position='" + position + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
