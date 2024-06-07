public class Elf extends GameCharacter {
  //CONSTANT VARIABLES
  public static final String DEFAULT_NAME = "Choso", DEFAULT_CLASS_TYPE = "Sorcerer", DEFAULT_ALIGNMENT = "Chaotic Good";
  public static final int DEFAULT_GOLD = 1000000, DEFAULT_EXP_POINTS = 1, DEFAULT_HIT_POINTS = 1, DEFAULT_ARMOR_CLASS = 1; 
  public static final Weapon DEFAULT_WEAPON_1 = new Weapon("Crossbow", 6, 30, 3), DEFAULT_WEAPON_2 = new Weapon();
  
  //CONSTRUCTORS
  public Elf(String name, String classType, String alignment, int gold, int expPoints, int hitPoints, int armorClass, Weapon weapon1, Weapon weapon2) {
    super(name, classType, alignment, gold, expPoints, hitPoints, armorClass, weapon1, weapon2);
  }
  public Elf() {
    this(Elf.DEFAULT_NAME, Elf.DEFAULT_CLASS_TYPE, Elf.DEFAULT_ALIGNMENT, Elf.DEFAULT_GOLD, Elf.DEFAULT_EXP_POINTS, Elf.DEFAULT_HIT_POINTS, Elf.DEFAULT_ARMOR_CLASS, Elf.DEFAULT_WEAPON_1, Elf.DEFAULT_WEAPON_2);
  }

  public Elf(Elf original){
    super(original);
  }

  /**
   * If param is Elf then heal, otherwise give some gold
   * 
   * @param other GameCharacter to help, will modify original objecct {HP or gold} 
   */

   @Override
  public void assist(GameCharacter other) {
    if(other instanceof Elf){
        int healed = other.getHitPoints() + 10;
        other.setHitPoints(healed);
        System.out.println("Assisted by healing 10");
        //ohter.heal(10);
        //other.addHitPoints(10);
    } else {
      int goldUpdated = other.getGold() + 5;
      other.setGold(goldUpdated);
      System.out.println("Assisted by giving 5 gold");
    }
  }

/**
  * If param is Elf then more likely to hit but less damage, else less likely to hit but more damage
  *
  * @param other GameCharacter to attack, will modify original objecct {HP or gold}
  *
  * @retun boolean indicating if attack was successful //could change this to int for more info (ex. how much damage was done) 
  */

  @Override
  public boolean attack(GameCharacter other){
    int diceRoll = (int)(Math.random() + 20) + 1;
    //random int 0.0 to 0.9999 (mult by 20 so 0 to 20) 
    //calculate base damage 
    int attackDamage = this.getExpPoints() / other.getArmorClass();
    int updatedHitPoints = other.getHitPoints();
    
    
    //modify damage based on character type 
    if(other instanceof Elf){
      attackDamage /= 2;
      diceRoll += 5;
    }
    
    //if over damage, make sure HP doesn't go below 0 
    if(attackDamage > updatedHitPoints){
      attackDamage = updatedHitPoints; 
      //other.setHitPoints(0); 
    }

    //determine if hit successful, if so update HP appropriately 
    if(diceRoll >= 10){
      updatedHitPoints -= attackDamage;
      other.setHitPoints(updatedHitPoints);
      return true;
    } else {
      return false;
    }
  }
}