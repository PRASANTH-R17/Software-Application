/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    
    public static ArrayList<Shops> createShops(){
        
        ArrayList<Shops> shops=new ArrayList<Shops>();
        
        int shopCount=2;
        for(int i=0 ; i<shopCount ; i++)
        {
            shops.add( new Shops() );
            shops.get(i).branchID = i+1;
            
        }
        return shops;
            
    }
    
//---------------------------------------------------------------------------------------------------------------------------------

    public static int findProductIndex(String product) {
        //System.out.println("Product Name ----> " + product);
        
		String[] products={"crocin","vicco","doloper","boro"};
		for(int i=0 ; i < products.length ; i++)
		    if( products[i].equals(product) )
		        return i;
	    return -1;
	    
	}
	
	
	public static boolean checkOtherBranch(int productIn,int qty,ArrayList<Shops> shops){
	    
	    for(Shops s : shops )
	        if ( qty <=  s.quantity.get(productIn ) )
	            return true;
	   return false;
	   
	}
	
//---------------------------------------------------------------------------------------------------------------------------------

	public static boolean alterProduct(int productIn,String product,int qty,int branch,ArrayList<Shops> shops){
	    Scanner sc = new Scanner(System.in);
	    
	    if( !( product.equals("crocin") || product.equals("vicco")) )
	    {
	        System.out.println("---> "+product+" is not have alternate");
	        return false;
	        
	    }
	    String[] products={"crocin","vicco","doloper","boro"};
	    
	    int alterProIndex = productIn + 2;
	    String altProductName = products[alterProIndex];
	    
	    if( !(qty <= shops.get(branch).quantity.get(alterProIndex))  )
	    {
	        System.out.println(altProductName +"-- Alter Product is out of stock" );
	        return false;
	        
	    }
	    
	    System.out.println(altProductName + "--> Alter Product ( "+altProductName +") is available");
	    System.out.print("--> Do you want Continue with Alternate Proudct ( Y/N )");
	    
	    int option = sc.next().toLowerCase().charAt(0);
	    
	    if( option == 'n')
	        return false;
	   
	    return true;
	    
	}
//---------------------------------------------------------------------------------------------------------------------------------

    public static String checkAvailable( int cusID, int branch, String product, int qty, ArrayList<Shops> shops ){
        String[] products={"crocin","vicco","doloper","boro"};
        
        //System.out.println("check 01");
        Scanner sc = new Scanner(System.in);
        
        int productIn=findProductIndex(product);
        
        if( qty <= shops.get(branch).quantity.get(productIn)  ){
            //System.out.println("check02");
            return (shops.get(branch).placeOrder(cusID,product,qty,productIn));
            
        }
            
            
        
        //check product in other branches
            
        else if(checkOtherBranch(productIn,qty,shops))
        {
            
            
            int altBranchIn=-1;
            for(Shops s : shops )
	            if ( qty <=  s.quantity.get(productIn) )
	            {
	                altBranchIn = s.branchID -1 ;
	                break;
	                
	            }
	        System.out.println();
	        System.out.println("-- Quantity is not available in Branch "+(branch + 1)+" --");
	        System.out.println("--"+ product+" available in Branch "+ (altBranchIn + 1));
	        
	        System.out.print("--> Do you want continue( Y/N ) : ");
	        char altOption=sc.next().toLowerCase().charAt(0);
	        System.out.println();
	        
	        
	        if( altOption == 'n')
	            return " ";
	            
	       branch = altBranchIn;
	       return (shops.get(branch).placeOrder(cusID,product,qty,productIn));
	       
	       
            
        }
        
        else if (alterProduct(productIn, product, qty, branch, shops)){
            
            productIn = productIn+2;
            product = products[productIn];
            return (shops.get(branch).placeOrder(cusID,product,qty,productIn));
            
            
        }
        
        else{
            System.out.println("-- Product is not available --");
            return " ";
        }
        
        
 
	   
    }
//---------------------------------------------------------------------------------------------------------------------------------
    
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    
	    ArrayList<Shops> shops = createShops() ;
	    
	    while(true){
		System.out.print("Select Option ( 01-Buy Medicine, 02-Stock Availbility, 03-Branch Details, 04-Customer Details ) : ");
		int option=sc.nextInt();
		
		switch(option)
		{
		    case 1:
		         ArrayList<String> cusDetail = new ArrayList<String>();
		        //collect details from n04-Customer
		        System.out.print("Enter Customer ID : ");
		        int cusID = sc.nextInt();
		        
		        System.out.print("Enter Branch : ");
		        int branch = sc.nextInt();
		        
		        System.out.print("Enter Product ( crocin,vicco,doloper,boro ) : ");
		        String product = sc.next().toLowerCase();
		        
		        System.out.print("Enter Quantity : ");
		        int qty = sc.nextInt();
		        
		        //System.out.println(cusID+" ------- "+branch+" ------- "+product+" ------- "+qty);
		        String cusMsg = ( checkAvailable( cusID, branch-1, product, qty, shops) );
		        
		        if( !(cusMsg.equals(" ") ) )
		            cusDetail.add(cusMsg);
		        
		        while(true)
		        {
		        
		        System.out.print("--> Do you want continue Purcase ( Y/N ) : ");
		        char cusOption = sc.next().toLowerCase().charAt(0);
		        
		        if(cusOption =='y' ){
		            System.out.print("Enter Branch : ");
    		        branch = sc.nextInt();
    		        
    		        System.out.print("Enter Product ( crocin,vicco,doloper,boro ) : ");
    		        product = sc.next().toLowerCase();
    		        
    		        System.out.print("Enter Quantity : ");
    		        qty = sc.nextInt();
    		        
		        
		            cusMsg = ( checkAvailable( cusID, branch-1, product, qty, shops) );
		        
		            if( !(cusMsg.equals(" ") ) )
		                cusDetail.add(cusMsg); } // if condition
		        
		        else
		            break;
		        } // while loop
		        
		        System.out.println("-------------------------------------------------------------------------------");
		        for(String detail : cusDetail)
    		        System.out.println(detail);
		        System.out.println("-------------------------------------------------------------------------------");
		        
		        break;
		        
		    case 2:
		        
		        for( Shops s: shops)
		            s.branchDetails();
		        break;
		        
		} // switch case
		
	} //while
	    
	    
	    
		
	} //main method
}
