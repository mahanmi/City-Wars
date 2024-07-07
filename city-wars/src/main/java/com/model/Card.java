package com.model;

public class Card {
    private final String name;
    private int id;
    private int power;
    private int duration;
    private int damage;
    private int level;
    private final int upgradeLevel;
    private final int upgradeCost;
    private int character;

    public Card(int id, String name, int power, int duration, int damage, int upgradeLevel, int upgradeCost, int character) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.duration = duration;
        this.damage = damage;
        this.level = 1;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.character = character;
    }

    public Card(String name, int power, int duration, int damage, int upgradeLevel, int upgradeCost, int character) {
        this.name = name;
        this.power = power;
        this.duration = duration;
        this.damage = damage;
        this.level = 1;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
        this.character = character;
    }

    public Card(Card card, int level) {
        this.id = card.getId();
        this.name = card.getName();
        this.power = card.getPower();
        this.duration = card.getDuration();
        this.damage = card.getDamage();
        this.level = level;
        this.upgradeLevel = card.getUpgradeLevel();
        this.upgradeCost = card.getUpgradeCost();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int isSpell() {
        return 0;
    }

    public int getPower() {
        return power;
    }

    public int getDuration() {
        return duration;
    }

    public int getDamage() {
        return damage;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }
    
    public int getCharacter() {
        return character;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }   

    @Override
    public String toString() {
        return name + " (Level " + level + ")" + " Power: " + power + " Duration: " + duration + " Damage: " + damage;
    }

    public String getCardInfo() {
        return "Name: " + name + "\n" +
                "Power: " + power + "\n" +
                "Duration: " + duration + "\n" +
                "Damage: " + damage + "\n" +
                "Level: " + level + "\n" +
                "Upgrade Level: " + upgradeLevel + "\n" +
                "Upgrade Cost: " + upgradeCost + "\n";
    }
}
