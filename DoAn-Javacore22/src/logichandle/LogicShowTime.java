package logichandle;

import entity.*;

import javax.xml.namespace.QName;
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
        startReadFileShowTimes();
        logicRoom.startReadFileRoom();
        if (logicRoom.roomList.isEmpty()){
            System.out.println("Chưa có phòng chiếu phim nào , Vui lòng thêm mới");
            return;
        }
        logicMovie.statrReadFileMovie();
        if (logicMovie.movieList.isEmpty()){
            System.out.println("Chưa có phim nào , Vui lòng thêm mới");
            return;
        }
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
            System.out.println("Nhập tên phòng " + (i + 1));
            String name;
            Room room;
            do {
                name =  new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
                room = logicRoom.searchRoombyShowTime(name);
                if (room != null) {
                    break;
                }
                System.out.println("Không có rạp chiếu nào như trên, Vui lòng nhập lại");
            } while (true);
//            System.out.println("Bạn muốn sắp xếp bao nhiêu phim vào trong phòng chiếu trên");
//            int quantityMovie = new Scanner(System.in).nextInt();
            List<ShowTimeMovie> showTimeMovieList = new ArrayList<>();
//            for (int j = 0; j < quantityMovie; j++) {
                if (logicMovie.movieList.isEmpty()) {
                        System.out.println("Chưa có bộ phim nào , Vui lòng thêm mới");
                        return;
                    }
                    System.out.println("Nhập tên phim : ");
                    String nameMovie;
                    Movie movie;
                    do {
                        nameMovie =  new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
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
                        timeInput =  new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                        inputShowTime = LocalTime.parse(timeInput, timeFormatter);
                        if (timeInput.equalsIgnoreCase("exit")){
                            return;
                        }
                        if (isAvailable(inputShowTime,name)){
                            System.out.println("Sắp xếp lịch chiếu thành công");
                            break;
                        }
                        System.out.println("Phòng đã có lịch chiếu vào thời điểm này, Vui lòng nhập lại giờ chiếu");
                    } catch (DateTimeException e) {
                        System.out.println("Đã sảy ra lỗi, vui lòng nhập lại");
                    }
                } while (true);
                ShowTimeMovie showTimeMovie = new ShowTimeMovie(movie, inputShowTime);
                showTimeMovieList.add(showTimeMovie);
//            }
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
        startReadFileShowTimes();
        if (showTimesList.isEmpty()){
            return true;
        }
        boolean ketqua = false;
        List<ShowTimes> showTimesCheck = new ArrayList<>();
        for (int i = 0; i < showTimesList.size(); i++) {
            if (name.equalsIgnoreCase(showTimesList.get(i).getRoom().getNameRoom())){
                showTimesCheck.add(showTimesList.get(i));
            }
        }

        if (showTimesCheck.isEmpty()){
            ketqua = true;
            return ketqua;
        }
        for (int i = 0; i < showTimesCheck.size(); i++) {
            for (int j = 0; j < showTimesCheck.get(i).getShowTimeMovieList().size(); j++) {
                LocalTime startTime = showTimesCheck.get(i).getShowTimeMovieList().get(j).getGiochieu();
                LocalTime durationMovie = showTimesCheck.get(i).getShowTimeMovieList().get(j).getMovie().getMovieDuration();
                LocalTime endTime = startTime.plusHours(durationMovie.getHour())
                        .plusMinutes(durationMovie.getMinute())
                        .plusSeconds(durationMovie.getSecond());
                if (showTime.isAfter(endTime)){
                    ketqua = true;
                }
            }
        }
        return ketqua;
    }
    public void printShowtime(){
        startReadFileShowTimes();
        if (showTimesList.isEmpty()){
            System.out.println("Chưa có lịch chiếu vui lòng thêm mới");
            return;
        }
        System.out.println("----------- Lịch chiếu phim ----------");
        System.out.println("1. Xem tất cả lịch chiếu");
        System.out.println("2. Xem lịch chiếu của 1 phòng chiếu");
        System.out.println("3. Trở lại");
        int choice;
        do {
            try{
                choice= new Scanner(System.in).nextInt();
                if (choice > 0 && choice<4){
                    break;
                }
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu \n Vui lòng nhập  lại");
            }catch (InputMismatchException e){
                System.out.println("Đã xảy ra lỗi khi nhập dữ liệu \n Vui lòng nhập  lại");
            }

        }while (true);
        switch (choice){
            case 1:
                printAllShowTime();
                break;
            case 2:
                printShowtimeARoom();
                break;
            case 3:
                break;
        }
    }

    private void printShowtimeARoom() {
        startReadFileShowTimes();
        System.out.println(showTimesList);
        if (showTimesList.isEmpty()){
            System.out.println("Chưa có lịch chiếu nào, Vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên phòng mà bạn muốn xem");
        String name = new Scanner(System.in).nextLine();
        for (int i = 0; i < showTimesList.size(); i++) {
            for (int j = 0; j < showTimesList.get(i).getShowTimeMovieList().size(); j++) {
                if (showTimesList.get(i).getRoom().getNameRoom().equalsIgnoreCase(name)){
                    System.out.println("***************************************************");
                    System.out.println("Phòng chiếu: "+showTimesList.get(i).getRoom().getNameRoom());
                    System.out.println("Tên phim : "+showTimesList.get(i).getShowTimeMovieList().get(j).getMovie().getNameMovie());
                    System.out.println("Thể Loại: "+showTimesList.get(i).getShowTimeMovieList().get(j).getMovie().getTypeMovie());
                    System.out.println("Giờ chiếu: "+showTimesList.get(i).getShowTimeMovieList().get(j).getGiochieu());
                    System.out.println("Ngày chiếu: " + showTimesList.get(i).getShowTimeMovieList().get(j).getNgaychieu());
                }
            }
        }
    }

    public void printAllShowTime() {
        startReadFileShowTimes();
        if (showTimesList.isEmpty()){
            System.out.println("Hiện tại rạp chưa có lịch chiếu nào. ");
        }
        for (int i = 0; i < showTimesList.size(); i++) {
            for (int j = 0; j < showTimesList.get(i).getShowTimeMovieList().size(); j++) {
                    System.out.println("***************************************************");
                    System.out.println("Phòng chiếu: "+showTimesList.get(i).getRoom().getNameRoom());
                    System.out.println("Tên phim : "+showTimesList.get(i).getShowTimeMovieList().get(j).getMovie().getNameMovie());
                    System.out.println("Thể Loại: "+showTimesList.get(i).getShowTimeMovieList().get(j).getMovie().getTypeMovie().value);
                    System.out.println("Giờ chiếu: "+showTimesList.get(i).getShowTimeMovieList().get(j).getGiochieu());
                    System.out.println("Ngày chiếu: " + showTimesList.get(i).getShowTimeMovieList().get(j).getNgaychieu());
            }
        }
    }

    public void startReadFileShowTimes(){
        File file = new File("showtime.data");
        if (file.exists()){
            showTimesList=readFileShowTimes();
        }
    }

    public void deleteShowTime() {
        startReadFileShowTimes();
        if (showTimesList.isEmpty()){
            System.out.println("Chưa có lịch chiếu nào vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên Phòng chiếu và giờ chiếu mà bạn muốn xóa");
        System.out.print("Tên phòng: ");
        String name;
        Room room = null;
        logicRoom.startReadFileRoom();
        do {
             name =  new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
            for (int i = 0; i < logicRoom.roomList.size(); i++) {
                if (logicRoom.roomList.get(i).getNameRoom().equalsIgnoreCase(name)){
                    room = logicRoom.roomList.get(i);
                }
            }
            if (room!=null){
                break;
            }
            if (name.equalsIgnoreCase("exit")){
                return;
            }
            System.out.println("Chưa có phòng chiếu phim nào như trên , Vui lòng nhập lại");
        }while (true);
        System.out.println("Giờ chiếu: ");
        String hour ;
        LocalTime localTime;
        do {
           try {
               hour =  new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
               DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
               localTime = LocalTime.parse(hour,timeFormatter);
               if (name.equalsIgnoreCase("exit")){
                   return;
               }
               break;
           }catch (DateTimeException e ){
               System.out.println("Đã xảy ra lỗi khi nhập dữ liệu \nVui lòng nhập  lại");
           }
        }while (true);
        for (int i = 0; i < showTimesList.size(); i++) {
            for (int j = 0; j < showTimesList.get(i).getShowTimeMovieList().size(); j++) {
                if (showTimesList.get(i).getRoom().getNameRoom().equalsIgnoreCase(name)
                && showTimesList.get(i).getShowTimeMovieList().get(j).getGiochieu().equals(localTime)){
                    showTimesList.remove(showTimesList.get(i));
                    writeFileShowTimes(showTimesList);
                    System.out.println("Xóa lịch chiếu thành công");
                    return;
                }
            }
        }
        System.out.println("Phòng chiếu phim "+ name + " không có lịch chiếu nòa như trên" );
    }

    public ShowTimes  searchShowTimeForUser(String nameRoom, String nameMovie ,LocalTime localTime ) {
        ShowTimes showTimes = null;
        for (int i = 0; i < showTimesList.size(); i++) {
            for (int j = 0; j < showTimesList.get(i).getShowTimeMovieList().size(); j++) {
                if (showTimesList.get(i).getShowTimeMovieList().get(j).getGiochieu().equals(localTime)
                && showTimesList.get(i).getShowTimeMovieList().get(j).getMovie().getNameMovie().equalsIgnoreCase(nameMovie)
                && showTimesList.get(i).getRoom().getNameRoom().equalsIgnoreCase(nameRoom)){
                    showTimes=showTimesList.get(i);
                }
            }
        }
        return showTimes;
    }
}




