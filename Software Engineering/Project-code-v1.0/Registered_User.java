import java.util.*;
//import javax.swing.*;

public class Registered_User extends User
{
    
    private String name;
    private String lastname;
    private int age;
    private String address;
    private String email;
    private ArrayList<Credit_Card> credit_card=new ArrayList<Credit_Card>();
    private ArrayList<Prepaid_Card> prepaid_card= new ArrayList<Prepaid_Card>();
    
    
    public Registered_User(String name,String lastname,int age,String address,String email,Credit_Card credit_card)
    {
        this.name=name;
        this.lastname=lastname;
        this.age=age;
        this.address=address;
        this.email=email;
        this.credit_card.add(credit_card);
       
    }

    
    
    public void inputData(){
        Scanner scan = new Scanner(System.in);
        Eisagwgi_Stoixeiwn esp1 = new Eisagwgi_Stoixeiwn();
        esp1.siblirwsiPlirwmis();
        esp1.first_name.setText(scan.nextLine());
        esp1.card_number.setText(scan.nextLine());
        esp1.last_name.setText(scan.nextLine());
        esp1.name_on_card.setText(scan.nextLine());
        esp1.email.setText(scan.nextLine());
        esp1.card_type.setText(scan.nextLine());
        esp1.address.setText(scan.nextLine());
        esp1.CVC_code.setText(scan.nextLine());
        
    }
    
    public void chooseArea(){
        Scanner scan = new Scanner(System.in);
        Eisagwgi_Stoixeiwn esp2 = new Eisagwgi_Stoixeiwn();
        esp2.epilogiPerioxis();
        esp2.choose_location.setText(scan.nextLine());
        esp2.choose_theater.setText(scan.nextLine());   
    }
    
    public void chooseCinema(){
        Scanner scan = new Scanner(System.in);
        Eisagwgi_Stoixeiwn esp5 = new Eisagwgi_Stoixeiwn();
        esp5.epilogiKinimatografou();
        esp5.cinema.setText(scan.nextLine());
    }
    
    public void chooseMovie(){
        Scanner scan = new Scanner(System.in);
        Eisagwgi_Stoixeiwn esp3 = new Eisagwgi_Stoixeiwn();
        esp3.epilogiTainias();
        esp3.movie.setText(scan.nextLine());
    }
    
    public void chooseHour(){}
    
    //paraplanitiko to onoma tis methodou alla emeine apo tin analisi sta sequence diagrams
    public void chooseNumberOfSeats(){ 
        Scanner scan = new Scanner(System.in);
        Eisagwgi_Stoixeiwn esp4 = new Eisagwgi_Stoixeiwn();
        esp4.epilogiThesis();
        esp4.seat_position.addActionListener(this);
    }
    
}
