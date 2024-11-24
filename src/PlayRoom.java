import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
public class PlayRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game.GameDisk[] physicalGames = {
                Game.getDisk("Game1", Game.Ganre.ACTION, "Action game"),
                Game.getDisk("Game2", Game.Ganre.SPORT, "Sport game"),
                Game.getDisk("Game3", Game.Ganre.RACE, "Racing game"),
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
        System.out.println("Console created: " + console.getBrand() + " - " + console.getSerial());
        System.out.println("\nAvailable physical games:");
        for (int i = 0; i < physicalGames.length; i++) {
            System.out.println((i + 1) + ". " + physicalGames[i].getData().getName() +
                    " (" + physicalGames[i].getData().getGanre() + ")");
        }
        System.out.println("Enter the number of the physical game to load:");
        int gameChoice = scanner.nextInt();
        while (gameChoice < 1 || gameChoice > physicalGames.length) {
            System.out.println("Invalid choice. Please try again:");
            gameChoice = scanner.nextInt();
        }
        console.loadGame(physicalGames[gameChoice - 1].getData());
        System.out.println("\nTurning on gamepads...");
        console.getFirstGamepad().powerOn();
        console.getSecondGamepad().powerOn();
        try {
            console.playGame();
        } catch (NoActivityException e) {
            System.out.println(e.getMessage());
        }
        Arrays.sort(physicalGames, Comparator.comparing(disk -> disk.getData().getGanre()));
        Arrays.sort(virtualGames, Comparator.comparingInt(Game.VirtualGame::getRating));
        System.out.println("\nSorted Physical Games:");
        for (Game.GameDisk game : physicalGames) {
            System.out.println(game.getData().getName() + " - " + game.getData().getGanre());
        }
        System.out.println("\nSorted Virtual Games:");
        for (Game.VirtualGame game : virtualGames) {
            System.out.println(game.getData().getName() + " - Rating: " + game.getRating());
        }
        scanner.close();
    }
}