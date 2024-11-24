public class GameConsole implements Powered {
    private final String brand;
    private final String serial;
    private final String model;
    private final Gamepad firstGamepad;
    private final Gamepad secondGamepad;
    private Game activeGame;
    private boolean isOn = false;
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
    public String getSerial() {
        return serial;
    }
    public String getModel() {
        return model;
    }
    public Gamepad getFirstGamepad() {
        return firstGamepad;
    }
    public Gamepad getSecondGamepad() {
        return secondGamepad;
    }
    public Game getActiveGame() {
        return activeGame;
    }
    public void setActiveGame(Game activeGame) {
        this.activeGame = activeGame;
    }
    @Override
    public void powerOn() {
        isOn = true;
        System.out.println("Console powered on.");
    }
    @Override
    public void powerOff() {
        isOn = false;
        System.out.println("Console powered off.");
    }
    public void loadGame(Game game) {
        if (isOn) {
            activeGame = game;
            System.out.println("Game " + game.getName() + " is loading.");
        } else {
            System.out.println("Console is off. Cannot load game.");
        }
    }
    public void playGame() {
        if (activeGame == null) {
            System.out.println("No game loaded.");
            return;
        }
        checkStatus();
        if (!isOn) return;
        System.out.println("Playing " + activeGame.getName());
        for (Gamepad gamepad : new Gamepad[]{firstGamepad, secondGamepad}) {
            if (gamepad.isOn()) {
                System.out.println("Gamepad " + gamepad.getConnectedNumber() + " charge: " + gamepad.getChargeLevel() + "%");
                gamepad.setChargeLevel(gamepad.getChargeLevel() - 10);
                if (gamepad.getChargeLevel() <= 0) {
                    gamepad.powerOff();
                }
            }
        }
    }
    private void checkStatus() {
        if (!firstGamepad.isOn() && !secondGamepad.isOn()) {
            System.out.println("Connect a gamepad.");
            waitingCounter++;
            if (waitingCounter > 5) {
                powerOff();
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
        @Override
        public void powerOn() {
            isOn = true;
            GameConsole.this.powerOn();
            System.out.println("Gamepad " + connectedNumber + " powered on.");
        }
        @Override
        public void powerOff() {
            isOn = false;
            System.out.println("Gamepad " + connectedNumber + " powered off.");
        }
    }
}