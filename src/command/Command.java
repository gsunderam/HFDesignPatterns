package command;

/**
 * This is the parent interface of ALL Concrete Command objects
 */
public abstract interface Command {
    public abstract void execute();
    public void undo();
}
