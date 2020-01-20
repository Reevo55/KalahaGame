import AlgorithmsAI.DesiciveTree;
import Constants.Constants;
import KalahaGame.Kalaha;
import Server.GameMonitor;

public class Tests {
    public static void main(String[] args) throws CloneNotSupportedException {
        GameMonitor gameMonitor = new GameMonitor();

        gameMonitor.startAIvsAIGame(12,5);

        /*Kalaha kalaha = new Kalaha();
        kalaha.setEverything(12,5);

        DesiciveTree dt = new DesiciveTree(kalaha);

        dt.createTree(5);

        System.out.println(dt.bestMoveDepthSearch(dt.getRoot(), Constants.FIRST_PLAYER_TURN));*/
    }
}
