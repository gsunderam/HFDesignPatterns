package util;

import java.util.ArrayList;
import java.util.List;

/**
 * User: gsunderam
 * Date: Oct 25, 2012
 */
public class DriverTest {
  public static void main(String[] args) {
    try {
      Driver.timeTasks(6, new SampleTask("i"));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
