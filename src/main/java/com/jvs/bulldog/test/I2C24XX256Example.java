package com.jvs.bulldog.test;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.io.bus.i2c.I2cBus;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.devices.eeprom.I2C24XX256;
import org.apache.log4j.Logger;


public class I2C24XX256Example {
    final static Logger log = Logger.getLogger(I2C24XX256Example.class);
    final static int timeSleep = 1000;

    public final byte EEPROM_ADDR = 0x50;

    public static void main(String[] args) {
        final Board board = Platform.createBoard();
        I2cBus i2cbus = board.getI2cBus(BBBNames.I2C_1);

        try {
            I2C24XX256 eeprom = new I2C24XX256(i2cbus, 0x50);

            log.debug("I2C24XX256 INITIALIZATION");
            //eeprom.writeString("Hello");
            eeprom.writeByteToRegister(0x00, 5);
            log.debug("Data written");

            BulldogUtil.sleepMs(timeSleep);

            int value = eeprom.readByteFromRegister(0x00);
            log.debug("Reads: " + value);


        } catch (Exception e) {
            System.out.print(e);
        }
    }

}