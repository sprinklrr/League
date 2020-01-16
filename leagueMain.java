import java.util.*;
import java.io.*;

// Main method, runs the game
class LeagueMain {
  
  static ArrayList<Champion> championList = new ArrayList<Champion>();
  static ArrayList<Champion> player1List = new ArrayList<Champion>();
  static ArrayList<Champion> player2List = new ArrayList<Champion>();
  
  static int player1Size = 0;
  static int player2Size = 0;
  boolean playerTurn = false;
  static int number = 0;
  
  public static void main(String[] args) throws Exception {
    Scanner reader = new Scanner(System.in);
    System.out.println("Type PVP or PVC");
    String choice = reader.nextLine();
    if (choice.equalsIgnoreCase("PVP")) {
      champIo(); // Reads the csv
      
      while (player2List.size() < 3) {
        System.out.println("Type the corresponding number of the champion you want:");
        int select = reader.nextInt();
        championPicker(select, player1List, championList);
        
        int select2 = reader.nextInt();   
        championPicker(select2, player2List, championList);
        
        System.out.println("Player 1 Team:");
        for(int i = 0; i < player1List.size(); i++){
          System.out.println("Champion: " + i + ": " + player1List.get(i).getName());
        }
        
        System.out.println("Player 2 Team:");
        for(int i = 0 ; i< player2List.size(); i++){
          System.out.println("Champion " + i + ": " + player2List.get(i).getName());
        }
      }
    }
    else if (choice.equalsIgnoreCase("PVC")) {
      champIo(); // Reads the csv
      Random randomgen = new Random();
      
      while (player2List.size() < 3) {
        System.out.println("Type the name of the champion you want:");
        int select = reader.nextInt();  
        championPicker(select, player1List, championList);
        
        int randomint = randomgen.nextInt(championList.size());
        championPicker(randomint, player2List, championList);
        
        System.out.println("Player 1 Team:");
        for(int i = 0; i < player1List.size(); i++){
          System.out.println("Champion: " + i + ": " + player1List.get(i).getName());
        }
        
        System.out.println("Player 2 Team:");
        for(int i = 0 ; i< player2List.size(); i++){
          System.out.println("Champion " + i + ": " + player2List.get(i).getName());
        }
      }
    }
  }
  
  public static void champIo() throws Exception {
    String csvFile = "champList.csv";
    BufferedReader br;
    String line = "";
    String cvsSplitBy = ",";
    
    try {
      br = new BufferedReader(new FileReader(csvFile));
      
      // skip header lines
      br.readLine();
      while ((line = br.readLine()) != null) { // while csv has champions in its file
        
        // use comma as separator     
        String[] row = line.split(cvsSplitBy);
        
        // sets champion stats
        Champion champion = new Champion(row[0], row[2]);
        champion.setHealth(Double.parseDouble(row[3]));
        champion.setAtkSpd(Double.parseDouble(row[4]));
        champion.setAtkDmg(Double.parseDouble(row[5]));
        champion.setArmor(Double.parseDouble(row[6]));
        champion.setCrit(Double.parseDouble(row[7]));
        champion.setMagicDmg(Double.parseDouble(row[8]));
        champion.setMagicRes(Double.parseDouble(row[9]));
        champion.setMana(Double.parseDouble(row[10]));
        champion.setManaReg(Double.parseDouble(row[11]));
        champion.setMoveSpd(Double.parseDouble(row[12]));
        champion.setTenacity(Double.parseDouble(row[13]));
        
        // adds champion to list
        championList.add(champion);
        System.out.println(number + ". " + champion);
        number++;
      }
    }
    
    // catch file exception
    catch (FileNotFoundException e) {
      e.printStackTrace();
    } 
  }
  
  public static void championPicker(int select, ArrayList<Champion> playerList, ArrayList<Champion> championList) {
    playerList.add(championList.get(select));
    championList.remove(select);
    championList.trimToSize();
  }
  
  /*
   public static void trait(Champion champion, Ability ability) {
   if (champion.getTrait().equals("Noxus")) {
   tribe.noxusTrait(champion);
   }
   //else if (champion.getTrait().equals("Demacia")) {
   //  tribe.demacianTrait(champion);
   //}
   else if (champion.getTrait().equals("Ionian")) {
   tribe.ionianTrait(champion);
   }
   else if (champion.getTrait().equals("Freljord")) {
   tribe.applyTrait(champion);
   }
   else if (champion.getTrait().equals("Zaun")) {
   tribe.applyTrait(champion);
   }
   }
   */
}