package model;

public class Wallet {
    private int id;
    private int coinsTotal;
    private int coinsAvailable;

    public Wallet(int id, int coinsTotal, int coinsAvailable) {
        this.id = id;
        this.coinsTotal = coinsTotal;
        this.coinsAvailable = coinsAvailable;
    }

    public boolean checkIfEnoughMoney(int value) {
        return false;
        // see if codecooler can afford artifact
    }

    public void useCoins(int value) {
        // delete coins from coins available when codecooler buys artifact
    }

    public void addCoins(int value) {
        // adds coins to Total and Available when codecooler achieves quest
    }
}
