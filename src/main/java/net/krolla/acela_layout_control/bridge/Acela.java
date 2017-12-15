package net.krolla.acela_layout_control.bridge;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import net.krolla.acela_layout_control.bridge.command.BaseCommand;
import net.krolla.acela_layout_control.bridge.response.*;

import javax.xml.bind.DatatypeConverter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Acela implements BridgeInterface {
    private SerialPort sp;
    private MessageReceivedListener listener;

    private Queue<BaseCommand> sentCommands = new LinkedList<>();
    private BaseResponse currentResponse = null;

    private SerialPortDataListener serialPortDataListener = new SerialPortDataListener() {
        @Override
        public int getListeningEvents() {
            return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
        }

        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {

            if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                return;
            }

            ArrayList<BaseResponse> processedResponses = new ArrayList<>();

            synchronized (this) {
                byte[] receivedData = new byte[sp.bytesAvailable()];
                int bytesRead = sp.readBytes(receivedData, receivedData.length);

                for (byte b : receivedData) {
                    if (currentResponse == null) {
                        currentResponse = ResponseFactory.getResponseByResponseCode(ResponseCode.valueOf(b));
                        if (currentResponse == null) {
                            try {
                                currentResponse = ResponseFactory.getResponseFromCommand(sentCommands.peek(), ResponseCode.valueOf(b));
                            } catch (Exception e) {
                                System.out.println(e.toString());
                                return;
                            }
                        }
                    } else {
                        currentResponse.addDataByte(b);
                    }

                    if (currentResponse.isResponseComplete()) {
                        processedResponses.add(currentResponse);
                        if (!(currentResponse instanceof SensorStateChangeResponse || currentResponse instanceof CommunicationLostResponse)) { sentCommands.remove(); }
                        currentResponse = null;
                    }
                }
            }

            for (BaseResponse r : processedResponses) {
                System.out.println("Complete response: " + r.getResponseCode() + "-" + DatatypeConverter.printHexBinary(r.getData()));
                listener.receivedMessage(r);
            }
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
        synchronized (this) {
            sentCommands.add(command);
            sp.writeBytes(command.getBinaryCommand(), command.getBinaryCommand().length);
            System.out.println("Sent command: " + DatatypeConverter.printHexBinary(command.getBinaryCommand()));
        }
    }

    public void setReceivedListener(MessageReceivedListener listener) { this.listener = listener; }
}
