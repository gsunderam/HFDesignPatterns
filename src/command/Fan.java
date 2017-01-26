package command;

import static log.Logger.stdout;

/**
 * Created by IntelliJ IDEA.
 * User: gsunderam
 * Date: Oct 18, 2010
 * Time: 4:35:25 PM
 * To change this template use File | Settings | File Templates.
 */
class Fan {
    FanStates speed;

    public void startRotate(FanStates speed) {
       this.speed = speed;
       stdout("Fan is rotating at speed " + speed.getState());
    }

    public void stopRotate() {
       stdout("Fan STOPPED rotating");
    }
}

