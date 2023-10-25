package logichandle;

import entity.Admin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicAdmin {
    LogicService logicService ;
    LogicTypeMove logicTypeMove ;
    LogicRoom logicRoom;
    LogicShowTime logicShowTime;
    LogicMovie logicMovie;

    List<Admin> adminList = new ArrayList<>();

    public void createAdmin() {
        Admin admin = new Admin("Tuyen90Hanam", "tuyen90hn", "Admin");
        adminList.add(admin);
    }

    public LogicAdmin(LogicService logicService, LogicTypeMove logicTypeMove, LogicRoom logicRoom, LogicShowTime logicShowTime, LogicMovie logicMovie) {
        this.logicService = logicService;
        this.logicTypeMove = logicTypeMove;
        this.logicRoom = logicRoom;
        this.logicShowTime = logicShowTime;
        this.logicMovie = logicMovie;
    }

    public Admin searchAdmin(String adminName, String password) {
        createAdmin();
        for (int i = 0; i < adminList.size(); i++) {
            if (adminList.get(i).getAccountName().equals(adminName)
                    && adminList.get(i).getPassword().equals(password)) {
                return adminList.get(i);
            }
        }
        return null;
    }

    public void menuService() {
        while (true) {
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
                    if (choice > 0 && choice <= 5) {
                        break;
                    }
                    System.out.println("Không có chức năng như bạn vừa nhập, Vui lòng nhập lại");
                } catch (InputMismatchException e) {
                    System.out.println("Dữ liệu nhập vào không hợp lệ, Vui lòng nhập lại");
                }

            } while (true);
            switch (choice) {
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

    public void menuMovie() {
        while (true) {
            System.out.println("------ Quản lý phim ------");
            System.out.println("1. Thêm phim");
            System.out.println("2. Xem danh sách phim");
            System.out.println("3. Xóa phim ");
            System.out.println("4. Thoát");
            int choice = 0;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    if (choice > 0 && choice < 5) {
                        break;
                    }
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                } catch (InputMismatchException e) {
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                }
            } while (true);

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

    public void typeMovieAdmin() {
        while (true) {
            System.out.println("-------------- Quản lý thể loại phim -------------");
            System.out.println("1. Hiển thị danh sách thể loại phim");
            System.out.println("2. Thêm thể loại phim");
            System.out.println("3. Xóa thể loại phim");
            System.out.println("4. Trở lại");
            int choice = 0;
            do {
                try {
                    choice = new Scanner(System.in).nextInt();
                    if (choice > 0 && choice < 5) {
                        break;
                    }
                    System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
                } catch (InputMismatchException e) {
                    System.out.println("Đã sảy ra lỗi , Vui lòng chọn lại");
                }

            } while (true);
            switch (choice) {
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
                    return;
            }
        }
    }
}
