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


    public void addMovie() {
        File file = new File("movie.data");
        if (file.exists()){
            movieList=readFileMovie();
        }
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
            movieList.add(movie);
            writeFileMovie(movieList);
        }
    }
    public   void writeFileMovie(  List<Movie> movieList ){
        try {
            ObjectOutputStream writeFile = new ObjectOutputStream(new FileOutputStream("movie.data"));
            writeFile.writeObject(movieList);
            writeFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public    List<Movie>   readFileMovie()  {

        ObjectInputStream readFile = null;
        try {
            readFile = new ObjectInputStream(new FileInputStream("movie.data"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return   (List<Movie>)  readFile.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
