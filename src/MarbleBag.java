public class MarbleBag {
    private int numOfMarbles;

    public MarbleBag(int numberOfMarbles) {
        this.numOfMarbles = numberOfMarbles;
    }

    public int getNumberOfMarbles() {
        return this.numOfMarbles;
    }

    //EFFECTS: produce true if there are still marbles in bag
    public boolean isThereNoMoreMarbles() {
        return this.numOfMarbles <= 0;
    }

    //REQUIRES: number > 0
    //MODIFIES: this
    //EFFECTS: remove specified marbles in bag or if number > marbles in bag, set marbles in bag to 0.
    //          Return remaining number of marbles in bag.
    public int removeMarbles(int number) {
        if (number >= this.numOfMarbles) {
            this.numOfMarbles = 0;
        } else {
            this.numOfMarbles = this.numOfMarbles - number;
        }
        return this.numOfMarbles;
    }
}
