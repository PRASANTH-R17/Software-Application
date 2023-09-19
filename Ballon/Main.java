/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.Scanner;
public class Main
{

//------------------------------------------------------------------------------------------------

    public static char[][] removeRow(char[][] l){
        for(int i=0 ; i<l.length;i++)
	{
	    int rowCount=0;
	    for(int j=0 ; j<l.length;j++)
	        if( l[i][0] == l[i][j] && l[i][j] != '-')
	            rowCount++;
	            
	   if(rowCount == l.length)
	   {
	        System.out.println("---> Remove Same Row");
	        for(int col=0;col<l[0].length;col++)
	            l[i][col] = '-';
	       
	   }
	       
	  
	 } // for loop
	 return l;
    }
    
//------------------------------------------------------------------------------------------------

    public static char[][] removeCol(char[][] l){
        for(int i=0 ; i<l.length;i++)
	{
	    int rowCount=0;
	    for(int j=0 ; j<l.length;j++)
	        if( l[0][i] == l[j][i] && l[j][i] != '-')
	            rowCount++;
	            
	   if(rowCount == l.length)
	   {
	        System.out.println("---> Remove Same Col");
	        for(int row=0;row<l[0].length;row++)
	            l[row][i] = '-';
	   }
	       
	  
	 } // for loop
	 return l;
    
    }
//------------------------------------------------------------------------------------------------

    public static boolean findEmptyCell(int emptyRow, char[][] l)
    {
        
		for(int i=l.length-1 ; emptyRow < i ; i--)
		    for(int j=0;j < l[0].length ; j++)
		        if(l[i][j] == '-')
		        {
		            System.out.println("---"+i+" "+j);
		            return true;
		            
		        }
        return false;
    }
    
//------------------------------------------------------------------------------------------------

    public static char[][] fillEmptyCell(int emptyRow,char[][] l , char color){
        
        
        for(int i=l.length-1 ; emptyRow < i ; i--)
		    for(int j=0;j < l[0].length ; j++)
		        if(l[i][j] == '-')
		        {
		             System.out.println("---"+i+" "+j);
		             l[i][j] = color;
		             return l;
		            
		        }
	    return l;
        
    }
//------------------------------------------------------------------------------------------------
    
    public static char[][] addballons(int col,char color,char[][] l){
        
        char[][] l1= l;
        int emptyRow = -1;
        for(int row=0 ; row< l.length ; row++)
            if( l[row][col]== '-' ) 
            {
                //System.out.println("Row is "+row);
                emptyRow=row;
                
            }

        
        System.out.println("Empty Row :"+emptyRow);
        System.out.println(emptyRow-1);
        if( emptyRow != l.length-1 && findEmptyCell(emptyRow,l) )
        {
            l=fillEmptyCell( emptyRow  ,l , color);
            //System.out.println("check01");
            
        }
        else
        {
            l[emptyRow][col]=color;
            //System.out.println("check02");
            
        }
        
        return l;
        
    };
    
//------------------------------------------------------------------------------------------------
    
    public static char[][] fillEmptyValues(char l[][]  )
    {
        for(int i=0;i<l.length ; i++)
            for(int j=0 ; j < l[0].length ; j++)
                l[i][j]='-';
        return l;
    }
//------------------------------------------------------------------------------------------------
    // Print Array
    
    public static void printArray(char[][] l)
    {
        for(int i=0;i<l.length ; i++)
        {
            for(int j=0 ; j < l[0].length ; j++)
                System.out.print(l[i][j]+" ");
            System.out.println();
            
        } 
    } //printArray method
    
//------------------------------------------------------------------------------------------------

    public static boolean checkBucketFull(char[][] l){
        
        int count=0;
        
        for(int i=0 ; i < l.length ; i++)
        {
            int eachCol=0;
		    for(int j=0; j < l[0].length ; j++)
		        if( l[j][i] != '-' )
		        {
		            count++ ;
		            eachCol++ ;
		            
		        }
	    
    	    if(count == l.length * l[0].length || eachCol == l.length)
    	    {
    	        System.out.println("-----------------------------------------------------------");
    	        System.out.println("Bucket is Full so Game is stop");
    	        printArray(l);
    	        System.out.println("-----------------------------------------------------------");
    	        
    	        return true; // Bucket is checkRowFull
    	    }
            
        }

	   return false; //Bucket is not full
		 
    }

	public static void main(String[] args) {
	    
	    Scanner sc = new Scanner(System.in);
	    
		char[][] l=new char[3][3];
		l = fillEmptyValues(l);
		
		boolean status = true;
		
		while(status){
		    System.out.println("Enter Column Number : ");
		    int col=sc.nextInt();
		    
		    System.out.println("Enter Ballon Color");
		    char color = sc.next().charAt(0);
		    
		    l=addballons(col,color,l); //add
		    printArray(l);
		    l=removeCol(l);
		    l=removeRow(l);
		    if(checkBucketFull(l)) break; // Check Single Column and Full coloumn is Full
		    
		    System.out.println("Do you want to Continue( Y/N ) :");
		    char option=sc.next().charAt(0);
		    if(option == 'N') break;
		    
		    
		    
		    
		}
		
		
		
	} // main method
} //main class
