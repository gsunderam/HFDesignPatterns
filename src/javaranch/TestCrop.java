package javaranch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jan 16, 2013
 */
public class TestCrop {

  public static void main(String[] args) {
     Crop maize = new Crop();
     maize.setName("maize");

     //create traits
     List<Trait> maizeTraits = new ArrayList<Trait>();

     Trait maizeTrait = new Trait1();
     maizeTrait.setName("trait1");
     ArrayList<Integer> list = new ArrayList<Integer>();
     list.add(1);
     list.add(3);
     maizeTrait.setObservations(list );
     maizeTraits.add(maizeTrait);

     Trait2 maizeTrait2 = new Trait2();
     maizeTrait2.setName("trait2");
     ArrayList<Integer> list2 = new ArrayList<Integer>();
     list2.add(4);
     maizeTrait2.setObservations(list2);
     maizeTraits.add(maizeTrait2);

     maize.setTraits(maizeTraits);

     //invoke operations
     double result = 0.0;
     for (Trait trait : maize.getTraits()) {
        result += trait.complexTraitSpecificCalc();
     }

     stdout("Result of maize observations " + result);
  }
}
