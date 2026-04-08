package oop.animals;
import oop.behaviors.NoisyThings;

public class Person extends Animal implements NoisyThings{

    public Person(String name, int age){
        super(age, name);
    }

    @Override
    public String makeNoise(){
        return "Hello";
    }

    public String makeSound(){
        return makeNoise();
    }
    
    public String toString(){
        return "Person: " + getName() + " age: " + getAge();
    }
}
