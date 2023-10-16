package entity;

import java.io.Serializable;
import java.time.LocalTime;

public class ShowTimeMovie  implements Serializable {
    private Movie movie;
    private LocalTime giochieu;

    public ShowTimeMovie(Movie movie, LocalTime giochieu) {
        this.movie = movie;
        this.giochieu = giochieu;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalTime getGiochieu() {
        return giochieu;
    }

    public void setGiochieu(LocalTime giochieu) {
        this.giochieu = giochieu;
    }

    @Override
    public String toString() {
        return "ShowTimeMovie{" +
                "movie=" + movie +
                ", giochieu=" + giochieu +
                '}';
    }
}
