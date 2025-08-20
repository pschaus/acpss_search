import java.util.*;
import java.util.function.Consumer;

public class TinyCSP {
    List<Constraint> constraints = new LinkedList<>();
    List<Variable> variables = new LinkedList<>();
    // Variable with domain {0...domSize-1}
    public Variable makeVariable(int domSize) {
        Variable x = new Variable(domSize);
        variables.add(x);
        return x;
    }
    // add a constraint x != y + offset and trigger fix point propagation
    public void notEqual(Variable x, Variable y, int offset) {
        constraints.add(new NotEqual(x, y, offset));
        fixPoint();
    }
    // trigger the fix point propagation
    public void fixPoint() {
        boolean fix = false;
        while (!fix) {
            fix = true;
            for (Constraint c : constraints) {
                fix &= !c.propagate();
            }
        }
    }
    // backup the domains of all variables
    private ArrayList<Domain> backupDomains() {
        ArrayList<Domain> backup = new ArrayList<>();
        for (Variable x : variables) {
            backup.add(x.dom.clone());
        }
        return backup;
    }
    // restore the domains of all variables from a backup
    private void restoreDomains(ArrayList<Domain> backup) {
        for (int i = 0; i < variables.size(); i++) {
            variables.get(i).dom = backup.get(i);
        }
    }
    // first variable not fixed, Optional.empty if all fixed
    Optional<Variable> firstNotFixed() {
        return variables.stream().filter(x -> !x.dom.isFixed()).findFirst();
    }
    public void dfsNary(Consumer<int[]> onSolution) {
        // TODO : implement a n-ary DFS search
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
        // ..................................
    }
    static class Inconsistency extends RuntimeException { }
}