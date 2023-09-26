import java.util.Arrays;
import java.util.Comparator;

public class Pakudex {
    // The following variables are used to initialize the attributes of a Pakudex object.
    private int capacityOfPakudex = 0;
    private int numberOfPakuriInPakudex;
    private int arrayIndex = 0;
    private String[] pakuriStringArray;
    private Pakuri[] pakuriArray;

    // When invoked, the Pakudex constructor will create a Pakuri array with a specified size.
    public Pakudex() {
        capacityOfPakudex = 20;
        numberOfPakuriInPakudex = 0;
        this.pakuriStringArray = new String[capacityOfPakudex];
        this.pakuriArray = new Pakuri[capacityOfPakudex];
    }
    public Pakudex(int capacity) {
        capacityOfPakudex = capacity;
        numberOfPakuriInPakudex = 0;
        this.pakuriStringArray = new String[capacityOfPakudex];
        this.pakuriArray = new Pakuri[capacityOfPakudex];
    }
    // The getSize getter, retrieves the number of Pakuri currently in the Pakudex and returns that value.
    public int getSize() {
        return numberOfPakuriInPakudex;
    }
    // The getCapacity getter, retrieves the max number of Pakuri that can fit in the Pakudex and returns that value.
    public int getCapacity() {
        return capacityOfPakudex;
    }
    // The getSpeciesArray getter, retrieves the string array that was initialized when the Pakudex object was created.
    // If there are no Pakuri in the Pakudex, this method will return null.
    // If there are Pakuri in the Pakudex, this method will add the names of the Pakuri in the Pakudex to the string array and then once complete will return the string array.
    public String[] getSpeciesArray() {
        if (numberOfPakuriInPakudex == 0) {
            return null;
        }
        pakuriStringArray = new String[numberOfPakuriInPakudex];
        for (int i = 0; i < numberOfPakuriInPakudex; i++) {
            if (pakuriArray[0] == null) {
                return null;
            }
            if (pakuriArray[i] == null) {
                continue;
            }
            pakuriStringArray[i] = pakuriArray[i].getSpecies();
        }
        return pakuriStringArray;
    }
    // The getStats getter, retrieves stats information about a given Pakuri based off of it's name.
    // If there are no Pakuri in the Pakudex, this method will return null if invoked.
    // If there are Pakuri in the Pakudex, this method will add the stats information to a int array called statsArray and then will return that array.
    public int[] getStats(String species) {
        if (numberOfPakuriInPakudex == 0) {
            return null;
        }
        int[] statsArray = new int[3];
        int temp = 0;
        for (int i = 0; i < numberOfPakuriInPakudex; i++) {
            if (pakuriArray[0] == null) {
                return null;
            }
            if (pakuriArray[i] == null) {
                continue;
            }
            if (pakuriArray[i].getSpecies().equals(species)) {
                temp = i;
                statsArray = new int[] {pakuriArray[temp].getAttack(), pakuriArray[temp].getDefense(), pakuriArray[temp].getSpeed()};
                break;
            }
        }
        return statsArray;
    }
    // The sortPakuri method will sort the Pakuri in the Pakudex based off of their species name.
    public void sortPakuri() {
        Arrays.sort(pakuriArray, Comparator.nullsLast(Comparator.naturalOrder()));
    }
    // The addPakuri method takes in a string species obtained from the user and adds to the Pakuri array in the Pakudex.
    // If the pakuriArray at a given spot already contains the Pakuri species or if the Pakuri array is full, this method will return a false boolean indicated that the addition of the Pakuri was unsuccessful.
    // If no errors occur, this method will add the given Pakuri name to the Pakuri array by creating a new Pakuri at the next available spot in the Pakudex and will return true indicating the the addition of the Pakuri was successful.
    public boolean addPakuri(String species) {

        for (int i = 0; i < capacityOfPakudex; i++) {
            if (pakuriArray[i] == null) {
                continue;
            }
            if (pakuriArray[i].getSpecies().equals(species)) {
                return false;
            }
            if (numberOfPakuriInPakudex == capacityOfPakudex) {
                return false;
            }
        }
        pakuriStringArray[arrayIndex] = species;
        pakuriArray[arrayIndex] = new Pakuri(species);
        numberOfPakuriInPakudex++;
        arrayIndex++;
        return true;
    }
    // The evolveSpecies method takes in a string species obtained from the user and evolved the Pakuri with the same name.
    // If no such Pakuri with the given name exists in the Pakuri array, this method will return a false boolean indicated that the evolution was unsuccessful.
    // If the evolution was successful the method will return a true boolean.
    public boolean evolveSpecies(String species) {
        boolean successfulEvolve = false;
        for (int i = 0; i < capacityOfPakudex; i++) {
            if (pakuriArray[i] == null) {
                continue;
            }
            if (pakuriArray[i].getSpecies().equals(species)) {
                pakuriArray[i].evolve();
                successfulEvolve = true;
                break;
            }
        }
        return successfulEvolve;
    }
}