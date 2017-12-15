package net.krolla.acela_layout_control.bridge.response;

import net.krolla.acela_layout_control.bridge.command.*;

public class ResponseFactory {
    public static BaseResponse getResponseByResponseCode(ResponseCode responseCode) {
        if (responseCode == ResponseCode.SENSOR_STATE_CHANGED) { return new SensorStateChangeResponse(responseCode); }
        if (responseCode == ResponseCode.COMMUNICATIONS_LOST) { return new CommunicationLostResponse(responseCode); }

        return null;
    }

    public static BaseResponse getResponseFromCommand(BaseCommand command, ResponseCode responseCode) throws Exception {
        if (command instanceof NetworkOnlineCommand) { return  new NetworkOnlineResponse(responseCode); }
        if (command instanceof NetworkOfflineCommand) { return  new NetworkOfflineResponse(responseCode); }
        if (command instanceof PollNetworkCommand) { return new PollNetworkResponse(responseCode); }
        if (command instanceof ReadRevisionCommand) { return new ReadRevisionResponse(responseCode); }

        throw new Exception("Not a supported type: " + command);
    }
}
