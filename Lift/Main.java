/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main{
    
    public static ArrayList<Lifts> findSameDistance(ArrayList<Lifts> lifts, int pickup){
        
        ArrayList<Lifts> selectedLifts= new ArrayList<Lifts>();
        
        int firstEle = Math.abs(lifts.get(0).currentFloor - pickup);
        for(Lifts lift : lifts)
            if( firstEle == Math.abs(lift.currentFloor - pickup))
                selectedLifts.add(lift);
                
        return  selectedLifts ;
    }
//----------------------------------------------------------------------------------------------------

    public static void printLifts(ArrayList<Lifts> lifts)
    {
        for(Lifts lift : lifts)
                System.out.println(lift.liftID+" "+lift.minRes+"-->"+lift.maxRes+" "+lift.selectedFloor);
    }

//----------------------------------------------------------------------------------------------------
       
    
    
    public static ArrayList<Lifts> sortLift( ArrayList<Lifts> selectedLift , int pickup){
        
        int min = Math.abs( selectedLift.get(0).currentFloor - pickup );
        
        for(int i=0 ; i<selectedLift.size()-1 ; i++)
            for(int j=i+1 ; j<selectedLift.size() ; j++)
            {
                int firstCoverDis = Math.abs( selectedLift.get(i).currentFloor - pickup );
                int secCoverDis = Math.abs( selectedLift.get(j).currentFloor - pickup );
                
                if( firstCoverDis > secCoverDis )
                    Collections.swap(selectedLift,i,j);
                
            }
                    
        return  selectedLift      ; 

//----------------------------------------------------------------------------------------------------

    }
    public static ArrayList<Lifts> findNearLift(int pickup,int drop,ArrayList<Lifts> lifts){
        
        ArrayList<Lifts> selectedLift = new ArrayList<Lifts>();
        
        for(Lifts lift : lifts)
            if ( (lift.selectedFloor).contains(pickup) && (lift.selectedFloor).contains(drop) )
                selectedLift.add(lift);
             
        selectedLift = sortLift(selectedLift , pickup);
        
        return selectedLift;
    }
    
//----------------------------------------------------------------------------------------------------

    public static ArrayList<Lifts> createLifts()// create Lifts and Assign Default Values
    {
        int totalLifts=5;
        ArrayList<Lifts> lifts=new ArrayList<Lifts>();
        
        // Create Lifts (1 to 5) and Assign name for the lifts
        for(int i=0 ; i<totalLifts ; i++)
        {

            lifts.add(new Lifts());
            lifts.get(i).liftID="L"+(i+1);
        }
        
        // assign MinRes and MaxRes for Each Lifts
        lifts.get(0).minRes=lifts.get(1).minRes=0;
        lifts.get(0).maxRes=lifts.get(1).maxRes=5;
        lifts.get(2).minRes=lifts.get(3).minRes=6;
        lifts.get(2).maxRes=lifts.get(3).maxRes=10;
        lifts.get(4).minRes=0;
        lifts.get(4).maxRes=10;
        
        // create Selected floor for each lift
        for(Lifts lift:lifts)
        {
            lift.suitable();
            
        }

        
    return lifts;  
    }
//----------------------------------------------------------------------------------------------------

    public static ArrayList<Lifts> findLiftByCount(int pickup, int drop , ArrayList<Lifts> lifts){
        
        ArrayList<Lifts> selectedLift = new ArrayList<Lifts>();
        
        for(int i=0 ; i<lifts.size()-1 ; i++)
            for(int j=i+1 ; j<lifts.size() ; j++)
                if( lifts.get(i).travelCount(pickup,drop) >  lifts.get(j).travelCount(pickup,drop))
                    Collections.swap( lifts , i , j);
                    
        int minCount =  lifts.get(0).travelCount(pickup,drop);  
        
        for(Lifts lift : lifts)
            if(lift.travelCount(pickup,drop)  == minCount)
                selectedLift.add(lift);
                
        return selectedLift;
    }

//----------------------------------------------------------------------------------------------------

    public static Lifts sameDirection(int pickup, int drop , ArrayList<Lifts> lifts){
        
        for(Lifts lift : lifts)
            if( (lift.currentFloor < pickup && lift.currentFloor < drop) || (lift.currentFloor > pickup && lift.currentFloor > drop) )
            {
                System.out.println("Find same Direction");
                return lift;
                
            }
        return lifts.get(0);
    }
    
//----------------------------------------------------------------------------------------------------

    public static void currentStatus(ArrayList<Lifts> lifts){
        String liftID="Lift : ";
        String Floor="Floor : ";
        
         for(Lifts lift : lifts)
         {
            liftID = liftID+"      "+lift.liftID;
            Floor = Floor+"      "+lift.currentFloor;
             
         }
         System.out.println("---------------------------------------------------------");
         System.out.println(liftID);
         System.out.println(Floor);
         System.out.println("---------------------------------------------------------");
    }
//----------------------------------------------------------------------------------------------------
	public static void main(String[] args) 
	{
	    Scanner sc=new Scanner(System.in);
	    
	    ArrayList<Lifts> lifts = createLifts();
	    ArrayList<Lifts> cloneLifts=lifts;
	    
	    while(true){
	        System.out.println("Select Option : \n1--> Use Lift \n2 --> Display Lift Position");
	        int option = sc.nextInt();
	        switch(option){
	        case 1:
    	        System.out.println("Enter Pickup Floor");
    	        int pickup=sc.nextInt();
    	        
    	        System.out.println("Enter Drop Floor");
    	        int drop=sc.nextInt();
    	        
    	        lifts=findNearLift(pickup,drop,lifts); // Find Nearest Lift
    	        //System.out.println("-----> Find Nearest Lift");
    	        //printLifts(lifts);
    	        
    	        lifts = findSameDistance( lifts, pickup); // find Same Distance Lift
    	        //System.out.println("-----> find Same Distance Lift");
    	        //printLifts(lifts);
    	        
    	        lifts = findLiftByCount(pickup,drop,lifts); // find Lift by Less count
    	        //System.out.println("-----> find Lift by Less count");
    	        //printLifts(lifts);
    	        
    	        sameDirection(pickup,drop,lifts).activateLift(pickup,drop);
    	        break;
    	   case 2:
	            currentStatus(cloneLifts);
	            break;
	        } //switch
	    }
	    
	    
	    
    }// main Method
    
    
    

} //Main Class
