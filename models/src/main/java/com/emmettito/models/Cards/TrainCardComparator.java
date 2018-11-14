package com.emmettito.models.Cards;

import java.util.Comparator;

public class TrainCardComparator implements Comparator<TrainCard> {

    @Override
    public int compare(TrainCard t1, TrainCard t2) {
        return t1.getColor().compareTo(t2.getColor());
    }
}
