package net.krolla.acela_layout_control.bridge.response;

import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Iterator;

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

    public ResponseCode getResponseCode() { return responseCode; }

    public byte[] getData() {
        byte[] ret = new byte[data.size()];
        Iterator<Byte> iterator = data.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next();
        }

        return ret;
    }
}
