package entity;

import statics.TypeMovie;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie implements InputInfor {
    private static int nextId =1;
    private int idMovie;
    private String nameMovie;
    private TypeMovie typeMovie;
    private String director;
    private String performer;
    private String manufacturer;
    private int price;
    private LocalTime movieDuration;
    private LocalDate publishYear;

    public Movie() {
        this.idMovie=nextId;
        nextId++;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
    public void infor() {

    }
}
