package logichandle;

import entity.BuyMovieTicket;
import entity.ShowTimeMovie;
import entity.ShowTimes;

import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicBuyMovieTicket {

    LogicShowTime logicShowTime;
    List<BuyMovieTicket> buyMovieTicketList = new ArrayList<>();

    public void bookTickets() {
        logicShowTime.readFileShowTimes();
        System.out.println("Dưới đây là lịch chiều của các bộ phim hiện có trong rạp");
        for (int i = 0; i < logicShowTime.showTimesList.size(); i++) {
            System.out.println(logicShowTime.showTimesList.get(i));
        }
        System.out.println("Bạn muốn đặt vé xem bộ phim nào ?");
        String name = new Scanner(System.in).nextLine();
        System.out.println("Chọn giờ chiếu mà bạn muốn xem");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeInput;
        LocalTime showtime ;
        boolean count = false;
        ShowTimes showTimes = null;
        do {
            name = new Scanner(System.in).nextLine();
            timeInput= new Scanner(System.in).nextLine();
            showtime = LocalTime.parse(timeInput, timeFormatter);
            for (int i = 0; i < logicShowTime.showTimesList.size(); i++) {
                if ( logicShowTime.showTimesList.get(i).getShowTimeMovieList().get(i).getMovie().getNameMovie().equalsIgnoreCase(name)
                && showtime.equals(logicShowTime.showTimesList.get(i).getShowTimeMovieList().get(i).getGiochieu())){
                    showTimes =logicShowTime.showTimesList.get(i);
                    count = true;
                    break;
                }
            }
            if (count){
                break;
            }
            System.out.println("Không có tên phim "+ name + " chiếu vào giờ: "+showtime + " Vui lòng nhập lại");
        }while (true);
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
    }


}
