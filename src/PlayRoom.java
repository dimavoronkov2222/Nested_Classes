import java.util.Arrays;
import java.util.Comparator;
public class PlayRoom {
    public static void main(String[] args) {
        Game.GameDisk[] physicalGames = {
                Game.getDisk("Game1", Game.Ganre.ACTION, "Action-packed game"),
                Game.getDisk("Game2", Game.Ganre.SPORT, "Sports simulator"),
                Game.getDisk("Game3", Game.Ganre.RACE, "Racing adventure"),
                Game.getDisk("Game4", Game.Ganre.ACTION, "Another action game")
        };
        Game.VirtualGame[] virtualGames = {
                Game.getVirtualGame("VGame1", Game.Ganre.RACE),
                Game.getVirtualGame("VGame2", Game.Ganre.ACTION),
                Game.getVirtualGame("VGame3", Game.Ganre.SPORT),
                Game.getVirtualGame("VGame4", Game.Ganre.RACE)
        };
        virtualGames[0].setRating(4);
        virtualGames[1].setRating(3);
        virtualGames[2].setRating(5);
        virtualGames[3].setRating(2);
        GameConsole console = new GameConsole("Sony", "PS5-12345");
        console.getFirstGamepad().powerOn();
        console.loadGame(physicalGames[0].getData());
        console.playGame();
        Arrays.sort(physicalGames, Comparator.comparing(disk -> disk.getData().getGanre()));
        Arrays.sort(virtualGames, Comparator.comparingInt(Game.VirtualGame::getRating));
        System.out.println("Physical games sorted by genre:");
        for (Game.GameDisk game : physicalGames) {
            System.out.println(game.getData().getName() + " - " + game.getData().getGanre());
        }
        System.out.println("\nVirtual games sorted by rating:");
        for (Game.VirtualGame game : virtualGames) {
            System.out.println(game.getData().getName() + " - Rating: " + game.getRating());
        }
    }
}