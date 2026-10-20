package org.example.model;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public final class AnimalId {
    private final String value;
    public AnimalId(){
        this.value = UUID.randomUUID().toString();
    }

    @Override
    public String toString(){
        return value;
    }
}
