import java.util.Random;

public class Player {
    String name;
    int health;
    int strength;
    int attack;
    Random rand;

    Player(String name, int health, int strength, int attack) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.attack = attack;
        this.rand = new Random();
    }

    int rollDice() {
        return rand.nextInt(6) + 1;
    }

    void takeDamage(int damage) {
        this.health -= damage;
    }

    boolean isAlive() {
        return this.health > 0;
    }

    String displayStatistics() {
        return String.format("%s:\nHealth - %d\nAttack - %d\nStrength - %d", this.name, this.health, this.attack, this.strength);
    }
}
