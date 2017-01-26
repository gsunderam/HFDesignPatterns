package command;

import java.util.Arrays;

import static log.Logger.stdout;

/**
 * User: gsunderam
 * Date: May 28, 2011
 * This is the Client.
 * 1) It instantiates the Concrete command -- say LightOnCommand -- and sets the receiver-- light #1
 * 2) Creates the invoker #2 --RemoteControl
 * 3) Sets the command on the invoker #3  -- invoker.setCommand(#1)
 * 4) At some point invokes the Invoker to carry out the command. #4  -- Call the onbuttonpressed
 */
public class RemoteLoader {

  public static void main(String[] args) {
    //First create the RECEIVERS
    Light light = new Light();
    Fan fan = new Fan();
    Stereo stereo = new Stereo();
    GarageDoor garageDoor = new GarageDoor();

    //Create the concrete command objects
    Command lightOnCommand = new LightOnCommand(light); //Steps #1 below
    Command lightOffCommand = new LightOffCommand(light);
    Command fanHighCommand = new FanHighCommand(fan);
    Command fanLowCommand = new FanLowCommand(fan);
    Command fanMediumCommand = new FanMediumCommand(fan);
    Command fanStopCommand = new FanStopCommand(fan);
    Command garageOpen = new GarageDoorOpenCommand(garageDoor);
    Command garageClose = new GarageDoorCloseCommand(garageDoor);
    Command stereoOff = new StereoOffCommand(stereo);
    Command stereoOn = new StereoOnCommand(stereo);
    
    //Set the commands on the invoker object
    RemoteControl remoteControl = new RemoteControl(); //Step #2
    remoteControl.setCommand(0, lightOnCommand, lightOffCommand); //Steps #3 below
    remoteControl.setCommand(1, fanHighCommand, fanStopCommand);
    remoteControl.setCommand(2, fanMediumCommand, fanStopCommand);
    remoteControl.setCommand(3, fanLowCommand, fanStopCommand);
    remoteControl.setCommand(4, garageOpen, garageClose);
    remoteControl.setCommand(5, stereoOn, stereoOff);

    stdout(remoteControl);

    //Now the FUN PART execute the commands. Get ready for success.
    for (int i = 0;i < 6;i++) {
      remoteControl.onButtonPressed(i); //Steps #4 below
      remoteControl.offButtonPressed(i);
    }

    //Test the undo button
     stdout("---------------Testing undo button for FAN---------------");
     remoteControl.onButtonPressed(2); //medium == 2
     remoteControl.offButtonPressed(2); //off == 0
     remoteControl.undoButtonPressed();  //FAN will be turned to its previous speed which is medium == 2!
     remoteControl.onButtonPressed(1); //make it high == 3
     remoteControl.undoButtonPressed(); //should be medium == 2

    //Demonstrate macro command
    stdout("---------------Now time for MACRO Commands-----------");
    light.setLocation("Party Room");
    TV tv = new TV();
    Command tvOnCmd = new TVOnCommand(tv);
    Command tvOffCmd = new TVOffCommand(tv);
    Command [] partyOn = { lightOnCommand, tvOnCmd, stereoOn }; //create an array of commands for on and off
    Command [] partyOff = { lightOffCommand, tvOffCmd, stereoOff };
    Command partyOnCmd = new MacroCommand(Arrays.asList(partyOn)); //Demonstrating my might in collection API!
    Command partyOffCmd = new MacroCommand(Arrays.asList(partyOff)); //set it on the MacroCommand object
    remoteControl.setCommand(6, partyOnCmd, partyOffCmd); //Set the Macro command as usual
    stdout(remoteControl.toStringASlot(6));
    stdout("----------party is beginning--------------");
    remoteControl.onButtonPressed(6);
    stdout("---------------party is ending------------");
    remoteControl.offButtonPressed(6);
    stdout("----------------undoing the macro command, should begin party again------------");
    remoteControl.undoButtonPressed();

    stdout("----------------Yay! Success for today with Command pattern------------------");
  }
}
