package oop.animals;
public class Animal{
  private int age;
  private String name;

  public Animal(int age, String name){
    this.age = age;
    this.name = name;
  }
  public int getAge(){
    return this.age;
  }
  public String getName(){
     return this.name;
  }

  public void setAge(int age){
    this.age = age;
  }
  public void setName(String name){
    this.name = name;
  }

  public String makeNoise(){
    return "Grr grr grr!";
  }
  
  public String toString(){
    return "Animal: " + getName() 
      + ", " + getAge();
  }
}
