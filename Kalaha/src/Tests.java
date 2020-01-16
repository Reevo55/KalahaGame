import Constants.Constants;
import GUI.KalahaGUI;
import KalahaGame.Kalaha;
import KalahaGame.PlayerAI;
import Server.GameMonitor;

public class Tests {
    public static void main(String[] args) throws CloneNotSupportedException {
        GameMonitor gameMonitor = new GameMonitor();

        gameMonitor.startGame();
    }
}
