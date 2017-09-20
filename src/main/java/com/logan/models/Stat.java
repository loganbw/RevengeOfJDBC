package com.logan.models;

public class Stat {
    private String name;
    private int wins;
    private int losses;

    public Stat(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
    }
}
