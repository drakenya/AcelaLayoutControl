package net.krolla.acela_layout_control.bridge;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import net.krolla.acela_layout_control.bridge.command.BaseCommand;

import javax.xml.bind.DatatypeConverter;

public class Acela implements BridgeInterface {
    private SerialPort sp;
    private MessageReceivedListener listener;

    private SerialPortDataListener serialPortDataListener = new SerialPortDataListener() {
        @Override
        public int getListeningEvents() {
            return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) { return; }

            byte[] receivedData = new byte[sp.bytesAvailable()];
            int bytesRead = sp.readBytes(receivedData, receivedData.length);

            System.out.println("Received: " + DatatypeConverter.printHexBinary(receivedData));
//            listener.receivedMessage();
        }
    };

    public Acela(String port) {
        sp = SerialPort.getCommPort(port);
        sp.addDataListener(serialPortDataListener);
    }

    public boolean isOpen() { return sp.isOpen(); }

    public void open() { sp.openPort(); }

    public void close() { sp.closePort(); }

    public void sendCommand(BaseCommand command) {
        sp.writeBytes(command.getBinaryCommand(), command.getBinaryCommand().length);
        System.out.println("Sent command: " + DatatypeConverter.printHexBinary(command.getBinaryCommand()));
    }

    public void setReceivedListener(MessageReceivedListener listener) { this.listener = listener; }
}
