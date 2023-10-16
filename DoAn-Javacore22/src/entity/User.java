package entity;

import statics.Gender;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User extends Person implements InputInfor, Serializable {
    private static int nextIdUser = 10000;
    private int idUser;
    protected String name ;
    private int ago;
    private String address;
    private String phone ;
    private Gender gender;



    public User() {
        this.idUser=nextIdUser;
        nextIdUser++;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgo() {
        return ago;
    }

    public void setAgo(int ago) {
        this.ago = ago;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public static void setNextIdUser(int nextIdUser) {
        User.nextIdUser = nextIdUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", ago=" + ago +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", gender=" + gender.value +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public void infor() {
        System.out.println("Nhập tên của bạn : ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhập tuổi của bạn : ");
        this.setAgo(new Scanner(System.in).nextInt());
        System.out.println("Nhập địa chỉ  của bạn : ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.println("Nhập số điện thoại của bạn : ");
        do {
            do {
                try {
                    this.setPhone(new Scanner(System.in).nextLine());
                    break;
                }
                catch (InputMismatchException e){
                    System.out.println("Dữ liệu bạn vừa nhập không đúng, Vui lòng nhập lại");
                }
            }while (true);
            if (regexPhoneNumber(getPhone())){
                break;
            }
            System.out.println("So dien thoai ban vua nhap khong dung vui long nhap lai");
        }while (true);
        System.out.println("Nhập giới tính của bạn : ");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        choiceGender();
    }

    private boolean regexPhoneNumber(String phone) {
        String regex =  "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        if(phone.matches(regex)){
            return true;
        }
        return false;
    }

    public void createAcccount(){
        System.out.print("Nhập tên đăng nhập của bạn : ");
        this.setAccountName(new Scanner(System.in).nextLine());
        do {
            System.out.print("Nhập mật khẩu của bạn : ");
            String password = new Scanner(System.in).nextLine();
            System.out.print("Nhập lại mật khẩu của bạn : ");
            String retypePassword = new Scanner(System.in).nextLine();
            if (password.compareTo(retypePassword)==0){
                this.setPassword(password);
                break;
            }
            System.out.println("Mật khẩu không khớp vui lòng nhập lại");
        }while (true);
    }

    private void choiceGender() {
        int choice;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                if (choice>0&&choice<3){
                    break;
                }
                System.out.println("Bạn vui lòng chọn 1 trong 2 lựa chọn trên");
            }catch (InputMismatchException e){
                System.out.println("Dữ liệu bạn vừa nhập không đúng, Vui lòng nhập lại :");
            }
        }while (true);
        switch (choice){
            case 1:
                this.setGender(Gender.NAM);
                break;
            case 2:
                this.setGender(Gender.NU);
                break;
        }
    }
}
