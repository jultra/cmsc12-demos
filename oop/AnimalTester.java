package oop;

import oop.animals.Animal;
import oop.animals.Baby;
import oop.animals.Cat;
import oop.animals.Dog;
import oop.animals.Person;
import oop.behaviors.NoisyThings;
import oop.others.Car;

public class AnimalTester {
    public static void main(String[] args) {
        // Animal dog = new Animal(2, "Puppy");

        // Dog anotherDog = new Dog(2, "Another dog!");
        // Baby baby = new Baby("Darren");
        // System.out.println(dog.makeNoise());
        // System.out.println(anotherDog.makeNoise());
        // System.out.println(baby.makeNoise());
        // anotherDog.speak();

        Animal animals[] = new Animal[5];
        animals[0] = new Animal(100, "Generic Animal");
        animals[1] = new Cat("Elsa", 6);
        animals[2] = new Dog(5,"Buddy");
        animals[3] = new Person("Juan", 23);
        animals[4] = new Baby("Baby");

        Person babyPerson = (Person) animals[4];
        System.out.println(babyPerson);

        // Cat sameCat = (Cat)animals[1];
        // // Cat anotherCat = (Cat) animals[2];
        
        // System.out.println(sameCat);
        // // System.out.println(anotherCat);

        // for(Animal hayop: animals){
        //     System.out.println(hayop.makeNoise());
        // }

        NoisyThings noisythings[] = new NoisyThings[5];
        noisythings[0] = new Car();
        noisythings[1] = new Cat("Elsa", 6);
        noisythings[2] = new Dog(5,"Buddy");
        noisythings[3] = new Person("Juan", 23);
        noisythings[4] = new Baby("Baby");
        
        for(NoisyThings hayop: noisythings){
            System.out.println(hayop.makeSound());
        }


        Cat furryCat = new Cat("furry cat", 10){

            @Override
            public String makeSound(){
                return "fur fur fur!";
            }

            @Override
            public String toString(){
                return "Furry Cat: I'm a furry cat!";
            }
        };

        System.out.println(furryCat);
        System.out.println(furryCat.makeSound());

        NoisyThings horn = new NoisyThings() {

            @Override
            public String makeSound() {
                return "Poot poot!";
            }
            
        };

        System.out.println(horn.makeSound());

    }
}
