/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
import java.util.Scanner;
public class Main
{
    
    public static void printTictactoe(char l[][]){
        System.out.println("-------------");
		for(int i=0 ; i<l.length ; i++){
		    System.out.printf("|");
		    for(int j=0 ; j<l[i].length ; j++)
		        System.out.printf(" %c |",l[i][j]);
		    System.out.println();
		    System.out.println("-------------");
		  }
    }
    
    public static boolean validation(char[][] l){
        int xcrossLeft=0;
        int ocrossLeft=0;
            
        int xcrossRight=0;
        int ocrossRight=0;
            
        for(int i=0;i<l.length ; i++)
        {
        
            int xrow=0;
            int xcol=0;
            int orow=0;
            int ocol=0;
            
            if(l[i][i] == 'x')
                xcrossLeft++ ;
            else if(l[i][i] == 'o')
                ocrossLeft++ ;
                
            if( l[l.length-1-i][l.length-1-i] =='x' )
                xcrossRight++ ;
            else if( l[l.length-1-i][l.length-1-i] =='0' )
                ocrossRight++ ;
                
            
        
            for(int j=0 ; j<l[i].length ; j++)
            {
                if (l[i][j] == 'x') xrow++;
                else if(l[i][j]== 'o') orow++;
                
                if (l[j][i] == 'x') xcol++;
                else if(l[j][i]== 'o') ocol++;
            
            }
     
            if( xrow==3 || xcol==3 || xcrossLeft==3 || xcrossRight==3)
            {
                System.out.println("X is win");
                printTictactoe(l);
                return true;
                
            }
                
            else if(orow==3 || ocol==3 || ocrossLeft==3 || xcrossRight==3)
            {
                System.out.println("X is win");
                printTictactoe(l);
                return true;
                
            }
        } //for loop
        return false;
    } //validation method
        
    
    
	public static void main(String[] args) 
	{
	    Scanner sc = new Scanner(System.in);
	    
		char[][] l={ {' ',' ',' '},{' ',' ',' '},{' ',' ',' '} };
	
		
		int row;
		int col;
		String input;
		while(true){
		    
		    System.out.println("Xenin Turn");
		    printTictactoe(l);
		    System.out.println("Xenin enter value : ");
		    
		    row=sc.nextInt();
		    col=sc.nextInt();
		    
		    l[row][col] = 'x';
		 
		    if(validation(l)) break;
		    
		    System.out.println("Ola Turn");
		    printTictactoe(l);
		    System.out.println("Ola enter value : ");
		    
		     row=sc.nextInt();
		    col=sc.nextInt();
		    
		    l[row][col] = 'o';
		    
		    if(validation(l)) break;
		    
		    
		
		    
		    
		}//while loop
		
	}//main method
	
} //Main Class
