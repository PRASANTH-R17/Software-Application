import java.util.ArrayList;

class Lifts{
    
    String liftID;
    int currentFloor=0;
    
    int minRes;
    int maxRes;
    
    ArrayList<Integer> selectedFloor =new ArrayList<Integer>();
    
    public void suitable(){
        
        for(int floor=0 ; floor < 10+1 ; floor++)
            if( ( minRes <=floor && floor <= maxRes ) || floor==0 )
                selectedFloor.add(floor);
                
        } 
    
    public int travelCount(int pickup,int drop){
        
        int min= Math.min(pickup,drop);
        int max=Math.max(pickup,drop);
        
        int count=0;
        for(int i=min ; i < max+1 ; i++)
        {
            
            if( selectedFloor.contains(i) )
            count = count+1;
        }
    
    return Math.abs(count-1);
    } 
    
    
    public void activateLift(int pickup,int drop){
        
        System.out.println("---------------------------------------------");
        System.out.println("Lift ID : "+liftID);
        System.out.println("Direction :"+pickup+" ----> "+drop);
        System.out.println("---------------------------------------------");
        
        currentFloor=drop;
        
    }
    
    
    
}
