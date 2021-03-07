
/**
 * Write a description of class Movie here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Movie
{
    // instance variables - replace the example below with your own

    private String title;
    private int count= 0;
    private int count_screen = 0;
    private String director;
    //private String[] starring;
    private String genre;
    private float duration;
    private int[] active_screening_halls= new int[10];//theoroume oti mia tainia den borei
    private double[] active_screening_times =  new double[10];         //na exei panw apo 10 provoles se 1 mera

    /**
     * Constructor for objects of class Movie
     */
    public Movie(String title, String director, String genre, float duration)
    {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        
        count++;
    }

  
    public int showMoviesList()
    {
        // put your code here
        //for (int i=0;i<count;i++){
            System.out.println(this.title);

       // }
        return 0;
                
    }
    
    public int Set_Screening(int x, int hall, double time ){
        this.active_screening_halls[x] = hall;
        this.active_screening_times[x] = time;
        count_screen++;
        return 0;
    }
    public int Show_Screenings(){
        for(int i=0;i<count_screen;i++){
            System.out.println(this.active_screening_halls[i]);
            System.out.println(this.active_screening_times[i]);
        }
        
        return 0;
    }
    public String get_title(){
        return this.title;
    
    }
    public void delete_screening(int id ){
        for(int i = 0; i < count_screen; i++){
            if(i == id){
                // deleting screening
                for(int j = i; j < count_screen - 1; j++){
                    this.active_screening_halls[j] = this.active_screening_halls[j+1];
                    this.active_screening_times[j] = this.active_screening_times[j+1];
                }
                count_screen--;
                break;
            }
        }

    }  
    public void delete_screenings( ){
       // deleting screenings
       for(int i = 0; i < count_screen;i++){
           this.active_screening_halls[i] = 0;
           this.active_screening_times[i] = 0;
       }
        count_screen = 0;
            
    }
    public void display_description(){
        System.out.println(this.title);
        System.out.println("Directed by " + this.director);
        System.out.println("It is a/an " + this.genre + "film");
        System.out.println("It lasts " + this.duration + " seconds");
    
    }
    public int get_Screening_hall(int x){
        return this.active_screening_halls[x];
       
    }
    public double get_Screening_time(int x){
        return this.active_screening_times[x];
       
    }
}  

