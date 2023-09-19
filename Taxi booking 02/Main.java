/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



public class Main
{
    private static Scanner sc= new Scanner(System.in);
    private static int BookCount=1;
    private static HashMap<Object,ArrayList<Object> >customerDetails = new HashMap<>();
    
    private static HashMap<Integer,Taxi > findTaxi = new HashMap<>();
    

    public static ArrayList<Taxi> createTaxi()
    {
        ArrayList<Taxi> taxis = new ArrayList<Taxi>();
        int totalNoTaxi = 4;
        
        for(int i=1 ; i <  totalNoTaxi+1 ; i++){
            taxis.add(new Taxi());
            taxis.get(i-1).taxiId = i;
        }
        return taxis;
    }

    public static boolean checkAvailability(ArrayList<Taxi> taxis){
        for(Taxi t : taxis)
            if(t.status == true)
                return true;
        
        return false;
    }
    
    public static Taxi findNearestTaxi(char pickup,ArrayList<Taxi> taxis){
        
        Taxi t=taxis.get(0);
        for(Taxi taxi : taxis)
            if(taxi.status == true)
                t=taxi;
        
        for(int i=0 ; i < taxis.size() ; i++)
                if(Math.abs(t.currentPoint - pickup) > Math.abs(taxis.get(i).currentPoint - pickup) && taxis.get(i).status == true )
                    t=taxis.get(i);
        
        return t;
    }

////

    public static int maintainance(String name,char pickup,char drop,ArrayList<Taxi> taxis){
        int bookNo = BookCount;
        BookCount += 1;
        Taxi t=findNearestTaxi(pickup,taxis);
        
        System.out.printf("\n%d Taxi is Booked for You - Booking Id : %d\n",t.taxiId,bookNo);
        
        // Update value
        
        ArrayList<Object> detail = new ArrayList<Object>();
        
        
        detail.add(name);
        detail.add(bookNo);
        detail.add(pickup);
        detail.add(drop);
        detail.add(Math.abs(t.currentPoint - pickup) * 10);
        detail.add("false");
        findTaxi.put(bookNo,t);
        
        customerDetails.put(bookNo,detail);
        
        // update Datails in Each Taxi
        t.updateValue(bookNo,name,pickup,drop,Math.abs(t.currentPoint - pickup) * 10);
        
        
        if(findTaxi.size() == 1)
            return 0;
            
        //update previous Taxi

        findTaxi.get(bookNo - 1).status = true;
        customerDetails.get(bookNo - 1).set(5,"True");
        
        
        return 0;  
        
    }
    
	public static void main(String[] args) {
	    ArrayList<Taxi> taxis = createTaxi();
	    
		while(true){
		    System.out.println("\n1 - Customer Login");
		    System.out.println("2 - Admin Login");
		    System.out.print("Enter your Option (1/2) : ");
		    int option1=sc.nextInt();
		    switch(option1)
		    {
		        
		        case 1:
		            System.out.println("\n1 - Booking Taxi");
		            System.out.println("2 - Get Each Taxi Detail");
		            System.out.print("Enter your Option (1/2) : ");
		            int option2=sc.nextInt();
		            switch(option2)
		            {
		                case 1:
		                    System.out.print("\nEnter your Name : ");
		                    String name = sc.next();
		                    
		                    System.out.print("Enter Pickup Location : ");
		                    char pickup =  sc.next().toUpperCase().charAt(0);
		                    
		                    System.out.print("Enter Drop Location : ");
		                    char drop = sc.next().toUpperCase().charAt(0);
		                        
		                    
		                    if( checkAvailability(taxis) == false ){
		                        System.out.println("\nTaxi Not Available, Pls try again later\n");
		                        break;}
		                        
		                    maintainance(name,pickup,drop,taxis);
		                    break;
		                case 2 :
		                    System.out.println("---------------------------------------------------------------------");
		                    System.out.println("Taxi No         Book No         Name         Pick UP         Drop         Travel Kms");
		                    System.out.println("---------------------------------------------------------------------");
		                    
		                    for(Taxi t : taxis)
		                        t.taxiHistory();
		                    System.out.println("---------------------------------------------------------------------");
		                    break;
		            }break;
		                    
		        case 2:
		            System.out.printf("\n\n1 - Travel History \n2 - Taxi Availability \nEnter Option (1/2) : ");
		            int option3=sc.nextInt();
		            
		            switch(option3)
		            {
		                
		                case 1:
		                    for(ArrayList<Object> t : customerDetails.values())
		                        System.out.println(t);
		                        break;
		                case 2:
		                    System.out.println();
		                    System.out.println("---------------------------------");
		                    System.out.println("Taxi No                 Busy");
		                    for(Taxi t : taxis)
		                        t.taxiStatus();
		                    System.out.println("-------------------------------");
		                    System.out.println();
		                    break;
		                    
		            } //switch 3
		                    
		                
		                
		      
		        
		   } //switch 01
		    
		} // while
	}
}
