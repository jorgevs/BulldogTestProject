package com.jvs.bulldog.test;

import java.io.IOException;

import org.apache.log4j.Logger;
import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.io.serial.SerialDataEventArgs;
import io.silverspoon.bulldog.core.io.serial.SerialDataListener;
import io.silverspoon.bulldog.core.io.serial.SerialPort;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;

/**
 * For this example, UART2 on the BeagleboneBlack is connected to UART1.
 * UART2 reads the data from UART1 event driven and writes to UART1.
 * UART1 reads the data from UART2 synchronously and writes to UART2.
 *
 * @author Datenheld
 */
public class SerialExample {
    final static Logger log = Logger.getLogger(SerialExample.class);

    public static void main(String[] args) throws IOException {

        //Get your platform
        final Board board = Platform.createBoard();

        //Retrieve a serial port (UART2) and configure it
        SerialPort serial2 = board.getSerialPort(BBBNames.UART2);
        serial2.setBaudRate(9600);
        serial2.setBlocking(false);
        serial2.open();

        //Add a listener... This will print the data once it is
        //available for read on the port
        serial2.addListener(new SerialDataListener() {
            public void onSerialDataAvailable(SerialDataEventArgs args) {
                log.debug(args.getDataAsString());
            }

        });
        ;

        //Retrieve another serial port, UART1 in this case
        SerialPort serial1 = board.getSerialPort(BBBNames.UART1);
        serial1.setBaudRate(9600);
        serial1.setBlocking(false);
        serial1.open();

        for (int i = 0; i < 10; i++) {
            serial2.writeString("Hello Serial1 - Greetings from Serial2\n");

            //Sleep, give UART2 time to transmit the data
            BulldogUtil.sleepMs(50);

            log.debug(serial1.readString());
            serial1.writeString("Hello Serial2 - Greetings from Serial1\n");
            BulldogUtil.sleepMs(500);
        }

        serial2.close();
        serial1.close();
    }
}
