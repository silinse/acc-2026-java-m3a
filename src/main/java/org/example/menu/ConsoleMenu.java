package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        String input;

        do {
            printMenu();
            System.out.print("Enter a number from the menu: ");
            input = scanner.next();
            switch (input) {
                case "1": {
                    System.out.print("Enter animal's name: ");
                    String name = scanner.next().trim();
                    if (name.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }
                    System.out.print("Enter animal's age: ");
                    int age = scanner.nextInt();
                    if (age < 0) {
                        System.out.println("Age cannot be negative.");
                        break;
                    }
                    System.out.print("Enter animal's (1 - Dog, 2 - Cat, 3 - Bird, 4 - Rabbit): ");
                    String species = scanner.next();

                    Animal animal = switch (species) {
                        case "1" -> new Dog(new AnimalId(), name, age);
                        case "2" -> new Cat(new AnimalId(), name, age);
                        case "3" -> new Bird(new AnimalId(), name, age);
                        case "4" -> new Rabbit(new AnimalId(), name, age);
                        default -> null;
                    };

                    if (animal != null) {
                        shelter.addAnimal(animal);
                        System.out.print(animal.getSpecies() + " added.\n");
                    } else {
                        System.out.print("Invalid species.\n");
                    }
                    break;
                }
                case "2": {
                    System.out.print("""
                            List animals:
                            1. Default order
                            2. Sort boy age
                            3. Sort by name
                            Enter your choice:\s""");
                    input = scanner.next();
                    List<Animal> animals = switch (input) {
                        case "2" -> shelter.getAnimalsSortedByAge();
                        case "3" -> shelter.getAnimalsSortedByName();
                        default -> shelter.getAllAnimals();
                    };
                    System.out.println("Animals in the shelter:");
                    for (Animal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;
                }
                case "3": {
                    System.out.print("Enter an animal species (Bird, Cat, Dog, Rabbit): ");
                    input = scanner.next();
                    if (!List.of("bird", "cat", "dog", "rabbit").contains(input.toLowerCase())) {
                        System.out.print("Invalid species.\n");
                        break;
                    }
                    for (Animal animal : shelter.findBySpecies(input)) {
                        System.out.println(animal.toString());
                    }
                    break;
                }
                case "4": {
                    System.out.println("Available animals:");
                    for (Animal animal : shelter.findAvailableAnimals()) {
                        System.out.println(animal.toString());
                    }
                    break;
                }
                case "5": {
                    System.out.println("Enter the ID of the animal you want to mark as adopted: ");
                    input = scanner.next().trim();
                    if (input.isEmpty()) {
                        System.out.println("Name cannot be empty.");
                        break;
                    }
                    if (shelter.isAnimalInShelter(input)) {
                        shelter.markAsAdopted(input);
                        System.out.println("Marked animal for adoption.");
                    } else {
                        System.out.println("Animal with that ID does not exist.");
                    }
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.out.print("Invalid option. Please try again.\n");
                }
            }
            System.out.println();


        } while (!input.equals("0"));
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
    }
}
