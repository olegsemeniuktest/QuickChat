package com.oleg.chat.common.beans;

import com.google.common.base.Objects;

public class Pair<FIRST, SECOND> {
    private final FIRST first;
    private final SECOND second;

    public Pair(FIRST first, SECOND second) {
        this.first = first;
        this.second = second;
    }

    public FIRST getFirst() {
        return first;
    }

    public SECOND getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equal(first, pair.first) &&
                Objects.equal(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first, second);
    }
}
