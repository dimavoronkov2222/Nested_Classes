public class GameConsole {
    private final String brand;
    private final String model;
    private final String serial;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private boolean isOn = false;
    private Game activeGame;
    private int waitingCounter = 0;
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
    public void loadGame(Game game) {
        this.activeGame = game;
        System.out.println("Loading game: " + game.getName());
    }
    public void playGame() throws NoActivityException {
        if (!isOn) {
            System.out.println("Console is off. Please turn it on first.");
            return;
        }
        if (activeGame == null) {
            System.out.println("No game loaded. Please load a game first.");
            return;
        }
        checkStatus();
        System.out.println("Playing game: " + activeGame.getName());
        if (firstGamepad.isOn()) {
            firstGamepad.decreaseCharge();
        }
        if (secondGamepad.isOn()) {
            secondGamepad.decreaseCharge();
        }
    }
    private void checkStatus() throws NoActivityException {
        if (!firstGamepad.isOn() && !secondGamepad.isOn()) {
            System.out.println("Connect a gamepad.");
            waitingCounter++;
            if (waitingCounter > 5) {
                isOn = false;
                throw new NoActivityException("Console shutting down due to inactivity.");
            }
        } else {
            waitingCounter = 0;
        }
    }
    public class Gamepad implements Powered {
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
        public void powerOn() {
            this.isOn = true;
            GameConsole.this.setOn(true);
        }
        public void powerOff() {
            this.isOn = false;
        }
        public void decreaseCharge() {
            if (chargeLevel > 0) {
                chargeLevel -= 10.0;
                if (chargeLevel <= 0) {
                    powerOff();
                    System.out.println("Gamepad " + connectedNumber + " turned off due to low battery.");
                }
            }
        }
    }
}