package math;
public class FractionEqualityTest {
    
    public static void main(String[] args) {
        Fraction num1, num2, num3;

        num1 = new Fraction(1, 2);
        num2 = new Fraction(1, 2);
        num3 = num1;

        // if(num1 == num2){
        //     System.out.println("Yeh! num1 and num2 are equal!");
        // }else{
        //     System.out.println("No! num1 and num2 are not equal!");
        // }

        // if(num1 == num3){
        //     System.out.println("Yeh! num1 and num3 are equal!");
        // }

        if(num1.equals(num2)){
            System.out.println("Yeh! num1 and num2 are equal!");
        }else{
            System.out.println("No! num1 and num2 are not equal!");
        }


        // String name1 = "Juan";
        // String name2 = "Juan";
        // String name3 = "juan";

        // if(name1.equals(name2)){
        //     System.out.println("name1 = name2 semantically speaking.");
        // }

        // if(name1.equals(name3)){
        //     System.out.println("is equals case-insensitive? yes!");
        // }

        String sentence = "This is a very long sentence";


        System.out.println("Is there a letter 'l' in the string? " + sentence.indexOf('l'));
        System.out.println("Is there a letter 'x' in the string? " + sentence.indexOf('x'));
        System.out.println("Is there a'the' in the string? " + sentence.indexOf("the"));
        System.out.println("Is there a'This' in the string? " + sentence.indexOf("This"));

        System.out.println("contents of sentence starting at index 5 up to 10 is -- " + sentence.substring(5, 10) + " --");


        Fraction fracString = new Fraction("3/4");
        Fraction fracString2 = new Fraction("29/100");

        System.out.println(fracString);
        System.out.println(fracString2);
        System.out.println(fracString.add(fracString2));
        System.out.println(fracString.add(25));
    }
}
