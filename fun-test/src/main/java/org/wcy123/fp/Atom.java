package org.wcy123.fp;

public class Atom {
    final private String name;

    public Atom(String name) {
        this.name = name.intern();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Atom atom = (Atom) o;
        // becauase of `intern`, it is safe to use hard equal
        return atom == o;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
