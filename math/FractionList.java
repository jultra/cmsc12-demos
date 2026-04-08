package math;

import java.util.ArrayList;
import java.util.Collections;

public class FractionList {
    public static void main(String[] args) {
        ArrayList<Fraction> fractions = new ArrayList<Fraction>();

        fractions.add(new Fraction(1, 2));
        fractions.add(new Fraction(2, 4));
        fractions.add(new Fraction(3, 4));
        fractions.add(new Fraction(6, 7));

        for(Fraction num: fractions){
            System.out.println(num);   
        }
        
        System.out.println(fractions.get(0).equals(new Fraction(1,2)));
        fractions.remove(new Fraction(1,2));
        System.out.println(fractions.contains(new Fraction(1,2)));

        Collections.sort(fractions);
         for(Fraction num: fractions){
            System.out.println(num);   
        }

        Collections.reverse(fractions);
         for(Fraction num: fractions){
            System.out.println(num);   
        }

    }
}
