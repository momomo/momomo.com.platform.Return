package momomo.com.platform.Return.examples;

import momomo.com.Objects;

import javax.swing.*;
import java.util.jar.JarInputStream;

/**
 * @author Joseph S.
 */
public class Examples {
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class CREATE {
        
        public static Objects.Two<Integer, Long> two() {
            return new Objects.Two<>(1, 2L);
        }
        
        public static Objects.Three<Integer, Long, String> three() {
            return new Objects.Three<>(1, 2L, "3");
        }
    
        public static Objects.Four<Integer, Long, String, String> four() {
            return new Objects.Four<>(1, 2L, "3", "4");
        }
    
        public static void main() {
            Objects.One<String>                         one   = new Objects.One<>  ("1");
            Objects.Two<String, Integer>                two   = new Objects.Two<>  ("1", 2);
            Objects.Three<String, Integer, Long>        three = new Objects.Three<>("1", 2, 3L);
            Objects.Four<String, Integer, Long, String> four  = new Objects.Four<> ("1"); four.second(2).third(3L).fourth("4");
            
            two = new Objects.Three<>("1");
            two = new Objects.Four<> ("1");
            two = new Objects.Five<> ("1");
            two = three; 
            two = four;
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class CAST {
        
        public static Objects.Two<Integer, Long> two() {
            return CREATE.four().asTwo();    // Same instance, just casted
        }
    
        public static Objects.Three<Integer, Long, String> three() {
            return CREATE.four();            // Same instance, just casted though method inference
        }
    
        public static void main() {
            Objects.Four<Integer, Long, String, String> four = CREATE.four();
        
            // All casted to lower ones
            Objects.One<Integer>                 one   = four.asOne();
            Objects.Two<Integer, Long>           two   = four.asTwo();
            Objects.Three<Integer, Long, String> three = four.asThree();
        
            if (one == two && two == three && three == four) {
                System.out.println("The universe is working!");
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class CLONE {
        public static Objects.Two<Integer, Long> two() {
            return CREATE.four().toTwo();    // A new instance, objects are copied over  
        }
    
        public static Objects.Three<Integer, Long, String> three() {
            return CREATE.four().toThree();  // A new instance, objects are copied over  
        }
    
        public static void main() {
            Objects.Four<Integer, Long, String, String> four = CREATE.four();
        
            // All copied / cloned to new instances
            Objects.One<Integer>                 one   = four.toOne();
            Objects.Two<Integer, Long>           two   = four.toTwo();
            Objects.Three<Integer, Long, String> three = four.toThree();
        
            if (one == two && two == three && three == four) {
                System.out.println("The universe is collapsing!");
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class COMPOSITION {
        public static Objects.Three<Integer, Long, JFrame> three() {
            // Note, we create a Return.Three from a Return.Two meaning the last one (third) will be null and has to be set manually, otherwise it will remain null.  
            return new Objects.Three<Integer, Long, JFrame>( CREATE.two() ).third(new JFrame());
        }
        
        public static Objects.Four<Integer, Long, String, JFrame> five() {
            // Note, we create a Return.Five from a Return.Four meaning the last one (fourth) will be null and has to be set manually, otherwise it will remain null.  
            return new Objects.Four<Integer, Long, String, JFrame>( CREATE.three() ).fourth(new JFrame());
        }
    
        public static void main() {
            Objects.Two<Integer, Long> two = CREATE.two();
    
            Objects.Three<Integer, Long, JFrame>                three = new Objects.Three<>(two);
            Objects.Four<Integer, Long, JFrame, JarInputStream> four  = new Objects.Four<>(three);
            
            if ( three.third == null && four.third == null && four.fourth == null ) {
                System.out.println("The universe is working!");
            }
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class READ {
        public static void three() {
            Objects.Three<Integer, Long, String> three = CREATE.three();
        
            Integer first  = three.first;
            Long    second = three.second;
            String  third  = three.third();
        }
    
        public static Objects.Two<String, String> four() {
            Objects.Four<Integer, Long, String, String> four = CREATE.four();
    
            return new Objects.Two<>( four.first + "::" + four.second, four.third() + "::" + four.fourth() );
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
    public static final class PASSING {
        public static void three(Objects.Three<Integer, Long, String> param) {
            Integer first  = param.first;
            Long    second = param.second;
            String  third  = param.third;
        }
    
        public static void four(Objects.Four<Integer, Long, String, String> param) {
            Integer first  = param.first;
            Long    second = param.second;
            String  third  = param.third;
        }
    
        public static void main() {
            three( new Objects.Three<>(1, 2L, "3") );
            three( new Objects.Four<> (1, 2L, "3", "4") );
    
            four( new Objects.Four<> (1, 2L, "3", "4") );
            four( new Objects.Five<> (1, 2L, "3", "4", "5") );
        }
    }
    
    /////////////////////////////////////////////////////////////////////
    
}
