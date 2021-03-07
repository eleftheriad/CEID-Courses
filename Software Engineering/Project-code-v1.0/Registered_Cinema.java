import java.util.*;

public class Registered_Cinema extends Cinema
{
    private String city;
    private String address;
    private String name;
    private String owner_name;
    private int telephone_number;
    private String email;
    private ArrayList<Movie> MovieList = new ArrayList<Movie>();
    
    public Registered_Cinema(int aithouses){
       super(aithouses);
    }
    
    public boolean checkForRegisteredCinemas(String area){
        if(this.getCity()==area && this instanceof Registered_Cinema)
            return true;
        else
            return false;
    }
    
    public void displayFoundCinemas(String name){
        if (this.name==name)
            System.out.println("Vrethike Cinema me to onoma: " + name);
    
    }
    
    public void removeMovie(Movie movie){
        for(Movie i : MovieList){
            if(movie==i)//an i tainia iparxei idei ston lista tainiwn tou kinimatografou
            MovieList.remove(movie);
        else
            System.out.println("H sigkekrimeni tiania den iparxei stin lista tainiwn tou Kinimatografou");
        }
     
    }
    
    public Movie returnMovie(int pos){
        if (pos<MovieList.size()-1)
            return MovieList.get(pos);
        else
            return null;
    }
    
    public void inputsData(){
        Scanner scan = new Scanner(System.in);
        Eisagwgi_Stoixeiwn esp = new Eisagwgi_Stoixeiwn();
        esp.eggrafiKinimatografou();
        esp.city.setText(scan.nextLine());
        esp.cinema_address.setText(scan.nextLine());
        esp.cinema_name.setText(scan.nextLine());
        esp.owner_name.setText(scan.nextLine());
        esp.telephone_number.setText(scan.nextLine());
        esp.cinema_email.setText(scan.nextLine());
        
    }
    
    public void addMovie(Movie movie){
         for(Movie i : MovieList){
            if(movie!=i)//an i tainia iparxei idei ston lista tainiwn tou kinimatografou
            MovieList.add(movie);
        else
            System.out.println("H sigkekrimeni tainia iparxei idi stin lista tainiwn tou Kinimatografou");
        }
    }
    
    public void inputScreeningInfo(int pos, Screening_Hall screening_hall, String screening_time){
        MovieList.get(pos).modifyScreeningTime(pos,screening_hall,screening_time);
    }
    
    public void addAditionalScreening(int pos, Screening_Hall screening_hall, String screening_time){
        MovieList.get(pos).addScreening(screening_hall,screening_time);
    }
    
    public String getCity(){
        return city;
    }

    
 
    
    
}
