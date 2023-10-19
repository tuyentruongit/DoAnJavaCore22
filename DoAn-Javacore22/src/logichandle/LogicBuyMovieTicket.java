package logichandle;

import entity.*;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicBuyMovieTicket {
   public LogicMovie logicMovie ;
    public LogicShowTime logicShowTime;
    public LogicAdmin logicAdmin;
    List<BuyMovieTicket> buyMovieTicketList = new ArrayList<>();

    public LogicBuyMovieTicket(LogicMovie logicMovie, LogicShowTime logicShowTime, LogicAdmin logicAdmin) {
        this.logicMovie = logicMovie;
        this.logicShowTime = logicShowTime;
        this.logicAdmin = logicAdmin;
    }

    public void bookTickets() {
        startReadFileBuyMovieTicket();
        System.out.println("Dưới đây là lịch chiếu của các bộ phim hiện có trong rạp");
        logicShowTime.printAllShowTime();
        if (logicShowTime.showTimesList.isEmpty()){
            System.out.println("Hiện Tại rạp chưa có lịch chiếu cụ thể , Bạn vui lòng quay lại khi khác ");
            return;
        }
        ShowTimes showTimes ;
        int capacity = 0;
        while (true){
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
            showTimes = logicShowTime.searchShowTimeForUser(nameRoom,nameMovie,showtime);
            for (int i = 0; i < buyMovieTicketList.size(); i++) {
                for (int j = 0; j < buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().size(); j++) {
                    if (buyMovieTicketList.get(i).getShowTimes().getRoom().getNameRoom().equalsIgnoreCase(nameRoom)
                            &&(buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(j).getMovie().getNameMovie().equalsIgnoreCase(nameMovie)
                            &&buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(j).getGiochieu().equals(showtime))){
                        capacity+=buyMovieTicketList.get(i).getTotalAmount();
                    }
                }
            }if (capacity>showTimes.getRoom().getCapacity()){
                System.out.println("Phòng chiếu trên đã đầy vui lòng chọn phòng chiếu khác");
                showTimes = null;
            }
            if (showTimes!=null){
                System.out.println("Số lượng vé còn lại trong phòng chiếu "+ nameRoom+" tại khung giờ "+timeInput+" là : " +( showTimes.getRoom().getCapacity()-capacity));
                break;
            }
            System.out.println("Không có phòng chiếu "+nameRoom+" chiếu bộ phim "+nameMovie+" vào khung giờ "+timeInput);
        }
        float priceMovie = showTimes.getShowTimeMovieList().get(0).getMovie().getPrice();
        System.out.println("Nhập số lượng vé mà bạn muốn mua");
        int numberTicket ;
        do {
            try {
                numberTicket = new Scanner(System.in).nextInt();
               if (numberTicket<showTimes.getRoom().getCapacity()-capacity){
                   break;
               }
                System.out.println("Số lượng vé bạn vừa nhập quá với số lượng vé còn trong rạp, Vui lòng nhập lại");
            }catch (InputMismatchException e ){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
            }
        }while (true);
        System.out.println("Bạn có muốn dùng thêm đồ ăn/uống không?");
        System.out.print("1. Có ");
        System.out.println(" 2. Không");
        int choiceEatDrink = exceptionChoice() ;
        BuyMovieTicket buyMovieTicket = null;
        ServiceDetail serviceDetail = null;
        float  sum =0;
        switch (choiceEatDrink){
            case 1:
                serviceDetail = chooseService();
                sum = (priceMovie * numberTicket)+(serviceDetail.getQuantity()*serviceDetail.getService().getPrice());
                buyMovieTicket = new BuyMovieTicket(numberTicket,showTimes,serviceDetail,sum);
                buyMovieTicketList.add(buyMovieTicket);
                writeFileBuyMoviTicket(buyMovieTicketList);
                break;
            case 2:
                sum = priceMovie * numberTicket;
                buyMovieTicket = new BuyMovieTicket(numberTicket,showTimes,sum);
                buyMovieTicketList.add(buyMovieTicket);
                writeFileBuyMoviTicket(buyMovieTicketList);
                break;
        }
        System.out.println("Tổng tiền bạn phải thanh toán là "+sum+" VND");
        System.out.println("1. Thanh toán");
        System.out.println("2.Trở lại");
        int choice = choice(); ;
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
                    System.out.println("Tổng tiền: "+sum+"VND");
                    break;
                }
                System.out.println("Tên phim: "+ showTimes.getShowTimeMovieList().get(0).getMovie().getNameMovie());
                System.out.println("Khởi chiếu: "+showTimes.getShowTimeMovieList().get(0).getMovie().getPublishYear());
                System.out.println("Phòng chiếu: "+showTimes.getRoom().getNameRoom());
                System.out.println("Giờ Chiếu: "+showTimes.getShowTimeMovieList().get(0).getGiochieu());
                System.out.println("Số lượng vé: "+buyMovieTicket.getTotalAmount());
                System.out.println("Đồ ăn / uống: "+serviceDetail.getService().getName()+"  Số lượng: "+serviceDetail.getQuantity());
                System.out.println("Thanh toán: QRPay");
                System.out.println("Tổng tiền: "+sum+"VND");
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
    private int choice() {
        int choice;
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
        return choice;
    }
    private int exceptionChoice() {
        int choiceEatDrink;
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
        return choiceEatDrink;
    }
    private ServiceDetail chooseService() {
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
        ServiceDetail serviceDetail = new ServiceDetail(number,service);
        return serviceDetail;
    }
//    private ShowTimes chonphimlichchieu() {
//        ShowTimes showTimes;
////        while (true){
////            System.out.println("Nhập tên phòng mà bạn muốn xem");
////            String nameRoom = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
////            System.out.println("Bạn muốn đặt vé xem bộ phim nào ?");
////            String nameMovie =new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
////            logicMovie.searchMovie(nameMovie);
////            System.out.println("Chọn giờ chiếu mà bạn muốn xem");
////            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
////            LocalTime showtime ;
////            String timeInput;
////            do {
////                try{
////                    timeInput= new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
////                    showtime = LocalTime.parse(timeInput,timeFormatter);
////                    break;
////                }catch (DateTimeException e){
////                    System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , vui lòng nhập lại");
////                }
////            }while (true);
////            showTimes = logicShowTime.searchShowTimeForUser(nameRoom,nameMovie,showtime);
////            int capacity = 0;
////            for (int i = 0; i < buyMovieTicketList.size(); i++) {
////                for (int j = 0; j < buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().size(); j++) {
////                    if (buyMovieTicketList.get(i).getShowTimes().getRoom().getNameRoom().equalsIgnoreCase(nameRoom)
////                            &&(buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(j).getMovie().getNameMovie().equalsIgnoreCase(nameMovie)
////                            &&buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(j).getGiochieu().equals(showtime))){
////                        capacity+=buyMovieTicketList.get(i).getTotalAmount();
////                    }
////                }
////            }if (capacity>showTimes.getRoom().getCapacity()){
////                System.out.println("Phòng chiếu trên đã đầy vui lo");
////            }
////            if (showTimes!=null){
////              break;
////            }
////            System.out.println("Không có phòng chiếu "+nameRoom+" chiếu bộ phim "+nameMovie+" vào khung giờ "+timeInput);
////        }
////        return showTimes;
//    }
    private void startReadFileBuyMovieTicket() {
        File file = new File("buyMovieTicket.data");
        if (file.exists()){
            buyMovieTicketList=readFileBuyMoviTicket();
        }
    }

    public   void writeFileBuyMoviTicket(  List<BuyMovieTicket> userList ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("buyMovieTicket.data"));
            writeFile.writeObject(userList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public    List<BuyMovieTicket>   readFileBuyMoviTicket()  {
        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("buyMovieTicket.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (  List<BuyMovieTicket>  )  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void historyBooking() {
        startReadFileBuyMovieTicket();
        if (buyMovieTicketList.isEmpty()){
            System.out.println("Chưa có khách hàng nào mua vé");
            return;
        }
        for (int i = 0; i < buyMovieTicketList.size(); i++) {
            if (buyMovieTicketList.get(i).getServiceDetails()==null){
                System.out.println("***************************************************");
                System.out.println("Tên phim: "+  buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(0).getMovie().getNameMovie());
                System.out.println("Khởi chiếu: "+buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(0).getMovie().getPublishYear());
                System.out.println("Phòng chiếu: "+buyMovieTicketList.get(i).getShowTimes().getRoom().getNameRoom());
                System.out.println("Giờ Chiếu: "+buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(0).getGiochieu());
                System.out.println("Số lượng vé: "+buyMovieTicketList.get(i).getTotalAmount());
                System.out.println("Đồ ăn / uống: Không");
                System.out.println("Thanh toán: QRPay");
                System.out.println("Tổng tiền: "+buyMovieTicketList.get(i).getToatlPrice()+"VND");
            }if (buyMovieTicketList.get(i).getServiceDetails()!=null){
                System.out.println("***************************************************");
                System.out.println("Tên phim: "+  buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(0).getMovie().getNameMovie());
                System.out.println("Khởi chiếu: "+buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(0).getMovie().getPublishYear());
                System.out.println("Phòng chiếu: "+buyMovieTicketList.get(i).getShowTimes().getRoom().getNameRoom());
                System.out.println("Giờ Chiếu: "+buyMovieTicketList.get(i).getShowTimes().getShowTimeMovieList().get(0).getGiochieu());
                System.out.println("Số lượng vé: "+buyMovieTicketList.get(i).getTotalAmount());
                System.out.println("Đồ ăn / uống: "+buyMovieTicketList.get(i).getServiceDetails().getService().getName()+"  Số lượng: "+buyMovieTicketList.get(i).getServiceDetails().getQuantity());
                System.out.println("Thanh toán: QRPay");
                System.out.println("Tổng tiền: "+buyMovieTicketList.get(i).getToatlPrice()+"VND");
            }
        }

    }

    public void revenue() {
        startReadFileBuyMovieTicket();
        float revenue = 0;
        for (int i = 0; i <buyMovieTicketList.size() ; i++) {
            revenue += buyMovieTicketList.get(i).getToatlPrice();
        }
        System.out.println("Doanh thu: "+revenue);
    }
}
