package entity;

import statics.TypeMovie;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Movie implements InputInfor, Serializable {
    private static int nextId =1;
    private int idMovie;
    private String nameMovie;
    private TypeMovie typeMovie;
    private String director;
    private String performer;
    private String manufacturer;
    private float price;
    private LocalTime movieDuration;
    private LocalDate publishYear;

    public Movie() {
        this.idMovie=nextId;
        nextId++;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public TypeMovie getTypeMovie() {
        return typeMovie;
    }

    public void setTypeMovie(TypeMovie typeMovie) {
        this.typeMovie = typeMovie;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalTime getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(LocalTime movieDuration) {
        this.movieDuration = movieDuration;
    }

    public LocalDate getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(LocalDate publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", nameMovie='" + nameMovie + '\'' +
                ", typeMovie=" + typeMovie.value +
                ", director='" + director + '\'' +
                ", performer='" + performer + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", movieDuration=" + movieDuration +
                ", publishYear=" + publishYear +
                '}';
    }

    @Override
    public void infor() {
        System.out.println("Nhập tên của bộ Phim");
        this.setNameMovie(new Scanner(System.in).nextLine());
        System.out.println("Nhập thể loại của bộ Phim");
        System.out.println("1. Phim Hoạt Hình ");
        System.out.println("2. Phim Hài ");
        System.out.println("3. Phim Kinh Dị ");
        System.out.println("4. Phim Hành Động ");
        System.out.println("5. Phim Gia Đình ");
        int choice = 0;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
            }catch (InputMismatchException e){
                System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , Vui lòng nhập lại");
            }if (choice>0&& choice<6){
                break;
            }
            System.out.println("Dữ liệu bạn vừa nhập không hợp lệ , Vui lòng nhập lại");
        }while (true);
        switch (choice){
            case 1:
                this.setTypeMovie(TypeMovie.CARTOON);
                break;
            case 2:
                this.setTypeMovie(TypeMovie.COMEDY);
                break;
            case 3:
                this.setTypeMovie(TypeMovie.HORROR_MOVIE);
                break;
            case 4:
                this.setTypeMovie(TypeMovie.ACTION_MOVIE);
                break;
            case 5:
                this.setTypeMovie(TypeMovie.FAMILY_MOVIE);
                break;
        }

        System.out.println("Nhập đạo diễn của bộ Phim");
        this.setDirector(new Scanner(System.in).nextLine());
        System.out.println("Nhập diễn viên của bộ Phim");
        this.setPerformer(new Scanner(System.in).nextLine());
        System.out.println("Nhập hãng sản xuất của bộ Phim");
        this.setManufacturer(new Scanner(System.in).nextLine());
        System.out.println("Nhập giá của bộ Phim");
        this.setPrice( new Scanner(System.in).nextFloat());
        System.out.println("Nhập năm xuất bản của bộ Phim (yyyy-MM-dd): ");
        do {
            try{
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateInput = new Scanner(System.in).nextLine();
                this.publishYear= LocalDate.parse(dateInput, dateFormatter);
                break;
            }catch (DateTimeParseException | NullPointerException | UnsupportedTemporalTypeException e){
                System.out.println("Đã sảy ra lỗi, vui lòng nhập lại");
            }catch (DateTimeException e){
                System.out.println("Đã sảy ra lỗi, vui lòng nhập lại");
            }
        }while (true);
        System.out.println("Nhập thời lượng của bộ Phim (HH:mm:ss)");
        do {
            try{
                String timeInput = new Scanner(System.in).nextLine();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                this.movieDuration = LocalTime.parse(timeInput, timeFormatter);
                break;
            } catch (DateTimeException e){
                System.out.println("Đã sảy ra lỗi, vui lòng nhập lại");
            }
        }while (true);
    }
    }
