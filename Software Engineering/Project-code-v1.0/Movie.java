import java.util.*;
import java.sql.Time;
import java.text.*;
import java.lang.*;


public class Movie 
{
    
    private String director;
    private String[] starring;
    private String genre;
    private float duration;
    private String title;
    /*---->*/private String screening_time; //<-----THELW TIPO TIPOU TIME 
     
    private Screening_Hall screening_hall;
    private String description;
    private Screening_Hall[] active_screening_halls= new Screening_Hall[10];//theoroume oti mia tainia den borei
    private SimpleDateFormat[] active_screening_times =  new SimpleDateFormat[10];//na exei panw apo 10 provoles se 1 mera
    

    
    public Movie()
    {
        
   
    }

    
    public void checkUserChoise() {}
    
    public void displayDescription(){
        System.out.println(this.getDescription());
    }
    
    public boolean checkForSameTitle(Search_Movie movie){
        if(this.getTitle()==movie.getTitle())
            return true;
        else 
            return false;
    }
    
    public ArrayList<Movie> displayUserSearchedMovies(Search_Movie[] search_movies){
        ArrayList<Movie> searched_movie_list = new ArrayList<Movie>();
        for(int i=0;i<search_movies.length;i++){
            if(this.getTitle()==search_movies[i].getTitle())
                searched_movie_list.add(this);
        }
        return searched_movie_list;
    }
    
    public void notifyMovieNotExist(){
        System.out.println("H Tainia pou anazitisate den iparxei");
    }
    
    public void deleteMovie(){
        try{
        for (int i=0;i<active_screening_halls.length;i++){
            this.active_screening_halls[i].setID(0); //theoroume oti dn iparxei aithousa cinema me ID 0
            this.active_screening_times[i].parse(null); // 0 se autous tous pinakes isodinamei me aniparxia provolis
        }
        }catch(ParseException e){
            e.printStackTrace();
        }
    }
    
    public void displayMovieScreenings(){
        for (int i=0;i<this.active_screening_halls.length;i++){
            System.out.printf("Aithousa Provolis: %d , wra provolis: %d\n",this.active_screening_halls[i],this.active_screening_times[i]);
        }
        }
    
    public void displayScreeningInformation(int pos){
        if(pos>=0 && pos<11){
            System.out.println("H tainia provaletai stin aithousa :" + this.active_screening_halls[pos] +
            "tin wra: " + this.active_screening_times[pos] + "\n");
            System.out.println("H tainia einai eidos: " + this.getGenre() + 
            "kai exei diarkeia" + this.getDuration());
        }
    }
    
    public void modifyScreeningTime(int pos, Screening_Hall new_screening_hall,String new_screening_time){
        if(pos>=0 && pos<11){
        this.active_screening_halls[pos]=new_screening_hall;
        try{this.active_screening_times[pos].parse(new_screening_time);
         }catch(ParseException e){
            e.printStackTrace();}
        }
        else 
         System.out.println("Den ginetai na tropopoieithei auti i provoli");
    }
    
    
    public void deleteScreening(Screening_Hall screening_hall, String screening_time){
       try{
            for(int i=0;i<this.active_screening_halls.length;i++){
                if (this.active_screening_halls[i]==this.screening_hall)
                    this.active_screening_halls[i].setID(0);
                if (this.active_screening_times[i].toString().compareTo(screening_time)==0)
                    this.active_screening_times[i].parse(null);
                }
            }catch(ParseException e){
                e.printStackTrace();
            }    
    }
    
    public void addScreening(Screening_Hall screening_hall, String screening_time){
        int pos=-1;
        for (int i=0;i<this.active_screening_halls.length;i++){
            if(this.active_screening_halls[i].getID()==0)
                pos=i;
            }
            try{
            if(pos>=0 && pos<11){
                this.active_screening_halls[pos+1]=screening_hall;
                this.active_screening_times[pos+1].parse(screening_time);
            }
            else 
                System.out.println("Den borei na prostethei nea provoli");
            }catch(ParseException e){
                e.printStackTrace();
            }
    }
    
    public boolean checkIfMovieExists(Movie movie){
        if (movie instanceof Movie)
         return true;
        else 
         return false;
    }
    
    public String getTitle(){
        return title;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public float getDuration(){
        return duration;
    }
    
    public String getDescription(){
        return description;
    }
}
