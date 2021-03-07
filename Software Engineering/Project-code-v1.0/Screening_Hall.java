import java.util.*;

public class Screening_Hall
{
    
    private int total_seats;
    private int available_seats;
    private int ID;
    private Seat[] seat = new Seat[total_seats];

    
    public Screening_Hall()
    {
       
    }

    public boolean checkAvailableSeats(int count){
        if(this.available_seats>count)
            return true;
        else 
            return false;
    }
    
    public void seatOptions(int screening_hall,Seat seat){}
    
    public void notifyMaxNumberOfSeats(){
        System.out.println("O megistos arithmos diathesimwn thesewn einai: "+ available_seats);
    }
    
    public void checkUserChoise(){
        Eisagwgi_Stoixeiwn esp = new Eisagwgi_Stoixeiwn();
        esp.cinema.getText();
    }
    
    public void seatsForAlternateScreening(){}
    
    public int getID(){
        return ID;
    }
    
    public void setID(int setID){
        ID=setID;
    }
    
}
