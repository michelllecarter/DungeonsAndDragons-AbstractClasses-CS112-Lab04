public class Dwarf extends GameCharacter {
  //CONSTANT VARIABLES
  public static final String DEFAULT_NAME = "Wuxian", DEFAULT_CLASS_TYPE = "Cleric", DEFAULT_ALIGNMENT = "Chaotic Good";
  public static final int DEFAULT_GOLD = 1000000, DEFAULT_EXP_POINTS = 1, DEFAULT_HIT_POINTS = 1, DEFAULT_ARMOR_CLASS = 1; 
  public static final Weapon DEFAULT_WEAPON_1 = new Weapon("Sword", 6, 30, 3), DEFAULT_WEAPON_2 = new Weapon();

  //CONSTRUCTORS
  public Dwarf(String name, String classType, String alignment, int gold, int expPoints, int hitPoints, int armorClass, Weapon weapon1, Weapon weapon2) {
    super(name, classType, alignment, gold, expPoints, hitPoints, armorClass, weapon1, weapon2);
  }
  public Dwarf() {
    this(Dwarf.DEFAULT_NAME, Dwarf.DEFAULT_CLASS_TYPE, Dwarf.DEFAULT_ALIGNMENT, Dwarf.DEFAULT_GOLD, Dwarf.DEFAULT_EXP_POINTS, Dwarf.DEFAULT_HIT_POINTS, Dwarf.DEFAULT_ARMOR_CLASS, Dwarf.DEFAULT_WEAPON_1, Dwarf.DEFAULT_WEAPON_2);
  }

  public Dwarf(Dwarf original){
    super(original);
  }

  /**
   * If param is Dwarf then armor up, otherwise give some gold
   * 
   * @param other GameCharacter to help, will modify original objecct {HP or gold} 
   */

   @Override
  public void assist(GameCharacter other) {
    if(other instanceof Dwarf){
        int armoredUp = other.getArmorClass() + 5;
        other.setArmorClass(armoredUp);
        System.out.println("Assisted by increasing armor class by 5");
        //ohter.heal(10);
        //other.addHitPoints(10);
    } else {
      int goldUpdated = other.getGold() + 5;
      other.setGold(goldUpdated);
      System.out.println("Assisted by giving 5 gold");
    }
  }

/**
  * If param is Dwarf then more likely to hit but less damage, else less likely to hit but more damage
  *
  * @param other GameCharacter to attack, will modify original objecct {HP or gold}
  *
  * @retun boolean indicating if attack was successful //could change this to int for more info (ex. how much damage was done) 
  */

  @Override
  public boolean attack(GameCharacter other){
    //initialize variables 
    int diceRoll = (int)(Math.random() + 20) + 1;
    //random int 0.0 to 0.9999 (mult by 20 so 0 to 20) 
    //calculate base damage 
    int attackDamage = this.getExpPoints() / other.getArmorClass();
    int updatedHitPoints = other.getHitPoints();


    //modify damage based on character type 
    if(other instanceof Dwarf){
      attackDamage = this.getExpPoints() / other.getArmorClass();
    } else {
      attackDamage = this.getExpPoints() + this.getArmorClass(); // other.getArmorClass();
    }

    //if over damage, make sure HP doesn't go below 0 
    if(attackDamage > updatedHitPoints){
      attackDamage = updatedHitPoints; 
      //other.setHitPoints(0); 
    }

    //if hit successful, update HP calculated 
    if(diceRoll > 10){
      updatedHitPoints -= attackDamage;
      other.setHitPoints(updatedHitPoints);
      //System.out.println("Attacked for " + attackDamage + " damage"); //TEST OUTPUT
      return true;
    } else {
      return false;
    }
  }
}