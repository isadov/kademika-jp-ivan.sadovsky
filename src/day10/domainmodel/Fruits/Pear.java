package day10.domainmodel.Fruits;



public class Pear extends Fruit {

    private boolean isFreshness = true;

    public Pear() {
    }

    public boolean isFreshness() {
        return isFreshness;
    }

    public void setFreshness(boolean isFreshness) {
        this.isFreshness = isFreshness;
    }
}
