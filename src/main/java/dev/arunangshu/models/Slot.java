package dev.arunangshu.models;

public class Slot {

    private Player player;

    public Slot() {}

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null;
    }

    public boolean isOccupied() {
        return player != null;
    }

    public char getSymbol() {
        return player != null ? player.symbol() : '-';
    }
}
