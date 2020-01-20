package AlgorithmsAI;

import Constants.Constants;
import KalahaGame.Kalaha;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DesiciveTree {
    private Node root;
    private Kalaha startingState;
    private boolean whichPlayer;

    public DesiciveTree(Node root, Kalaha startingState, boolean whichPlayer) {
        this.root = root;
        this.startingState = startingState;
        this.whichPlayer = whichPlayer;
    }
    public DesiciveTree(Kalaha currentState) {
        this.startingState = currentState;
    }


    public void createTree(int depth)
    {
        ArrayList<Node> arrNodes = new ArrayList<>(startingState.getKalahaBoardSize());
        for(int i=0 ; i < startingState.getSizeOfPlayerBoard(); i++){arrNodes.add(null);}

        root = new Node(startingState.getFirstPlayerPoints() - startingState.getSecondPlayerPoints(), startingState.copyKalaha(), arrNodes);

        fillTree(root, 4);
    }

    private void fillTree(Node parent, int depth)
    {
        if(depth > 0) {
            for (int i = 0; parent.getNextMoves().size() > i; i++) {
                ArrayList<Node> arrNodes = new ArrayList<>(startingState.getKalahaBoardSize());
                for(int j=0 ; j < startingState.getSizeOfPlayerBoard(); j++){arrNodes.add(null);}

                Kalaha kalahaCurrent = parent.getCurrentState().copyKalaha();
                if(kalahaCurrent.makeMove(i) == Constants.ERROR) return;

                Node node = new Node(kalahaCurrent.getFirstPlayerPoints() - kalahaCurrent.getSecondPlayerPoints(), kalahaCurrent, arrNodes);

                parent.getNextMoves().set(i, node);

                fillTree(node, depth - 1);
            }
        }
    }

    public int bestMoveDepthSearch(Node node, boolean whichPlayer)
    {
        ArrayList<Integer> sumPointsMoves = new ArrayList<>();

        for(int i=0; i < node.getCurrentState().getSizeOfPlayerBoard(); i++)
        {
            if(node.getNextMoves().get(i) != null) sumPointsMoves.add(depth(node.getNextMoves().get(i)));
        }

        int move = getIndexOfLargest(sumPointsMoves);

        return move;
    }

    private int depth(Node currentNode)
    {
        int max = 0;

        for(int i=0; i < currentNode.getNextMoves().size(); i++) {
            int points = currentNode.getPoints();
            if(currentNode.getNextMoves().get(i) != null) {
                max += points + depth(currentNode.getNextMoves().get(i));
            } else max += points;
        }

        return max;
    }

    private int getIndexOfLargest(ArrayList<Integer> array )
    {
        if ( array == null || array.size() == 0 ) return 0; // null or empty

        int largest = 0;
        for ( int i = 1; i < array.size(); i++ )
        {
            if ( array.get(i) > array.get(largest ) ) largest = i;
        }
        return largest; // position of the first largest found
    }

    private int getIndexOfSmallest(ArrayList<Integer> array )
    {
        if ( array == null || array.size() == 0 ) return 0; // null or empty

        int smallest = 0;
        for ( int i = 1; i < array.size(); i++ )
        {
            if ( array.get(i) < array.get(smallest ) ) smallest = i;
        }
        return smallest; // position of the first largest found
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Kalaha getStartingState() {
        return startingState;
    }

    public void setStartingState(Kalaha startingState) {
        this.startingState = startingState;
    }

    public boolean isWhichPlayer() {
        return whichPlayer;
    }

    public void setWhichPlayer(boolean whichPlayer) {
        this.whichPlayer = whichPlayer;
    }
}

class Node{

    private int points;
    private Kalaha currentState;
    private ArrayList<Node> nextMoves;

    public Node(int points, Kalaha currentState, ArrayList<Node> nextMoves) {
        this.points = points;
        this.currentState = currentState;
        this.nextMoves = nextMoves;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Kalaha getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Kalaha currentState) {
        this.currentState = currentState;
    }

    public ArrayList<Node> getNextMoves() {
        return nextMoves;
    }

    public void setNextMoves(ArrayList<Node> nextMoves) {
        this.nextMoves = nextMoves;
    }
}
