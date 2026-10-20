package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return animals;
    }

    public List<T> getAnimalsSortedByName() {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getName))
                .toList();
    }

    public List<T> getAnimalsSortedByAge() {
        return animals.stream()
                .sorted(Comparator.comparing(Animal::getAge))
                .toList();
    }

    public List<T> findBySpecies(String species){
        return animals.stream()
                .filter(animal -> animal.getSpecies().equalsIgnoreCase(species))
                .toList();
    }

    public List<T> findAvailableAnimals(){
        return animals.stream()
                .filter(animal ->animal.getAdoptionStatus().equals(AdoptionStatus.AVAILABLE))
                .toList();
    }

    public boolean isAnimalInShelter(String id) {
        for (Animal animal : animals) {
            if (animal.getId().toString().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void markAsAdopted(String id){
        for (Animal animal : animals) {
            if (animal.getId().toString().equals(id)) {
                animal.markAsAdopted();
            }
        }
    }
}
