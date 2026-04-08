package oop.animals;

public class Baby extends Person{

    public Baby(String name){
        super(name, 1);
    }

    @Override
    public String makeNoise(){
        return "aah nyaaaah!";
    }

    public String makeSound(){
        return makeNoise();
    }

    public String toString(){
        return "Baby: " + getName() + " age: " + getAge();
    }
    
}
