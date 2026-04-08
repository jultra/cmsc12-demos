package math;
public class FractionTester {
    public static void main(String[] args)
    {
        Fraction onehalf = new Fraction(1, 2);
        Fraction onethird = new Fraction(1, 3);

        Fraction sum = onehalf.add(onethird);
        
        System.out.println(onehalf);
        System.out.println( new Fraction(5) );
        System.out.println("Sum : " + sum);

        Fraction num1 = new Fraction(2, 3);
        Fraction num2 = new Fraction(4, 3);
        Fraction[] num = {new Fraction(2, 3), 
                            new Fraction(4, 3)};

        Fraction sum2 = num1.add(num2);
        Fraction sum3 = num[0].add(num[1]);

        System.out.println("Sum 2 : " + sum2);
        System.out.println("Sum 3 : " + sum3);

        int[] n;
        Fraction[] f;

        n = new int[2];
        f = new Fraction[5];

        System.out.println("n : " + n + ", f: " + f);
        System.out.println("n[0]=" + n[0]);
        System.out.println("f[0]=" + f[0]);
        //System.out.println("f[0] + num1 = " + f[0].add(num1));

        f[0] = new Fraction(3, 2);
        System.out.println("f[0] + num1 = " + f[0].add(num1));

        Fraction[][] ff;

        ff = new Fraction[2][]; // ff[0] ok, ff[1] ok, ff[2] not ok

        ff[0] = new Fraction[2];
        ff[1] = new Fraction[4];

        ff[0][0] = new Fraction(1, 2);
        ff[1][3] = new Fraction(2,3);

        System.out.println("ff[0][0] + ff[1][3] = " + ff[0][0].add(ff[1][3]));

        System.out.println("length of ff[0]=" + ff[0].length);
        System.out.println("length of ff[1]=" + ff[1].length);
        System.out.println("length of ff=" + ff.length);

        try{
            Fraction fsum = ff[1][0].add(num1);
            System.out.println("1/0 = " + 1/0);
        }catch(ArithmeticException e){
            System.out.println("Aah, an arithmetic exception occured!");
        }catch(NullPointerException e){
            //ff[1][0] = new Fraction(3,4);
            try{
                ff[1][0].add(num1);
            }catch(Exception e1){
                e1.printStackTrace();
            }
            
            System.out.println("Aah, null pointer exception.");
        }
        

        System.out.println("End of the program.");


        Fraction fff = new Fraction(1,0);

        System.out.println("fff = " + fff);

    }
} 
