return y == null ? Optional.empty() : Optional.of(y);
int min = Integer.MAX_VALUE;
for (Variable x : variables) {
if (!x.dom.isFixed() && x.dom.size() < min) {
y = x;
}
min = y.dom.size();
}
// first variable with empty domain, Optional.empty if all domains are not empty
Variable y = null;