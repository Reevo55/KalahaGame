package Server;

import Constants.Constants;
import GUI.KalahaGUI;
import KalahaGame.Kalaha;
import KalahaGame.PlayerAI;

public class GameMonitor {


    public void startGame()
    {
        PlayerAI player1 = new PlayerAI(Constants.FIRST_PLAYER_TURN);
        PlayerAI player2 = new PlayerAI(Constants.SECOND_PLAYER_TURN);

        int kalahaBoardSize = 12;
        int startingValues = 4;

        Kalaha kalaha = new Kalaha();
        kalaha.setEverything(kalahaBoardSize, startingValues);

        while(!kalaha.isFinished())
        {
            int move;
            System.out.print("Gracz ");
            if(kalaha.isWhichPlayerTurn() == Constants.FIRST_PLAYER_TURN)
            {
                move = player1.playerTurn(kalaha);
                kalaha.makeMove(move);
                System.out.print(" 1 ");
            }
            else
            {
                move = player2.playerTurn(kalaha);
                kalaha.makeMove(move);
                System.out.print(" 2 ");
            }

            System.out.println("Wykonany ruch to: " + move);

            KalahaGUI.printGUI(kalaha);
        }
    }
}
