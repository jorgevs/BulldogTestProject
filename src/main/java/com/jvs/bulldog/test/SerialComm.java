package com.jvs.bulldog.test;

import java.io.IOException;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.io.serial.SerialPort;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import org.apache.log4j.Logger;

public class SerialComm {
    final static Logger log = Logger.getLogger(SerialComm.class);

    public static void main(String[] args) throws IOException {

        //Get your platform
        final Board board = Platform.createBoard();

        //Retrieve a serial port (UART2) and configure it
        SerialPort serial2 = board.getSerialPort(BBBNames.UART2);
        serial2.setBaudRate(9600);
        serial2.setBlocking(false);
        serial2.open();

        for (int i = 0; i < 10; i++) {

            serial2.writeString("Hello COMX - Greetings from Serial2\n\r");
            //Sleep, give UART2 time to transmit the data
            BulldogUtil.sleepMs(500);
        }
        serial2.close();
    }

}
