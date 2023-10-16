package entity;

import statics.TypeService;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Service implements Serializable,InputInfor {
    private String name ;
    private float price;
    private TypeService typeService;

    public TypeService getTypeService() {
        return typeService;
    }

    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", typeService=" + typeService.value +
                '}';
    }

    @Override
    public void infor() {
        System.out.println("Nhập tên ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.println("Nhập giá  ");
        this.setPrice(new Scanner(System.in).nextFloat());
        System.out.println("Dịch vụ trên thuộc loại nào");
        System.out.println("1. Đồ ăn ");
        System.out.println("2. Nước ");
        int choice = 0;
        do {
            try  {
                choice = new Scanner(System.in).nextInt();
                if (choice>0 && choice<4){
                    break;
                }
                System.out.println("Chức năng bạn vừa chọn không hợp lệ , Vui lòng chọn lại");
            }catch (InputMismatchException e){
                System.out.println("Đã sảy ra lỗi, Vui lòng nhập lại");
                break;
            }

        }while (true);
        switch (choice){
            case 1:
                this.setTypeService(TypeService.EAT);
                break;
            case 2:
                this.setTypeService(TypeService.DRINK);
                break;
        }
    }
}
