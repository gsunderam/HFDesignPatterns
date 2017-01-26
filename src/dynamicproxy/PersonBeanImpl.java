package dynamicproxy;

/**
 * User: gsunderam
 * Date: Jul 9, 2011
 */
public class PersonBeanImpl implements PersonBean {
  private String name;
  private String gender;
  private String interests;
  private int hotOrNot; //Rating by another customer
  private int ratingCount; //Keep track of Number of times this person got rated 
  
  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public String getInterests() {
    return interests;
  }

  public int getHotOrNotRating() {
    return ratingCount != 0 ? hotOrNot / ratingCount : 0;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setInterests(String interests) {
    this.interests = interests;
  }

  /**
   * Set the hototnot and increment the frequency of rating count
   * @param rating
   */
  public void setHotOrNot(int rating) {
     this.hotOrNot += rating;
     ratingCount++;
  }
}
