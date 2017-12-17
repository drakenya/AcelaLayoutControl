package net.krolla.acela_layout_control.bridge.command;

public enum OpCode {
    NETWORK_ONLINE ((byte) 0x16),
    NETOWRK_RESET ((byte) 0x15),
    NETWORK_OFFLINE ((byte) 0x17),
    POLL_NETWORK ((byte) 0x18),
    READ_REVISION ((byte) 0x19),
    READ_ALL_SENSORS ((byte) 0x14);

    private final byte opCode;

    OpCode(byte opCode) {
        this.opCode = opCode;
    }

    public byte getOpCode() {
        return opCode;
    }
}
