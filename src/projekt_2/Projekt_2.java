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
    //skapar ett globalt fält som användas till att spara om en stol är ledig eller inehåller personnumert av den som sitter där 
    static long[] stol = new long[21];
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //sätter alla stolars värde till -1 som representerar att den är tomm
        for(int i = 0; i < stol.length; i++){
            stol[i] = -1;
        }
        
        //skapar en variabel som kommer användas så att prugramet kan stängas av
        Boolean run = true;
        
        //skapar en do while loop så prugramet kan köras flera gånger 
        do{
            
            //gör så att det är mellanrum lellan texten så att det inte blir en enda lång oavbryten text
            System.out.println();
            
            //variaven som bestener vad prugramet ska göra den här iterationen
            int val;
            
            //en do while loop som gör så att programet kan hoppa hit när den vill
            do{
                //skriver ut alla alternativ du kan göra
                System.out.print("""
                                 Vad vill du göra?
                                 1: Lägga till en pasagerare
                                 2: Skriva ut hur många legiga platser det finns
                                 3: Beräkan vinsten av antalet sålda biljetter
                                 4: Avsluta programet
                                 val: """);
                
                try{
                    //ser till så att programet inte krachar om användaren skriver in nåt annat än en sifra
                    val = scan.nextInt();
                }catch(Exception e){
                    //skriver ut att su skrev in et ogiltigt svar och sen gör så användaren kan skrive in ett nytt alternativ
                    System.out.println("Ogiltigt svar\n");
                    continue;
                }
                
                //skriver ut bara en tom rad. sama anledningen som på rad 33
                System.out.println();
                
                // kollar om användaren har skrivit in ett giltigt alternativ
                //om ja så hopar krogramet ur loppen som börga på rad 39
                if(val >=1 && val <= 4){
                    break;
                }
                
                //skriver ut att användaren har skrivit in ett ogiltigt alternativ
                System.out.println("Ogiltigt svar\n");
                
            }while(true);
            
            //switch på val så att programet kör rätt alternativ användaren valde
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
        
        //variabel som komer användas för att välja vilken stol användaren vill sitta i
        int val;
        
        //loop för att kolla om en stol är ledig eller upptagen och skriva ut det
        for(int i = 0; i < stol.length; i++){
            String status;//statusen av stol i kommer skrivas in här
            
            //kollar om sktol i är ledig eller upptagen sch sen sktiver in satatusen i status
            if(stol[i] == -1){
                status = "Ledig";
            }else{
                status = "Upptagen";
            }
            
            //skriver ut skolsnumret och dens statuss
            System.out.println("Stol " + (i+1) + ": " + status);
            
        }
        
        //en do while loop så att prugramet kan hoppa hit när de behövs
        do{
            System.out.print("Vart vill du sitta? \nstol:");
            
            //sama som i rad 49 till 56
            try{
                val = scan.nextInt();
            }catch(Exception e){
                System.out.println("Ogiltigt svar");
                continue;
            }
            
            //kolar om användaren skrev in ett giltig stol
            if(val < 1 || val > 21){
                System.out.println("Ogiltigt svar\nMåste vara ett svar mellan 1-21");
                continue;
            }
            
            //kollar om stolen användaren valde är upptagen sch skriver ut det om den är
            if(stol[val-1] != -1){
                System.out.println("Stolen är upptagen");
                continue;
            }
            
            //hoppar ur loopen om alla kontroller är klarade
            break;
            
        }while(true);
        
        //variabel som komer användas så att användaren kan skrive in sitt personnummer
        long svar;
        
        //en do while loop så att prugramet kan hoppa hit när de behövs
        do{
            System.out.print("Vad är dit personnummer: ");
            
            //rad 111
            try{
                svar = scan.nextInt();
            }catch(Exception e){
                System.out.println("Ogiltigt svar\nMåste varen en sifra");
                continue;
            }
            
            //kollar om talet användaren skrev in är lång nog för att vare ett personnummer
            if(svar < 100000000 || svar > 9999999999l){
                System.out.println("Ogiltigt svar\ninte ett giltigt personnummer");
                continue;
            }
            
            //kollar så att det är ett giltigt personnummer
            if(!koll_persNum(svar)){
                System.out.println("Ogiltigt svar\ninte ett giltigt personnummer");
                continue;
            }
            
            break;
            
        }while(true);
        
        //sätter in användaren personnumert i stolen som användaren vill sitta i
        stol[val-1] = svar;
        
    }
    
    static void kalk_ledig(){
        int ledig = 0;
        
        //kollar hur många platsr som har väret -1
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
        
        //lägger till vinst och ökar hur mång astolar som är upptagna för vargie upptegen stol
        for(int i = 0; i < stol.length; i++){
            if(stol[i] != -1){
                vinst += 299.9;
                sålda++;
            }
        }
        
        System.out.println(sålda + " biljetter har sålts\nVinsten är: " + vinst + " kr");
        
    }
    
    static boolean koll_persNum(long num){
        //tog hjälp av den här wikipedia artiken för att se hur personnumrets kontrollsifra räknas ut
        //https://sv.wikipedia.org/wiki/Personnummer_i_Sverige
        int kontrol = 0;
        //loopar igenom för varige sifra förutom kontrollsifran
        for(int i = 2; i <= 10; i++){
            //hämpter ut sifran på plats i 
            int num1 = get_num(i, num);
            num1 *= ((i+1)%2+1);//om platsen sifran sitter på är jäm så multiplisera med 2
            num1 = (num1 % 10) + (num1/10);//om talet är större en 10 so addera ihop siffersumman
            kontrol += num1;//kägger på det programet har räknat ut på kontroll
        }
        //tar ental sifran på kontroll och suptreherar det från 10 och kollar om det är loka med kontrollsifrarn i personnumret
        return (10-(kontrol%10) == get_num(1, num));
    }
    
    static int get_num(int pos, long num){
        num = num/ten_pow_n(pos-1);//tar bort allt efter sifran pugramet vill ha
        num = num%10;//tar bort all före sista fifran
        return (int) num;
    }
    
    static long ten_pow_n(int n){
        //multipliserar 10 n gonger
        long svar = 1;
        for(int i = 0; i < n; i++){
            svar *= 10;
        }
        return svar;
    }
    
}
