import java.util.Scanner;

public class Arena {
    Player playerA;
    Player playerB;
    Scanner scanner;

    Arena() {
        scanner = new Scanner(System.in);
    }

    void setPlayers() {
        System.out.println("Enter the details of Fighter A:");
        this.playerA = createPlayer();
        System.out.println("\nEnter the details of Fighter B:");
        this.playerB = createPlayer();
    }

    Player createPlayer() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        int health = validateInput("Health: ");
        int attack = validateInput("Attack: ");
        int strength = validateInput("Strength: ");
        // Consume the newline character
        scanner.nextLine();
        return new Player(name, health, strength, attack);
    }

    int validateInput(String prompt) {
        int value;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a valid integer.");
                scanner.next();
            }
            value = scanner.nextInt();
            if (value <= 0)
                System.out.println("Invalid input! Please enter a positive integer.");
        } while (value <= 0);
        return value;
    }

    void startBattle() {
        System.out.printf("%s vs %s - Let the battle begin!\n", this.playerA.name, this.playerB.name);
        int roundNumber = 1;

        while (this.playerA.isAlive() && this.playerB.isAlive()) {
            System.out.printf("\n*** Round %d: Begins ***\n", roundNumber);
            displayPlayerStats(); // Display player statistics before each round
            battleRound();
            roundNumber++;
        }

        displayWinner();
        scanner.close();
    }

    void displayPlayerStats() {
        System.out.println(playerA.displayStatistics());
        System.out.println("\n");
        System.out.println(playerB.displayStatistics());
        System.out.println();
    }

    void battleRound() {
        int attackRollA = this.playerA.rollDice();
        int defenseRollB = this.playerB.rollDice();

        int attackDamageA = attackRollA * this.playerA.attack;
        int defenseDamageB = defenseRollB * this.playerB.strength;

        int actualDamageB = Math.max(attackDamageA - defenseDamageB, 0);
        this.playerB.takeDamage(actualDamageB);

        System.out.printf("%s attacks with %d, %s defends with %d.\n", this.playerA.name, attackRollA, this.playerB.name, defenseRollB);
        System.out.printf("Damage dealt: %d. %s's health: %d\n", actualDamageB, this.playerB.name, this.playerB.health);

        // Switch roles for next turn
        Player temp = this.playerA;
        this.playerA = this.playerB;
        this.playerB = temp;
    }

    void displayWinner() {
        Player winner = this.playerA.isAlive() ? this.playerA : this.playerB;
        System.out.printf("\n%s wins the battle!\n", winner.name);

        System.out.println("\nWinner's Statistics:");
        System.out.println(winner.displayStatistics());
    }
}
