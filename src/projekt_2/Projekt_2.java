/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projekt_2;
import java.util.Scanner;
/**
 *
 * @author arvid.oscarsson
 */
public class Projekt_2 {
    
    static int[] stol = new int[21];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        for(int i = 0; i < stol.length; i++){
            stol[i] = -1;
        }
        
        Boolean run = true;
        
        do{
            
            System.out.println();
            
            int val;
            do{
                System.out.print("""
                                 Vad vill du göra?
                                 1: Lägga till en pasagerare
                                 2: Skriva ut hur många legiga platser det finns
                                 3: Beräkan vinsten av antalet sålda biljetter
                                 4: Avsluta programet
                                 val: """);
                
                try{
                    val = scan.nextInt();
                }catch(Exception e){
                    System.out.println("Ogiltigt svar\n");
                    continue;
                }
                
                System.out.println();
                
                if(val >=1 && val <= 4){
                    break;
                }
                
                System.out.println("Ogiltigt svar\n");
                
            }while(true);
            
            switch(val){
                case 1 -> läggtill();
                case 2 -> kalk_ledig();
                case 3 -> kalk_vinst();
                case 4 -> run=false;
            }
            
        }while(run);
        
    }
    
    
    static void läggtill(){
        Scanner scan = new Scanner(System.in);
        
        int val;
        
        for(int i = 0; i < stol.length; i++){
            String status;
            
            if(stol[i] == -1){
                status = "Ledig";
            }else{
                status = "Upptagen";
            }
            
            System.out.println("Stol " + (i+1) + ": " + status);
            
        }
        
        do{
            System.out.print("Vart vill du sitta? \nstol:");
            
            try{
                val = scan.nextInt();
            }catch(Exception e){
                System.out.println("Ogiltigt svar");
                continue;
            }
            
            if(val < 1 || val > 21){
                System.out.println("Ogiltigt svar\nMåste vara ett svar mellan 1-21");
                continue;
            }
            
            if(stol[val-1] != -1){
                System.out.println("Stolen är upptagen");
                continue;
            }
            
            break;
            
        }while(true);
        
        int svar;
        
        do{
            System.out.print("Vad är dit personnummer: ");
            
            try{
                svar = scan.nextInt();
            }catch(Exception e){
                System.out.println("Ogiltigt svar\nMåste varen en sifra");
                continue;
            }
            
            break;
            
        }while(true);
        
        stol[val-1] = svar;
        
    }
    
    static void kalk_ledig(){
        int ledig = 0;
        
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == -1){
                ledig++;
            }
        }
        
        System.out.println("Det fins " + ledig + " lediga platser kvar");
        
    }
    
    static void kalk_vinst(){
        int sålda = 0;
        double vinst = 0;
        
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == -1){
                vinst += 299.9;
                sålda++;
            }
        }
        
        System.out.println(sålda + " biljetter har sålts\nVinsten är: " + vinst + " kr");
        
    }
    
}
