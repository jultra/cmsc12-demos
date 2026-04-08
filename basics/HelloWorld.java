package basics;

import java.util.Scanner;

import oop.animals.Dog;

class HelloWorld {

    public static void main(String args[])
    {
       System.out.printf("Hello world!, Hi %s!\n", "CMSC 12"); 
       System.out.println("This is a line.".toUpperCase());
       
       int num = 0;

       System.out.println("This is a number: " + num);

       for (int i = 0; i < 5; i++)
       {
        System.out.println("" + i);
       }

       Dog harry = new Dog("Juan");

       harry.bark(10);
       harry.bark();

       int[] nums = {1, 2, 3, 4, 5};

       for (int i = 0; i < 5; i++){
        System.out.println(nums[i]);
       }

       for (int i : nums){
        System.out.println(i);
       }

       Scanner console = new Scanner(System.in);
    }

}