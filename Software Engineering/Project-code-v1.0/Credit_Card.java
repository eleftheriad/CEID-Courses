import java.lang.*;
import java.util.*;


public class Credit_Card implements Payment
{
    
    private float balance;
    private int card_number;

   
    public Credit_Card()
    {
        
        this.card_number= (int)(Math.random()*1000000000); //prosomoiwsi tou arithmou tis kartas
        
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
        if (this.balance>=1.1*price)  //theoroume oti me tin xrisi pistwtikis iparxei 10% epivarinsi sti xrewsi
         return true;
        else 
         return false;
    }
    
    
    public boolean dataChecking(int number) {
        if(this.getCardNumber()==number)
         return true;
        else
         return false;
    
    }
    
    public int getCardNumber(){
        return card_number;
    }
}
