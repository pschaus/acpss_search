package org.acpss.tinycsp;


abstract class Constraint {
    // Propagate the constraint and return true if any value could be removed
    abstract boolean propagate();
}