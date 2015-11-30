package com.jvs.bulldog.test;

import io.silverspoon.bulldog.beagleboneblack.BBBNames;
import io.silverspoon.bulldog.core.platform.Board;
import io.silverspoon.bulldog.core.platform.Platform;
import io.silverspoon.bulldog.core.util.BulldogUtil;
import io.silverspoon.bulldog.devices.lcd.*;
import io.silverspoon.bulldog.linux.io.LinuxI2cBus;
import org.apache.log4j.Logger;

public class I2CLcdDisplayExample {
    final static Logger log = Logger.getLogger(I2CLcdDisplayExample.class);
    final static int timeSleep = 1000;

    public final static int LCD_ROW_1 = 0;
    public final static int LCD_ROW_2 = 1;

    public static void main(String... args) {
        final Board board = Platform.createBoard();
        LinuxI2cBus i2cbus = (LinuxI2cBus) board.getI2cBus(BBBNames.I2C_1);

        try {
            I2CLcdDisplay lcd = new I2CLcdDisplay(LcdMode.Display2x16, LcdFont.Font_5x8, i2cbus, 0x20);

            log.debug("INITIALIZATION");
            lcd.write("Initialization...");
            BulldogUtil.sleepMs(timeSleep);

            log.debug("HOLA MUNDO");
            lcd.clear();
            lcd.write("Hola mundo!");
            BulldogUtil.sleepMs(timeSleep);

            log.debug("GO HOME");
            lcd.clear();
            lcd.home();
            lcd.write("Go home");
            BulldogUtil.sleepMs(timeSleep);

            log.debug("GO TO (1,1)");
            lcd.clear();
            lcd.setCursorPosition(1,1);
            lcd.write("(1,1)");
            BulldogUtil.sleepMs(timeSleep);

            log.debug("CLEAR");
            lcd.clear();
            lcd.write("Clear screen");
            BulldogUtil.sleepMs(timeSleep);

            log.debug("SHOW CURSOR");
            lcd.clear();
            lcd.home();
            lcd.write("Show cursor");
            lcd.showCursor(true);
            BulldogUtil.sleepMs(timeSleep);

            log.debug("CURSOR BLINKING");
            lcd.clear();
            lcd.home();
            lcd.write("Blinking");
            lcd.blinkCursor(true);
            BulldogUtil.sleepMs(timeSleep);

            log.debug("GO TO (5,1)");
            lcd.clear();
            lcd.setCursorPosition(5,1);
            lcd.write("(5,1)");
            BulldogUtil.sleepMs(timeSleep);

            log.debug("STOP BLINKING");
            lcd.clear();
            lcd.home();
            lcd.write("Stop Blinking");
            lcd.blinkCursor(false);
            BulldogUtil.sleepMs(timeSleep);

            log.debug("HIDE CURSOR");
            lcd.clear();
            lcd.home();
            lcd.write("Hide cursor");
            lcd.showCursor(false);
            BulldogUtil.sleepMs(timeSleep);

            log.debug("DISPLAY OFF");
            lcd.clear();
            lcd.home();
            lcd.write("Display off");
            lcd.off();
            BulldogUtil.sleepMs(timeSleep);

            log.debug("DISPLAY ON");
            lcd.clear();
            lcd.home();
            lcd.write("Display on");
            lcd.on();
            BulldogUtil.sleepMs(timeSleep);



            // clear LCD
            lcd.clear();
            Thread.sleep(1000);

            // write line 1 to LCD
            lcd.write(LCD_ROW_1, "The Pi4J Project");

            // write line 2 to LCD
            lcd.write(LCD_ROW_2, "----------------");

            // line data replacement
            for(int index = 0; index < 5; index++)
            {
                lcd.write(LCD_ROW_2, "----------------");
                Thread.sleep(500);
                lcd.write(LCD_ROW_2, "****************");
                Thread.sleep(500);
            }
            lcd.write(LCD_ROW_2, "----------------");

            // single character data replacement
            for(int index = 0; index < lcd.getColumnsCount(); index++) {
                lcd.write(LCD_ROW_2, index, ">");
                if(index > 0)
                    lcd.write(LCD_ROW_2, index - 1, "-");
                Thread.sleep(300);
            }
            for(int index = lcd.getColumnsCount()-1; index >= 0 ; index--) {
                lcd.write(LCD_ROW_2, index, "<");
                if(index < lcd.getColumnsCount()-1)
                    lcd.write(LCD_ROW_2, index + 1, "-");
                Thread.sleep(300);
            }

            // left alignment, full line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.writeln(LCD_ROW_2, "<< LEFT");
            Thread.sleep(1000);

            // right alignment, full line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.writeln(LCD_ROW_2, "RIGHT >>", LCDTextAlignment.ALIGN_RIGHT);
            Thread.sleep(1000);

            // center alignment, full line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.writeln(LCD_ROW_2, "<< CENTER >>", LCDTextAlignment.ALIGN_CENTER);
            Thread.sleep(1000);

            // mixed alignments, partial line data
            lcd.write(LCD_ROW_2, "----------------");
            Thread.sleep(500);
            lcd.write(LCD_ROW_2, "<L>", LCDTextAlignment.ALIGN_LEFT);
            lcd.write(LCD_ROW_2, "<R>", LCDTextAlignment.ALIGN_RIGHT);
            lcd.write(LCD_ROW_2, "CC", LCDTextAlignment.ALIGN_CENTER);
            Thread.sleep(3000);


            log.debug("FINALIZATION");
            lcd.clear();
            lcd.home();
            lcd.write("Finalization");
            BulldogUtil.sleepMs(timeSleep);

        }catch(Exception e){
            System.out.print(e);
        }
    }
}

