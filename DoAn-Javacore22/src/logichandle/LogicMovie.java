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

    public void searchMovieForUser() {
        statrReadFileMovie();
        System.out.println("Nhập tên phim mà bạn muốn tìm ");
        String nameMovie = new Scanner(System.in).nextLine();
        Movie movie = searchMovie(nameMovie);
        if (movie!=null){
            System.out.println("***************************************************");
            System.out.println("Tên phim: "+ movie.getNameMovie());
            System.out.println("Thể Loại: "+ movie.getTypeMovie().value);;
            System.out.println("Đạo diễn: "+ movie.getDirector());;
            System.out.println("Diễn Viên Chính: "+ movie.getPerformer());;
            System.out.println("Thời lượng phim: "+ movie.getMovieDuration());;
            System.out.println("Hãng sản xuất: "+ movie.getManufacturer());;
            System.out.println("Năm sản xuất: "+ movie.getPublishYear());;
            System.out.println("Giá tiền: "+ movie.getPrice()+ " VND");;
            System.out.println("***************************************************");
            return;
        }
        System.out.println("Không có tên phim nào như trên");
    }
    public Movie searchMovie(String nameMovie) {
        statrReadFileMovie();
        for (int i = 0; i < movieList.size(); i++) {
            if (nameMovie.equalsIgnoreCase(movieList.get(i).getNameMovie())){
                return movieList.get(i);
            }
        }
        return null;
    }

    public void printMovie() {
      statrReadFileMovie();
        if (movieList.isEmpty()){
            System.out.println("Chưa có phim nào, Vui lòng thếm mới");
            return;
        }
        for (int i = 0; i < movieList.size(); i++) {
            System.out.println("***************************************************");
            System.out.println("Tên phim: "+movieList.get(i) .getNameMovie());
            System.out.println("Thể Loại: "+ movieList.get(i).getTypeMovie().value);;
            System.out.println("Đạo diễn: "+ movieList.get(i).getDirector());;
            System.out.println("Diễn Viên Chính: "+ movieList.get(i).getPerformer());;
            System.out.println("Thời lượng phim: "+ movieList.get(i).getMovieDuration());;
            System.out.println("Hãng sản xuất: "+ movieList.get(i).getManufacturer());;
            System.out.println("Năm sản xuất: "+ movieList.get(i).getPublishYear());;
            System.out.println("Giá tiền: "+ movieList.get(i).getPrice()+ " VND");;
            System.out.println("***************************************************");
        }
    }


    public void addMovie() {
        statrReadFileMovie();
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

    public void deleteMovie() {
        statrReadFileMovie();
        if (movieList.isEmpty()){
            System.out.println("Chưa có  phim nào, Vui lòng thêm mới");
            return;
        }
        System.out.println("Nhập tên phim mà bạn muốn xóa");
        String delete = new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");
        for (int i = 0; i < movieList.size(); i++) {
            if (delete.equalsIgnoreCase(movieList.get(i).getNameMovie())){
                movieList.remove(movieList.get(i));
                writeFileMovie(movieList);
                System.out.println("Phim "+delete+" đã bị xóa");
                return;
            }
        }
        System.out.println("Phim trên không tồn tại trong rạp");
    }

    public void searchByTypeMovie() {
      statrReadFileMovie();
        System.out.println("Nhập thể loại phim mà bạn muốn tìm kiếm");
        String nameTypemovie =new Scanner(System.in).nextLine().trim().replaceAll("\\s+", " ");;
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getTypeMovie().value.equalsIgnoreCase(nameTypemovie)){
                System.out.println("***************************************************");
                System.out.println("Tên phim: "+movieList.get(i) .getNameMovie());
                System.out.println("Thể Loại: "+ movieList.get(i).getTypeMovie().value);;
                System.out.println("Đạo diễn: "+ movieList.get(i).getDirector());;
                System.out.println("Diễn Viên Chính: "+ movieList.get(i).getPerformer());;
                System.out.println("Thời lượng phim: "+ movieList.get(i).getMovieDuration());;
                System.out.println("Hãng sản xuất: "+ movieList.get(i).getManufacturer());;
                System.out.println("Năm sản xuất: "+ movieList.get(i).getPublishYear());;
                System.out.println("Giá tiền: "+ movieList.get(i).getPrice()+ " VND");;
                System.out.println("***************************************************");
                return;
            }
        }
        System.out.println("Hiện tại rạp chưa có thể loại phim "+ nameTypemovie + " như bạn đã nhập");
    }
    public void statrReadFileMovie(){
        File file = new File("movie.data");
        if (file.exists()){
            movieList=readFileMovie();
        }
    }
}
