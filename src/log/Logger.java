package log;

import static java.lang.System.out;
import static java.lang.System.err;


/**
 * Created by IntelliJ IDEA.
 * User: gsunderam
 * Date: Nov 4, 2010
 * An abstraction over java util log
 */
public final class Logger {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getAnonymousLogger();

    public static void log(String name, Object any) {
       java.util.logging.Logger logger = java.util.logging.Logger.getLogger(name);
       if (any != null ) logger.info(any.toString());
       else logger.info(null);
    }

    public static void log(Object o) {
       if (o != null) log.info(o.toString());
       else log.info(null);
    }

  /**
   * Meant to be used if a newline is intended after a line
    * @param o
   */
  public static void stdout(Object o) {
     if (o != null ) out.println(o.toString());
     else out.println(o);
   }

  /**
   * Use this when no newline is required
   * @param o
   */
   public static void print(Object o) {
     if (o != null ) out.print(o.toString());
     else out.print(o);
   }

   public static void stderr(Object o) {
     if (o != null ) err.println(o.toString());
     else err.println(o);
   }

    
}
