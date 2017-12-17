package net.krolla.acela_layout_control.bridge.command;

public class ReadAllSensorsCommand extends BaseCommand {
    public ReadAllSensorsCommand() {
        super(OpCode.READ_ALL_SENSORS);
    }
}
