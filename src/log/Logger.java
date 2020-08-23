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

    public static final String TAB = "\t";

    /**
     * prints with a new line
     * @param obj
     */
    public static void stdout(Object obj) {
        if (obj != null) out.println(obj.toString());
        else out.println(obj);
    }

    /**
     * prints without a new line
     * @param obj
     */
    public static void print(Object obj) {
        if (obj != null) out.print(obj.toString());
        else out.print(obj);
    }

    public static void printTab(Object obj) {
        if (obj != null) out.print(obj.toString() + TAB);
        else out.print(obj + TAB);
    }

    public static void stderr(Object obj) {
        if (obj != null) err.println(obj.toString());
        else err.println(obj);
    }

    
}
