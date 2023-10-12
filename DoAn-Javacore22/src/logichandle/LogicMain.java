package logichandle;

import entity.Admin;
import entity.Movie;
import entity.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LogicMain {
    LogicAdmin logicAdmin = new LogicAdmin();

    LogicUser logicUser = new LogicUser();
    LogicMovie logicMovie = new LogicMovie();

    public void run(){

        logInAndCreateAccount();
    }

    private void logInAndCreateAccount() {
        System.out.println("***************************************************");
        System.out.println("*         QUẢN LÝ RẠP CHIẾU PHIM VÀ BÁN VÉ        *");
        System.out.println("***************************************************");
        System.out.println("Bạn đã có tài khoản chưa ?\nVui lòng tạo mới nếu bạn chưa có tài khoản.");
        System.out.println("1. Tạo Tài Khoản Mới");
        System.out.println("2. Đăng Nhập ");
        System.out.print("Vui lòng chọn lưa chọn trên : ");
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
                    create();
                    break;
                case 2:
                    logIn();
                    break;
            }

    }

    private void create() {
        System.out.println("***************************************************");
        System.out.println("*      TẠO TÀI KHOẢN NGƯỜI DÙNG RẠP CHIẾU PHIM      *");
        System.out.println("***************************************************");
        logicUser.inputInforUser();
        logIn();
    }

    private void logIn() {
        System.out.println("***************************************************");
        System.out.println("*                ĐĂNG NHẬP PHẦN MỀM               *");
        System.out.println("***************************************************");
        System.out.println("Vui lòng đăng nhập để truy cập hệ thống.     ");
        do {
            System.out.print("Tên đăng nhập : ");
            String userName = new Scanner(System.in).nextLine();
            System.out.print("Mật khẩu : ");
            String password = new Scanner(System.in).nextLine();
            Admin admin = logicAdmin.searchAdmin(userName,password) ;
            User user = logicUser.searchUser(userName,password);
            if (admin!=null){
                menuAdmin();
                break;
            }
            if (user!=null){
                menuUser();
                break;
            }
            System.out.println("Tên tài khoản hoặc mật khẩu không đúng vui lòng nhập lại ");
        }while (true);
        choiceMenu();
    }

    private void menuAdmin() {
    }

    private void choiceMenu(){
       while (true){
           int chcoiceFuntion = 0;
           do {
               try {
                   chcoiceFuntion = new Scanner(System.in).nextInt();
               }
               catch (InputMismatchException e){
                   System.out.println("Đã sảy ra lỗi, Bạn vui lòng chọn lại  ");
               }
               if (chcoiceFuntion>0&&chcoiceFuntion<11){
                   break;
               }
               System.out.println("Đã sảy ra lỗi, Bạn vui lòng chọn lại  ");

           }while (true);
           switch (chcoiceFuntion){
               case 1:
                   choiceFuntion1();
                   break;
               case 2:
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 5:
                   break;
               case 6:
                   break;
               case 7:
                   printAllMovie();
                   break;
               case 8:
                   menuUser();
                   break;
               case 9:
                   break;
               case 10:
                   break;

           }
       }
    }

    private void printAllMovie() {
        logicMovie.printMovie();
    }

    private void choiceFuntion1() {
        System.out.println("Nhập tên phim mà bạn muốn tìm ");
        String nameMovie = new Scanner(System.in).nextLine();
        Movie movie = logicMovie.searchMovie(nameMovie);
        if (movie==null){
            System.out.println("Không có tên phim nào như trên");
        }
        menuUser();
    }
    public   void menuUser(){
        System.out.println("***************************************************");
        System.out.println("*                     Trang Chủ                   *");
        System.out.println("***************************************************");
        System.out.println("1. Tìm kiếm ");
        System.out.println("");
        System.out.println(" Thể Loại phim ");
        System.out.print("2 Phim Hành Động ");
        System.out.print("3 Phim Hài ");
        System.out.println("4 Phim Gia Đình ");
        System.out.print("5 Phim Kinh Dị ");
        System.out.println("6 Phim Hoạt Hình ");
        System.out.println("");
        System.out.println("7. Tất cả Phim ");
        System.out.println("");
        System.out.println("****************************************************");
        System.out.println("* 8. Trang chủ    9. lịch sử đặt vé   10. Tài Khoản *");
        System.out.println("****************************************************");
    }
}
