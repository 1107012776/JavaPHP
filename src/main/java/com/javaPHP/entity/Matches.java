package com.javaPHP.entity;

import java.util.ArrayList;
import java.util.List;

public class Matches {
    public List<String> matchesList;

    public Matches() {
        matchesList = new ArrayList<String>();
    }

    public List<String> getAll() {
        return matchesList;
    }

    public boolean add(String str) {
        return matchesList.add(str);
    }

    public boolean remove(String str) {
        return matchesList.remove(str);
    }

    public boolean remove(Integer index) {
        return matchesList.remove(index);
    }

    @Override
    public String toString() {
        return "Matches{" +
                "matchesList=" + matchesList +
                '}';
    }
}
