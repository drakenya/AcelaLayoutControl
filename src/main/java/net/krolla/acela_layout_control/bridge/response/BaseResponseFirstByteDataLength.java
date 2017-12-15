package net.krolla.acela_layout_control.bridge.response;

abstract class BaseResponseFirstByteDataLength extends BaseResponse {
    BaseResponseFirstByteDataLength(ResponseCode responseCode) { super(responseCode); }

    public boolean isResponseComplete() {
        if (data == null || data.size() == 0) { return false; }

        if (!isResultSuccessful()) { return false; }

        int dataSize = data.get(0);
        return data.size() == dataSize;
    }
}
