package com.jvs.bulldog.test;


import java.io.IOException;

import org.apache.log4j.Logger;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.gpio.Pwm;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.core.util.easing.EasingOptions;
import io.silverspoon.bulldog.core.util.easing.SineEasing;
import io.silverspoon.bulldog.devices.actuators.movement.EasedMove;
import io.silverspoon.bulldog.devices.actuators.movement.LinearMove;
import io.silverspoon.bulldog.devices.actuators.movement.Sweep;
import io.silverspoon.bulldog.devices.servo.Servo;
import io.silverspoon.bulldog.devices.servo.ServoListener;
import io.silverspoon.bulldog.devices.servo.TowerProMicroSG90;

public class ServoExample {
    final static Logger log = Logger.getLogger(ServoExample.class);

    public static void main(String[] args) throws IOException {
        // Grab the platform the application is running on
        Board board = Platform.createBoard();

        Pwm pwm = board.getPin(BBBNames.PWM_P8_13).as(Pwm.class);
        Servo servo = new TowerProMicroSG90(pwm);

        // Controlling the servo by setting the angle
        // Alternatively, the setPosition() method can be used to set
        // the angle.
        servo.setAngle(90.0f);
        BulldogUtil.sleepMs(1000);

        servo.setAngle(180.0f);
        BulldogUtil.sleepMs(1000);

        servo.setAngle(0.0f);
        BulldogUtil.sleepMs(1000);

        // Controlling the servo using the move API
        log.debug("Moving to 45 degrees");
        servo.moveTo(45.0f);

        // move 45 degrees (from 45 degrees to 90 degrees) in 3000 milliseconds
        log.debug("Moving to 90 degrees");
        servo.moveTo(90.0f, 2000);

        // performing a sweep (90 degrees to 180 degrees to 0 degrees in 5000
        // milliseconds)
        log.debug("Sweeping");
        servo.move(new Sweep(3000));

        // perform a linear move - move to position in specified time
        log.debug("Moving to 60 in 2 seconds degrees");
        servo.move(new LinearMove(60.0f, 2000));

        // perform an eased move (a smoothed move) to 120 degrees in 2000
        // milliseconds
        log.debug("Moving to 120 degrees in 2 seconds");
        servo.move(new EasedMove(new SineEasing(), 120.0f, 2000, EasingOptions.EaseInOut));

        // perform a smooth move
        log.debug("Moving to 0 degrees as fast as possible, but smoothed");
        servo.moveSmoothTo(0.0f);

        // move async
        servo.moveAsyncTo(120.0f, 3000);
        log.debug("SERVO now moving asynchronously");
        servo.awaitMoveCompleted();

        servo.moveSmoothAsyncTo(180.0f, 3000);
        while (servo.isMoving()) {
            log.debug("SERVO MOVING");
            BulldogUtil.sleepMs(1000);
        }

        // You can add listener to the servos
        /*servo.addServoListener(new ServoListener() {
			public void angleChanged(Servo servo, double oldAngle, double newAngle) {
				log.debug("Angle changed - [from: " + oldAngle + "] [to: " + newAngle + "]");
			}
			public void moveCompleted(Servo servo, double oldAngle, double newAngle) {
				log.debug("Servo completed move");
			}

		});*/

        servo.moveSmoothAsyncTo(90.0f, 500);
        servo.awaitMoveCompleted();

        servo.moveTo(10.0f);

        //servo.clearServoListeners();
    }
}
