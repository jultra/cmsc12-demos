package math;

import java.util.ArrayList;
import java.util.Collections;

public class StringSearch {
 
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();

        strings.add("Juan");
        strings.add("Maria");
        strings.add("Pedro");
        strings.add("Juan");

        // Collections.sort(strings);

        for(String string: strings){
            System.out.println(string);
        }

        Collections.sort(strings);


        for(int  i = 0; i < strings.size(); i++){
            System.out.println(strings.get(i));
        }
        
        Collections.reverse(strings);

        for(String string: strings){
            System.out.println(string);
        }

        Collections.sort(strings);
        System.out.println(Collections.binarySearch(strings, "Juan"));

    }
}
