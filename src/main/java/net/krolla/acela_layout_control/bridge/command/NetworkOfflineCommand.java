package net.krolla.acela_layout_control.bridge.command;

public class NetworkOfflineCommand extends BaseCommand {
    public NetworkOfflineCommand() {
        super(OpCode.NETWORK_OFFLINE);
    }
}
