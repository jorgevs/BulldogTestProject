package com.jvs.bulldog.test;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.io.bus.i2c.I2cBus;
import io.silverspoon.bulldog.core.io.bus.i2c.I2cConnection;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.devices.lcd.I2CLcd;

import java.io.IOException;

import org.apache.log4j.Logger;

public class I2C_LcdExample {
    final static Logger log = Logger.getLogger(I2C_LcdExample.class);

    public static void main(String... args) throws IOException {

        // Get your platform
        final Board board = Platform.createBoard();

        I2cBus bus = board.getI2cBus(BBBNames.I2C_1);

        I2cConnection busConn = bus.createI2cConnection(0x20);
        log.debug("busConn: " + busConn.toString());
        //log.debug(busConn.readByte());
        I2CLcd lcd = new I2CLcd(busConn);

        // Let's assume we have got a device on address xx
        for (int i = 0; i < 10; i++) {
            lcd.write("Hello world!");
            BulldogUtil.sleepMs(50);
        }
    }
}
