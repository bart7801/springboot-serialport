//Before setting the serial port, check in the device manager which port is assigned to the modem
//You also need to set a modem phone number

package com.bart.springbootserialport;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Component;

@Component
public class ModemConnection {

    byte newLine = 0x0D;
    byte endOfLine = 0x1A;

    SerialPort serialPort;

    public ModemConnection() throws SerialPortException, InterruptedException {
        this.serialPort = new SerialPort("COM4");
        serialPort.openPort();
        serialPort.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

//        System.out.println(readSms("0"));
//
//        sendSms("tel. number", "test message");
//
//        serialPort.writeString("AT+CGMI");
//        serialPort.writeByte(newLine);
//        Thread.sleep(1000);
//        System.out.println(serialPort.readString());
    }

    public String readSms(String memoryPlace) {
        try {
            serialPort.writeString("AT+CMGF=1");
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeString("AT+CMGR=" + memoryPlace);
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            return serialPort.readString();
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void sendSms(String telNumber, String message) {
        try {
            serialPort.writeString("AT+CMGF=1");
            Thread.sleep(1000);
            serialPort.writeByte(newLine);

            serialPort.writeString("AT+CMGS=\"" + telNumber + "\"");
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeString(message);
            Thread.sleep(1000);
            serialPort.writeByte(newLine);
            Thread.sleep(1000);
            serialPort.writeByte(endOfLine);

        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}