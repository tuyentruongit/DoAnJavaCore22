package logichandle;

import Service.FileService;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogicUser {
    List<User> userList = new ArrayList<>();
    public void inputInforUser() {
        User user = new User();
        user.createAcccount();
        user.infor();
        userList.add(user);
        FileService fileService = new FileService();
        fileService.writeFile(userList);
        List<User> users  =   fileService.readFile();
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

}
