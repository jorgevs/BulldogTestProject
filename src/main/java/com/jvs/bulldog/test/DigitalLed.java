package com.jvs.bulldog.test;

import java.io.IOException;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.Signal;
import io.silverspoon.bulldog.core.gpio.DigitalOutput;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import org.apache.log4j.Logger;

public class DigitalLed {
    final static Logger log = Logger.getLogger(DigitalLed.class);

    public static void main(String[] args) throws IOException {

        // Get your platform
        final Board board = Platform.createBoard();

        DigitalOutput outputPin = board.getPin(BBBNames.P8_10).as(DigitalOutput.class);

        for (int i = 0; i < 10; i++) {
            outputPin.write(Signal.High);
            BulldogUtil.sleepMs(2000);
            outputPin.write(Signal.Low);
            BulldogUtil.sleepMs(2000);
        }
    }
}

