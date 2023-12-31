package logichandle;

import entity.*;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicMain {
    LogicMovie logicMovie = new LogicMovie();
    LogicRoom logicRoom = new LogicRoom();
    LogicService logicService = new LogicService();
    LogicTypeMove logicTypeMove = new LogicTypeMove();
    LogicShowTime logicShowTime = new LogicShowTime(logicRoom,logicMovie);
    LogicUser logicUser = new LogicUser();
    LogicAdmin logicAdmin = new LogicAdmin(logicService,logicTypeMove,logicRoom,logicShowTime,logicMovie);
    LogicBuyMovieTicket logicBuyMovieTicket = new LogicBuyMovieTicket(logicMovie,logicShowTime,logicAdmin);
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
                return;
            }
            if (user!=null){
                menuUser();
                break;
            }
            System.out.println("Tên tài khoản hoặc mật khẩu không đúng vui lòng nhập lại ");
        }while (true);
    }
    private void menuAdmin() {
        while (true){
            System.out.println("***************************************************");
            System.out.println("*              Quản Lý Rạp Chiếu Phim             *");
            System.out.println("***************************************************");
            System.out.println("1. Thể Loại Phim");
            System.out.println("2. Đồ ăn/ Uống");
            System.out.println("3. Phim");
            System.out.println("4. Lich sử mua vé của khách hàng");
            System.out.println("5. Phòng chiếu phim");
            System.out.println("6. Tìm kiếm thông tin khách hàng");
            System.out.println("7. Báo cáo doanh thu");
            System.out.println("8. Đăng xuất");
            int choice = 0;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    if (choice>0 && choice<9){
                        break;
                    }
                    System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
                }catch (InputMismatchException e){
                    System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
                }
            }while (true);
            switch (choice){
                case 1:
                    logicAdmin.typeMovieAdmin();
                    break;
                case 2:
                    logicAdmin.menuService();
                    break;
                case 3:
                    logicAdmin.menuMovie();
                    break;
                case 4:
                    logicBuyMovieTicket.historyBooking();
                    break;
                case 5:
                    logicAdmin.menuRoom();
                    break;
                case 6:
                    logicUser.searchClient();
                    break;
                case 7:
                    logicBuyMovieTicket.revenue();
                    break;
                case 8:
                    logIn();
                    break;
            }
        }
    }
    public   void menuUser(){
        while (true){
            printMenu();
                int chcoiceFuntion = 0;
                do {
                    try {
                        chcoiceFuntion = new Scanner(System.in).nextInt();
                        if (chcoiceFuntion>0&&chcoiceFuntion<11){
                            break;
                        }
                        System.out.println("Đã sảy ra lỗi, Bạn vui lòng chọn lại  ");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Đã sảy ra lỗi, Bạn vui lòng chọn lại  ");
                    }
                }while (true);
                switch (chcoiceFuntion){
                    case 1:
                        logicMovie.searchMovieForUser();
                        break;
                    case 2:
                        logicMovie.searchByTypeMovie();
                        break;
                    case 3:
                        logicMovie.printMovie();
                        break;
                    case 4:
                        logicBuyMovieTicket.bookTickets();
                        break;
                    case 5:
                        logicUser.inforAccount();
                        break;
                    case 6:
                        logIn();
                        return;
                }

        }
    }
    private void printMenu() {
        System.out.println("***************************************************");
        System.out.println("*                     Trang Chủ                   *");
        System.out.println("***************************************************");
        System.out.println("1. Tìm kiếm phim ");
        System.out.println("2. Tìm Kiếm theo thể loại phim");
        System.out.println("3. Tất cả Phim ");
        System.out.println("4. Đặt vé ");
        System.out.println("5. Tài Khoản");
        System.out.println("6. Đăng xuât");
    }
}


