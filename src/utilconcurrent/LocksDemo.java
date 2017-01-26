package utilconcurrent;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: Mar 10, 2013
 */
public class LocksDemo {
  public static void main(String[] args) {
    StackUsingLock stack = new StackUsingLock();
    stack.push(23);
    stack.push(33);
    stack.push(42);
    stack.push(51);

    stdout("Number of elements in stack " + stack.size());
    int capacity = stack.capacity();
    stdout("Capacity of the stack " + capacity);

    stack.push(5);
    //stack.push(6);

    stdout("Number of elements in stack " + stack.size());
    stdout("Holds by the lock " + stack.getHoldCount());
    stdout("Popping element " + stack.pop());
    stdout("Popping element " + stack.pop());
    stdout("Popping element " + stack.pop());
    stdout("Popping element " + stack.pop());
    //stdout("Popping element " + stack.pop());
    stdout("Number of elements in stack " + stack.size());

    if(capacity == stack.size()) stdout("Try to POP. Thread will block! " + stack.pop());
  }
}
