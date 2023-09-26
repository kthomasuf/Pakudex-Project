public class Pakuri implements Comparable<Pakuri> {

    // The following variables are used to initialize the attributes of a Pakuri object.
    private String species;
    private int attack;
    private int defense;
    private int speed;

    // The following constructor only activates given a String species which comes from the user.
    // When invoked the constructor will initialize the attributes of the Pakuri to be specific values based off of the length of the Pakuri name.
    public Pakuri(String species) {
        this.species = species;
        this.attack = (species.length() * 7) + 9;
        this.defense = (species.length() * 5) + 17;
        this.speed = (species.length() * 6) + 13;

    }
    // The following getters, get specific attributes of a Pakuri object and return them when prompted.
    public String getSpecies() {
        return this.species;
    }
    public int getAttack() {
        return this.attack;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getSpeed() {
        return this.speed;
    }
    // The setAttack setter, takes in a value and sets the given Pakuri's attack to be the given value.
    public void setAttack(int newAttack) {
        this.attack = newAttack;
    }
    // The evolve method increases the stats related to the attributes of the Pakuri.
    public void evolve() {
        this.attack = this.attack * 2;
        this.defense = this.defense * 4;
        this.speed = this.speed * 3;
    }
    @Override
    public int compareTo(Pakuri species) {
        return this.species.compareTo(species.species);
    }
}
