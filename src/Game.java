public class Game {
    private final String name;
    private final Ganre ganre;
    private final Type type;
    public enum Ganre { ACTION, SPORT, RACE }
    public enum Type { VIRTUAL, PHYSICAL }
    private Game(String name, Ganre ganre, Type type) {
        this.name = name;
        this.ganre = ganre;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public Ganre getGanre() {
        return ganre;
    }
    public Type getType() {
        return type;
    }
    public static class GameDisk {
        private final String description;
        private final Game data;
        private GameDisk(String name, Ganre ganre, String description) {
            this.description = description;
            this.data = new Game(name, ganre, Type.PHYSICAL);
        }
        public String getDescription() {
            return description;
        }
        public Game getData() {
            return data;
        }
    }
    public static class VirtualGame {
        private final Game data;
        private int rating;
        private VirtualGame(String name, Ganre ganre) {
            this.data = new Game(name, ganre, Type.VIRTUAL);
            this.rating = 0;
        }
        public Game getData() {
            return data;
        }
        public int getRating() {
            return rating;
        }
        public void setRating(int rating) {
            this.rating = rating;
        }
    }
    public static GameDisk getDisk(String name, Ganre ganre, String description) {
        return new GameDisk(name, ganre, description);
    }
    public static VirtualGame getVirtualGame(String name, Ganre ganre) {
        return new VirtualGame(name, ganre);
    }
}