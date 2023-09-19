import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class Taxi{
    
    int taxiId;
    char currentPoint = 'A';
    boolean status = true;
    int TotalTravelKm=0;
    ArrayList<String> customerDetails = new ArrayList<String>();
    
    public void updateValue(int bookNo,String name,char pickup,char drop,int travelKm){
        
        status = false;
        currentPoint = drop;
        TotalTravelKm += travelKm;
        customerDetails.add(bookNo+"     "+name+"     "+pickup+"     "+drop+"     "+travelKm);
        
    }
    
    public void taxiStatus(){
        if(status == true)
            System.out.println("Taxi "+taxiId+"       Available");
        else
            System.out.println("Taxi "+taxiId+"       Busy");
    }
    
    public void taxiHistory(){
        for(String s : customerDetails)
            System.out.println(taxiId+"     "+s);
    }
}