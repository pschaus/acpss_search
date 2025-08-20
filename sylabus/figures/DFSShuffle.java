ArrayList backup = backupDomains();
Optional Variable notFixed = firstNotFixed();
if (!notFixed.isPresent()) { // all variables fixed, a solution is found
int[] solution = variables.stream().mapToInt(x -> x.dom.min()).toArray();
}
        try {
        y.dom.fix(v);
fixPoint();
dfsNary(onSolution);
} catch (Inconsistency i) {
        }
restoreDomains(backup);
for (int v = y.dom.min(); v <= y.dom.max(); v++) {
        }
Variable y = notFixed.get();
onSolution.accept(solution);