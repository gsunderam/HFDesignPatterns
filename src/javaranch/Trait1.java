package javaranch;

/**
 * User: gsunderam
 * Date: Jan 16, 2013
 */
public class Trait1 extends Trait {

  //List<Integer> observations = new ArrayList<Integer>();

  public double complexTraitSpecificCalc() {
     double avg = calculateAvg();
     int weight = 0;
     int i = 0, sum = 0;
     int size = observations.size();

    //add weights  4 for even and 5 for odd positions
    for (int value : observations) {
      if (i++ % 2 == 0) sum += value * 4;
      else sum += value * 5;
    }

     double weightedAvg = sum / ((4 *(size/2)) + (5 * size/2));
     return weightedAvg - avg;
  }
}
