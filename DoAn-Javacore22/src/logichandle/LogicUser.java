package logichandle;

import entity.User;

import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.sound.midi.MidiFileFormat;
import java.io.*;
import java.util.*;

public class LogicUser {
//    LogicBuyMovieTicket logicBuyMovieTicket;
    LogicMovie logicMovie;
    List<User> userList = new ArrayList<>();
    public void inputInforUser() {
        startReadFileUser();
        User user = new User();
        createAccount(user);
        user.setIdUser(nextId());
        user.infor();
        userList.add(user);
        writeFileUser(userList);
    }

    private void createAccount(User user) {
        System.out.print("Nhập tên đăng nhập của bạn : ");
        String accountName;
        do {
            accountName = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
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
            String password =new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
            System.out.print("Nhập lại mật khẩu của bạn : ");
            String retypePassword =new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
            if (password.compareTo(retypePassword)==0){
                user.setPassword(password);
                break;
            }
            System.out.println("Mật khẩu không khớp vui lòng nhập lại");
        }while (true);
    }
    private boolean searchAccount(String accountName) {
        startReadFileUser();
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
        startReadFileUser();
        System.out.println("Nhập tên khách hàng mà bạn muốn tìm kiếm");
        String name = new Scanner(System.in).nextLine();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getName().equalsIgnoreCase(name)){
                System.out.println(userList.get(i));
            }
        }
    }

    public void inforAccount() {
        while (true){
            System.out.println("-------------- Tài Khoản -------------");
            System.out.println("1. Đổi mật khẩu ");
            System.out.println("2. Trở Lại");
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
                    return;
            }
        }
    }

    private void changePassword() {
        String name;
        String passwordOld;
        String passwordNew;
        String passwordNew1;
        do {
            System.out.println("Nhập tên đăng nhập ");
             name = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
            System.out.println("Nhập mật khẩu cũ ");
             passwordOld = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
            System.out.println("Nhập mật khẩu mới");
             passwordNew = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
            System.out.println("Nhập lại mật khẩu mới");
             passwordNew1=  new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
            for (int i = 0; i <userList.size() ; i++) {
                if (userList.get(i).getAccountName().equals(name)){
                    if ((userList.get(i).getPassword().equals(passwordOld) && passwordNew.equals(passwordNew1))){
                        userList.get(i).setPassword(passwordNew);
                        writeFileUser(userList);
                        System.out.println("Đổi Mật khẩu Thành công");
                        return;
                    }

                }if (!userList.get(i).getAccountName().equals(name)){
                    System.out.println("Không có tên đăng nhập nào như trên");
                }
            }

        }while (true);


    }
    public int nextId(){
        startReadFileUser();
        int a = 10000;
        for (int i = 0; i < userList.size(); i++) {
            for (int j = 10000; j <99999 ; j++) {
                if (j==userList.get(i).getIdUser()){
                    break;
                }
                a=i;
            }
        }
        return a;
    }
}
