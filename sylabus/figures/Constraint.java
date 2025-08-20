abstract class Constraint {
    // Propagate the constraint and return true if any value could be removed
    abstract boolean propagate();
}
// Constraint x != y + offset
class NotEqual extends Constraint {

    Variable x, y;
    int offset;

    public NotEqual(Variable x, Variable y, int offset) {
        this.x = x;
        this.y = y;
        this.offset = offset;
    }

    public NotEqual(Variable x, Variable y) { this(x, y, 0); }

    @Override
    boolean propagate() {
        if (x.dom.isFixed()) {
            return y.dom.remove(x.dom.min() - offset);
        }
        if (y.dom.isFixed()) {
            return x.dom.remove(y.dom.min() + offset);
        }
        return false;
    }
}