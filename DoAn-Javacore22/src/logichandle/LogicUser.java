package logichandle;

import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicUser {
    List<User> userList = new ArrayList<>();
    public void inputInforUser() {
        File file = new File("account.data");
        if (file.exists()){
            userList=readFileUser();
        }
        User user = new User();
        System.out.println(userList);
        System.out.print("Nhập tên đăng nhập của bạn : ");
        String accountName;
        do {
            accountName = new Scanner(System.in).nextLine();
            if (userList.isEmpty()){
                user.setAccountName(accountName);
                break;
            }
            if (searchAccount(accountName)){
                user.setAccountName(accountName);
                break;
            }
            System.out.println("Tài khoản đã tồn tại, vui lòng nhập lại tên tài khoản");
        }while (true);
        do {
            System.out.print("Nhập mật khẩu của bạn : ");
            String password = new Scanner(System.in).nextLine();
            System.out.print("Nhập lại mật khẩu của bạn : ");
            String retypePassword = new Scanner(System.in).nextLine();
            if (password.compareTo(retypePassword)==0){
                user.setPassword(password);
                break;
            }
            System.out.println("Mật khẩu không khớp vui lòng nhập lại");
        }while (true);
        user.infor();
        userList.add(user);
        writeFileUser(userList);
        System.out.println(userList);//in ra để xem lúc ghi xuống File ghi những gì.
    }

    private boolean searchAccount(String accountName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAccountName().equalsIgnoreCase(accountName)){
                return false;
            }
        }
        return true;
    }


    public User searchUser(String userName, String password) {
        startReadFileUser();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAccountName().equalsIgnoreCase(userName)
                    && userList.get(i).getPassword().equalsIgnoreCase(password)){
                return userList.get(i);
            }
        }
        return null;
    }

    private void startReadFileUser() {
        File file = new File("account.data");
        if (file.exists()){
            userList=readFileUser();
        }
    }

    public   void writeFileUser(  List<User> userList ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("account.data"));
            writeFile.writeObject(userList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public    List<User>   readFileUser()  {

        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("account.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (  List<User>  )  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchClient() {
        File file = new File("account.data");
        if (file.exists()){
            userList=readFileUser();
        }
        System.out.println("Nhập tên khách hàng mà bạn muốn tìm kiếm");
        String name = new Scanner(System.in).nextLine();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equalsIgnoreCase(name)){
                System.out.println(userList.get(i));
            }
        }
    }

    public void inforAccount() {
        System.out.println("-------------- Tài Khoản -------------");
        System.out.println("1. Đổi mật khẩu ");
        System.out.println("2. Đăng Xuất");
        System.out.println("3. Trở Lại");
        System.out.println("Bạn vui lòng chọn những chức năng trên");
        int choice;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                if (choice>0 && choice <4){
                    break;
                }
                System.out.println("Vui lòng nhập lại dữ liệu");
            }catch (InputMismatchException e){
                System.out.println("Vui lòng nhập lại dữ liệu");
            }
        }while (true);
        switch (choice){
            case 1:
                changePassword();
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void changePassword() {
        String name;
        String passwordOld;
        String passwordNew;
        String passwordNew1;
        do {
            System.out.println("Nhập tên đăng nhập ");
             name = new Scanner(System.in).nextLine();
            System.out.println("Nhập mật khẩu cũ ");
             passwordOld =  new Scanner(System.in).nextLine();
            System.out.println("Nhập mật khẩu mới");
             passwordNew =  new Scanner(System.in).nextLine();
            System.out.println("Nhập lại mật khẩu mới");
             passwordNew1=  new Scanner(System.in).nextLine();
            for (int i = 0; i <userList.size() ; i++) {
                if (userList.get(i).getAccountName().equals(name)){
                    if ((userList.get(i).getPassword().equals(passwordOld) && passwordNew.equals(passwordNew1))){
                        userList.get(i).setPassword(passwordNew);
                        System.out.println("Đổi Mật khẩu Thành công");
                        break;
                    }

                }if (!userList.get(i).getAccountName().equals(name)){
                    System.out.println("Không có tên đăng nhập nào như trên");
                }
            }

        }while (true);


    }
    public void nextId(int nextID){
        startReadFileUser();
        for (int i = 0; i < userList.size(); i++) {
            if (nextID==userList.get(i).getIdUser()){
                nextID++;
            }

        }
    }
}
