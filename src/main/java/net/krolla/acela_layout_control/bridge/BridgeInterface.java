package net.krolla.acela_layout_control.bridge;

import net.krolla.acela_layout_control.bridge.command.BaseCommand;

public interface BridgeInterface {
    void open();

    void close();

    void sendCommand(BaseCommand command);

    void setReceivedListener(MessageReceivedListener listener);

    boolean isOpen();
}
