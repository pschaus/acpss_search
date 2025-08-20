package org.acpss.tinycsp;

import java.util.BitSet;

class Domain {

    private BitSet values;

    // Domain with {0 ... n-1}
    public Domain(int n) {
        values = new BitSet(n);
        values.set(0, n);
    }
    // Domain with given BitSet
    private Domain(BitSet dom) {
        this.values = dom;
    }
    // Verifies if only one value left
    public boolean isFixed() {
        return size() == 1;
    }
    public int size() {
        return values.cardinality();
    }
    public int min() {
        return values.nextSetBit(0);
    }
    public int max() {
        return values.previousSetBit(values.size() - 1);
    }
    // Removes v and, return true present, Inconsistency if domain is empty
    public boolean remove(int v) {
        if (0 <= v && v < values.length()) {
            if (values.get(v)) {
                values.clear(v);
                if (size() == 0) throw new TinyCSP.Inconsistency();
                return true;
            }
        }
        return false;
    }
    // Fix the domain to value v, Inconsistency if v is not in the domain
    public void fix(int v) {
        if (!values.get(v)) throw new TinyCSP.Inconsistency();
        values.clear();
        values.set(v);
    }
    @Override
    public Domain clone() {
        return new Domain((BitSet) values.clone());
    }
    @Override
    public String toString() {
        return values.toString();
    }
}

