package logichandle;

import entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogicUser {
    List<User> userList = new ArrayList<>();
    public void inputInforUser() {
        userList=readFileUser();
        System.out.println(userList);// in ra để xem đọc dữ liệu từ File
        User user = new User();
        user.createAcccount();
        user.infor();
        userList.add(user);
        writeFileUser(userList);
        System.out.println(userList);//in ra để xem lúc ghi xuống File ghi những gì.
    }


    public User searchUser(String userName, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getAccountName().compareTo(userName)==0
                    && userList.get(i).getPassword().compareTo(password)==0){
                return userList.get(i);
            }
        }
        return null;
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

}
