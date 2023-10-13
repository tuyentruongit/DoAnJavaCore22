package logichandle;

import entity.Admin;
import entity.User;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class LogicAdmin {
    List<Admin> adminList = new ArrayList<>();
    public void  createAdmin(){
        Admin admin = new Admin("Tuyen90Hanam","tuyen90hn","Admin");
        adminList.add(admin);
    }
    public Admin searchAdmin(String adminName, String password ){
        createAdmin();
            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).getAccountName().compareTo(adminName)==0
                        && adminList.get(i).getPassword().compareTo(password)==0){
                    return adminList.get(i);
                }
            }
            return null;

    }
}
