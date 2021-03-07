import java.util.*;

public class Ticket
{
   
    private String type;
    private double price; 
    private Movie movie;
    private Cinema cinema;
    private int screening_hall;
    private double screening_time;
    
    
    public Ticket(String type,double price,Movie movie,Cinema cinema,double screening_time,int screening_hall)
    {
        
        this.type = type;
        checkTicketType(type,price);
        this.movie = movie;
        this.cinema = cinema;
        //this.screening_hall = screening_hall;
        //this.screening_time = screening_time;
    }

    
    public void highlightMistakes() {}
    
    public void messageToUser(){}
    
    public void validityCheck(){
    
    }
    
    public void checkTicketType(String type, double price){
        if(type == "Student") this.price = 0.5 * price;
        else if(type == "Normal") this.price = 1.0 * price;
        else if(type == "Child") this.price = 0.75 * price;
    }
    
    public boolean checkType(int age, String type){
        if (this.getTicketType()=="Anilikos" && age>18)
            return false;
        else if (this.getTicketType()=="Enilikos" && age<18)
            return false;
        else
            return true;
    }
    
    public void falseTicketChoise(){
        System.out.println("Lathos epilogi Eisitiriou, Prospathiste ksana");
    }
    
    public String getTicketType(){
        return type;
    }
    public double getPrice(){
        return this.price;
    }
}