package kz.lamoda.lamoda.models;

public class Item {
    private Dress dress;
    private int quantity;

    public Item() {
    }

    public Item(Dress dress, int quantity) {
        this.dress = dress;
        this.quantity = quantity;
    }

    public Dress getDress() {
        return dress;
    }

    public void setDress(Dress dress) {
        this.dress = dress;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
