package main;

import java.util.Scanner;

import util.Processor;

public class TextAdventure {

  private static final String WELCOME_MESSAGE = "Welcome to Text Adventure!\n" +
      "You are about to go on a journey like never before! " +
      "You can travel in any cardinal direction to explore the lands of this great Kingdom " +
      "of Albarat and explore what there is to do in the different regions. " +
      "Remember that you can check your bag to see what items you have in it any time " +
      "and toss ones you no longer need (but be careful with important ones!) Good luck!\n" +
      "You are currently at your home village. It looks like your mom wants to talk to you.\n";
  
  public static void main(final String[] args) {

    System.out.println(WELCOME_MESSAGE);
    final Scanner input = new Scanner(System.in);
    final Processor processor = new Processor();

    boolean shouldQuit = false;
    while (!shouldQuit) {
      final String command = input.nextLine().toLowerCase();
      final String printout = processor.processCommand(command);
      System.out.println("\n" + printout);
      shouldQuit = isExitString(printout);
      if (!shouldQuit) {
        System.out.println("What would you like to do?\n");
      }
    }
  }

  private static boolean isExitString(final String printout) {
    return printout.equals("Bye!") || printout.equals("You died!") ||
        printout.equals("You jumped off the cliff and died.") ||
        printout.equals("You have slain the dragon and beat the game! Congratulations!");
  }

}
