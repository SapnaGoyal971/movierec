package com.example.movi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopMovieByGenre {
    public static void top_by_genre(int gen) throws IOException {
        List<Movie> mo =new ArrayList<Movie>() ;
        ParseFromFile.parsing_movie(mo);
/////////////////////////////////////////////////////////////
        List<Integer> movie_ids=new ArrayList<>();   //To get the list of movie id's with the given genre gen
        for(int i=0;i<mo.size();i++){
            if(mo.get(i).genre.get(gen)==1)
                movie_ids.add(mo.get(i).movieId);
        }

       /* for(int i=0;i<movie_ids.size();i++){
            System.out.println(movie_ids.get(i));
        }*/
///////////////////////////////////////////////////////////////
        List<Rating> ra =new ArrayList<Rating>() ;
        ParseFromFile.parsing_rating(ra);

        int top_average_rating_movie_id_by_genre=-1;

        float maxx=0;
        for(int j=0;j<movie_ids.size();j++) {
            int sum=0;
            int count=0;
            for (int i = 0; i < ra.size(); i++) {
                if (ra.get(i).ItemId ==movie_ids.get(j)){
                    sum+=ra.get(i).rating;
                    count++;
                }
            }
            float avg= sum/(float)count;
            if(maxx<avg){
                maxx=avg;
                top_average_rating_movie_id_by_genre=movie_ids.get(j);
            }
        }

        System.out.println(top_average_rating_movie_id_by_genre);
        System.out.println(maxx);

    }
}
