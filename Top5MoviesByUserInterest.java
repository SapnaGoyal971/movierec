package com.example.movi;

import java.io.IOException;
import java.util.*;


public class Top5MoviesByUserInterest {
    public static<K> void increment(Map<K,Integer> map, K key,int rate) {
        map.merge(key, 1, Integer::sum);
    }

    public static void findtop5(int us_id) throws IOException {
////////////////////////////////////////////////////////////////////////
        List<Rating> ra =new ArrayList<Rating>() ;
        ParseFromFile.parsing_rating(ra);

        List<Integer> movies=new ArrayList<>();//In order to find the list of movies that user has seen

        Map<Integer,Integer> map=new HashMap<Integer,Integer>(); //In order to map the movie id with the rating that user have given to that movie

        for(int i=0;i< ra.size();i++){
            if(us_id==ra.get(i).UserId){
                movies.add(ra.get(i).ItemId);
              //  increment(map, ra.get(i).ItemId,ra.get(i).rating);
                map.put(ra.get(i).ItemId,ra.get(i).rating);
            }
        }


        List<Movie> mo =new ArrayList<Movie>() ;
        ParseFromFile.parsing_movie(mo);

        List<Integer> genre=new ArrayList<>(); //In order to find the genre's points based on user's interest

        for(int i=0;i<19;i++)
            genre.add(0);

        for(int j=0;j<movies.size();j++){
            for(int i=0;i<mo.size();i++){

                if(movies.get(j)==mo.get(i).movieId){
                    for(int k=0;k<19;k++){
                      genre.set(k,genre.get(k)+ map.get(movies.get(j))*mo.get(i).genre.get(k));
//In this we have added genre k to the movie's(that user have seen) genre k multiplied to the points that user has given to that movie
                    }
                }

            }
        }

        Map<Integer,Integer> m2=new HashMap<Integer,Integer>();//In order to keep track of genre's index even after sorting
        for(int i=0;i<19;i++) {
            m2.put(genre.get(i),i);
        }

        Collections.sort(genre, Collections.reverseOrder());

        for(int i=0;i<19;i++)
            System.out.println(genre.get(i));
//We have got the list of genres that a user likes from most favourite to least favourite
/////////////////////////////////////////////////////////////////////////////////////


        //Now we will have to find top 5 movies based on user's favorite genres



         TopMovieByGenre.top_by_genre(m2.get(genre.get(0)));


         TopListOfMoviesByGenre.top_list_by_genre(m2.get(genre.get(0)),us_id);




    }
}
