package net.krolla.acela_layout_control.bridge.response;

public enum ResponseCode {
    PROCESSED_SUCCESSFULLY ((byte) 0x00),
    NETWORK_OFFLINE ((byte) 0x01),
    ADDRESS_OUT_OF_RANGE ((byte) 0x02),
    INVALID_COMMAND_SENT ((byte) 0x03),
    SENSOR_STATE_CHANGED ((byte) 0x81),
    COMMUNICATIONS_LOST ((byte) 0x82);

    private final byte responseCode;

    ResponseCode(byte responseCode) {
        this.responseCode = responseCode;
    }

    public byte getResponseCode() {
        return responseCode;
    }
}
