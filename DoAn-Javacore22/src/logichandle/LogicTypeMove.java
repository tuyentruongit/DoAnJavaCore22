package logichandle;

import java.io.*;
import java.util.*;

public class LogicTypeMove {
    List<String> listTypeMovie = new ArrayList<>();
    public void printTypeMovie() {
        File file = new File("listTypeMovie.txt");
        if (file.exists()){
            listTypeMovie = readFileTypeMovie();
        }
        if (listTypeMovie.isEmpty()){
            System.out.println("Chưa có thể loại phim nào, Vui lòng thêm mới");
            return;
        }
        for (int i = 0; i < listTypeMovie.size(); i++) {
            System.out.println(listTypeMovie.get(i));
        }
    }
    public   void writeFileTypeMovie(  List<String> stringList ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("listTypeMovie.txt"));
            writeFile.writeObject(stringList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public   List<String>   readFileTypeMovie()  {

        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("listTypeMovie.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (  List<String>  )  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTypeMovie() {
        if (listTypeMovie.isEmpty()){
            System.out.println("Chưa có thể loại phim nào, Vui lòng thêm mới");
            return;
        }
        System.out.println(listTypeMovie);
        System.out.println("Nhập thể loại mà bạn muốn xóa");
        String delete = new Scanner(System.in).nextLine();
        for (int i = 0; i < listTypeMovie.size(); i++) {
            String name = listTypeMovie.get(i);
            if (delete.equalsIgnoreCase(name)){
                listTypeMovie.remove(name);
                writeFileTypeMovie(listTypeMovie);
                System.out.println("Thể loại  "+delete+" đã bị xóa");
                break;
            }
            System.out.println("Thể loại phim trên không tồn tại");
        }
    }

    public void addTypeMovie() {
        File file = new File("listTypeMovie.txt");
        if (file.exists()){
            listTypeMovie = readFileTypeMovie();
        }
        System.out.println("Bạn muốn thêm bao nhiêu thể loại phim mới");
        int   quantity = 0;
       do {
           try{
               quantity = new Scanner(System.in).nextInt();
               break;
           }catch (InputMismatchException e){
               System.out.println("Dữ liệu bạn vừa nhập không đúng, vui lòng nhập lại");
           }
       }while (true);
        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập tên thể loại phim mới thứ"+(i+1));
            String name = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
            for (int j = 0; j < listTypeMovie.size(); j++) {
                if (listTypeMovie.get(j).equals(name)){
                    System.out.println("Thể loại này đã có trong rạp");
                    return;
                }
            }
            listTypeMovie.add(name);
            writeFileTypeMovie(listTypeMovie);
            System.out.println("Thêm thành công");
        }
    }

}
