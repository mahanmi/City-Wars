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

    public Card(int id, String name, int power, int duration, int damage, int upgradeLevel, int upgradeCost) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.duration = duration;
        this.damage = damage;
        this.level = 1;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
    }

    public Card(String name, int power, int duration, int damage, int upgradeLevel, int upgradeCost) {
        this.name = name;
        this.power = power;
        this.duration = duration;
        this.damage = damage;
        this.level = 1;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCost;
    }

    public Card(Card card,int level){
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

    public int isSpecial() {
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

    public void upgrade(int balance) {
        if (balance >= upgradeCost * Math.pow(1.25, level - 1)) {
            level++;
            balance -= upgradeCost * Math.pow(1.25, level - 1);
        } else {
            System.out.println("Not enough balance to upgrade card");
        }
    }

    @Override
    public String toString() {
        return name + " (Level " + level + ")" + " Power: " + power + " Duration: " + duration + " Damage: " + damage;
    }
}
