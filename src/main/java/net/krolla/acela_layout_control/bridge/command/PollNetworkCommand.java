package net.krolla.acela_layout_control.bridge.command;

public class PollNetworkCommand extends BaseCommand {
    public PollNetworkCommand() {
        super(OpCode.POLL_NETWORK);
    }
}
