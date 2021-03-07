    import java.util.*;
    import java.sql.Time;
    import java.text.*;
    import java.lang.*;
    import java.util.Scanner;
    
    
    public class main1{
    
    public static void main(String[] args) {
        
            
            Movie[] MovieArray = new Movie[4]; 
            Seat[] SeatArray = new Seat[20]; 
            Cinema[] CinemaArray = new Cinema[2];
            Movie[] CinemaMovieArray = new Movie[2];
            Ticket[] TicketArray = new Ticket[20];
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            
            double def_price = 8.0;//default price for all movies
            int movie_id;
            int seat_count = 0;
            int ticket_count = 0;
            int choice;
            int choice2;
            int cinema_id = 0;
            int cinema_movie_id;
            int loc_id=0;
            boolean flag = true;
            String[] type_of_ticket= new String[]{"Student","Normal","Child"};
            String[] locations = new String[] {"Patras", "Komotini", "Chalkida"};
            //movies playing in random cinemas. Lets say only 4 are in the system
            MovieArray[0]= new Movie( "Avengers", "Kubrick", "action", 90.6f  );
            MovieArray[1]= new Movie( "BadBoys3", "Dan", "comedy", 130.5f );  
            MovieArray[2]= new Movie( "Nemo", "Jorge", "family", 95.4f ); 
            MovieArray[3]= new Movie( "Pirates", "Dbweiss", "drama" , 100.5f ); 
            
            //we create 2 cinemas and pass 2 movies that play in that cinema
            //Normally the cinemas themselves add and delete movies
            //but here we need some for the example so we pass them manually
            CinemaArray[0]= new Cinema( "Patras", "VESO", MovieArray[0],MovieArray[2]  );
            CinemaArray[1]= new Cinema( "Komotini", "ODEON", MovieArray[1],MovieArray[3]);
            
            //setting some screenings for the example
            CinemaArray[0].set_screenings(0);
            CinemaArray[1].set_screenings(1);
            while(true){
                System.out.println("Browse Movies? Press 1");
                System.out.println("Browse Theaters? Press 2");
                choice2 = myObj.nextInt();
                //movie-->theaters
                if(choice2==1){
                    System.out.println("------------------");
                    System.out.println("Movies playing now:");
                    for(int i=0;i<4;i++){
                         MovieArray[i].showMoviesList();
                    }
                    System.out.println("Click for more info: (Press '0' for first etc)");
                    movie_id = myObj.nextInt(); 
                    if(movie_id > 3){System.out.println("There are only 4 in the system"); break;}
                    //show info for movie
                    MovieArray[movie_id].display_description();
                    System.out.println("------------");
                    System.out.println("Want to get a ticket for this movie? Press '1' for yes, '0' for no");
                    System.out.println("------------");
                    choice = myObj.nextInt();
                    if(choice == 1){
                        System.out.println("Select your location (Press '0' for first etc)");
                        for(int i=0;i<locations.length;i++){
                            System.out.println(locations[i]);
                        }
                        loc_id = myObj.nextInt();
                        for(int i=0;i<CinemaArray.length;i++){
                            if(locations[loc_id] == CinemaArray[i].getCity()){   
                                flag = false;
                                if(CinemaArray[i].check_if_movie_exists(MovieArray[movie_id].get_title())){
                                    System.out.println("Cinemas in your city that have this movie:");
                                    System.out.println(CinemaArray[i].getName());
                                    System.out.println("Select a cinema (Press '0' for first etc)");
                                    cinema_id = myObj.nextInt();
                                    
                                    
                                }else{System.out.println("No cinemas with this movie in your city");break;} 
                               
                            }
                            
                        }   
                        if(flag == true){
                            System.out.println("Sorry, there is no Cinema in your city");
                            continue;
                        }
                    
                    }else{
                        continue;
                    }
                    
                    System.out.println("------------");
                    System.out.println("Screenings for the Movie: " + MovieArray[movie_id].get_title() + " at Cinema: " +  CinemaArray[cinema_id].getName() );
                    System.out.println("------------");
                    
                    CinemaArray[cinema_id].display_screening_info(CinemaArray[cinema_id].get_movie_array_id());
                    System.out.println("Choose a screening. '0' for first etc");
                    int screen_id = myObj.nextInt(); //screening they want 
                    System.out.println("------------");
                    System.out.println("choose seat (Enter row number, then column number)");
                    System.out.println("------------");
                    System.out.println("(up to 19) Row :");
                    int row_id = myObj.nextInt();
                    System.out.println("(up to 19) Column :");
                    int col_id = myObj.nextInt();
                    if(row_id > 19 || col_id>19){
                        System.out.println("UP TO 19!");
                        continue;
                    }
                    for(int i=0; i<seat_count;i++){
                        if(SeatArray[i].getrow() == row_id && SeatArray[i].getcol() == col_id){
                            System.out.println("This seat is taken.");
                            continue;
                        }
                    }
                    
                    SeatArray[seat_count]= new Seat(row_id,col_id);
                    seat_count++;
                    
                    System.out.println("------------");
                    System.out.println("Choose type of ticket");
                    System.out.println("Student/Normal/Child (Press '0' for first etc)");
                    int type_id = myObj.nextInt();
                    
                    System.out.println("------------");
                    System.out.println("Confirm? Press '1' for yes, '0' for no");
                    if (myObj.nextInt() == 1){
                        TicketArray[ticket_count++] = new Ticket(type_of_ticket[type_id], def_price, MovieArray[movie_id],CinemaArray[cinema_id],CinemaArray[cinema_id].getget_screeningTime(CinemaArray[cinema_id].get_movie_array_id(),screen_id), CinemaArray[cinema_id].getget_screeningHall(CinemaArray[cinema_id].get_movie_array_id(),screen_id));
                        System.out.println("------------");
                        System.out.println("You got a ticket of type  " + TicketArray[ticket_count-1].getTicketType() + " at the price of " + TicketArray[ticket_count-1].getPrice() + " for the movie " + MovieArray[movie_id].get_title()); 
                        System.out.println(" It starts at " + CinemaArray[cinema_id].getget_screeningTime(CinemaArray[cinema_id].get_movie_array_id(),screen_id) + " in hall " + CinemaArray[cinema_id].getget_screeningHall(CinemaArray[cinema_id].get_movie_array_id(),screen_id) + " at cinema: " + CinemaArray[cinema_id].getName() + " in " + locations[loc_id]);
                        System.out.println("You are sitting in row: " + SeatArray[seat_count-1].getrow()+ " column: " + SeatArray[seat_count-1].getcol());
                    }else{
                        System.out.println("---Back to Main Page--");
                        continue;
                    }
                    
                }
                
                //theater-->movies
                else if(choice2==2){
                    System.out.println("-----------");
                    System.out.println("Select your location (Press '0' for first etc)");
                    for(int i=0;i<locations.length;i++){
                        System.out.println(locations[i]);
                    }
                    loc_id = myObj.nextInt();
                    for(int i=0;i<CinemaArray.length;i++){
                        if(locations[loc_id] == CinemaArray[i].getCity()){   
                            
                            flag = false;
                            
                            System.out.println("Cinemas in your city:"); 
                            System.out.println(CinemaArray[i].getName());
                            System.out.println("Select a cinema (Press '0' for first etc)");
                            cinema_id = myObj.nextInt();
                            CinemaArray[cinema_id].show_Movies();
                               
                        }
                        
                    }   
                    if(flag == true){
                        System.out.println("Sorry, there is no Cinema in your city");
                        continue;
                    }
                    System.out.println("Click a movie for more info: (Press '0' for first etc)");
                    movie_id = myObj.nextInt();
                    if(movie_id > 3){System.out.println("There are only 4 in the system"); break;}
                    //show info for movie
                    MovieArray[movie_id].display_description();
                    System.out.println("------------");
                    System.out.println("Want to get a ticket for this movie? Press '1' for yes, '0' for no");
                    System.out.println("------------");
                    choice = myObj.nextInt();
                    if(choice != 1){
                        continue;
                    }   
                    System.out.println("------------");
                    System.out.println("Screenings for the Movie: " + MovieArray[movie_id].get_title() + " at Cinema: " +  CinemaArray[cinema_id].getName() );
                    System.out.println("------------");
                    CinemaArray[cinema_id].display_screening_info(CinemaArray[cinema_id].get_movie_array_id());
                    System.out.println("Choose a screening. '0' for first etc");
                    int screen_id = myObj.nextInt(); //screening they want 
                    System.out.println("------------");
                    System.out.println("choose seat (Enter row number, then column number)");
                    System.out.println("------------");
                    System.out.println("(up to 19) Row :");
                    int row_id = myObj.nextInt();
                    System.out.println("(up to 19) Column :");
                    int col_id = myObj.nextInt();
                    if(row_id > 19 || col_id>19){
                        System.out.println("UP TO 19!");
                        continue;
                    }
                    for(int i=0; i<seat_count;i++){
                        if(SeatArray[i].getrow() == row_id && SeatArray[i].getcol() == col_id){
                            System.out.println("This seat is taken.");
                            continue;
                        }
                    }
                    
                    SeatArray[seat_count]= new Seat(row_id,col_id);
                    seat_count++;
                    
                    System.out.println("------------");
                    System.out.println("Choose type of ticket");
                    System.out.println("Student/Normal/Child (Press '0' for first etc)");
                    int type_id = myObj.nextInt();
                    
                    System.out.println("------------");
                    System.out.println("Confirm? Press '1' for yes, '0' for no");
                    if (myObj.nextInt() == 1){
                        TicketArray[ticket_count++] = new Ticket(type_of_ticket[type_id], def_price, MovieArray[movie_id],CinemaArray[cinema_id],CinemaArray[cinema_id].getget_screeningTime(CinemaArray[cinema_id].get_movie_array_id(),screen_id), CinemaArray[cinema_id].getget_screeningHall(CinemaArray[cinema_id].get_movie_array_id(),screen_id));
                        System.out.println("------------");
                        System.out.println("You got a ticket of type  " + TicketArray[ticket_count-1].getTicketType() + " at the price of " + TicketArray[ticket_count-1].getPrice() + " for the movie " + MovieArray[movie_id].get_title()); 
                        System.out.println(" It starts at " + CinemaArray[cinema_id].getget_screeningTime(CinemaArray[cinema_id].get_movie_array_id(),screen_id) + " in hall " + CinemaArray[cinema_id].getget_screeningHall(CinemaArray[cinema_id].get_movie_array_id(),screen_id) + " at cinema: " + CinemaArray[cinema_id].getName() + " in " + locations[loc_id]);
                        System.out.println("You are sitting in row: " + SeatArray[seat_count-1].getrow()+ " column: " + SeatArray[seat_count-1].getcol());
                    }else{
                        System.out.println("---Back to Main Page--");
                        continue;
                    }
                    
                }
  
            }
    
        } 
}
