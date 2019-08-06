package com.example.demo.util;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Map.Entry<Object, Double>> {
    public int compare(Map.Entry<Object, Double> mp1, Map.Entry<Object, Double> mp2) {
        return (int) ((mp2.getValue() - mp1.getValue()) * 10);
    }
}