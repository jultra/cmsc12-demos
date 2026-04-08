package math;
public class FractionModifier {
    public static void main(String[] args) {
        Fraction num1 = new Fraction(1, 2);

        Fraction num2 = new Fraction("3/4");

        System.out.println(num2);

        Fraction num3 = new Fraction(3,4);
        Fraction sum = num3.add(num1);

        System.out.println(num3);
        System.out.println(sum);

        //Fraction.ONEHALF = new Fraction(3,4);

        //System.out.println(Fraction.ONEHALF);

        System.out.println(Fraction.counter);

        Fraction num4 = Fraction.rand();
        Fraction num5 = Fraction.rand();

        System.out.println("num4 " + num4 + ", num5 " + num5);

        Fraction num6 = new Fraction(25);

        Fraction num7 = Fraction.fromString("3/4");

    }    
}
