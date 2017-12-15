package net.krolla.acela_layout_control.bridge.response;

abstract class BaseResponseNoData extends BaseResponse {
    BaseResponseNoData(ResponseCode responseCode) { super(responseCode); }

    public boolean isResponseComplete() {
        return true;
    }
}
