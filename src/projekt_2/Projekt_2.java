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
                                 2: Lägga till en pasagerare på en fönsterplats
                                 3: Skriva ut hur många legiga platser det finns
                                 4: Beräkan vinsten av antalet sålda biljetter
                                 5: Hitta bokning
                                 6: Ta bort en bokning
                                 7: Avsluta programet
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
                if(val >=1 && val <= 7){
                    break;
                }
                
                //skriver ut att användaren har skrivit in ett ogiltigt alternativ
                System.out.println("Ogiltigt svar\n");
                
            }while(true);
            
            //switch på val så att programet kör rätt alternativ användaren valde
            switch(val){
                case 1 -> läggtill();
                case 2 -> läggtill_f();
                case 3 -> kalk_ledig();
                case 4 -> kalk_vinst();
                case 5 -> hitta_bokning();
                case 6 -> tabort();
                case 7 -> run=false;
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
        
        //sätter in användaren personnumert i stolen som användaren vill sitta i
        stol[val-1] = ange_Pnum();
        
    }
    
    static void läggtill_f(){
        Scanner scan = new Scanner(System.in);
        
        //sätter upp variaben där stolen användaren vill sita sparas
        int val = 0;
        
        System.out.println("lediga fönsterplatser");
        
        //loopar igenom för var fjärde stol med börgan på den första
        for(int i = 0; i < stol.length; i += 4){
            //kollar om stol i är upptagen
            if(stol[i] == -1){
                //skriver ut att stolen är ledig
                System.out.println("stol " + (i+1));
            }
            //kollar om stol i+3 är upptagen och om det fins flera stolar om fem stolar till
            //gör det härar för att det är tre stolar från den vänstra fänsterstolen till den högra förutom i sista raden där det är fyra stolar
            if(i+5 < stol.length && stol[i+3] == -1){
                //skriver ut att stol i+3 är ledig
                System.out.println("stol " + (i+4));
            }
        }
        
        //gör så attprugramet han håppa hit när den vill
        do{
            //gör lite rum och skriver ut en fråga
            System.out.print("\nvart vill du sitta?\nstol: ");
            
            //gör så att användaren inte kan kracha prugramet med sit svar
            try{
                //låter användraen skriva in sit svar
                val = scan.nextInt();
            }catch(Exception e){
                //skriver ut att det är ett ogiltigt svar om det är
                System.out.println("Ogiltigt svar\nMånse vara en sifra");
                //hoppar upp för att starta om loopen
                continue;
            }
            
            //kollar om användarens valda stol är inte en fönsterplats
            if(!koll_f(val-1)){
                System.out.println("Ogiltigt svar\nStolen är inte en fönsterplats");
                continue;
            }
            
            //kollar om användarens valda stol är upptagen
            if(stol[val-1] != -1){
                System.out.println("Ogiltigt svar\nStolen är upptagen");
                continue;
            }
            
            break;
            
        }while(true);
        
        //skriver in användaren personnumer på stolen de valde
        stol[val-1] = ange_Pnum();
        
    }
    
    static void kalk_ledig(){
        int ledig = 0;
        
        //kollar hur många platsr som har väret -1
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == -1){
                ledig++;
            }
        }
        
        //skriver ut hur många lediga platser det fins kvar
        System.out.println("Det fins " + ledig + " lediga platser kvar");
        
    }
    
    static void kalk_vinst(){
        int sålda = 0; //antalet solda biljeter
        int barn = 0;  //antalet banbiljeter solda
        int vuxna = 0; //antalet vuxna biljeter solda
        int panch = 0; //antalet pansionärsbiljetter solda
        double vinst = 0; //totala vinsten av de solda biljeterna
        
        //lägger till vinst och ökar hur många stolar som är sålda för vargie upptegen stol
        for(int i = 0; i < stol.length; i++){
            if(stol[i] != -1){
                sålda++;
                if(barn(stol[i])){ //kollar om personumret i stol i är till ett barn
                    barn++;
                    vinst += 149.9;
                }else if(pens(stol[i])){ //kollar om personnumret i stol i är en panchionär
                    panch++;
                    vinst += 199.9;
                }else{ //om personummret i stol i inte är panchionär eller bars så är de en vuxen
                    vuxna++;
                    vinst += 299.9;
                }
            }
        }
        
        //skriver ut vinsten samt hur många av verige åldersgrupp som åker med
        System.out.println(sålda + " biljetter har sålts\nVinsten är: " + vinst + " kr");
        System.out.println(vuxna + " vuxna\n" + barn + " barn\n" + panch + " pensionärer");
    }
    
    static void hitta_bokning(){
        
        //tar in personnumret som skas hittas
        long pNum = ange_Pnum();
        
        //kollar igenom vargige plats om det har pärsonnumret som söks efter
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == pNum){
                System.out.println("Du sitter på plats "+(i+1));
                return;
            }
        }
        //skrivs ut om personnummret inte hittas
        System.out.println("Det fins inget plats som är bokad under det här personnumret");
        
    }
    
    static void tabort(){
        //tar in personnummret som skas ta borts
        long pNum = ange_Pnum();
        
        //kollar igenom alla stolar för perssonnumret som skars ta borts
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == pNum){
                stol[i] = -1; //markerar att stolen är ledig
                System.out.println("Din bokning har tagits bort");
                return;
            }
        }
        //skrivs ut om personnumret inte hitas
        System.out.println("Det fins ingen bokning med det här personnumret");
        
    }
    
    
    static boolean koll_f(int koll){
        //kollar om väret som matas in är en fönsterplats
        for(int i = 0; i < stol.length; i += 4){
            if(koll == i){ //kolar om värdet är lika med i
                return true;
            }
            if(i+5 < stol.length && koll == i+3){ //kollar om värdet är lika med i+3 och om i+5 är en existerande stol
                return true;
            }
        }
        return false;
    }
    
    static long ange_Pnum(){
        Scanner scan = new Scanner(System.in);
        
        long pNum = 0;
        do{ //programet kan håppa hit när den vill
            System.out.print("Vad är dit personnummer: ");
            
            try{ //ser til så att användarer inke kan kracha prugramet
                pNum = scan.nextLong(); //låter användarens skriva in sitt personnumer
            }catch(Exception e){
                System.out.println("Ogiltigt svar\nMåste varen en sifra");
                continue;
            }
            
            //kollar om talet användaren skrev in är lång nog för att vare ett personnummer
            if(pNum < 100000000 || pNum > 9999999999l){
                System.out.println("Ogiltigt svar\ninte ett giltigt personnummer");
                continue;
            }
            
            //kollar så att det är ett giltigt personnummer
            if(!koll_persNum(pNum)){
                System.out.println("Ogiltigt svar\ninte ett giltigt personnummer");
                continue;
            }
            
            return pNum;
            
        }while(true);
    }
    
    static boolean koll_persNum(long num){
        //tog hjälp av den här wikipedia artiken för att se hur personnumrets kontrollsifra räknas ut
        //https://sv.wikipedia.org/wiki/Personnummer_i_Sverige
        int kontrol = 0;
        //loopar igenom för varige sifra förutom kontrollsifran
        for(int i = 2; i <= 10; i++){
            //hämpter ut sifran på plats i 
            int num1 = get_num(i, 1, num);
            num1 *= ((i+1)%2+1);//om platsen sifran sitter på är jäm så multiplisera med 2
            num1 = (num1 % 10) + (num1/10);//om talet är större en 10 so addera ihop siffersumman
            kontrol += num1;//kägger på det programet har räknat ut på kontroll
        }
        //tar ental sifran på kontroll och suptreherar det från 10 och kollar om det är loka med kontrollsifrarn i personnumret
        return (10-(kontrol%10) == get_num(1, 1, num));
    }
    
    static int get_num(int pos, int leng, long num){
        num = num/ten_pow_n(pos-1);//tar bort allt efter sifran pugramet vill ha
        num = num%ten_pow_n(leng);//tar bort all före sista fifran
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
    
    static boolean barn(long pNum){
        int ålder = get_num(9, 2, pNum); //hämtar ålder sifrorna från personnumret
        if(ålder > 5 && ålder < 23){ //kollar om ålder sifrorna är till nån föd för mindre än 18 år sedan. extluderar alla som har 23 och över som deras år (kan inte ha föds i framtiden)
            return true;
        }else if(ålder == 5){ //om persomumret är till nån som födes föt 18 år sedan
            int mån = get_num(7, 2, pNum); //hämtar månad sifrerna från personnumret
            if(mån > 4){ //kollar om månaden är efter April
                return true;
            }else if(mån == 4 && get_num(5, 2, pNum) > 5){ //kollar om personnumret är till nån som födes i April och hämtar dag sifrorna från personnumret och kollar om de är födda efter den femter April 
                return true;
            }
        }
        return false;
    }
    
    static boolean pens(long pNum){
        int ålder = get_num(9, 2, pNum); //hämtar ålder sifrorna från personnumret
        if(ålder < 54 && ålder >= 23){ //kollar om ålder sifrorna är till nån föd för mer än 69 år sedan. extluderar alla som har under 23 som deras år
            return true;
        }else if(ålder == 54){ //om persomumret är till nån som födes föt 69 år sedan 
            int mån = get_num(7, 2, pNum); //hämtar månad sifrerna från personnumret
            if(mån > 4){ //kollar om månaden är efter April
                return true;
            }else if(mån == 4 && get_num(5, 2, pNum) >= 5){ //kollar om personnumret är till nån som födes i April och hämtar dag sifrorna från personnumret och kollar om de är födda efter den femter April
                    return true;
            }
        }
        return false;
    }
    
}
