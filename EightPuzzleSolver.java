package eightpuzzle;

import aima.core.agent.Action;
import java.util.List;
import java.util.Optional;

public class EightPuzzleSolver {

    public static void main(String[] args) {
        try {
            EightPuzzleBoard initialState = new EightPuzzleBoard(new int[]{7, 1, 8, 0, 4, 6, 2, 3, 5});
            
            // Print the initial state
            System.out.println("Initial State:");
            printPuzzle(initialState);

            // Solve using BFS
            Optional<List<Action>> bfsSolution = SearchAlgorithms.breadthFirstSearch(initialState);
            printSolution("BFS", bfsSolution, initialState);

            // Solve using DFS
            Optional<List<Action>> dfsSolution = SearchAlgorithms.depthFirstSearch(initialState);
            printSolution("DFS", dfsSolution, initialState);

            // Solve using DLS
            Optional<List<Action>> dlsSolution = SearchAlgorithms.depthLimitedSearch(initialState, 9); // Increased limit
            printSolution("DLS", dlsSolution, initialState);

            // Solve using IDS
            Optional<List<Action>> idsSolution = SearchAlgorithms.iterativeDeepeningSearch(initialState);
            printSolution("IDS", idsSolution, initialState);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printSolution(String method, Optional<List<Action>> solution, EightPuzzleBoard initialState) {
        if (solution.isPresent()) {
            System.out.println(method + " Solution: " + solution.get());
            EightPuzzleBoard state = initialState;
            for (Action action : solution.get()) {
                state = EightPuzzleFunctions.getResult(state, action);
            }
            System.out.println("Final State after " + method + ":");
            printPuzzle(state);
        } else {
            System.out.println("No solution found with " + method);
        }
    }

    private static void printPuzzle(EightPuzzleBoard state) {
        int[] board = state.getState();
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0) System.out.println();
            System.out.print(board[i] + " ");
        }
        System.out.println();
    }
}