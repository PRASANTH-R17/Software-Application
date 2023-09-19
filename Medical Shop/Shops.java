import java.util.ArrayList;

class Shops{
    
    
    int branchID;
    
    ArrayList<String> productName= new ArrayList<String>();
    ArrayList<Integer> quantity= new ArrayList<Integer>();
    ArrayList<Integer> prices= new ArrayList<Integer>();
    
    Shops(){
        productName.add("crocin");
        productName.add("doloper");
        productName.add("vicco");
        productName.add("boro");
        
        // Add quantity
        
        quantity.add(3); // cropin
        quantity.add(3); // doloper
        quantity.add(3); // vicco
        quantity.add(3); // boro
        
        
        // Product Price
        prices.add(10); // cropin
        prices.add(10); // doloper
        prices.add(10); // vicco
        prices.add(10); // boro
        
    } //constructor
    
    public String placeOrder(int cusID,String product, int qty, int productIn){
        
        // Data Update
        
        quantity.set( productIn, quantity.get(productIn) - qty );
        
        System.out.printf("%s ( %d ) is placen in branch %d at Rupees %d \n", product, qty, branchID, prices.get(productIn)*qty);
        return(cusID+"      "+branchID +"      "+ product +"      "+ qty+"      "+prices.get(productIn)*qty );
    }
    
    public void branchDetails(){
        for(int i=0 ; i<4 ; i++)
            System.out.println( branchID+"        "+productName.get(i)+"        "+quantity.get(i) );
        
    }
    
    
    
} // Shop class