
import java.util.*;

public class Seat 
{
    
    //private int[][] row_col = new int[20][20];
    private int column;
    private boolean taken;
    int row;
    int col;
    int seat_count = 0;
    
    public Seat(int row, int col)
    {
        this.row = row;
        this.col = col;
        seat_count++;
       
    }

    
    public int getrow(){
        return this.row;
    }
    public int getcol(){
        return this.col;
    }
}