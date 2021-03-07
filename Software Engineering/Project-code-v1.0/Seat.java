import java.util.*;

public class Seat 
{
    
    private int row;
    private int column;
    private boolean taken;
    

    
    public Seat()
    {
        
       
    }

    public void ChooseSeat(int row,int column){
        if(!this.taken){
        this.row=row;
        this.column=column;
        this.taken=true;
    }
    else 
    System.out.println("H thesi einai piasmeni");
    }
  
}

