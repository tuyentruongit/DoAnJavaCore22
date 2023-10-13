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
    LogicTypeMove logicTypeMove = new LogicTypeMove();

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
    }


    private void typeMovieAdmin() {
       while (true){
           System.out.println("-------------- Quản lý thể loại phim -------------");
           System.out.println("1. Hiển thị danh sách thể loại phim");
           System.out.println("2. Thêm thể loại phim");
           System.out.println("3. Xóa thể loại phim");
           System.out.println("4. Thoát");
           int choice = 0 ;
           do {
               try {
                   choice = new Scanner(System.in).nextInt();
               }catch (InputMismatchException e){
                   System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
               }
               if (choice>0 && choice<5){
                   break;
               }
               System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
           }while (true);
           switch (choice){
               case 1:
                   logicTypeMove.printTypeMovie();
                   break;
               case 2:
                   logicTypeMove.addTypeMovie();
                   break;
               case 3:
                   logicTypeMove.deleteTypeMovie();
                   break;
               case 4:
                   menuAdmin();
                   break;
           }
       }

    }

    private void menuAdmin() {
      while (true){
          System.out.println("***************************************************");
          System.out.println("*              Quản Lý Rạp Chiếu Phim             *");
          System.out.println("***************************************************");
          System.out.println("1. Thể Loại Phim");
          System.out.println("2. Đồ ăn/ Uống");
          System.out.println("3. Phim");
          System.out.println("4. Lich sử");
          System.out.println("5. Phòng chiếu phim");
          System.out.println("6. Tìm kiếm thông tin khách hàng");
          System.out.println("7. Báo cáo doanh thu");
          System.out.println("8. Đăng xuất");
          int choice = 0;
          do {
              try {
                  choice = new Scanner(System.in).nextInt();
              }catch (InputMismatchException e){
                  System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
              }
              if (choice>0 && choice<9){
                  break;
              }
              System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
          }while (true);
          switch (choice){
              case 1:
                  typeMovieAdmin();
                  break;
              case 2:

                  break;
              case 3:
                  menuMovie();
                  break;
              case 4:
                  break;
              case 5:

                  break;
              case 6:
                  break;
              case 7:
                  break;
              case 8:
                  logIn();
                  break;
          }
      }
    }

    private void menuMovie() {
        while (true){
            System.out.println("------ Quản lý phim ------");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xem danh sách phim");
            System.out.println("3. Xóa phim ");
            System.out.println("3. Thoát");
            int choice = 0;
            do {
                try{
                    choice = new Scanner(System.in).nextInt();
                }catch (InputMismatchException e){
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
                if (choice>0 && choice<4){
                    break;
                }
                System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }while (true);

            switch (choice) {
                case 1:
                    logicMovie.addMovie();
                    break;
                case 2:
                    logicMovie.printMovie();
                    break;
                case 3:
                    logicMovie.deleteMovie();
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
        if (movie!=null){
            System.out.println(movie);
            menuUser();
            return;
        }
        System.out.println("Không có tên phim nào như trên");
    }
    public   void menuUser(){
        System.out.println("***************************************************");
        System.out.println("*                     Trang Chủ                   *");
        System.out.println("***************************************************");
        System.out.println("1. Tìm kiếm ");
        System.out.println("2. Tìm Kiếm theo thể loại phim");
        System.out.println("3. Tất cả Phim ");
        System.out.println("4. Lịch sử đặt vé ");
        System.out.println("5 Tài Khoản");
        System.out.println("6. Đăng xuất");
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
                    logicMovie.searchTypeMovie();
                    break;
                case 3:
                    printAllMovie();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    logIn();
                    return;
            }
        }
    }
}
