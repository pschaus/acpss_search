import java.util.ArrayList;

public class NQueensTinyCSP {

    public static void main(String[] args) {
        int n = 8;

        TinyCSP csp = new TinyCSP();
        Variable[] q = new Variable[n];

        for (int i = 0; i < n; i++) {
            q[i] = csp.makeVariable(n);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // queens q[i] and q[i] not on ...
                csp.notEqual(q[i], q[j], 0); // ... the same line
                csp.notEqual(q[i], q[j], i - j); // ... the same left diagonal
                csp.notEqual(q[i], q[j], j - i); // ... the same right diagonal
            }
        }

        ArrayList<int[]> solutions = new ArrayList<>();
        // collect all the solutions
        csp.dfs(solution -> {
            solutions.add(solution);
        });

        System.out.println("# solutions: " + solutions.size());

    }
}