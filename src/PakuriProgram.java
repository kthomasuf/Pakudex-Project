import java.util.InputMismatchException;
import java.util.Scanner;

public class PakuriProgram {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxCapacity;

        // The following statements welcome to the user to the pakudex and then prompts them with the decision to choose how large the pakudex should be.
        // To ensure that the user inputs a usable value, a try-catch block is used to catch any errors the user might make and then reprompt the user to choose a usable value.
        System.out.println("Welcome to Pakudex: Tracker Extraordinaire!");
        while (true) {
            try {
                System.out.print("Enter max capacity of the Pakudex: ");
                maxCapacity = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Please enter a valid size. ");
                scanner.next();
                continue;
            }
            if (maxCapacity > 0) {
                break;
            }
            else {
                System.out.println("Please enter a valid size.");
            }
        }
        System.out.println("The Pakudex can hold " + maxCapacity +  " species of Pakuri.");
        System.out.println();

        // The following statement initializes a new pakudex with the given size from the user.
        Pakudex pakudex = new Pakudex(maxCapacity);

        // The following statements initialize the variables used throughout the main menu of the pakudex.
        String menuInput;
        String pakuriInput;
        boolean pakudexEmpty = true;

        /*
        The following while loop runs the main portion of the pakudex.
        Here the main manu of the program is printed each time the while loop iterates.
        The user can interact with the different options presented in the pakudex by inputting a value between 1 and 6 when prompted by the program.
        If the user enters anything other than a value between 1-6 when prompted, the program will alert the user of an "Unrecognized user input" and then allow the user to enter in a new value.
        */
        while (true) {
            System.out.println("Pakudex Main Menu");
            System.out.println("-----------------");
            System.out.println("1. List Pakuri");
            System.out.println("2. Show Pakuri");
            System.out.println("3. Add Pakuri");
            System.out.println("4. Evolve Pakuri");
            System.out.println("5. Sort Pakuri");
            System.out.println("6. Exit");
            System.out.println();
            System.out.print("What would you like to do? ");
            menuInput = scanner.next();

            // If the user inputs 1 when prompted; the Pakuri in the Pakudex will be listed in the order they were inputted.
            // If no Pakuri exist in the Pakudex yet, the program will alert the user that the Pakudex is empty.
            // If the user sorts the Pakuri using menu option #5, this option will print out the Pakuri in the Pakudex in lexicographical order.
            if (menuInput.equals("1")) {
                if (!pakudexEmpty) {
                    System.out.println("Pakuri In Pakudex: ");
                    int index = 1;
                    String[] speciesArray = pakudex.getSpeciesArray();
                    for (int i = 0; i < speciesArray.length; i++) {
                        if (speciesArray[i] == null) {
                            continue;
                        }
                        System.out.println((index++) + ". " + speciesArray[i]);
                    }
                    System.out.println();
                }
                else {
                    System.out.println("No Pakuri in Pakudex yet!");
                    System.out.println();
                }
            }
            // If the user inputs 2 when prompted; the program will ask the user to input the name of the Pakuri the user wants displayed.
            // If the inputted Pakuri does not exist the in the Pakudex, the program will alert the user that there is "No such Pakuri!".
            // If the inputted Pakuri does exist in the Pakudex, the program will utilize the data in Pakudex to display information relating to the species attack, defense and speed.
            else if (menuInput.equals("2")) {
                System.out.print("Enter the name of the species to display: ");
                boolean showError = false;
                pakuriInput = scanner.next();
                String[] pakuriChecker = pakudex.getSpeciesArray();

                if (pakuriChecker == null) {
                    System.out.println("Error: No such Pakuri!");
                    System.out.println();
                }
                else {
                    for (int i = 0; i < pakuriChecker.length; i++) {
                        if (pakuriChecker[i] == null) {
                            continue;
                        }
                        if (pakuriChecker[i].equals(pakuriInput)) {
                            showError = true;
                        }
                    }
                    if (showError) {
                        int[] pakuriStatsArray = pakudex.getStats(pakuriInput);
                        System.out.println("Species: " + pakuriInput);
                        System.out.println("Attack: " + pakuriStatsArray[0]);
                        System.out.println("Defense: " + pakuriStatsArray[1]);
                        System.out.println("Speed: " + pakuriStatsArray[2]);
                        System.out.println();
                    } else {
                        System.out.println("Error: No such Pakuri!");
                        System.out.println();
                    }
                }
            }
            // If the user inputs 3 when prompted; the program will first check if the Pakudex is full by comparing the number of Pakuri in the Pakudex to the Pakudex's max capacity.
            // If the Pakudex is full, the program will state that there is an "Error: Pakudex is full!".
            // If the Pakudex has space, the program will ask the user to input the name of the species and then will add a new Pakuri object to the Pakudex with the name the user gave it.
            // If the user enters the name of a species already in the Pakudex, the program will state that the inputted Pakuri already exists in the Pakudex.
            // A successful addition to the Pakudex will yield a statement stating that the addition of the species was successful.
            else if (menuInput.equals("3")) {
                if (pakudex.getSize() == maxCapacity) {
                    System.out.println("Error: Pakudex is full!");
                    System.out.println();
                }
                else {
                    boolean check;
                    System.out.print("Enter the name of the species to add: ");
                    pakuriInput = scanner.next();
                    check = pakudex.addPakuri(pakuriInput);
                    pakudexEmpty = false;

                    if (!check) {
                        System.out.println("Error: Pakudex already contains this species!");
                        System.out.println();
                    }
                    else {
                        System.out.println("Pakuri species " + pakuriInput + " successfully added!");
                        System.out.println();
                    }
                }
            }
            // If the user inputs 4 when prompted; the program will ask the user to enter the name of the Pakuri the user wants to evolve.
            // If the user enters the name of a Pakuri that is not in the Pakudex, the program will alert the user that there is "No such Pakuri!".
            // If the user enters the name of a Pakuri that is in the Pakudex, the program will evolve that Pakuri using the evolve method from the Pakudex class.
            else if (menuInput.equals("4")) {
                System.out.print("Enter the name of the species to evolve: ");
                pakuriInput = scanner.next();
                boolean successfulEvolve = pakudex.evolveSpecies(pakuriInput);
                if (successfulEvolve) {
                    System.out.println(pakuriInput + " has evolved!");
                    System.out.println();
                }
                else {
                    System.out.println("Error: No such Pakuri!");
                    System.out.println();
                }
            }
            // If the user inputs 5 when prompted; the program will invoke the sortPakuri function from the Pakudex class.
            // This function will sort the Pakuri in the Pakudex by their lexicographical order.
            // Once completed the program will state that the "Pakuri have been sorted!".
            else if (menuInput.equals("5")) {
                pakudex.sortPakuri();
                System.out.println("Pakuri have been sorted!");
                System.out.println();
            }
            // If the user inputs 6 when prompted; the program will quit and will state a thank you message.
            else if (menuInput.equals("6")) {
                System.out.println("Thanks for using Pakudex! Bye!");
                System.exit(0);
            }
            // As stated previously, if the user enters anything other than a value between 1-6 when prompted; the program will alert the user of an "Unrecognized user input" and then allow the user to enter in a new value.
            else {
                System.out.println("Unrecognized menu selection!");
                System.out.println();
            }
        }
    }
}