package com.example.movi;
import java.io.*;
import java.util.regex.Pattern;


public class movii {
    public static void main(String[] args)throws Exception {


        TopMovieByGenre.top_by_genre(0);
        TopListOfMoviesByGenre.top_list_by_genre(0,9    );
        Top5MoviesByUserInterest.findtop5(62);

    }

}
