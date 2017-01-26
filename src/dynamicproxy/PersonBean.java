package dynamicproxy;

/**
 * User: gsunderam
 * Date: Jul 9, 2011
 */
public interface PersonBean {

  public String getName();
  public String getGender();
  public String getInterests();
  public int getHotOrNotRating();

  public void setName(String name);
  public void setGender(String gender);
  public void setInterests(String interests);
  public void setHotOrNot(int rating);
}
