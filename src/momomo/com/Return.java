package momomo.com;

/**
 * This class and its inner classes are made to support any method to return more than one instance, statically and retain types and what not. 
 * 
 * Using these classes a method can simply return 1-5 return values.
 * 
 * Note, that they are intentionally not final, since we might desire to generate the returns not in one place, the constructor, but might prepare it using if else logic.
 * 
 * See examples at the bottom of this file for examples. 
 * 
 * @author Joseph S.
 */
public class Return { protected Return(){ /* Just a wrapper class for contained classes. Only subclasses allowed to invoke constructor */ }
    
    /**
     * Not really neccessary but good to support the below classes
     */
    public static class One<First> {
        public First first;
    
        public One() {}
        public One(First first) {
            this.first = first;
        }
        public void One(One<First> $) {
            this.first = $.first;
        }
    
        /**
         * Get first 
         */
        public final First first() {
            return first;
        }
    
        /**
         * Set first
         */
        public One<First> first(First first) {
            this.first = first; return this;
        }
    
        /**
         * Casts to avoid hassles with casting if you ever need it, not that we forsee it.
         * 
         * For consitency only, but also inherited. 
         */
        public One<First> asOne() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Two
         * 
         * For consitency only, but also inherited. 
         */
        public One<First> toOne() {
            return new One<>(this.first);
        }
    }
    
    public static class Two<First, Second> extends One<First> {
        public Second second;
    
        public Two() {}
        public Two(First first) {
            this.first = first; 
        }
        public Two(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        
        public Two(One<First> $) {
            this($.first);
        }
        public Two(Two<First, Second> $) {
            this($.first, $.second);
        }
    
        /**
         * Get second 
         */
        public final Second second() {
            return second;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Two<First, Second> first(First first) {
            super.first = first; return this;
        }
    
        /**
         * Set second
         */
        public Two<First, Second> second(Second second) {
            this.second = second; return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it. 
         */
        public Two<First, Second> asTwo() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Two
         *
         */
        public Two<First, Second> toTwo() {
            return new Two<>(this.first(), this.second());
        }
    }
    
    public static class Three<First, Second, Third> extends Two<First, Second> {
        public Third third;
    
        public Three() {}
        public Three(First first) {
            this.first = first;
        }
        public Three(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        public Three(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        public void Three(Three<First, Second, Third> $) {
            this.first  = $.first;
            this.second = $.second;
            this.third  = $.third;
        }
    
        public Three(One<First> $) {
            this($.first);
        }
        public Three(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Three(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
    
        /**
         * Get third 
         */
        public final Third third() {
            return third;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Three<First, Second, Third> first(First first) {
            super.first = first; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set second
         */
        @Override
        public Three<First, Second, Third> second(Second second) {
            super.second = second;  return this;
        }
    
        /**
         * Set third
         */
        public Three<First, Second, Third> third(Third third) {
            this.third = third; return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it. 
         */
        public Three<First, Second, Third> asThree() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Three 
         *
         */
        public Three<First, Second, Third> toThree() {
            return new Three<>(this.first(), this.second());
        }
    }
    
    public static class Four<First, Second, Third, Fourth> extends Three<First, Second, Third> {
        public Fourth fourth;
    
        public Four() {}
        public Four(First first) {
            this.first = first;
        }
        public Four(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        public Four(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        public Four(First first, Second second, Third third, Fourth fourth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
        }
        public void Four(Four<First, Second, Third, Fourth> $) {
            this.first  = $.first;
            this.second = $.second;
            this.third  = $.third;
            this.fourth = $.fourth;
        }
    
        public Four(One<First> $) {
            this($.first);
        }
        public Four(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Four(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
        public Four(Four<First, Second, Third, Fourth> $) {
            this($.first, $.second, $.third, $.fourth);
        }
    
        /**
         * Get fourth 
         */
        public final Fourth fourth() {
            return fourth;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Four<First, Second, Third, Fourth> first(First first) {
            super.first = first; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set second
         */
        @Override
        public Four<First, Second, Third, Fourth> second(Second second) {
            super.second = second;  return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set third
         */
        @Override
        public Four<First, Second, Third, Fourth> third(Third third) {
            super.third = third;  return this;
        }
    
        /**
         * Set fourth
         */
        public Four<First, Second, Third, Fourth> fourth(Fourth fourth) {
            this.fourth = fourth; return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it. 
         */
        public Four<First, Second, Third, Fourth> asFour() {
            return this;
        }
    
        /**
         * Copies to new instance, no longer a Four
         *
         */
        public Four<First, Second, Third, Fourth> toFour() {
            return new Four<>(this.first(), this.second(), this.third(), this.fourth());
        }
    }
    
    public static class Five<First, Second, Third, Fourth, Fifth> extends Four<First, Second, Third, Fourth> {
        public Fifth fifth;
        
        public Five() {}
        
        public Five(First first) {
            this.first = first;
        }
        public Five(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        public Five(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        public Five(First first, Second second, Third third, Fourth fourth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
        }
        public Five(First first, Second second, Third third, Fourth fourth, Fifth fifth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
        }
        public Five(One<First> $) {
            this($.first);
        }
        public Five(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Five(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
        public Five(Four<First, Second, Third, Fourth> $) {
            this($.first, $.second, $.third, $.fourth);
        }
        public Five(Five<First, Second, Third, Fourth, Fifth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth);
        }
        
        /**
         * Get fifth 
         */
        public final Fifth fifth() {
            return fifth;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Five<First,Second,Third,Fourth,Fifth> first(First first) {
            super.first = first; return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set second
         */
        @Override
        public Five<First,Second,Third,Fourth,Fifth> second(Second second) {
            super.second = second;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set third
         */
        @Override
        public Five<First,Second,Third,Fourth,Fifth> third(Third third) {
            super.third = third;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set fourth
         */
        @Override
        public Five<First,Second,Third,Fourth,Fifth> fourth(Fourth fourth) {
            super.fourth = fourth;  return this;
        }
        
        /**
         * Set fifth
         */
        public Five<First, Second, Third, Fourth, Fifth> fifth(Fifth fifth) {
            this.fifth = fifth; return this;
        }
        
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it.
         *
         * For consitency only.
         */
        public Five<First, Second, Third, Fourth, Fifth> asFive() {
            return this;
        }
        
        /**
         * Copies to new instance, no longer the same Five. 
         *
         * For consitency only. 
         */
        public Five<First, Second, Third, Fourth, Fifth> toFive() {
            return new Five<>(this.first(), this.second(), this.third(), this.fourth(), this.fifth());
        }
    }
    
    public static class Six<First, Second, Third, Fourth, Fifth, Sixth> extends Five<First, Second, Third, Fourth, Fifth> {
        public Sixth sixth;
        
        public Six() {}
        
        public Six(First first) {
            this.first = first;
        }
        
        public Six(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        
        public Six(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        
        public Six(First first, Second second, Third third, Fourth fourth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
        }
        
        public Six(First first, Second second, Third third, Fourth fourth, Fifth fifth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
        }
        
        public Six(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
        }
    
        public void Six(Six<First, Second, Third, Fourth, Fifth, Sixth> $) {
            this.first  = $.first;
            this.second = $.second;
            this.third  = $.third;
            this.fourth = $.fourth;
            this.fifth  = $.fifth;
            this.sixth  = $.sixth;
        }
    
        public Six(One<First> $) {
            this($.first);
        }
        public Six(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Six(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
        public Six(Four<First, Second, Third, Fourth> $) {
            this($.first, $.second, $.third, $.fourth);
        }
        public Six(Five<First, Second, Third, Fourth, Fifth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth);
        }
        public Six(Six<First, Second, Third, Fourth, Fifth, Sixth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth);
        }
    
        /**
         * Get sixth 
         */
        public final Sixth sixth() {
            return sixth;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Six<First, Second, Third, Fourth, Fifth, Sixth> first(First first) {
            super.first = first; return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set second
         */
        @Override
        public Six<First, Second, Third, Fourth, Fifth, Sixth> second(Second second) {
            super.second = second;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set third
         */
        @Override
        public Six<First, Second, Third, Fourth, Fifth, Sixth> third(Third third) {
            super.third = third;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set fourth
         */
        @Override
        public Six<First, Second, Third, Fourth, Fifth, Sixth> fourth(Fourth fourth) {
            super.fourth = fourth;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set fifth
         */
        public Six<First, Second, Third, Fourth, Fifth, Sixth> fifth(Fifth fifth) {
            this.fifth = fifth; return this;
        }
        
        /**
         * Set sixth
         */
        public Six<First, Second, Third, Fourth, Fifth, Sixth> sixth(Sixth sixth) {
            this.sixth = sixth; return this;
        }
        
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it.
         *
         * For consitency only.
         */
        public Six<First, Second, Third, Fourth, Fifth, Sixth> asSix() {
            return this;
        }
        
        /**
         * Copies to new instance, no longer the same Five. 
         *
         * For consitency only. 
         */
        public Six<First, Second, Third, Fourth, Fifth, Sixth> toSix() {
            return new Six<>(this.first(), this.second(), this.third(), this.fourth(), this.fifth(), this.sixth());
        }
    }
    
    public static class Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> extends Six<First, Second, Third, Fourth, Fifth, Sixth> {
        public Seventh seventh;
        
        public Seven() {}
        public Seven(First first) {
            this.first = first;
        }
        public Seven(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        public Seven(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        public Seven(First first, Second second, Third third, Fourth fourth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
        }
        public Seven(First first, Second second, Third third, Fourth fourth, Fifth fifth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
        }
        public Seven(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
        }
        public Seven(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth, Seventh seventh) {
            this.first   = first;
            this.second  = second;
            this.third   = third;
            this.fourth  = fourth;
            this.fifth   = fifth;
            this.sixth   = sixth;
            this.seventh = seventh;
        }
    
        public Seven(One<First> $) {
            this($.first);
        }
        public Seven(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Seven(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
        public Seven(Four<First, Second, Third, Fourth> $) {
            this($.first, $.second, $.third, $.fourth);
        }
        public Seven(Five<First, Second, Third, Fourth, Fifth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth);
        }
        public Seven(Six<First, Second, Third, Fourth, Fifth, Sixth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth);
        }
        public Seven(Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth, $.seventh);
        }
        
        /**
         * Get seventh 
         */
        public final Seventh seventh() {
            return seventh;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> first(First first) {
            super.first = first; return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set second
         */
        @Override
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> second(Second second) {
            super.second = second;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set third
         */
        @Override
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> third(Third third) {
            super.third = third;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set fourth
         */
        @Override
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> fourth(Fourth fourth) {
            super.fourth = fourth;  return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set fifth
         */
        @Override
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> fifth(Fifth fifth) {
            this.fifth = fifth; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set sixth
         */
        @Override
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> sixth(Sixth sixth) {
            this.sixth = sixth; return this;
        }
    
        /**
         * Set seventh
         */
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> seventh(Seventh seventh) {
            this.seventh = seventh; return this;
        }
        
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it.
         *
         * For consitency only.
         */
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> asSeven() {
            return this;
        }
        
        /**
         * Copies to new instance, no longer the same Five. 
         *
         * For consitency only. 
         */
        public Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> toSeven() {
            return new Seven<>(this.first(), this.second(), this.third(), this.fourth(), this.fifth(), this.sixth(), this.seventh());
        }
    }
    
    public static class Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> extends Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> {
        public Eighth eighth;
        
        public Eight() {}
        
        public Eight(First first) {
            this.first = first;
        }
        
        public Eight(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        
        public Eight(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        
        public Eight(First first, Second second, Third third, Fourth fourth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
        }
        
        public Eight(First first, Second second, Third third, Fourth fourth, Fifth fifth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
        }
        
        public Eight(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
        }
    
        public Eight(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth, Seventh seventh) {
            this.first   = first;
            this.second  = second;
            this.third   = third;
            this.fourth  = fourth;
            this.fifth   = fifth;
            this.sixth   = sixth;
            this.seventh = seventh;
        }
        
        public Eight(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth, Seventh seventh, Eighth eighth) {
            this.first   = first;
            this.second  = second;
            this.third   = third;
            this.fourth  = fourth;
            this.fifth   = fifth;
            this.sixth   = sixth;
            this.seventh = seventh;
            this.eighth  = eighth;
        }
    
        public Eight(One<First> $) {
            this($.first);
        }
        public Eight(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Eight(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
        public Eight(Four<First, Second, Third, Fourth> $) {
            this($.first, $.second, $.third, $.fourth);
        }
        public Eight(Five<First, Second, Third, Fourth, Fifth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth);
        }
        public Eight(Six<First, Second, Third, Fourth, Fifth, Sixth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth);
        }
        public Eight(Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth, $.seventh);
        }
        public Eight(Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth, $.seventh, $.eighth);
        }
        
        /**
         * Get eighth 
         */
        public final Eighth eighth() {
            return eighth;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> first(First first) {
            super.first = first; return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set second
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> second(Second second) {
            super.second = second;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set third
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> third(Third third) {
            super.third = third;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set fourth
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> fourth(Fourth fourth) {
            super.fourth = fourth;  return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set fifth
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> fifth(Fifth fifth) {
            this.fifth = fifth; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super. 
         * 
         * Set sixth
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> sixth(Sixth sixth) {
            this.sixth = sixth; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set seventh
         */
        @Override
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> seventh(Seventh seventh) {
            this.seventh = seventh; return this;
        }
    
        /**
         * Set eight
         */
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> eighth(Eighth eighth) {
            this.eighth = eighth; return this;
        }
        
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it.
         *
         * For consitency only.
         */
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> asEight() {
            return this;
        }
        
        /**
         * Copies to new instance, no longer the same Five. 
         *
         * For consitency only. 
         */
        public Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> toEight() {
            return new Eight<>(this.first(), this.second(), this.third(), this.fourth(), this.fifth(), this.sixth(), this.seventh(), this.eighth());
        }
    }
    
    public static class Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> extends Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> {
        public Ninth ninth;
        
        public Nine() {}
        public Nine(First first) {
            this.first = first;
        }
        public Nine(First first, Second second) {
            this.first  = first;
            this.second = second;
        }
        public Nine(First first, Second second, Third third) {
            this.first  = first;
            this.second = second;
            this.third  = third;
        }
        public Nine(First first, Second second, Third third, Fourth fourth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
        }
        public Nine(First first, Second second, Third third, Fourth fourth, Fifth fifth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
        }
        public Nine(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
        }
        public Nine(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth, Seventh seventh) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
            this.eighth = eighth;
            this.ninth  = ninth;
        }
        public Nine(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth, Seventh seventh, Eighth eighth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
            this.eighth = eighth;
            this.ninth  = ninth;
        }
        public Nine(First first, Second second, Third third, Fourth fourth, Fifth fifth, Sixth sixth, Seventh seventh, Eighth eighth, Ninth ninth) {
            this.first  = first;
            this.second = second;
            this.third  = third;
            this.fourth = fourth;
            this.fifth  = fifth;
            this.sixth  = sixth;
            this.eighth = eighth;
            this.ninth  = ninth;
        }
        
        public Nine(One<First> $) {
            this($.first);
        }
        public Nine(Two<First, Second> $) {
            this($.first, $.second);
        }
        public Nine(Three<First, Second, Third> $) {
            this($.first, $.second, $.third);
        }
        public Nine(Four<First, Second, Third, Fourth> $) {
            this($.first, $.second, $.third, $.fourth);
        }
        public Nine(Five<First, Second, Third, Fourth, Fifth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth);
        }
        public Nine(Six<First, Second, Third, Fourth, Fifth, Sixth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth);
        }
        public Nine(Seven<First, Second, Third, Fourth, Fifth, Sixth, Seventh> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth, $.seventh);
        }
        public Nine(Eight<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth, $.seventh, $.eighth);
        }
        public Nine(Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> $) {
            this($.first, $.second, $.third, $.fourth, $.fifth, $.sixth, $.seventh, $.eighth, $.ninth);
        }
        
        /**
         * Get ninth 
         */
        public final Ninth ninth() {
            return ninth;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set first
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> first(First first) {
            super.first = first; return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set second
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> second(Second second) {
            super.second = second;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set third
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> third(Third third) {
            super.third = third;  return this;
        }
        
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set fourth
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> fourth(Fourth fourth) {
            super.fourth = fourth;  return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set fifth
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> fifth(Fifth fifth) {
            this.fifth = fifth; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set sixth
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> sixth(Sixth sixth) {
            this.sixth = sixth; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set seventh
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> seventh(Seventh seventh) {
            this.seventh = seventh; return this;
        }
    
        /**
         * We override for chaining. We set directly to avoid hitting the entire chain of super.
         * 
         * Set eight 
         */
        @Override
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> eighth(Eighth eighth) {
            this.eighth = eighth; return this;
        }
    
        /**
         * Set ninth
         */
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> ninth(Ninth ninth) {
            this.ninth = ninth; return this;
        }
    
        /**
         * Casts to to avoid hassles with casting if you ever need it, not that we forsee it.
         *
         * For consitency only.
         */
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> asNine() {
            return this;
        }
        
        /**
         * Copies to new instance, no longer the same Five. 
         *
         * For consitency only. 
         */
        public Nine<First, Second, Third, Fourth, Fifth, Sixth, Seventh, Eighth, Ninth> toNine() {
            return new Nine<>(this.first(), this.second(), this.third(), this.fourth(), this.fifth(), this.sixth(), this.seventh(), this.eighth(), this.ninth());
        }
    }
    
    
}
