package net.krolla.acela_layout_control.bridge.command;

abstract public class BaseCommand {
    private final OpCode opCode;

    BaseCommand(OpCode opCode) {
        this.opCode = opCode;
    }

    public OpCode getOpCode() {
        return opCode;
    }

    public byte[] getBinaryCommand() {
        return new byte[] { opCode.getOpCode() };
    }
}
