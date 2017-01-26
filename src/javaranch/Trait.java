package javaranch;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gsunderam
 * Date: Jan 16, 2013
 */
public abstract class Trait {
  List<Integer> observations = new ArrayList<Integer>();
  String name;

  public double calculateAvg() {
     int sum = 0;

     for (int observation : observations) {
        sum += observation;
     }

     return sum / observations.size();
  }

  public List<Integer> getObservations() {
    return observations;
  }

  public void setObservations(List<Integer> observations) {
    this.observations = observations;
  }

  public String getName() {
    return name;
  }

  public abstract double complexTraitSpecificCalc();

  public void setName(String name) {
    this.name = name;
  }
}
