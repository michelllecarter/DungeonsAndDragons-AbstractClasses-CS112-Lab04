// Main.java
import java.util.Scanner;

public class Main{
  public static void main(String[] args){

    //DECLARATION + INITIALIZATION 
    Scanner keyboard = new Scanner(System.in);

 /**
  * GameCharacter player1 = new Elf("Elfy", "Sorcerer", "Chaotic Good", 100, 10, 10, 10, new Weapon("Crossbow", 6, 30, 3), null);
    GameCharacter player2 = new Dwarf("Dwarfy", "Fighter", "Lawful Good", 100, 10, 10, 10, new Weapon("Club", 4, 1, 0), DEFAULT_WEAPON_1);
  */
  GameCharacter player1 = new Dwarf("Xie", "Fighter", "Lawful Good", 100, 450, 200, 8, new Weapon("Deathsword", 6, 2, 21), null);
  GameCharacter player2 = new Dwarf("Wangji", "Barbarian", "Chaotic Evil", 5000, 250, 220, 3, new Weapon("Battleaxe", 6, 2, 2), Dwarf.DEFAULT_WEAPON_1); 

    boolean battleDone = false;
    GameCharacter winner = null, loser = null, offense, defense, temp;
    int menuChoice;

    System.out.println("\nFlipping coin...");
    int simulatedFlip = (int)(Math.random() * 2); //0 or 1 value
    if(simulatedFlip == 0){ //heads 
      System.out.println("... Heads!");
      offense = player1;
      defense = player2;
    } else {
      System.out.println("...Tails!");
      offense = player2;
      defense = player1;
    }
      System.out.println(offense.getName() + " goes first!\n");
    
    //GameCharacter Arthur = new GameCharacter();
    //GameCharacter elf = new GameCharacter("Varis Liadon", "Monk", "Lawful Neutral", 50000, 90, 100, 5, newWeapon("Longbow", 8, 150, 2), null);
    //System.out.print(elf);
    //not allowed bc it calls abstract class 
     
    //System.out.println(Arthur);

    //INPUT + PROCESSING + OUTPUT
    do {
      Main.printSideBySide(offense, defense);
      System.out.println();
      //print menu + input choice
      System.out.println(offense.getName() + " what would you like to do?\n");
      System.out.println("1) Attack");
      System.out.println("2) Assist (Attempt to befriend)");
      System.out.println("3) Run Away");
      System.out.println("4) Enter choice> ");
      menuChoice = keyboard.nextInt();
      System.out.println();
      
        //switch statement for menu choice
        switch(menuChoice ){
          case 1:
            battleDone = Main.attackChoice(offense, defense);
            if(battleDone){
              winner = offense;
              loser = defense;
              System.out.println("\n" + winner.getName() + "has KOd" + loser.getName() + "!");
            }
            break;
          case 2:
            Main.assistChoice(offense, defense);
            System.out.println(offense.getName() + " assisted " + defense.getName() + "!");
            break;
          case 3:
            System.out.println(offense.getName() + " ran away! Why? We may never know...");
            battleDone = true;
            winner = defense; 
            loser = offense;
            break;
          default:
            System.out.println("\nERROR: please try aain and enter valid choice, 1-3");
            break;
        }
          //switch for next round!
          System.out.println();
          temp = offense;
          offense = defense;
          defense = temp;

      } while (!battleDone);
  
        keyboard.close();
      
        System.out.println("\n!!!" + winner.getName() + " beat " + loser.getName() + "!!!\n\n");
  
        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│            FINAL STATS           │");
        System.out.println("└──────────────────────────────────┘");
        System.out.println();
        System.out.println("Winner:\n" + winner.toAscii() + "\n");
        System.out.println("Loser:\n" + loser.toAscii() + "\n");
  
        System.out.println("Thanks for playing!");
    }
  public static void assistChoice(GameCharacter assister, GameCharacter receiver){
    assister.assist(receiver);
  }

  //return true if defender is defeated, false if still has HP left
  public static boolean attackChoice(GameCharacter attacker, GameCharacter defender){
    boolean successfulAttack = attacker.attack(defender);

    if(successfulAttack){
      System.out.println("It was a hit! " + defender.getName() + " took some damage...");
    } else {
      System.out.println("It was a miss! " + defender.getName() + " took no damage...");
    }
    return defender.getHitPoints() == 0;
  }
  public static void printSideBySide(GameCharacter left, GameCharacter right) {
    String[] leftParts = left.toAscii().split("\n");
    String[] rightParts = right.toAscii().split("\n");

    for(int i = 0; i < leftParts.length; i++){
      System.out.print(leftParts[i]);

      if (i == leftParts.length / 2) {
        System.out.print("-->");
      } else {
        System.out.print("   ");
      }
      System.out.println(rightParts[i]);
    }
  }
}