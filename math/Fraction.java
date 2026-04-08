package math;
public class Fraction implements Comparable<Fraction>{

    private int numerator;
    private int denominator;

    public static final Fraction ONEHALF = new Fraction(1,2);

    public static int counter = 0;

    public Fraction(int numerator, int denominator) throws ArithmeticException{

        if(denominator == 0){
            throw new ArithmeticException("Bawal ang zero (0) denominator in a fraction.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        this.simplify();
        Fraction.counter++;
    }

    public Fraction(int numerator){
        // this.numerator = numerator;
        // this.denominator = 1;

        this(numerator, 1);
        Fraction.counter++;
    }

    public Fraction(String stringFraction) throws ArithmeticException{
        // stringFraction = "3/4" or "29/100"

        int separatorIndex = stringFraction.indexOf('/');
        String numeratorString = stringFraction.substring(0, separatorIndex);
        String denominatorString = stringFraction.substring(separatorIndex+1, stringFraction.length());

        this.numerator = Integer.parseInt(numeratorString);
        this.denominator = Integer.parseInt(denominatorString);
        //this(this.numerator, this.denominator);

        if(this.denominator == 0){
             throw new ArithmeticException("Bawal ang zero (0) denominator in a fraction.");
        }

        this.simplify();

        Fraction.counter++;
    }



    public Fraction add(Fraction that){
        int num, den;
        num = this.numerator * that.denominator +
            that.numerator * this.denominator;
        den = this.denominator * that.denominator;

        return new Fraction(num, den);
    }

    public Fraction add(int num){
        Fraction numFrac = new Fraction(num);

        Fraction sum = this.add(numFrac);
        sum.simplify(); 
        return sum;
    }

    private void simplify(){
        int gcd = this.computeGCD();
        this.numerator /= gcd; // this.numerator = this.numerator / gcd;
        this.denominator /= gcd;
    }

    private int computeGCD(){
        int num = this.numerator;
        int den = this.denominator;

        while(den != 0){
            int t = den;
            den = num % den;
            num = t;
        }
        
        return num;
    }

    public String toString(){
        if(this.denominator == 1){
            return "" + numerator;
        }
        return numerator + " / " + denominator;
    }

    public void setDenominator(int denominator) throws ArithmeticException{
        if(denominator == 0){
            throw new ArithmeticException("Bawal talaga ang zero sa denominator!");
        }

        this.denominator = denominator;
    }

    public int getDenominator(){
        return this.denominator;
    }

    public static Fraction fromString(String stringFraction){

        // factory method

        int separatorIndex = stringFraction.indexOf('/');
        String numeratorString = stringFraction.substring(0, separatorIndex);
        String denominatorString = stringFraction.substring(separatorIndex+1, stringFraction.length());

        int numerator = Integer.parseInt(numeratorString);
        int denominator = Integer.parseInt(denominatorString);

        // if(this.denominator == 0){
        //      throw new ArithmeticException("Bawal ang zero (0) denominator in a fraction.");
        // }

        // this.simplify();

        return new Fraction(numerator, denominator);
    }

    public static Fraction rand(){

        int num = (int)(Math.random() * 100);
        int den = (int)(Math.random() * 100) + 1; 

        return new Fraction(num, den);
    }

    @Override
    public boolean equals(Object that){ // Object
    
        if(this == that) return true;

        Fraction that2 = (Fraction) that;

        return this.denominator == that2.denominator && this.numerator == that2.numerator;
    }

    @Override
    public int compareTo(Fraction that) {
        if(this.equals(that)){
			return 0;
		}		
		if(this.numerator * that.denominator <
		  that.numerator * this.denominator){
			  return -1;
		  }
		 else{
			 return 1;
		 }
    }

    // @Override
    // public int hashCode() {
    //     return java.util.Objects.hash(numerator, denominator);
    // }


}
