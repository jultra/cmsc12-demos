package oop.animals;

import oop.behaviors.NoisyThings;

public class Cat extends Animal implements NoisyThings{

    public Cat(String name, int age){
        super(age, name);
    }

    @Override
    public String makeNoise(){
        return "I'm a cat! Meow meow!";
    }

     public String makeSound(){
        return makeNoise();
    }
    
    public String toString(){
        return "Cat: " + getName() + " age: " + getAge();
    }
}
