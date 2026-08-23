package com.example;

import java.util.List;

public class AlexLion extends Lion {
    private static final String DEFAULT_SEX = "Самец";
    private static final List<String> FRIENDS = List.of("Марти", "Глория", "Мелман");
    private static final String PLACE_OF_LIVING = "Нью-Йоркский зоопарк";

    public AlexLion(Feline feline) throws Exception {
        super(feline, DEFAULT_SEX);
    }

    public List<String> getFriends() {
        return FRIENDS;
    }

    public String getPlaceOfLiving() {
        return PLACE_OF_LIVING;
    }

    @Override
    public int getKittens() {
        return 0; // У Алекса нет львят
    }
}