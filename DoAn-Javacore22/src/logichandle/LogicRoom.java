package logichandle;

import entity.Room;
import entity.User;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicRoom {
    List<Room> roomList = new ArrayList<>();

    public void addRoom() {
        File file = new File("room.data");
        if (file.exists()){
            roomList=readFileRoom();
        }
        System.out.println(" Bạn muốn thêm bao nhiêu phòng chiếu");
        int quantity ;
        do {
            try {
                 quantity = new Scanner(System.in).nextInt();
                 break;
            }catch (InputMismatchException e){
                System.out.println("Đã xảy ra lỗi, Vui lòng nhập lại");
            }
        }while (true);
        for (int i = 0; i < quantity; i++) {
           System.out.println("Nhập thông tin của phòng chiếu thứ "+ (i+1));
            Room room = new Room();
            room.infor();
            roomList.add(room);
            writeFileRoom(roomList);
        }
    }
    public   void writeFileRoom(  List<Room> roomList ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("room.data"));
            writeFile.writeObject(roomList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public    List<Room>   readFileRoom()  {

        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("room.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (  List<Room>  )  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRoom() {
        if (roomList.isEmpty()){
            System.out.println("Chưa có  Phòng chiếu nào, Vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên phòng chiếu mà bạn muốn xóa");
        String delete = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
        for (int i = 0; i < roomList.size(); i++) {
            if (delete.equalsIgnoreCase(roomList.get(i).getNameRoom())){
                roomList.remove(roomList.get(i));
                writeFileRoom(roomList);
                System.out.println("Phòng chiếu "+delete+" đã bị xóa");
                break;
            }
            System.out.println("Phòng chiếu không tồn tại trong rạp");
        }
    }

    public void searchRoom() {
        startReadFileRoom();
        System.out.println("Nhập tên phòng chiếu mà bạn muốn tìm");
        String name = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getNameRoom().equalsIgnoreCase(name)){
                System.out.println("***************************************************");
                System.out.println("Tên: "+roomList.get(i).getNameRoom());
                System.out.println("Sức chứa: "+roomList.get(i).getCapacity());
                System.out.println("***************************************************");
                return;
            }
        }
        System.out.println("Phòng chiếu bạn vừa nhập không tồn tại trong rạp");
    }

    public Room searchRoombyShowTime(String name) {
        Room room = null;
        for (int i = 0; i < roomList.size(); i++) {
            if (roomList.get(i).getNameRoom().equalsIgnoreCase(name)){
               room=roomList.get(i);
            }
        }
        return room;
    }
    public void startReadFileRoom(){
        File file = new File("room.data");
        if (file.exists()){
            roomList=readFileRoom();
        }
    }
}
