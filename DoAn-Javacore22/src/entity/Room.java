package entity;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Room implements Serializable {
    private String nameRoom;
    private int capacity;

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + nameRoom + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public void infor() {
        System.out.println("Nhập tên của phòng chiếu ");
        this.setNameRoom(new Scanner(System.in).nextLine());
        do {
            System.out.println("Nhập sức chứa phòng chiếu ");
            try{
                this.setCapacity(new Scanner(System.in).nextInt());
                break;
            }
            catch (InputMismatchException e){
                System.out.println("Đã xay ra lỗi vui lòng nhập lại");
            }
        }while (true);
    }
}
