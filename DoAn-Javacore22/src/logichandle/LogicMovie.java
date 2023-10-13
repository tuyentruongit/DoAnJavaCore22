package logichandle;

import entity.Movie;
import entity.User;

import javax.sound.midi.MidiFileFormat;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LogicMovie {
    List<String> listTypeMovie = new ArrayList<>();
    List<Movie> movieList = new ArrayList<>();

    public Movie searchMovie(String nameMovie) {
        for (int i = 0; i < movieList.size(); i++) {
            if (nameMovie.equalsIgnoreCase(movieList.get(i).getNameMovie())){
                return movieList.get(i);
            }
        }
        return null;
    }

    public void printMovie() {
        System.out.println(movieList);
    }

    public void printTypeMovie() {
        if (listTypeMovie.isEmpty()){
            System.out.println("Chưa có thể loại phim nào, Vui lòng thêm mới");
            return;
        }
        System.out.println(listTypeMovie);
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
            String a = listTypeMovie.get(i);
            if (delete.equalsIgnoreCase(a)){
                listTypeMovie.remove(a);
                System.out.println("Thể loại phim "+delete+" đã bị xóa");
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
        System.out.println("Nhập tên thể loại phim mới");
        String name = new Scanner(System.in).nextLine();
        listTypeMovie.add(name);
        writeFileTypeMovie(listTypeMovie);
        System.out.println("Thể Loại mới đã được thêm");
    }

    public void addMovie() {
        System.out.println("Bạn muốn thêm bao nhiêu bộ phim mới");
        int quantity ;
        do {
            try {
                 quantity = new Scanner(System.in).nextInt();
                 break;
            }catch (InputMismatchException e){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , Vui lòng nhập lại ");
            }
        }while (true);
        for (int i = 0; i < quantity; i++) {
            System.out.println("Nhập thông tin của bộ phim thứ "+(i+1));
            Movie movie = new Movie();
            movie.infor();
        }
    }
}
