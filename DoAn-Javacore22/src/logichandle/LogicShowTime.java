package logichandle;

import entity.*;

import java.io.*;
import java.security.cert.TrustAnchor;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicShowTime {
    LogicRoom logicRoom;
    LogicMovie logicMovie;
    List<ShowTimes> showTimesList = new ArrayList<>();

    public LogicShowTime(LogicRoom logicRoom, LogicMovie logicMovie) {
        this.logicRoom = logicRoom;
        this.logicMovie = logicMovie;
    }



    public void sortShowTimes() {
        File file = new File("showtime.data");
        if (file.exists()){
            showTimesList=readFileShowTimes();
        }
        logicRoom.startReadFileRoom();//Đọc File phòng chiếu
        if (logicRoom.roomList.isEmpty()){
            System.out.println("Chưa có phòng chiếu phim nào , Vui lòng thêm mới");
            return;
        }
        logicMovie.statrReadFileMovie();//Đọc file phim
        System.out.println("Bạn muốn sắp xếp lịch chiếu cho bao nhiêu phòng  ");
        int quantity ;
        do {
            try {
                quantity = new Scanner(System.in).nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Đã xảy ra lỗi , vui lòng nhập lại");
            }
        }while (true);
        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập tên phòng thứ " + (i + 1));
            String name;
            Room room;
            do {
                name = new Scanner(System.in).nextLine();
                room = logicRoom.searchRoombyShowTime(name);
                if (room != null) {
                    break;
                }
                System.out.println("Không có rạp chiếu nào như trên, Vui lòng nhập lại");
            } while (true);
            System.out.println("Bạn muốn sắp xếp bao nhiêu phim vào trong phòng chiếu trên");
            int quantityMovie = new Scanner(System.in).nextInt();
            List<ShowTimeMovie> showTimeMovieList = new ArrayList<>();
            for (int j = 0; j < quantityMovie; j++) {
                if (logicMovie.movieList.isEmpty()) {
                        System.out.println("Chưa có bộ phim nào , Vui lòng thêm mới");
                        return;
                    }
                    System.out.println("Nhập tên phim thứ " + (i + 1));
                    String nameMovie;
                    Movie movie;
                    do {
                        nameMovie = new Scanner(System.in).nextLine();
                    movie = logicMovie.searchMovie(nameMovie);
                    if (movie != null) {
                        break;
                    }
                    System.out.println("Không có tên phim nào như trên, Vui lòng nhập lại");
                } while (true);
                System.out.println("Nhập giờ chiếu của bộ phim");
                String timeInput;
                LocalTime inputShowTime;
                do {
                    try {
                        timeInput = new Scanner(System.in).nextLine();
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        inputShowTime = LocalTime.parse(timeInput, timeFormatter);
                        if (showTimesList.isEmpty()){
                            System.out.println("Thành công");
                            break;
                        }
                        if (isAvailable(inputShowTime,name)){
                            System.out.println("Thành công");
                            break;
                        }
                        System.out.println(showTimesList);
                        System.out.println("Phòng đã có lịch chiếu vào thời điểm này, Vui lòng nhập lại giờ chiếu");
                    } catch (DateTimeException e) {
                        System.out.println("Đã sảy ra lỗi, vui lòng nhập lại");
                    }
                } while (true);
                ShowTimeMovie showTimeMovie = new ShowTimeMovie(movie, inputShowTime);
                showTimeMovieList.add(showTimeMovie);
            }
            ShowTimes showTimes = new ShowTimes(room, showTimeMovieList);
            showTimesList.add(showTimes);
            writeFileShowTimes(showTimesList);
        }
    }

    public   void writeFileShowTimes(  List<ShowTimes> showTimes ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("showtime.data"));
            writeFile.writeObject(showTimes);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public    List<ShowTimes>   readFileShowTimes()  {

        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("showtime.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (  List<ShowTimes>  )  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isAvailable(LocalTime showTime,String name){
        File file = new File("showtime.data");
        if (file.exists()){
            showTimesList=readFileShowTimes();
        }
        for (int i = 0; i < showTimesList.size(); i++) {
            if (showTimesList.get(i).getRoom().getNameRoom().equalsIgnoreCase(name)){
                LocalTime startTime = showTimesList.get(i).getShowTimeMovieList().get(i).getGiochieu();
                LocalTime durationMovie = showTimesList.get(i).getShowTimeMovieList().get(i).getMovie().getMovieDuration();
                LocalTime endTime = startTime.plusHours(durationMovie.getHour())
                        .plusMinutes(durationMovie.getMinute())
                        .plusSeconds(durationMovie.getSecond());
                System.out.println(endTime);
                if ( showTime.isAfter(endTime)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void printShowtime(){
        File file = new File("showtime.data");
        if (file.exists()){
            showTimesList=readFileShowTimes();
        }
        if (showTimesList.isEmpty()){
            System.out.println("Chưa có lịch chiếu vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên phòng mà bạn muốn xem");
        String name = new Scanner(System.in).nextLine();
        for (int i = 0; i < showTimesList.size(); i++) {
            if (showTimesList.get(i).getRoom().getNameRoom().equalsIgnoreCase(name)){
                System.out.println(showTimesList.get(i));
            }
        }
    }
    public void startReadFileShowTimes(){
        File file = new File("room.data");
        if (file.exists()){
            showTimesList=readFileShowTimes();
        }
        System.out.println(showTimesList);
    }

}




