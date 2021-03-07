import java.util.*;

public class Cinema
{
    private int count = 0;
    private String city;
    private String address;
    private String name;
    private String owner_name;
    private int telephone_number;
    private String email;
    private int movie_array_id;
    //private ArrayList<Movie> MovieList = new ArrayList<Movie>();
    public Movie[] CinemaMovieArray = new Movie[2];
    
    public Cinema(String city, String name,Movie movie1,Movie movie2){
       this.city = city;
       this.name = name;
       this.CinemaMovieArray[count++] = movie1;
       this.CinemaMovieArray[count++] = movie2;
       //we ignore some attributes because they dont matter as much
    }
    
    public boolean checkForRegisteredCinemas(String area){
        if(this.getCity()==area && this instanceof Cinema)
            return true;
        else
            return false;
    }
    
    public void displayFoundCinemas(){}
    
   public boolean check_if_movie_exists(String title){
       for(int i=0; i<CinemaMovieArray.length;i++){
           if(title == CinemaMovieArray[i].get_title()){
             movie_array_id = i;
             System.out.println("This Cinema has this movie");
             return true;
            }
        }
        return false;
    }
    
   public String getName(){
       return this.name;
    }
    public int get_movie_array_id(){
       return this.movie_array_id;
    }
    public void inputsData(){}
   
    
    public String getCity(){
        return this.city;
    }
    public void display_screening_info(int id){
        CinemaMovieArray[id].Show_Screenings();
    }
    
    public void set_screenings(int id ){
        if(id==0){
            /* We manually set 3 screenings for each movie for both cinemas
               so to have some numbers to play with. As previously mentioned
               normally these numbers would be set by the cinema itself from 
               the main2 function */
               
            this.CinemaMovieArray[0].Set_Screening(0, 4, 19.30 );
            this.CinemaMovieArray[0].Set_Screening(1, 1, 20.30 );
            this.CinemaMovieArray[0].Set_Screening(2, 5, 21.00 );
            this.CinemaMovieArray[1].Set_Screening(0, 4, 19.30 );
            this.CinemaMovieArray[1].Set_Screening(1, 1, 20.30 );
            this.CinemaMovieArray[1].Set_Screening(2, 5, 21.00 );
        }else{
            this.CinemaMovieArray[0].Set_Screening(0, 3, 03.30 );
            this.CinemaMovieArray[0].Set_Screening(1, 1, 02.30 );
            this.CinemaMovieArray[0].Set_Screening(2, 3, 05.00 );
            this.CinemaMovieArray[1].Set_Screening(0, 2, 06.30 );
            this.CinemaMovieArray[1].Set_Screening(1, 1, 07.30 );
            this.CinemaMovieArray[1].Set_Screening(2, 2, 08.00 );
        }
    }
    public double getget_screeningTime(int id, int screen_id){
         return CinemaMovieArray[id].get_Screening_time(screen_id);
    }
    public int getget_screeningHall(int id, int hall_id){
        return CinemaMovieArray[id].get_Screening_hall(hall_id);
    }
    public void show_Movies(){
        for(int i=0;i<CinemaMovieArray.length;i++){
            System.out.println(CinemaMovieArray[i].get_title());
        
        }
    
    }
    
}