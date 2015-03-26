package day10.generics.boxexample;

public class Box<T> {

    private T item;

    public Box() {

    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
