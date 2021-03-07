import java.util.*;

public class Prepaid_Card implements Payment
{
   
    private float balance;
    private int card_number;

    
    public Prepaid_Card()
    {
        this.card_number=(int)(Math.random()*1000000000); //tixaios arithmos kartas
       
    }

    
    public void amountSubstraction(float price){
        if (this.checkCardBalance(price)==true){
            this.balance=-price;
        }
        else{
         System.out.println("To ipoloipo tis kartas den eparkei gia na ginei i sinalagi");
        }
    
    }
    
    public boolean checkCardBalance(float price){
        if (this.balance>=price)
         return true;
        else 
         return false;
    
    }
    
    
    public boolean dataChecking(int number)
    {
        if(this.getCardNumber()==number)
         return true;
        else
         return false;
    }
    
    public int getCardNumber(){
        return card_number;
    }
}
