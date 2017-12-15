package net.krolla.acela_layout_control.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Network {
    private final BooleanProperty isOnline = new SimpleBooleanProperty(this, "isOnline", false);

    public Network() {
        this(false);
    }

    private Network(boolean isOnline) {
        this.isOnline.set(isOnline);
    }

    public final boolean getIsOnline() {
        return isOnline.get();
    }

    public final void setIsOnline(boolean isOnline) {
        isOnlineProperty().set(isOnline);
    }

    public final BooleanProperty isOnlineProperty() {
        return isOnline;
    }
}
