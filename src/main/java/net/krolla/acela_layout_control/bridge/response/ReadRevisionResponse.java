package net.krolla.acela_layout_control.bridge.response;

public class ReadRevisionResponse extends BaseResponse {
    public ReadRevisionResponse(ResponseCode responseCode) {
        super(responseCode);
    }

    public boolean isResponseComplete() {
        return data.size() == 2;
    }
}
