package momomo.com.platform.Return.examples;

import momomo.com.Params;
import momomo.com.Return;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Joseph S.
 */
public class Examples {
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    // Supports the examples below, and are examples of their own. Read them.
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    private static Return.Two<String, Integer> two() {
        return new Return.Two<>("", 1);
    }
    
    private static Return.Three<String, Integer, Long> three() {
        return new Return.Three<>("", 1, 2L);
    }
    
    private static Return.Four<String, Integer, Long, Boolean> four() {
        return new Return.Four<>("", 1, 2L, false);
    }
    
    private static Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five() {
        return new Return.Five<>("", 1, 2L, Boolean.FALSE, new LinkedHashMap<>());
    }
    
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleZero() {
        Return.Two<String, Integer> $ = two();
    
        String  first  = $.first;
        Integer second = $.second;
    
        Return.One<String> casted = $.asOne();
        Return.One<String> cloned = $.toOne();
        
        // Casted
        if ( casted instanceof Return.Two ) {
            System.out.println(true);
        }
    
        // Cloned
        if ( cloned instanceof Return.Two ) {
            System.out.println(false);
        }
    
        casted.first("value");
    
        System.out.println(casted.first());
    
        if ( casted.first.equals($.first) ) {
            System.out.println(true); // Same instance!
        }
    
        exampleOne();
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleOne() {
        Return.Three<String, Integer, Long> $      = three();
        Return.Two<String, Integer>         casted = $.asTwo();
        
        paramsThree($);
        // paramsThree(casted); // Won't work
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleTwo() {
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
        
        String                                         first  = $.first;
        Integer                                        second = $.second;
        Long                                           third  = $.third;
        Boolean                                        fourth = $.fourth;
        LinkedHashMap<String, List<ArrayList<String>>> fifth  = $.fifth;
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleThree() {
        // Notice the Return.Four from a five() !
        Return.Four<String, Integer, Long, Boolean> $ = five();
        
        // We can also reassign it to a four, three, two, one ... 
        Return.Three<String, Integer, Long> casted = $;
        String  first = casted.first;
        Integer two   = casted.second;
        // ...
        
        // We can pass it to params method 
        paramsThree(five());
        paramsThree($);
        paramsThree(casted);
    
        paramsThree( threeFromFour() );
        paramsThree( threeFromFive() );
        paramsThree( fourFromFive()  );
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleFour() {
        // Here we show how all methods that call five() are able to return lower / higher inheritance classes
        Return.One<String>                                                                          one   = oneFromFive();
        Return.Two<String, Integer>                                                                 two   = twoFromFive();
        Return.Three<String, Integer, Long>                                                         three = threeFromFive();
        Return.Four<String, Integer, Long, Boolean>                                                 four  = fourFromFive();
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = five();
    
        // paramsThree( one   );  // error!
        // paramsThree( two   );  // error!
        paramsThree( three );
        paramsThree( four  );
        paramsThree( five  );
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleFive() {
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
    
        // Auto casting. The only major benefit of this one is that you won't have to type all of the generic signatures. 
        // If you have $ here, and you would like to express it as a Four, typing those might a be a hassle, at least when typing examples. 
        // So we provide convienience methods to do that. Intellij / Eclipse will generate the field. 
    
        Return.One<String>                                                                          one   = $.asOne();
        Return.Two<String, Integer>                                                                 two   = $.asTwo();
        Return.Three<String, Integer, Long>                                                         three = $.asThree();
        Return.Four<String, Integer, Long, Boolean>                                                 four  = $.asFour();
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.asFive();
        
        // Yes, the last one is not required, but it looks so much more consistent for this example so we added it. It just returns this and is cheap. 
        
        // Note that when casting from say a Five to a Two, the instance would still be a Five! So the values would still be accessible as it is not recreated. 
        // It is the same instance! To transform it, we suggest you use the constructor for that. More features we are adding on the fly!
        
        // So we added it. Now we have: 
        
        exampleSix();
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static void exampleSix() {
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> $ = five();
    
        // Clones. 
        Return.One<String>                                                                          one   = $.toOne();
        Return.Two<String, Integer>                                                                 two   = $.toTwo();
        Return.Three<String, Integer, Long>                                                         three = $.toThree();
        Return.Four<String, Integer, Long, Boolean>                                                 four  = $.toFour();
        Return.Five<String, Integer, Long, Boolean, LinkedHashMap<String, List<ArrayList<String>>>> five  = $.toFive(); 
        
        // Note that these are all clones. Sure, we could have done: 
        Return.Four<String, Integer, Long, Boolean> last = new Return.Four<>($.first, $.second, $.third, $.fourth);
        // But typing that manyally is a bit too much. 
    }
    
    /////////////////////////////////////////////////////////////////////
    // Method auto casting, when reassigning aslo
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Auto casting since Five inherit Four which inherits Three which ...
     * 
     * @return One
     */
    private static Return.One<String> oneFromFive() {
        return five();       // Perfetly valid
    }
    
    /**
     * @return Two
     */
    private static Return.Two<String, Integer> twoFromFive() {
        return five();       // Perfetly valid
    }
    
    /**
     * @return Three
     */
    private static Return.Three<String, Integer, Long> threeFromFour() {
        return four();       // Perfetly valid
    }
    
    /**
     * @return Three
     */
    private static Return.Three<String, Integer, Long> threeFromFive() {
        return five();       // Perfetly valid
    }
    
    /**
     * @return Four
     */
    private static Return.Four<String, Integer, Long, Boolean> fourFromFive() {
        return five();       // Perfetly valid
    }
    
    /////////////////////////////////////////////////////////////////////
    // Params demo below. Maybe overkill. 
    /////////////////////////////////////////////////////////////////////
    // We also provide a syntactic sugar class equivalent to Return called Params to allow you to pass your Return.One, Return.Two .. as Params.One instead.
    // Checkout the Params.java class which is an empty skeleton.
    // It is only because it might be ugly to have a method param be called Params. 
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    
    /**
     * Should you desire to pass the Return... you can declare it as Params.One ... instead.
     * See example calls from {@link Examples#paramsTest}
     */
    private static Return.Three<String, Integer, Long> paramsThree(Params.Three<String, Integer, Long> params) {
        return params;
    }
}
