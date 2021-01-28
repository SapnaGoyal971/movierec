package com.example.movi;

        import java.io.IOException;
        import java.util.*;

public class TopListOfMoviesByGenre {

    public static HashMap<Integer, Float> sortByValue(HashMap<Integer, Float> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Float> > list =
                new LinkedList<Map.Entry<Integer, Float> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Float> >() {
            public int compare(Map.Entry<Integer, Float> o1,
                               Map.Entry<Integer, Float> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        // put data from sorted list to hashmap
        HashMap<Integer, Float> temp = new LinkedHashMap<Integer, Float>();
        for (Map.Entry<Integer, Float> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }


    public static int userhasnotseen(int usid,int movieid, List<Rating> ra){
        for(int i=0;i<ra.size();i++){
            if(ra.get(i).UserId==usid && ra.get(i).ItemId==movieid)
                return 0;
        }
        return 1;
    }

    public static void top_list_by_genre(int gen,int us_id) throws IOException {
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
        HashMap<Integer, Float> hm = new HashMap<Integer, Float>();

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
            if(userhasnotseen(us_id,movie_ids.get(j),ra)==1)
            hm.put(movie_ids.get(j),avg);
        }
        Map<Integer, Float> hm1 = sortByValue(hm);

        for (Map.Entry<Integer, Float> en : hm1.entrySet()) {
            System.out.println("Key = " + en.getKey() +
                    ", Value = " + en.getValue());
        }
//////////////////////////////////////////////////



    }
}


