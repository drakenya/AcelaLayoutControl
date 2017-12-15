package net.krolla.acela_layout_control.bridge.command;

public class ReadRevisionCommand extends BaseCommand {
    public ReadRevisionCommand() {
        super(OpCode.READ_REVISION);
    }
}
