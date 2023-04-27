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
    static String[] fnamn = new String[21];
    static String[] enamn = new String[21];
    static char[] kön = new char[21];
    
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
                                 7: Skriv ut alla pasagerare
                                 8: Avsluta programet
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
                if(val >=1 && val <= 8){
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
                case 7 -> skriv_ut();
                case 8 -> run=false;
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
        
        //sätter in användarens information i stolen som användaren vill sitta i
        plaserar(val-1);
        
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
        
        //skriver in användarens information på stolen de valde
        plaserar(val-1);
        
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
        Scanner scan = new Scanner(System.in);
        int val;//är variaben som använs så att användaren kan skriva in sit val
        
        //gör så att programet kan håppa hit när den vill
        do{ 
            //skrivet ur alternativen
            System.out.print("""
                             välj alternativ du vill hita bokning med
                             1: personnummer
                             2: namn
                             val: """);

            try{//går så att användaren inta kan kracha systemet
                val = scan.nextInt();//låter användaren skriva in sit val
            }catch(Exception e){//gårs om ett prublem uppstod
                System.out.println("ogiltigt svar\nmårste vara en sifra");
                continue;//hoppar till börgan an loolen
            }
            
            if(val < 1 || val > 2){//kollar om användarens val är ett giltigt alternativ
                System.out.println("Ogiltigt alternativ");
                continue;//hoppar till börgan an loolen
            }
            
            break;//hoppar ur loopen
            
        }while(true);
        
        switch(val){//kör användaren val
            case 1 -> hitta_bokning_Pnum();
            case 2 -> hitta_bokning_namn();
        }
        
    }
    
    static void tabort(){//kåden för den hära är nästan den samma som för "hitta_bokning" mad bara lite andra ord som skrivs ut och leter till andra funktioner
        Scanner scan = new Scanner(System.in);
        int val;
        do{
            System.out.print("""
                             välj alternativ du vill ta bort bokning med
                             1: personnummer
                             2: namn
                             val: """);

            try{
                val = scan.nextInt();
            }catch(Exception e){
                System.out.println("ogiltigt svar\nmårste vara en sifra");
                continue;
            }
            
            if(val < 1 || val > 2){
                System.out.println("Ogiltigt alternativ");
                continue;
            }
            
            break;
            
        }while(true);
        
        switch(val){
            case 1 -> tabort_Pnum();
            case 2 -> tabort_namn();
        }
        
    }
    
    static void skriv_ut(){
        Scanner scan = new Scanner(System.in);
        int längd = 0;//används till att bestämma längden på fälten där den temporära datan om pasagerare förvaras
        int val;//varisben som använd för att spara användaren val
        
        for(int i = 0; i < stol.length; i++){//kollar igenom alla stolar och räknar hur många som är bokade
            if(stol[i] != -1){
                längd++;
            }
        }
        
        if(längd == 0){//om ingen stol är bokar så avslutas funktionen
            System.out.println("Det fins inga bokningar för tilfället");
            return;//avslutar funktionen
        }
        
        long[] pnum = new long[längd];       //kommer spara personnumter från alla stolar som är bokade
        String[] lfnamn = new String[längd]; //kommer spare förnamnet från alla stolar som är bokade
        String[] lenamn = new String[längd]; //kommer spara efternamnet från alla stolar som är kokade
        int[] lstol = new int[längd];        //kommar spara stolsnumret för alla stolar som är bokade
        int plats = 0; //kommer hålla kol på hur många platser från fälten över som är upptagna
        
        for(int i = 0; i < stol.length; i++){//skriver in datan från stolarna till de lokala fälten
            if(stol[i] != -1){ 
                pnum[plats] = stol[i];
                lfnamn[plats] = fnamn[i];
                lenamn[plats] = enamn[i];
                lstol[plats] = i+1;
                plats++;
            }
        }
        
        do{//låter programmet hoppa hit när den vill
            //skriver ut alternativen användaren kan välja
            System.out.print("""
                               Hur vill du skriva ut pasagerarne
                               1: stolsårdning
                               2: åldersårning
                               val: """);
            
            try{//ser till så att användaren inte kan kracha porgramet
                val = scan.nextInt();//låter användaren skriva in sit val
            }catch(Exception e){//om ett prublem uppstår
            System.out.println("Ogiltigt svar\nmåste vara en sifra");
            continue;//hoppar till börgan av loopen
            }
            
            if(val < 1 || val > 2){//kollar om användarens val är ett giltigt alternativ
                System.out.println("Ogiltigt alternativ");
                continue;//hoppar till börgan av loopen
            }
            
            break;//hoppar ut loopen
            
        }while(true);
        
        if(val == 2){//kollar om användaren valde alternativ två
            sotera(pnum, lfnamn, lenamn, lstol);//soterar de lokale fälten i åldersårdning
        }
        
        for(int i = 0; i < pnum.length; i++){//skriver ut informationen för alla bokade stolar
            System.out.println(lfnamn[i] + " " + lenamn[i] + " " + pnum[i] + " stol: "+ lstol[i]);
        }
        
    }
    
    
    static void plaserar(int plats){
        Scanner scan = new Scanner(System.in);
        
        long pnum;//variben där användarens personnummer kommer temporärt plaseras i
        String lfnamn;//variaben där användarens förnamn kommer temporärt plaseras i 
        String lenamn;//variaben där användarens efternamn kommer tempotärt plaseras i
        char lkön;//variaben där användarens kön kommer temporärt plasers i
        char sant;//variaben som låter användaren bekräfta om informationen de har gät är korekt
        
        do{//programet hoppa hit om informationen är fel
            
            pnum = ange_Pnum();//frågar efter användarens personnummer
            lfnamn = ange_namn(true);//frågar efter användarens förnamn
            lenamn = ange_namn(false);//frågar efter användarens efternamn
            lkön = ange_Kön();//frågar efter anvåndaren kön
            
            //skriver ut informationen användaren gav så att användaren kan bekränfa om det är rätt
            System.out.println("\nNamn: " + lfnamn + " " + lenamn + "\nPersonnummer: " + pnum + "\nkön: " + lkön);
            System.out.print("Är deta korekt(y/n): ");
            
            sant = scan.next().charAt(0);//låter användaren skriva in en strin som prugramet far sen första teknet ifrån och sparar det
            
            if(sant == 'y'){//om användaren svarade "y" så komer programmet hoppa ur lopen anars så startas loopen om
                break;
            }
            
        }while(true);
        
        //skriver in informationen användaren anvav i rätt fält
        stol[plats] = pnum;
        fnamn[plats] = lfnamn;
        enamn[plats] = lenamn;
        kön[plats] = lkön;
        
    }
    
    static void hitta_bokning_Pnum(){
        
        //tar in personnumret som skas hittas
        long pNum = ange_Pnum();
        
        //kollar igenom varje plats om det har personnummret som söks efter
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == pNum){//kollar om stol i inehåller personnumret som söks efter
                System.out.println("Du sitter på plats "+(i+1));//skriver ut platsen om ett matchande personnummer hittas
                return;//avslutar funktionen
            }
        }
        //skrivs ut om personnummret inte hittas
        System.out.println("Det fins inget plats som är bokad under det här personnumret");
        
    }
    
    static void hitta_bokning_namn(){
        
        //tar in för och efternamn som prugramet ska leta efter
        String lfnamn = ange_namn(true);
        String lenamn = ange_namn(false);
        
        for(int i = 0; i < fnamn.length; i++){//kollar igenom alla stolar om de har samma för och efternamn sparade i sig
            if(lfnamn.equals(fnamn[i]) && lenamn.equals(enamn[i])){
                System.out.println("Du sitter på plats "+(i+1));//skrive ut om programet har hittat en stol med matchande för och efternamn
                return;//avslutar fuktionen
            }
        }
        //skrivs ut om namnet inte hittas
        System.out.println("Det fins inget plats som är bokad under det här namnet");
        
    }
    
    static void tabort_Pnum(){
        //tar in personnummret som lesas efter
        long pNum = ange_Pnum();
        
        //kollar igenom alla stolar för perssonnumret som letas efter
        for(int i = 0; i < stol.length; i++){
            if(stol[i] == pNum){
                stol[i] = -1;//markerar att stolen är ledig
                fnamn[i] = "";//tar bort förnannet
                enamn[i] = "";//far bort efternamnet
                kön[i] = '\0';//kar bort könet
                System.out.println("Din bokning har tagits bort");//skriver ut att bokningen är borttagen
                return;//avslutar funktionen
            }
        }
        //skrivs ut om personnumret inte hitas
        System.out.println("Det fins ingen bokning med det här personnumret");
        
    }
    
    static void tabort_namn(){
        
        String lfnamn = ange_namn(true);//hämtar in förnamnet som söks efter
        String lenamn = ange_namn(false);//hämtar in efternamnet som söks efter
        
        for(int i = 0; i < fnamn.length; i++){//kollar igennom alla stolar efter det spesifierade för och efternamnet
            if(lfnamn.equals(fnamn[i]) && lenamn.equals(enamn[i])){
                stol[i] = -1;//markerar att stolen är ledig
                fnamn[i] = "";//tar bort förnannet
                enamn[i] = "";//far bort efternamnet
                kön[i] = '\0';//kar bort könet
                System.out.println("Din bokning har tagits bort");//skriver ut att bokningen är borttagen
                return;//avslutar funktionen
            }
        }
        //skrivs ut om namnet inte hittas
        System.out.println("Det fins inget plats som är bokad under det här namnet");
        
    }
    
    static boolean koll_f(int koll){
        //kollar om väret som matas in är en fönsterplats
        for(int i = 0; i < stol.length; i += 4){
            if(koll == i){ //kolar om värdet är lika med i som är en fönsterplats
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
        
        long pNum;
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
    
    static String ange_namn(boolean för){
        Scanner scan = new Scanner(System.in);
        
        String pos;//variaben som kommer inefålla "för" eller "efter" 
        
        if(för){//fom för är sant så är matas "för" in i pos annas masta "efter" in 
            pos = "för";
        }else{
            pos = "efter";
        }
        
        System.out.print("Ange dit " + pos + "namn: ");//skriver ut att användaren ska ange sit namn
        
        return scan.nextLine();//låter användaren ange sit namn och sen ger ut det
    }
    
    static char ange_Kön(){
        Scanner scan = new Scanner(System.in);
        
        char lkön;//variaben där användarens kön kommer temporärs sparas
        
        do{//startar en loop
            
            //skriver ut altenativen användaren kan välja på
            System.out.print("""
                             Ange dit kön
                             m: man
                             k: kvinna
                             a: annat/vill inte ange
                             svar: """);
            
            lkön = scan.next().charAt(0);//låter användaren skrive in sit svar
            
            if(lkön == 'm' || lkön == 'k' || lkön == 'a'){//kollar om avaret snvändaren gav är giltigt
                return lkön;//get ut värdet på variaben
            }
            
            System.out.println("Inte ett giltigt svar");//skriver tu att svaren användaren gav var inte giltigt
            
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
    
    static void sotera(long[] pnum, String[] lfnamn, String[] lenamn, int[] lstol){
        
        for(int i = 0; i < pnum.length; i++){//kollar igenom personnummret på bokningar
            if(get_num(9, 2, pnum[i]) < 23){//kom personnumrets år är mindre än 23
                pnum[i] += 10000000000l;//lägger på en etta på 11 platsen för att göra personnumret större
            }
        }
        
        //soteringsystem som osdnar nomrena fråm minst till störst
        for(int i = 0; i < pnum.length-1; i++){
            int minst = i;//variabel som sparar positionen av det minsta värdet algoritmen har hittat
            for(int j = i+1; j < pnum.length; j++){//kollar igenom alla fält till höger om i för ett mindre värde
                if(pnum[minst] > pnum[j]){//kollar om värdet i j är mindre än värdet i minst
                    minst = j;//uppdaterar positionen för det minsta värdet om ett mindre värde har hitas
                }
            }
            
            //byter plats på personnumret mellan i och platsen där det minsta värdet hitades
            long temp = pnum[i]; 
            pnum[i] = pnum[minst];
            pnum[minst] = temp;
            
            //byter plats på förnamnet mellan i och platsen där det minsta värdet hitades
            String tempf = lfnamn[i];
            lfnamn[i] = lfnamn[minst];
            lfnamn[minst] = tempf;
            
            //byter plats på efternamnet mellan i och platsen där det minsta värdet hitades
            String tempe = lenamn[i];
            lenamn[i] = lenamn[minst];
            lenamn[minst] = tempe;
            
            //byter plats på stolspositionen mellan i och platsen där det minsta värdet hitades
            int temps = lstol[i];
            lstol[i] = lstol[minst];
            lstol[minst] = temps;
        }
        
        //tar bort allt efter 10 sifrer för alla personnummer
        for(int i = 0; i < pnum.length; i++){
            pnum[i] %= 10000000000l;
        }
        
    }
    
}
