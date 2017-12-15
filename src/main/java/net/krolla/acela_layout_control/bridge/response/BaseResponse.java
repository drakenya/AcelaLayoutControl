package net.krolla.acela_layout_control.bridge.response;

import java.util.ArrayList;

abstract public class BaseResponse {
    private final ResponseCode responseCode;
    ArrayList<Byte> data = new ArrayList<>();

    BaseResponse(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isResultSuccessful() {
        return responseCode == ResponseCode.PROCESSED_SUCCESSFULLY || responseCode == ResponseCode.SENSOR_STATE_CHANGED;
    }

    abstract public boolean isResponseComplete();

    public void addDataByte(byte dataByte) {
        data.add(dataByte);
    }
}
