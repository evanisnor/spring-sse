package com.evanisnor.api.model;

import java.util.Date;
import java.util.Objects;

public class CommitReceipt {

    private long id;
    private Date committedOn;

    public CommitReceipt(long id, Date committedOn) {
        this.id = id;
        this.committedOn = committedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommitReceipt that = (CommitReceipt) o;
        return id == that.id &&
                Objects.equals(committedOn, that.committedOn);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, committedOn);
    }
}
