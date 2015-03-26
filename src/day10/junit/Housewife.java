package day10.junit;

public class Housewife {

    private String name;
    private int age;

    public Housewife() {

    }

    public void feed (Cat [] cats) {
        for (Cat c : cats) {
            if (c.isHungry()) {
                c.setIsHungry(Boolean.FALSE);
            } else {
                throw new CatNotHungryException(c.getName() + " is not hungry");
            }
        }
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
