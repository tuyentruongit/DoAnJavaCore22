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
    LogicAdmin logicAdmin = new LogicAdmin();
    LogicUser logicUser = new LogicUser();
    LogicMovie logicMovie = new LogicMovie();
    LogicTypeMove logicTypeMove = new LogicTypeMove();
    LogicRoom logicRoom = new LogicRoom();
    LogicShowTime logicShowTime = new LogicShowTime(logicRoom,logicMovie);
    List<BuyMovieTicket> buyMovieTicketList = new ArrayList<>();
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
            System.out.println("4. Trở lại");
            int choice = 0 ;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    if (choice>0 && choice<5){
                        break;
                    }
                    System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
                }catch (InputMismatchException e){
                    System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
                }

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
                    typeMovieAdmin();
                    break;
                case 2:
                    logicAdmin.menuService();
                    break;
                case 3:
                    menuMovie();
                    break;
                case 4:
                    break;
                case 5:
                    menuRoom();
                    break;
                case 6:
                    logicUser.searchClient();
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
            System.out.println("4. Thoát");
            int choice = 0;
            do {
                try{
                    choice = new Scanner(System.in).nextInt();
                    if (choice>0 && choice<5){
                        break;
                    }
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }catch (InputMismatchException e){
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
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
                case 4:
                    return;
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
                        bookTickets();
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

    public void menuRoom() {
        while (true) {
            System.out.println("----------- Quản Lý Phòng chiếu ----------");
            System.out.println("1. Thêm Phòng Chiếu");
            System.out.println("2. Xóa Phòng chiếu");
            System.out.println("3. Tìm kiếm phòng chiếu phim");
            System.out.println("4. Sắp xếp lịch chiếu phim của phòng chiếu ");
            System.out.println("5. Xem lịch chiếu ");
            System.out.println("6. Xóa Lịch chiếu");
            System.out.println("7. Trở lại ");
            System.out.println("Bạn vui lòng chọn những tính năng trên");
            int choice = 0;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    if (choice > 0 && choice < 8) {
                        break;
                    }
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                } catch (InputMismatchException e) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            } while (true);
            switch (choice) {
                case 1:
                    logicRoom.addRoom();
                    break;
                case 2:
                    logicRoom.deleteRoom();
                    break;
                case 3:
                    logicRoom.searchRoom();
                    break;
                case 4:
                    logicShowTime.sortShowTimes();
                    break;
                case 5:
                    logicShowTime.printShowtime();
                    break;
                case 6:
                    logicShowTime.deleteShowTime();
                    break;
                case 7:
                    return;
            }
        }
    }
    public void bookTickets() {
        logicShowTime.readFileShowTimes();
        System.out.println("Dưới đây là lịch chiều của các bộ phim hiện có trong rạp");
        logicShowTime.printAllShowTime();
        ShowTimes showTimes = chonphimlichchieu();
        float priceMovie = showTimes.getShowTimeMovieList().get(0).getMovie().getPrice();
        System.out.println("Nhập số lượng vé mà bạn muốn mua");
        int numberTicket ;
        do {
            try {
                numberTicket = new Scanner(System.in).nextInt();
                break;
            }catch (InputMismatchException e ){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }
        }while (true);
        System.out.println("Bạn có muốn dùng thêm đồ ăn/uống không?");
        System.out.print("1. Có");
        System.out.println("2. Không");
        int choiceEatDrink ;
        do {
            try {
                choiceEatDrink = new Scanner(System.in).nextInt();
                if (choiceEatDrink>0 && choiceEatDrink<3){
                    break;
                }
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }catch (InputMismatchException e ){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }
        }while (true);
        float  sum =0;
        BuyMovieTicket buyMovieTicket = null;
        ServiceDetail serviceDetail = null;
        switch (choiceEatDrink){
            case 1:
                System.out.println("Dưới đây là những đồ ăn / uống có trong rạp");
                logicAdmin.logicService.printAllService();
                System.out.println("Bạn muốn dùng đồ ăn / uống nào ");
                String nameService;
                Service service;
               do {
                    nameService = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
                    service = logicAdmin.logicService.searchServiceForBuyTickets(nameService);
                   if (service!= null){
                       break;
                   }
                   System.out.println("Không có đồ ăn / uống nào như trên");
               }while (true);
                System.out.println("Nhập số lượng đồ ăn / uống bạn muốn mua");
                int number;
                do {
                    try {
                        number= new Scanner(System.in).nextInt();
                        break;
                    }catch (InputMismatchException e){
                        System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
                    }
                }while (true);
                serviceDetail = new ServiceDetail(number,service);
                buyMovieTicket = new BuyMovieTicket(numberTicket,showTimes,serviceDetail,sum);
                sum = (priceMovie * numberTicket)+(number*service.getPrice());
                break;
            case 2:
                 sum = priceMovie * numberTicket;
                 buyMovieTicket = new BuyMovieTicket(numberTicket,showTimes,sum);
                break;
        }
        System.out.println("Tổng tiền bạn phải thanh toán là "+sum+" VND");
        System.out.println("1. Thanh toán");
        System.out.println("2.Trở lại");
        int choice ;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                if (choice>0 && choice<3){
                    break;
                }
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }catch (InputMismatchException e ){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }
        }while (true);
        switch (choice){
            case 1:
                if (serviceDetail==null){
                    System.out.println("Tên phim: "+ showTimes.getShowTimeMovieList().get(0).getMovie().getNameMovie());
                    System.out.println("Khởi chiếu: "+showTimes.getShowTimeMovieList().get(0).getMovie().getPublishYear());
                    System.out.println("Phòng chiếu: "+showTimes.getRoom().getNameRoom());
                    System.out.println("Giờ Chiếu: "+showTimes.getShowTimeMovieList().get(0).getGiochieu());
                    System.out.println("Số lượng vé: "+buyMovieTicket.getTotalAmount());
                    System.out.println("Đồ ăn / uống: Không");
                    System.out.println("Thanh toán: QRPay");
                    System.out.println("Tổng tiền: "+sum);
                   break;
                }
                System.out.println("Tên phim: "+ showTimes.getShowTimeMovieList().get(0).getMovie().getNameMovie());
                System.out.println("Khởi chiếu: "+showTimes.getShowTimeMovieList().get(0).getMovie().getPublishYear());
                System.out.println("Phòng chiếu: "+showTimes.getRoom().getNameRoom());
                System.out.println("Giờ Chiếu: "+showTimes.getShowTimeMovieList().get(0).getGiochieu());
                System.out.println("Số lượng vé: "+buyMovieTicket.getTotalAmount());
                System.out.println("Đồ ăn / uống: "+serviceDetail.getService().getName()+"  Số lượng: "+serviceDetail.getQuantity());
                System.out.println("Thanh toán: QRPay");
                System.out.println("Tổng tiền: "+sum);
                for (int i = 0; i < logicAdmin.logicService.serviceList.size(); i++) {
                    if (serviceDetail.getService().getName().equalsIgnoreCase(logicAdmin.logicService.serviceList.get(i).getName())){
                        logicAdmin.logicService.serviceList.get(i).setRemainingQuantity(logicAdmin.logicService.serviceList.get(i).getRemainingQuantity()- serviceDetail.getQuantity());
                        logicAdmin.logicService.writeFileService(logicAdmin.logicService.serviceList);
                    }
                }
                System.out.println("Thanh toán thành công");
                break;
            case 2:
                break;
        }
    }

    private ShowTimes chonphimlichchieu() {
        System.out.println("Nhập tên phòng mà bạn muốn xem");
        String nameRoom = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
        System.out.println("Bạn muốn đặt vé xem bộ phim nào ?");
        String nameMovie =new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
        logicMovie.searchMovie(nameMovie);
        System.out.println("Chọn giờ chiếu mà bạn muốn xem");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime showtime ;
        String timeInput;
        do {
            try{
                timeInput= new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
                showtime = LocalTime.parse(timeInput,timeFormatter);
                break;
            }catch (DateTimeException e){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }
        }while (true);
        ShowTimes showTimes = logicShowTime.searchShowTimeForUser(nameRoom,nameMovie,showtime);
        if (showTimes==null){
            System.out.println("Không có phòng chiếu "+nameRoom+" chiếu bộ phim "+nameMovie+" vào khung giờ "+timeInput);
        }
        return showTimes;
    }

}


