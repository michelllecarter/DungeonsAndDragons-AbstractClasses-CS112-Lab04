//class invariant all int values must be >= 0, name must exist not null, length > 0, and 
// class/alignment have to be part of allowed values (limited to classes from players handbook)
// Weapon 1 must exist, weapon 2 can be null 

public abstract class GameCharacter{

  //CONSTANT VARIABLES
  public static final String DEFAULT_NAME = "Merlin", DEFAULT_CLASS_TYPE = "Wizard", DEFAULT_ALIGNMENT = "Neutral";
  public static final int DEFAULT_GOLD = 100, DEFAULT_EXP_POINTS = 1, DEFAULT_HIT_POINTS = 1, DEFAULT_ARMOR_CLASS = 1;
  public static final Weapon DEFAULT_WEAPON_1 = new Weapon(), DEFAULT_WEAPON_2 = null;

  public static final String[] VALID_CLASSES = {"Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};
  public static final String[] VALID_ALIGNMENTS = {"Lawful Good", "Neutral Good", "Chaotic Good", "Lawful Neutral", "Neutral", "Chaotic Neutral", "Lawful Evil", "Neutral Evil", "Chaotic Evil", "unaligned"};
  
  //INSTANCE VARIABLES
  private String name, classType, alignment;
  private int gold, expPoints, hitPoints, armorClass;
  private Weapon weapon1, weapon2;

  //CONSTRUCTORS 
  public GameCharacter(String name, String classType, String alignment, int gold, int expPoints, int hitPoints, int armorClass, Weapon weapon1, Weapon weapon2){
    if(!this.setAll(name, classType, alignment, gold, expPoints, hitPoints, armorClass, weapon1, weapon2)){
      System.out.println("ERROR: invalid data given to GameCharacter constructor. Shutting down.");
      System.exit(0);
    }
  }

  public GameCharacter(){
    this(DEFAULT_NAME, DEFAULT_CLASS_TYPE, DEFAULT_ALIGNMENT, DEFAULT_GOLD, DEFAULT_EXP_POINTS, DEFAULT_HIT_POINTS, DEFAULT_ARMOR_CLASS, DEFAULT_WEAPON_1, DEFAULT_WEAPON_2);
  }

  public GameCharacter(GameCharacter original){
    if(original == null){
      System.out.println("ERROR: null data given to copy GameCharacter constructor. Shutting down.");
      System.exit(0);
    } else{
      this.setAll(original.name, original.classType, original.alignment, original.gold, original.expPoints, original.hitPoints, original.armorClass, original.weapon1, original.weapon2);
    }
  }
  
  //MUTATORS/SETTERS 

  public boolean setName(String name){
    if(name != null & name.length() > 0){
      this.name = name;
      return true;
    } else{
      return false;
    }
  }

  public boolean setClassType(String classType){
    if(GameCharacter.isInArray(VALID_CLASSES, classType)){
      this.classType=classType;
      return true;
    } else{
      return false;
    }
  }

  public boolean setAllignment(String alignment){
    if(GameCharacter.isInArray(VALID_ALIGNMENTS, alignment)){
      this.alignment = alignment;
      return true;
    } else{
      return false;
    }
  }
  
  public boolean setGold(int gold){
    if(gold >=0){
      this.gold = gold;
      return true;
    } else{
      return false;
    }
  }

  public boolean setExpoPoints(int expPoints){
    if(expPoints >=0){
      this.expPoints = expPoints;
      return true;
    } else{
      return false;
    }
  }

  public boolean setHitPoints(int hitPoints){
    if(hitPoints >=0){
      this.hitPoints = hitPoints;
      return true;
    } else{
      return false;
    }
  }

  public boolean setArmorClass(int armorClass){
    if(armorClass >=0){
      this.armorClass = armorClass;
      return true;
    } else{
      return false;
    }
  }

  //class Type so must be deep copy (does not break encapsulation)
  public boolean setWeapon1(Weapon weapon){
    if(weapon != null){
      this.weapon1 = new Weapon(weapon);
      return true;
    } else{
      return false;
    }
  }

  //class Type so must be deep copy (does not break encapsulation)
  //can be null, if not then deep copy 
  public boolean setWeapon2(Weapon weapon){
    if(weapon == null){
      this.weapon2 = null;
      return true;
    } else{
      this.weapon2 = new Weapon(weapon);
    }
    return true;
  }

  public boolean setAll(String name, String classType, String alignment, int gold, int expPoints, int hitPoints, int armorClass, Weapon weapon1, Weapon weapon2){
    
    return this.setName(name) && this.setClassType(classType) && this.setAllignment(alignment) && this.setGold(gold) && this.setExpoPoints(expPoints) && this.setHitPoints(hitPoints) && this.setArmorClass(armorClass) && this.setWeapon1(weapon1) && this.setWeapon2(weapon2);
  }

  //ACCESSORS/GETTERS
  
  public String getName(){
    return this.name;
  }

  public String getClassType(){
    return this.classType;
  }

  public String getAlignment(){
    return this.alignment;
  }
  
  public int getGold(){
    return this.gold;
  }

  public int getExpPoints(){
    return this.expPoints;
  }

  public int getHitPoints(){
    return this.hitPoints;
  }

  public int getArmorClass(){
    return this.armorClass;
  }

  public Weapon getWeapon1(){
    return new Weapon(this.weapon1);
  }

  public Weapon getWeapon2(){
    if(this.weapon2 == null){
      return null;
    } else{
      return new Weapon(this.weapon2);
    }

  }
  
  //OTHER REQUIRED METHODS
   @Override
  public String toString(){
    //create weapons string 
    String weapons = "Equipped = {" + this.weapon1 + "}";
    if(this.weapon2 != null){
      weapons += "+ {" + this.weapon2 + "}";
    }
    return String.format("GameCharacter: Name = %s, Class = %s, Alignment = %s, Gold = %d, XP = %d, HP = %d, Armor Class = %d,%n\t%s", this.name, this.classType, this.alignment, this.gold, this.expPoints, this.hitPoints, this.armorClass, weapons);
    }

@Override
  public boolean equals(Object other){
    if(other == null || !(other instanceof GameCharacter)){
      return false;
    }
    GameCharacter otherCharacter = (GameCharacter)other;
    boolean secondWeaponsEqual = (this.weapon2 == null && otherCharacter.weapon2 == null) || this.weapon2.equals(otherCharacter.weapon2);

    return this.name.equals(otherCharacter.name) && this.classType.equals(otherCharacter.classType) && this.alignment.equals(otherCharacter.alignment) && this.gold == otherCharacter.gold && this.expPoints == otherCharacter.expPoints && this.hitPoints == otherCharacter.hitPoints && this.armorClass == otherCharacter.armorClass && this.weapon1.equals(otherCharacter.weapon1) && secondWeaponsEqual; 
  }
  
  //HELPER METHOD
    private static boolean isInArray(String[] values, String value){
      if(values == null || value == null){
        return false;
      }
      int location = 0;
      boolean isPresent = false;

      while(!isPresent && location < values.length){
        //while not found and haven't reached the end of the array
        isPresent = values[location].equalsIgnoreCase(value);
        //makes answers not case sensitive , YAY flexibility 
        location++;
        //how to avoid array out of bounds exceptions
        //2 loop control variables; isPresent and location (both are initialized above, checked, and updated (in loop body))
      }
      return isPresent;
      }

  public String toAscii() {
    final String BAR = "──────────────────────────────────", DIVIDER = "├" + BAR + "┤";
    final int BAR_LENGTH = BAR.length();

    String identity = this.name + " (" + this.getClass().getCanonicalName() + " / " + this.classType + ")";
    String hp = "HP: " + this.hitPoints, xp = "XP: " + this.expPoints, armor = "AC: " + this.armorClass, gold = String.format("%,dG",this.gold);
    int identityCenterWidth = (identity.length() + BAR_LENGTH) / 2; 
    int alignmentCenterWidth = (this.alignment.length() + BAR_LENGTH) / 2;
    int specWidth = BAR.length() / 4;

    //top of box with name, race, class, and alignement 
    String ascii = "┌" + BAR + "┐\n";
    ascii += String.format("│%" + identityCenterWidth + "s%" + (BAR_LENGTH - identityCenterWidth) + "s│%n", identity, ""); //center identity in ascii box
    ascii += String.format("│%" + alignmentCenterWidth + "s%" + (BAR_LENGTH - alignmentCenterWidth) + "s│%n", this.alignment, ""); //center alignment in ascii box 
    ascii += DIVIDER + "\n";

    //add on each of the stats on same row (note -2-1 because of extra space added after │)
    ascii += String.format("│ %-" + (specWidth-2) + "s", hp);
    ascii += String.format("│ %-" + (specWidth-2) + "s", xp);
    ascii += String.format("│ %-" + (specWidth-2) + "s", armor);
    ascii += String.format("│ %" + (specWidth-2) + "s │\n", gold);

    //bottom of box 
    ascii += "└" + BAR + "┘";

    return ascii;
    
  }
  //ABSTRACT METHODS 
  public abstract void assist(GameCharacter other);
  public abstract boolean attack(GameCharacter other);
  
  
}