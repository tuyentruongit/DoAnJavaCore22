package logichandle;

import entity.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class LogicService {
    List<Service> serviceList = new ArrayList<>();

    public void addService() {
        startFileService();
        System.out.println("Bạn muốn thêm bao nhiêu loại đồ ăn / uống");
        int quantity = 0;
        do {
            try  {
                quantity = new Scanner(System.in).nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Đã sảy ra lỗi, Vui lòng nhập lại");
            }
        }while (true);
        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập thông tin");
            Service service = new Service();
            service.infor();
            serviceList.add(service);
            writeFileService(serviceList);
        }

    }
    public   void writeFileService(  List<Service> serviceList ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("Service.data"));
            writeFile.writeObject(serviceList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public    List<Service>   readFileService()  {

        ObjectInputStream readFile ;
        try {
            readFile = new ObjectInputStream(new FileInputStream("Service.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (  List<Service>  )  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteService() {
        startFileService();
        if (serviceList.isEmpty()){
            System.out.println("Chưa có dịch vụ nào vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên dịch vụ mà bạn muốn xóa ");
        String name = new Scanner(System.in).nextLine();
        for (int i = 0; i < serviceList.size(); i++) {
            Service service = serviceList.get(i);
            if (service.getName().equalsIgnoreCase(name)){
                serviceList.remove(service);
                writeFileService(serviceList);
                System.out.println("Đã xóa dịch vụ trên");
                return;
            }
        }
        System.out.println("Không có dịch vụ bạn vừa nhập");
    }

    public void printAllService() {
        startFileService();
        if (serviceList.isEmpty()){
            System.out.println("Chưa có dịch vụ nào , Vui lòng thêm mới");
            return;
        }
        System.out.println("Đồ uống :");
        for (int i = 0; i < serviceList.size(); i++) {
           if (serviceList.get(i).getTypeService().value.equalsIgnoreCase("Đồ uống")){
               System.out.print("Tên: "+serviceList.get(i).getName() +" ");
               System.out.println("Giá: "+serviceList.get(i).getPrice()+" VND");
               System.out.println("Số lượng còn lại: "+serviceList.get(i).getRemainingQuantity());
           }
        }
        System.out.println("Đồ ăn :");
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).getTypeService().value.equalsIgnoreCase("Đồ ăn")) {
                System.out.print("Tên: "+serviceList.get(i).getName() +" ");
                System.out.println("Giá: " + serviceList.get(i).getPrice() + " VND");
                System.out.println("Số lượng còn lại: "+serviceList.get(i).getRemainingQuantity());
            }
        }
    }

    public void searchService() {
        startFileService();
        if (serviceList.isEmpty()){
            System.out.println("Chưa có dịch vụ nào , Vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên dịch vụ mà bạn muốn tìm");
        String name = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
        for (int i = 0; i < serviceList.size() ; i++) {
            if (serviceList.get(i).getName().equalsIgnoreCase(name)){
                System.out.print("Tên: "+serviceList.get(i).getName());
                System.out.println("Giá: "+serviceList.get(i).getPrice()+" VND");
                return;
            }
        }
        System.out.println("Không có dịch vụ bạn vừa nhập");
    }

    public Service searchServiceForBuyTickets(String nameService) {
        startFileService();
        for (int i = 0; i < serviceList.size(); i++) {
          if (serviceList.get(i).getName().equalsIgnoreCase(nameService)){
              return serviceList.get(i);
          }
        }
        return null;
    }
    public void startFileService(){
        File file = new File("Service.data");
        if (file.exists()){
            serviceList=readFileService();
        }
    }
}
