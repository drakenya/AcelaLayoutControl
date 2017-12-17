package net.krolla.acela_layout_control.bridge.response;

public class ReadAllSensorsResponse extends BaseResponseFirstByteDataLength {
    public ReadAllSensorsResponse(ResponseCode responseCode) {
        super(responseCode);
    }
}
