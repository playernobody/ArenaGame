import random

class Player:
    def __init__(self, name, health, strength, attack):
        self.name = name
        self.health = health
        self.strength = strength
        self.attack = attack

    def roll_dice(self):
        return random.randint(1, 6)

    def take_damage(self, damage):
        self.health -= damage

    def is_alive(self):
        return self.health > 0

    def display_statistics(self):
        return f"{self.name}:\nHealth - {self.health}\nAttack - {self.attack}\nStrength - {self.strength}"

class Arena:
    def __init__(self):
        self.playerA = None
        self.playerB = None

    def validate_input(self, value):
        try:
            return int(value)
        except ValueError:
            print("Invalid input! Please enter a valid integer.")
            return None

    def set_players(self):
        print("Enter the details of Fighter A:")
        nameA = input("Name: ")
        healthA, attackA, strengthA = map(int, input("Health Attack Strength (space-separated): ").split())
        if None in (healthA, attackA, strengthA):
            return
        self.playerA = Player(nameA, healthA, strengthA, attackA)
        print(f"Player A: {self.playerA.name}, Health: {self.playerA.health}, Attack: {self.playerA.attack}, Strength: {self.playerA.strength}")

        print("\nEnter the details of Fighter B:")
        nameB = input("Name: ")
        healthB, attackB, strengthB = map(int, input("Health Attack Strength (space-separated): ").split())
        if None in (healthB, attackB, strengthB):
            return
        self.playerB = Player(nameB, healthB, strengthB, attackB)
        print(f"Player B: {self.playerB.name}, Health: {self.playerB.health}, Attack: {self.playerB.attack}, Strength: {self.playerB.strength}")



    def start_battle(self):
        print(f"{self.playerA.name} vs {self.playerB.name} - Let the battle begin!")
        round_number = 1

        while self.playerA.is_alive() and self.playerB.is_alive():
            print(f"\n*** Round {round_number}: Begins ***")
            self.battle_round()
            round_number += 1

        self.display_winner()

    def battle_round(self):
        attack_roll_A = self.playerA.roll_dice()
        defense_roll_B = self.playerB.roll_dice()

        attack_damage_A = attack_roll_A * self.playerA.attack
        defense_damage_B = defense_roll_B * self.playerB.strength

        actual_damage_B = max(attack_damage_A - defense_damage_B, 0)
        self.playerB.take_damage(actual_damage_B)

        print(f"{self.playerA.name} attacks with {attack_roll_A}, {self.playerB.name} defends with {defense_roll_B}.")
        print(f"Damage dealt: {actual_damage_B}. {self.playerB.name}'s health: {self.playerB.health}")

        # Switch roles for next turn
        self.playerA, self.playerB = self.playerB, self.playerA

    def display_winner(self):
        winner = self.playerA if self.playerA.is_alive() else self.playerB
        print(f"\n{winner.name} wins the battle!")

        print("\nWinner's Statistics:")
        print(winner.display_statistics())


arena = Arena()
arena.set_players()
arena.start_battle()
