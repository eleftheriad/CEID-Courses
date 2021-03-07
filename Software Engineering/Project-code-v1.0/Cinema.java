import java.util.*;

public class Cinema
{
    private String area;
    private Screening_Hall[] screening_halls;

    
    public Cinema(int aithouses){
        screening_halls = new Screening_Hall[aithouses];
        
    }

    public void notifyUser(){}
    
    public String getArea(){
        return area;
    }
    
}
