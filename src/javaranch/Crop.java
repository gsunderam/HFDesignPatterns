package javaranch;

import java.util.List;

/**
 * User: gsunderam
 * Date: Jan 16, 2013
 */
public class Crop {

  String name;
  List<Trait> traits;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Trait> getTraits() {
    return traits;
  }

  public void setTraits(List<Trait> traits) {
    this.traits = traits;
  }
}
