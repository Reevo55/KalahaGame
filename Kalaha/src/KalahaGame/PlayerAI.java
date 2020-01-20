package KalahaGame;

import AlgorithmsAI.DesiciveTree;
import Constants.Constants;
import java.util.ArrayList;
import java.util.Random;

public class PlayerAI {

    private Kalaha kalahaGame;
    private Random random = new Random();
    private boolean whichPlayer;

    public PlayerAI(boolean whichPlayer)
    {
        this.whichPlayer = whichPlayer;
    }

    private int makeBestMove(Kalaha currentState)
    {
        whichPlayer = currentState.isWhichPlayerTurn();
        kalahaGame = currentState.copyKalaha();

        int bestScore = -9999;
        int move = random.nextInt(currentState.getSizeOfPlayerBoard());

        for(int i=0; i < currentState.getSizeOfPlayerBoard(); i++)
        {
            int help = kalahaGame.makeMove(i);
            if(help != Constants.ERROR) {

            int currentScore;

            if (whichPlayer == Constants.FIRST_PLAYER_TURN) // obliczenia czy ruch ten jest lepszy od ostatniego najlepszego
            {
                currentScore = kalahaGame.getFirstPlayerPoints() - kalahaGame.getSecondPlayerPoints();
                if (currentScore > bestScore) {
                    move = i;
                    bestScore = currentScore;
                }
            } else {
                currentScore = kalahaGame.getSecondPlayerPoints() - kalahaGame.getFirstPlayerPoints();
                if (currentScore > bestScore) {
                    move = i;
                    bestScore = currentScore;
                }
            }

            kalahaGame = currentState.copyKalaha(); // ustawienie do aktualnego stanu przed obliczeniem korzysci ruchu
            }
        }
        return move;
    }
    public int playerTurn(Kalaha currentState)
    {
        int move = 0;
        DesiciveTree dt = new DesiciveTree(currentState);
        dt.createTree(10);
        if(currentState.checkIfMoveIsValid(dt.bestMoveDepthSearch(dt.getRoot(), currentState.isWhichPlayerTurn())) != Constants.ERROR)
        {
            dt.bestMoveDepthSearch(dt.getRoot(), currentState.isWhichPlayerTurn());
        }
        else move = makeBestMove(currentState);
        return move;
        //return makeBestMove(currentState);
    }
}
