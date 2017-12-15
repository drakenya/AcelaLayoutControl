package net.krolla.acela_layout_control.bridge;

import net.krolla.acela_layout_control.bridge.response.BaseResponse;

public interface MessageReceivedListener {
    void receivedMessage(BaseResponse response);
}
