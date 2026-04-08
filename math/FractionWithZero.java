package math;
class FractionWithZero{
  public static void main(String[] args){
    Fraction number, number2, number3;
    number = new Fraction(1,2);
    number2 = new Fraction(3,4);
    try{
      number3 = new Fraction(4,0);
    }catch(ArithmeticException e){
	System.out.println("Ooopes! " + e);
    }finally{
        number3= new Fraction(1);
    }
    Fraction sum = number.add(number2);		
    //ff line throws an error 
    //since number3 was not initialized
    System.out.println(number3); // number3 = 1
    System.out.println(sum);
  }
}
