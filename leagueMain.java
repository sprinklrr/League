package League;

import java.util.*;
import java.io.*;

// Main method, runs the game
class LeagueMain {
  public static void main(String[] args) throws Exception {
    
    Champion champOnBoard = new Champion("temp", "temp", "temp"); // Temp champs that will be filled in by chosen champs
    Champion champ2OnBoard = new Champion("temp", "temp", "temp");
    boolean playerTurn = false; // If true, player 1 turn - if false, player 2 turn
    
    ArrayList<Champion> championList = new ArrayList<Champion>(); // Champ list
    Team player1List = new Team("Player 1"); // Player 1 Champ list
    Team player2List = new Team("Player 2"); // Player 2 Champ list
    int championPick = 0;
    int championPick2 = 0;
    Scanner reader = new Scanner(System.in);
    System.out.println("Type PVP or PVC");
    String mode = reader.nextLine();
    if (mode.equalsIgnoreCase("PVP")) {
      champIo(championList); // Reads the csv
      
      while (player2List.getChamps().size() < 3) { // While all champs are not selected yet
        System.out.println("\nType the corresponding number of the champion you want:");
        int select = reader.nextInt();
        championPicker(select, player1List.getChamps(), championList); // Selects a champion
        
        int select2 = reader.nextInt();   
        championPicker(select2, player2List.getChamps(), championList);
        
        System.out.println("Player 1 Team:"); // Prints out player 1's team
        for(int i = 0; i < player1List.getChamps().size(); i++){
          System.out.println("Champion: " + i + ": " + player1List.getChamps().get(i).getName());
        }
        
        System.out.println("Player 2 Team:"); // Prints out player 2's team
        for(int i = 0 ; i< player2List.getChamps().size(); i++){
          System.out.println("Champion " + i + ": " + player2List.getChamps().get(i).getName());
        }
      }
      
      // Game runs while either player does not have a champ that has died
      while (player1List.getChamps().size() != 0 || player2List.getChamps().size() != 0) {
        System.out.println("Player 1: Which champion do you wish to choose?");
        championPick = reader.nextInt(); // Chooses a champ from player 1 list
        try {
          System.out.println("You have chosen: " + player1List.getChamps().get(championPick).getName());
          champOnBoard = player1List.getChamps().get(championPick);
        }
        catch (Exception e) {
          while (championPick < player1List.getChamps().size() || championPick > player1List.getChamps().size() - 1) {
            System.out.println("Invalid input! Choose a valid champ.");
            championPick = reader.nextInt();
          }
        }
        
        System.out.println("Player 2: Which champion do you wish to choose?");
        championPick2 = reader.nextInt(); // Chooses a champ from player 2 list
        try {
          System.out.println("You have chosen: " + player2List.getChamps().get(championPick2).getName());
          champ2OnBoard = player2List.getChamps().get(championPick);
        }
        catch (Exception e) {
          while (championPick2 < player2List.getChamps().size() || championPick2 > player2List.getChamps().size() - 1) {
            System.out.println("Invalid input! Choose a valid champ.");
            championPick2 = reader.nextInt();
          }
        }
        
        // Chooses which player goes first
        playerTurn = compareSpd(player1List.getChamps().get(championPick), player2List.getChamps().get(championPick2), playerTurn); 
        
        // Player 1's turn
        if (playerTurn == true) {
          System.out.println("\nIt is player 1's turn. What would you like to do?");
          System.out.println("1. Attack");
          System.out.println("2. Equip Items");
          System.out.println("3. Switch Champions");
          int choice = reader.nextInt();
          
          if (choice == 1) {
            
          }
          else if (choice == 2) {
            
          }
          else if (choice == 3) {
            
          }
        }
        
        // Player 2 turn
        else if (playerTurn == false) {
          System.out.println("\nIt is player 2's turn. What would you like to do?");
          System.out.println("1. Attack");
          System.out.println("2. Equip Items");
          System.out.println("3. Switch Champions");
          int choice = reader.nextInt();
          
          if (choice == 1) {
            
          }
          else if (choice == 2) {
            
          }
          else if (choice == 3) {
            
          }
        }
        
      }
      
      
      
    }
    
    /*
     else if (mode.equalsIgnoreCase("PVC")) {
     champIo(championList); // Reads the csv
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
     */
  }
  
  public static boolean compareSpd(Champion champion, Champion champion2, boolean playerTurn) {
    if (champion.getAtkSpd() < champion2.getAtkSpd()) {
      return playerTurn = false;
    }
    else {
      return playerTurn = true;
    }
  }
  
  public static void champIo(ArrayList<Champion> championList) throws Exception {
    String csvFile = "champList.csv";
    BufferedReader br;
    String line = "";
    String cvsSplitBy = ",";
    int number = 0;
    try {
      br = new BufferedReader(new FileReader(csvFile));
      
      // skip header lines
      br.readLine();
      while ((line = br.readLine()) != null) { // while csv has champions in its file
        
        // use comma as separator     
        String[] row = line.split(cvsSplitBy);
        
        // sets champion stats
        Champion champion = new Champion(row[0], row[1], row[2]);
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
      br.close();
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