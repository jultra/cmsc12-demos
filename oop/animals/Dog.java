package oop.animals;

import oop.behaviors.NoisyThings;

public class Dog extends Animal implements NoisyThings{

    public Dog(int age, String name){
        super(age, name);
    }
    
    public Dog(String name) {
        this(0, name);
    }

    @Override
    public String makeNoise(){
        return "I'm a dog! Aww aww!";
    }

    public String makeSound(){
        return makeNoise();
    }

    public void speak(){
        System.out.println("Aww! aww!");
    }

    public String toString(){
        return "Dog: " + getName() + " age: " + getAge();
    }

    public void bark(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bark'");
    }

    public void bark() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bark'");
    }
}
