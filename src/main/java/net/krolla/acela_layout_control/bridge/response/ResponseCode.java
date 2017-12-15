package net.krolla.acela_layout_control.bridge.response;

import java.util.HashMap;
import java.util.Map;

public enum ResponseCode {
    PROCESSED_SUCCESSFULLY ((byte) 0x00),
    NETWORK_OFFLINE ((byte) 0x01),
    ADDRESS_OUT_OF_RANGE ((byte) 0x02),
    INVALID_COMMAND_SENT ((byte) 0x03),
    SENSOR_STATE_CHANGED ((byte) 0x81),
    COMMUNICATIONS_LOST ((byte) 0x82);

    private final byte responseCode;
    private static Map<Byte, ResponseCode> map = new HashMap<>();

    ResponseCode(byte responseCode) { this.responseCode = responseCode; }

    public byte getResponseCode() { return responseCode; }

    static {
        for (ResponseCode responseCode : ResponseCode.values()) {
            map.put(responseCode.getResponseCode(), responseCode);
        }
    }

    public static ResponseCode valueOf(byte responseCode) { return map.get(responseCode); }
}
