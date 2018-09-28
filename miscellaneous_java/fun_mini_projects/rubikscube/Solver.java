package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * Immutable.
 * 
 * @author AlexanderWu
 */
public class Solver {

    private int numMoves;
    private Stack<WorldState> solutionSequence;

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     * 
     * @param initial
     *            initial state
     */
    public Solver(WorldState initial) {

        MinPQ<SearchNode> moves = new MinPQ<>();
        // no moves have been made yet, there is no previous search node
        SearchNode bestMove = new SearchNode(initial, 0, null);

        while (!bestMove.state.isGoal()) {
            for (WorldState neighbor : bestMove.state.neighbors()) {
                if (bestMove.prevNode == null || !neighbor.equals(bestMove.prevNode.state)) {
                    moves.insert(new SearchNode(neighbor, bestMove.prevMoves + 1, bestMove));
                }
            }
            bestMove = moves.delMin();
        }

        numMoves = bestMove.prevMoves;

        solutionSequence = new Stack<>();
        while (bestMove != null) {
            solutionSequence.push(bestMove.state);
            bestMove = bestMove.prevNode;
        }

    }

    /**
     * @return the minimum number of moves to solve the puzzle starting
     *         at the initial WorldState
     */
    public int moves() {
        return numMoves;
    }

    /**
     * @return a sequence of WorldStates from the initial WorldState
     *         to the solution
     */
    public Iterable<WorldState> solution() {
        return solutionSequence;
    }

    private class SearchNode implements Comparable<SearchNode> {

        private WorldState state;
        private int prevMoves;
        private SearchNode prevNode;

        private int estimatedDistance;

        public SearchNode(WorldState worldState, int numPrevMoves, SearchNode previous) {
            state = worldState;
            prevMoves = numPrevMoves;
            prevNode = previous;

            estimatedDistance = worldState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode o) {
            return prevMoves + estimatedDistance - o.prevMoves - o.estimatedDistance;
        }

        @Override
        public String toString() {
            return state.toString();
        }

    }

}
