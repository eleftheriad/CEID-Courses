import java.util.*;
import java.sql.Time;
import java.text.*;
import java.lang.*;
import java.util.Scanner;


public class main2{


    public static void main(String[] args) {
    
            UpComingMovies[] UpComingMoviesArray = new UpComingMovies[4];
            Movie[] MovieArray = new Movie[4];   
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            
    
            
        
            UpComingMoviesArray[0]= new UpComingMovies( "Avengers", "Kubrick", "action", 90.6f  );
            UpComingMoviesArray[1]= new UpComingMovies( "BadBoys3", "Dan", "comedy", 130.5f );  
            UpComingMoviesArray[2]= new UpComingMovies( "Nemo", "Jorge", "family", 95.4f ); 
            UpComingMoviesArray[3]= new UpComingMovies( "Pirates", "Dbweiss", "drama" , 100.5f ); 
            
            int movie_counter = 0;
            int hall;
            double time;
            int screening_counter=0;
            int movie_id;
            while(true){
                System.out.println("Movies playing in your cinema:");
                for(int i=0;i<movie_counter;i++){
                        MovieArray[i].showMoviesList();
                    }
                System.out.println("Want to ADD, EDIT OR DELETE a movie? Press 1/2/3");
                int choice = myObj.nextInt();
                if(choice == 1){
                    //Add movie
                    System.out.println("Upcoming Movies:");
                    for(int i=0;i<UpComingMoviesArray.length;i++){
                        UpComingMoviesArray[i].showUpComingMoviesList();
                    }
                    
                    System.out.println("which movie to add? Press '0' for the first one, '1' for the second etc");
                    int userchoice = myObj.nextInt();  
                    screening_counter=0;
                    boolean flag = false;
                    for(int i=0; i<movie_counter; i++){
                        if(UpComingMoviesArray[userchoice].get_title() == MovieArray[i].get_title() ){
                           flag = true; 
                        }
                    
                    } 
                    if(flag == true){
                        System.out.println("This movie has been already added");
                        continue;
                    }else{
                         MovieArray[movie_counter]= new Movie(UpComingMoviesArray[userchoice].get_title(), UpComingMoviesArray[userchoice].getDirector(), UpComingMoviesArray[userchoice].getGenre(), UpComingMoviesArray[userchoice].getDuration());

                    }
                    //MovieArray[1]= new Movie(UpComingMoviesArray[1].get_title());
                    
                  
                    System.out.println("Movie added successfully!");
                    System.out.println("Add screenings? Press '1' for Yes and '0' for No");
                    int answer = myObj.nextInt();
                    
                    
                    if(answer == 1){
                        while(true){
                            System.out.println("Insert hall and time");
                            hall = myObj.nextInt();
                            time = myObj.nextDouble();
                            MovieArray[movie_counter].Set_Screening(screening_counter, hall, time);
                            screening_counter++;
                           
                            System.out.println("Add new screening? Press '1' for Yes and '0' for No");
                            int screen_answer = myObj.nextInt();
                            if(screen_answer == 0){break;}
                        }
                    }
               
                    movie_counter++;
                    continue;
                }else if(choice == 2){    
                   for(int i=0;i<movie_counter;i++){
                            MovieArray[i].showMoviesList();
                        }
                   System.out.println("which movie to edit? Press '0' for the first one, '1' for the second etc");
                   movie_id = myObj.nextInt();
                    while(true){
                        //edit
                        
                        
                        System.out.println("Screenings: ");
                        MovieArray[movie_id].Show_Screenings();
                        System.out.println("add, delete or edit screening? Press 1/2/3. Press 0 to go back");
                        int choice2 = myObj.nextInt(); 
                        if(choice2 == 1){
                            System.out.println("Insert hall and time");
                            hall = myObj.nextInt();
                            time = myObj.nextDouble();
                            MovieArray[movie_id].Set_Screening(screening_counter, hall, time);
                            screening_counter++;
                            continue;
                        }else if(choice2 == 2){
                            MovieArray[movie_id].Show_Screenings();
                            System.out.println("which screening to delete? Press '0' for the first one, '1' for the second etc");
                            int screening_id = myObj.nextInt();
                            MovieArray[movie_id].delete_screening(screening_id);
                            screening_counter--;
                            continue;
                        }else if(choice2 == 3){
                            //edit screening
                            MovieArray[movie_id].Show_Screenings();
                            System.out.println("which screening to edit? Press '0' for the first one, '1' for the second etc");
                            int screening_id = myObj.nextInt();
                            
                            System.out.println("Set new hall and time");
                            hall = myObj.nextInt();
                            time = myObj.nextDouble();
                            MovieArray[movie_id].Set_Screening(screening_id, hall, time);
                            
                            MovieArray[movie_id].Show_Screenings();
                            continue;
                        }else{break;}
                   }
                    
                }
                else if(choice == 3){
                    // delete movie
                    
                    for(int i=0;i<movie_counter;i++){
                        MovieArray[i].showMoviesList();
                    }
                    System.out.println("which movie to delete? Press '0' for the first one, '1' for the second etc");
                    movie_id = myObj.nextInt();
                    //first delete all screenings
                    MovieArray[movie_id].delete_screenings();
                    screening_counter=0;
                    
                    for(int i = 0; i < movie_counter; i++){
                        if(i == movie_id){
                         
                            for(int j = i; j < movie_counter - 1; j++){
                                MovieArray[j] = MovieArray[j+1];
                            }
                            movie_counter--;
                            break;
                        }
                    
                    
                    }   
                    
                    /*System.out.println("Movies left:");
                    for(int i=0;i<movie_counter;i++){
                        MovieArray[i].showMoviesList();
                    }
                    System.out.println("screenings left:");
                    MovieArray[movie_id].Show_Screenings();*/
                    continue;
                }  
                else{
                    System.out.println("Wrong Input. Program Terminated");
                    break;
                }
            }
    }      
}    
