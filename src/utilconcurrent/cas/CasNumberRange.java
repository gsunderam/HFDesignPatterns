package utilconcurrent.cas;

import java.util.concurrent.atomic.AtomicReference;

import static log.Logger.stderr;
import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Aug 31, 2013
 *
 * Non blocking program to manipulate ranges
 * Structure to use CAS.
 * 
 * while(true) { //infinite loop
 * 	if (atomicref.compareAndSet(oldRef, newRef)) //return ONLY after true is set
 *  	return;
 * }
 */
public class CasNumberRange {

  private static class IntPair {
    final int lower;
    final int upper;

    public IntPair(int lower, int upper) {
      this.lower = lower;
      this.upper = upper;
    }
  }

  private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(1,50));

  public int getLower() {
    return values.get().lower;    
  }

  public int getUpper() {
    return values.get().upper;
  }

  public void setLower(int lower, String threadName) {
    while(true) {
      IntPair oldv = values.get();

      if (lower > oldv.upper) {
        //throw new IllegalArgumentException("Lower must be < upper");
        stderr(threadName + ": Says Lower > upper. so skipping");
        return;
      }

      IntPair newv = new IntPair(lower, oldv.upper);
      if (values.compareAndSet(oldv, newv)) {
				stdout(threadName + " successfully set lower " + lower + " upper " + newv.upper);
        return;
			} else stdout(threadName + " failed to set lower " + lower + " while upper is " + newv.upper);
    }
  }

  public void setUpper(int upper, String threadName) {
    while(true) {
      IntPair oldv = values.get();

      if (upper < oldv.lower) {
//        throw new IllegalArgumentException("Upper must be > lower");
         stderr(threadName + ": Says Upper < Lower. so skipping");
         return;
      }

      IntPair newv = new IntPair(oldv.lower, upper);
      if (values.compareAndSet(oldv, newv)) {
				stdout(threadName + " successfully set upper " + upper + " Lower " + newv.lower);
        return;
			} else stdout(threadName + " failed to set upper " + upper + " Lower " + newv.lower); 
    }
  }
}