import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/*Με την κλάση αυτή υλοποιούμε κάποια στοιχειώδη στοιχεία γραφικής διεπαφής που χρειάζονται
   για την επικοινωνία των χρηστών με το σύστημα μας. Έχουμε προσπαθήσει όσα υλοποιούνται εδώ
   να συνάδουν με τα όσα παρουσιάσαμε στα mockup screens*/


public class Eisagwgi_Stoixeiwn extends JFrame implements ActionListener
{
    //Eisagwgi stoixeiwn Plirwmis
    JTextField first_name = new JTextField();
    JTextField card_number= new JTextField();
    JTextField last_name = new JTextField();
    JTextField name_on_card= new JTextField();
    JTextField email = new JTextField();
    JTextField card_type = new JTextField();
    JTextField address = new JTextField();
    JTextField CVC_code = new JTextField();
    JButton oloklorwsi = new JButton("Oloklirwsi Agoras");
    
    //Epilogi Perioxis Kinimatografou
    JTextField choose_location = new JTextField();
    JTextField choose_theater = new JTextField();
    
    //epiologi thesis k eisitiriou
    String[] epiloges_thesis = {"MPROSTA","DEKSIA","PISW","ARISTERA"};
    String[] tipoi_eisitiriou = {"KANONIKO","FOITITIKO","ANILIKOS","PROSFORA"};
    JComboBox seat_position = new JComboBox();
    JComboBox ticket_type = new JComboBox();
    
    //epilogi tainias
    JTextField movie = new JTextField();
    
    //epilogi kinimatografou
    JTextField cinema = new JTextField();
    
    //eggrafi Kinimatografou sto sistima
    JTextField city = new JTextField();
    JTextField cinema_address = new JTextField();
    JTextField cinema_name = new JTextField();
    JTextField owner_name = new JTextField();
    JTextField telephone_number = new JTextField();
    JTextField cinema_email = new JTextField();
   
    public Eisagwgi_Stoixeiwn()
    {   
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        ActionListener Box = new ActionListener(){
    public void actionPerformed(ActionEvent evt) {
        if (seat_position.getSelectedItem() != null) {
            System.out.println(seat_position.getSelectedItem().toString());
        }
    }
};   
        
    }
    
    public void siblirwsiPlirwmis(){
        JPanel pane = new JPanel();
        JLabel first_name_label = new JLabel("first name: ");
        JLabel card_number_label = new JLabel("cardnumber: ");
        JLabel last_name_label = new JLabel("last name: ");
        JLabel name_on_card_label= new JLabel("name on Card: ");
        JLabel email_label = new JLabel("email: ");
        JLabel card_type_label =  new JLabel("card type: ");
        JLabel address_label = new JLabel("address: ");
        JLabel CVC_code_label = new JLabel("CVC: ");
        pane.add(first_name_label);
        pane.add(first_name);
        pane.add(card_number_label);
        pane.add(card_number);
        pane.add(last_name_label);
        pane.add(last_name);
        pane.add(name_on_card_label);
        pane.add(name_on_card);
        pane.add(email_label);
        pane.add(email);
        pane.add(card_type_label);
        pane.add(card_type);
        pane.add(address_label);
        pane.add(address);
        pane.add(CVC_code_label);
        pane.add(CVC_code);
        add(pane);
        setVisible(true);
    }
    
    
    public void epilogiPerioxis(){
        JPanel pane = new JPanel();
        JLabel choose_location_label = new JLabel("Choose Location: ");
        JLabel choose_theater_label = new JLabel("Choose Theater: ");
        pane.add(choose_location_label);
        pane.add(choose_location);
        pane.add(choose_theater_label);
        pane.add(choose_theater);
        add(pane);
        
    }
    
    public void epilogiThesis(){
        String[] epiloges_thesis = {"MPROSTA","DEKSIA","PISW","ARISTERA"};
        String[] tipoi_eisitiriou = {"KANONIKO","FOITITIKO","ANILIKOS","PROSFORA"};
        JPanel pane = new JPanel();
        JLabel choose_seat = new JLabel("Choose Seat: ");
        JComboBox seat_position = new JComboBox();
        for (int i=0;i<epiloges_thesis.length;i++){
            seat_position.addItem(epiloges_thesis[i]); //prosomoiwsi tis siberiforas pou dwsame sta mockup screens 
        }
        JLabel choose_ticket = new JLabel("Choose Ticket: ");
        JComboBox ticket_type = new JComboBox();
        for (int i=0;i<tipoi_eisitiriou.length;i++){
            ticket_type.addItem(tipoi_eisitiriou[i]); //prosomoiwsi tis siberiforas pou dwsame sta mockup screens 
        }
        pane.add(choose_seat);
        pane.add(seat_position);
        pane.add(choose_ticket);
        pane.add(ticket_type);
        add(pane);
           
    }
   
   
    public void actionPerformed(ActionEvent evt) {
        if (seat_position.getSelectedItem() != null) {
            System.out.println(seat_position.getSelectedItem().toString());
        }
    }
 
    
    public void epilogiTainias(){
        
        JLabel search_movie = new JLabel("Search Movie");
        JPanel pane = new JPanel();
        pane.add(movie);
        pane.add(search_movie);
        add(pane);
    }
  
    
    public void epilogiKinimatografou(){
        JLabel search_cinema = new JLabel("Search cinema");
        JPanel pane = new JPanel();
        pane.add(cinema);
        pane.add(search_cinema);
        add(pane);
    }
    
    
    public void eggrafiKinimatografou(){
        JPanel pane= new JPanel();
        JLabel city_label = new JLabel("City: ");
        JLabel cinema_address_label = new JLabel("Address: ");
        JLabel cinema_name_label= new JLabel("Name: ");
        JLabel cinema_owner_label= new JLabel("Owner name: ");
        JLabel telephone_label = new JLabel("Telephone: ");
        JLabel cinema_email_label = new JLabel("Email: ");
        pane.add(city_label);
        pane.add(city);
        pane.add(cinema_address_label);
        pane.add(cinema_address);
        pane.add(cinema_name_label);
        pane.add(cinema_name);
        pane.add(cinema_owner_label);
        pane.add(owner_name);
        pane.add(telephone_label);
        pane.add(telephone_number);
        pane.add(cinema_email_label);
        pane.add(cinema_email);
        add(pane);
        
    }

    
}
