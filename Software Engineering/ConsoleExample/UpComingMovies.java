
/**
 * Write a description of class UpComingMovies here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UpComingMovies
{
    protected String title;
    private int count=0;
    private String director;
    //private String[] starring;
    private String genre;
    private float duration;


    /**
     * Constructor for objects of class UpComingMovies
     */
    public UpComingMovies(String title, String director, String genre, float duration )
    {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.duration = duration;
        count++;
    }

    /**
     * An example of a method - replace this comment with your own
     *
  <!--    * @param    sample parameter for a method
     * @return    the sum of x and y -->
     */
    public int showUpComingMoviesList()
    {
        // put your code here
        //for (int i=0;i<count;i++){
            System.out.println(this.title);

       // }
        return 0;
                
    }
    public String get_title(){
        return this.title;
    }
    public String getDirector(){
    return this.director;
    }
    public String getGenre(){
    return this.genre;
    }
    public float getDuration(){
    return this.duration;
    }
}
