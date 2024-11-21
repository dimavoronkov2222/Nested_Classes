import java.util.Objects;
public class GameConsole {
    private final String brand;
    private final String model;
    private final String serial;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private boolean isOn = false;
    public GameConsole(String brand, String serial) {
        this.brand = brand;
        this.serial = serial;
        this.model = "Default Model";
        this.firstGamepad = new Gamepad(brand, 1);
        this.secondGamepad = new Gamepad(brand, 2);
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public String getSerial() {
        return serial;
    }
    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }
    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }
    public boolean isOn() {
        return isOn;
    }
    public void setOn(boolean on) {
        isOn = on;
    }
    public class Gamepad {
        private final String brand;
        private final String consoleSerial;
        private final int connectedNumber;
        private final String color = "Black";
        private double chargeLevel = 100.0;
        private boolean isOn = false;
        public Gamepad(String brand, int connectedNumber) {
            this.brand = brand;
            this.consoleSerial = GameConsole.this.serial;
            this.connectedNumber = connectedNumber;
        }
        public String getBrand() {
            return brand;
        }
        public String getConsoleSerial() {
            return consoleSerial;
        }
        public int getConnectedNumber() {
            return connectedNumber;
        }
        public String getColor() {
            return color;
        }
        public double getChargeLevel() {
            return chargeLevel;
        }
        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }
        public boolean isOn() {
            return isOn;
        }
        public void setOn(boolean on) {
            isOn = on;
        }
    }
}