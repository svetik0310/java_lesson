package ru.zotkina.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Issues extends ForwardingSet<Issue> {
    private Set<Issue> delegate;

    public Issues(Collection<Issue> is) {
        this.delegate = new HashSet<Issue>(is);
    }

    @Override
    protected Set<Issue> delegate() {
        return delegate;
    }

    public Issues()
    {
        this.delegate = new HashSet<Issue>();
    }

    public Issues(Issues is) {
        this.delegate = new HashSet<Issue>(is.delegate);
    }
    public Issues withAdded(Issue i)
    {
        Issues is=new Issues(this);
        is.add(i);
        return is;
    }
    public Issues withOut(Issue i)
    {
        Issues is=new Issues(this);
        is.remove(i);
        return is;
    }
}