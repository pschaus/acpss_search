public class Variable {
    Domain dom;
    // Variable with domain {0...n-1}
    public Variable(int n) {
        dom = new Domain(n);
    }
}