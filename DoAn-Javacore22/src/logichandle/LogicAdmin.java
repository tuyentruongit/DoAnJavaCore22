package logichandle;

import entity.Admin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicAdmin {
    LogicService logicService = new LogicService();
    List<Admin> adminList = new ArrayList<>();
    public void  createAdmin(){
        Admin admin = new Admin("Tuyen90Hanam","tuyen90hn","Admin");
        adminList.add(admin);
    }
    public Admin searchAdmin(String adminName, String password ){
        createAdmin();
            for (int i = 0; i < adminList.size(); i++) {
                if (adminList.get(i).getAccountName().equals(adminName)
                        && adminList.get(i).getPassword().equals(password)){
                    return adminList.get(i);
                }
            }
            return null;

    }


    public void menuService() {
      while (true){
          System.out.println("---------- Quản Lý Đồ Ăn Uống ---------");
          System.out.println("1. Thêm Đồ Ăn, Thức Uống");
          System.out.println("2. Xóa Đồ Ăn , Xóa Thức Uống");
          System.out.println("3. Xem danh sách tất cả đồ ăn uống");
          System.out.println("4. Tìm kiếm ");
          System.out.println("5. Thoát");
          System.out.println("Bạn vui lòng chọn những tính năng trên");
          int choice = 0;
          do {
              try {
                  choice = new Scanner(System.in).nextInt();
                  if (choice>0 && choice<=5){
                      break;
                  }
                  System.out.println("Không có chức năng như bạn vừa nhập, Vui lòng nhập lại");
              }catch (InputMismatchException e){
                  System.out.println("Dữ liệu nhập vào không hợp lệ, Vui lòng nhập lại");
              }

          }while (true);
          switch (choice){
              case 1:
                  logicService.addService();
                  break;
              case 2:
                  logicService.deleteService();
                  break;
              case 3:
                  logicService.printAllService();
                  break;
              case 4:
                  logicService.searchService();
                  break;
              case 5:
                  return;
          }
      }
    }
}
