package com.jvs.bulldog.test;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.io.bus.i2c.I2cBus;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.devices.rtc.I2CDS3231RTC;
import org.apache.log4j.Logger;


public class I2C3231RTCExample {
    final static Logger log = Logger.getLogger(I2C3231RTCExample.class);
    final static int timeSleep = 1000;


    public static void main(String... args) {
        final Board board = Platform.createBoard();
        I2cBus i2cbus = board.getI2cBus(BBBNames.I2C_1);

        try {
            I2CDS3231RTC rtc = new I2CDS3231RTC(i2cbus, 0x68);
            byte[] buffer = rtc.readRTC();
            for (byte b : buffer) {
                log.debug("value: " + bcdtodec(b));
            }

            BulldogUtil.sleepMs(timeSleep);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public static int dectobcd(int val) {
        return ((val / 10 * 16) + (val % 10));
    }

    public static int bcdtodec(int val) {
        return ((val / 16 * 10) + (val % 16));
    }

}
