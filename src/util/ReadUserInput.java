package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Jun 7, 2011
 */
public class ReadUserInput {
  public static String getUserInput() {
    String answer = null;

    stdout("Would you like condiments with your beverage  (y/n)");
    //Component is input stream reader
    Reader in = new InputStreamReader(System.in);
    //Now the decorator buffered reader
    BufferedReader br = new BufferedReader(in);
    try {
      answer = br.readLine();
    } catch(IOException ioe) {
      stdout("IO Exception encountered while reading input " + ioe.getMessage());
    }

    if (answer == null) return "no";
    return answer;
  }
}
