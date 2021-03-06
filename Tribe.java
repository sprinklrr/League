package League;

import java.util.*;

class Tribe {
  private String FRELJORD = "Freljord";
  private String ZAUN = "Zaun";
  private String IONIA = "Ionia";
  private String PILTOVER = "Piltover";
  private String DEMACIA = "Demacia";
  private String NOXUS = "Noxus";
  
  private boolean freeze; // Freljord
  private boolean poison; // Zaun
  private double lifesteal; // Ionian
  
  // Applies the noxian trait
  public void noxusTrait(Champion champion) {
    champion.setArmor(champion.getArmor() + 2);
  } 
  
  public void demacianTrait(Champion champion) {
    champion.setMagicRes(champion.getMagicRes() + 2);
  }
  
  // Applies the ionian effect
  public double ionianTrait(Champion champion, Ability ability) {
    
    lifesteal = 0; // Heals ionians for 10% of the damage they deal
    champion.setHealth(champion.getHealth() - 100); // Decreases the total health of ionians by 100
    
    lifesteal = (ability.getBaseAttack() + ability.getBaseMagic() * 0.1);
    
    return lifesteal;
  }
  
  // Applies freljord and zaun traits
  public boolean applyTrait(Champion champion, Ability ability) {
    
    Random randomGen = new Random(); // Random generator
    int randomNum = randomGen.nextInt(100); // Random num between 0 and 99
    if (champion.getTribe() == "Freljord") { // If freljord, any ability has the chance to freeze the target
      if (randomNum <= 0 && randomNum >= 10) { // 10% to freeze
        return freeze = true;
      }
    }
    
    if (champion.getTribe() == "Zaun") { // If zaun, any ability has the chance to poison the target
      if (randomNum <= 0 && randomNum >= 39) { // 40% to freeze
        return poison = true;
      }
    }
    return false; // Default case
  }
  
  // Getters
  public double getLifeSteal() {
    return lifesteal;
  }
  
  public boolean poisonResult() {
    return poison;
  }
  
  public boolean freezeResult() {
    return freeze;
  }
}
