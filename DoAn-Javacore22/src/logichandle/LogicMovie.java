package logichandle;

import entity.Movie;

import java.util.ArrayList;
import java.util.List;

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
}
