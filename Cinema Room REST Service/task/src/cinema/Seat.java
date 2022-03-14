package cinema;

public class Seat {
    private int row;
    private int column;
    private final int price;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = this.row < 5 ? 10 : 8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "seat[r:" + this.row + " c:" + this.column + " p:" + this.price + "]";
    }
}
