package efs.task.syntax;

 import java.util.Arrays;
 import java.util.Random;
 import java.util.Scanner;


public class GuessNumberGame {
    private final int upperBound;
    private final int secretNumber;
    private final int maxAttempts;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public GuessNumberGame(String argument) throws IllegalArgumentException {
        try {
            this.upperBound = Integer.parseInt(argument);
        }catch (IllegalArgumentException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new IllegalArgumentException("Niepoprawny argument", e);
        }
        if (upperBound < 1 || upperBound > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            System.out.println("Poza zakresem <1," + upperBound +">");
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        this.secretNumber = random.nextInt(upperBound) + 1;
        maxAttempts = (int)Math.abs(Math.floor(Math.log(upperBound) / Math.log(2))) + 1;
    }

    public void play() {
        int currentAttempt = 0;
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1," + upperBound + ">");
        Scanner scanner = new Scanner(System.in);
        int guess;
        String[] bar=new String[maxAttempts];
        Arrays.fill(bar, ".");
        while (true) {
            if(currentAttempt==maxAttempts){
                System.out.print(UsefulConstants.UNFORTUNATELY);
                System.out.println(" wyczerpałeś limit prób (" + maxAttempts + ") do odgadnięcia liczby " + secretNumber);
                break;
            }
            bar[currentAttempt]="*";
            printAttemptsProgress(bar);
            System.out.println(UsefulConstants.GIVE_ME + " LICZBĘ:");
            String instr = scanner.next();
            try {
                guess = Integer.parseInt(instr);
            } catch (NumberFormatException e) {
                System.out.println("Niestety, ale: " + instr + " to " + UsefulConstants.NOT_A_NUMBER);
                scanner.nextLine();
                currentAttempt++;
                continue;
            }
            if (guess > secretNumber) {
                System.out.println(UsefulConstants.TO_MUCH);
            } else if (guess < secretNumber) {
                System.out.println(UsefulConstants.TO_LESS);
            } else {
                System.out.println(UsefulConstants.YES + "!");
                System.out.println(UsefulConstants.CONGRATULATIONS + " zgadles szukana liczbe");
                return;
            }
            currentAttempt++;
        }

    }

    private void printAttemptsProgress(String[] bar) {
        System.out.print("Twoje próby: [");
        for(String l:bar) System.out.print(l);
        System.out.println("]");
    }
}

