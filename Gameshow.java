import java.io.IOException;
import java.lang.Math;
import com.phidget22.*;
import java.util.Scanner;

public class Gameshow {
    
    public static boolean[] buttonPressed = new boolean[2];
    
    public static DigitalInputStateChangeListener onStateChangeButton0 = new DigitalInputStateChangeListener() {
        @Override
        public void onStateChange(DigitalInputStateChangeEvent ev) {
            if (ev.getState() == true) {
                buttonPressed[0] = true;
            } else {
                buttonPressed[0] = false;
            }
        }
    };
    
    public static DigitalInputStateChangeListener onStateChangeButton1 = new DigitalInputStateChangeListener() {
        @Override
        public void onStateChange(DigitalInputStateChangeEvent ev) {
            if (ev.getState() == true) {
                buttonPressed[1] = true;
            } else {
                buttonPressed[1] = false;
            }
        }
    };
    
    public static void main(String args[]) throws PhidgetException, InterruptedException, IOException {
        
        int[] score = new int[2];
        
        int playerTurn;
        int question;
        boolean answer = false;
        boolean questionAnswered = false;
        int howManyAsked = 0;
        
        boolean programIsRunning = true;
        DigitalOutput led0 = new DigitalOutput();
        DigitalOutput led1 = new DigitalOutput();
        DigitalInput button0 = new DigitalInput();
        DigitalInput button1 = new DigitalInput();
        
        led0.setHubPort(0);
        led0.setIsHubPortDevice(true);
        led0.open();
        
        led1.setHubPort(5);
        led1.setIsHubPortDevice(true);
        led1.open();
        
        button0.setHubPort(2);
        button0.setIsHubPortDevice(true);
        button0.addStateChangeListener(onStateChangeButton0);
        button0.open();
        
        button1.setHubPort(3);
        button1.setIsHubPortDevice(true);
        button1.addStateChangeListener(onStateChangeButton1);
        button1.open();
        
        //Intro
        System.out.println("Welcome to your Portal themed gameshow!\n");
        System.out.println("A question will appear on the screen, \nthe first person to press their button gets to answer.");
        System.out.println("Once a button has been pressed, \nthe green button will then be 'true' and the red button is 'false'.");
        System.out.println("An LED will light up to correspond with the current players turn, \nit will turn off when the players turn has ended.");
        System.out.println("Please have fun \n\n");
        
        
        
        while (howManyAsked < 5) {
            
            
            Questions openSesame = new Questions(); //Opens the Questions class
            openSesame.makeQuestion();
            
            do { //Randomizes a question to ask
                question = (int)(Math.random() * 5);
            } while (Questions.noRepeat[question] == true);
            Questions.noRepeat[question] = true; //Tells boolean that the question has been asked, prevents repeats
            System.out.println(Questions.ask[question]); //Prints the question
            
            
            do { //Checks if a button has been pressed, repeats until one has been
                System.out.print("");
                if (buttonPressed[0] == true) { //Assigns the players turn when their respective button has been pressed
                    led0.setState(true);
                    playerTurn = 1;
                } else if (buttonPressed[1] == true) {
                    led1.setState(true);
                    playerTurn = 2;
                } else {
                    playerTurn = 0;
                }
            } while (playerTurn == 0);
            
            Thread.sleep(1000); //Prevents mashing of button accidentally giving an answer
            
            System.out.println("Player " + playerTurn + "'s turn.");
            System.out.println("Please answer true or false");
            
            do { //Repeats until an answer has been given
                System.out.print("");
                if (buttonPressed[0] == true) {
                    answer = true;
                    questionAnswered = true;
                } else if (buttonPressed[1] == true) {
                    answer = false;
                    questionAnswered = true;
                }
            } while (questionAnswered == false);
            
            
            if (answer == Questions.answers[question]) {
                System.out.println("Correct!");
                score[playerTurn - 1] = score[playerTurn - 1] + 1;
            } else {
                System.out.println("Sorry, that was incorrect");
                System.out.println(Questions.explanation[question]);
                if (playerTurn == 1) {
                    score[1] = score[1] + 1;
                } else {
                    score[0] = score[0] + 1;
                }
            }
            System.out.println();
            
            System.out.println("Player 1: " + score[0] + "\nPlayer 2: " + score[1] + "\n");
            
            led0.setState(false);
            led1.setState(false);
            
            
            Thread.sleep(1000);
            
            howManyAsked = howManyAsked + 1;
            questionAnswered = false;
        }
        led0.close();
        led1.close();
        button0.close();
        button1.close();
        
        System.out.print("Please enter the winning players name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        input.close();
        
        if (score[0] < score[1]) {
            SaveFile.Save(score[1], name);
        } else {
            SaveFile.Save(score[0], name);
        }
        
        
    }


}
