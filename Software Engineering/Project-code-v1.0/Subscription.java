import java.util.*;

public class Subscription
{
    
    private ArrayList<Date> date_of_subscription_ending;
    private ArrayList<Float> amount;
    private ArrayList<Registered_Cinema> registered_cinemas;
    
    public Subscription()
    {
       
    }

    public void displaySubscriptionInfo(int pos){
        System.out.println("H sindromi gia ton kinimatografo: " + registered_cinemas.get(pos) + 
        "isxuei mexri: " + date_of_subscription_ending.get(pos) +
        " kai antistoixei se minaio poso: " + amount.get(pos));
    }
    
    public void displayPaymentPage(){}
    
    public void displayErrorMessage(){
        System.out.println("H plirwmi apetixe dokimaste ksana");
    }
    
    public void displaySuccessMessage(){
        System.out.println("H plirwmi oloklirwthike me epitixia");
    }
    
}
