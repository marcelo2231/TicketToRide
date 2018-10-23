package com.emmettito.tickettorideserver.database;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.Cards.TrainCard;
import com.emmettito.models.Cards.TrainColor;

public class DeckDao {
    /** Variables **/
    private static Database dbInstance = Database.getInstance();

    /** Deck **/
    public boolean addDestCard(String gameName){

        return false;
    }
    public DestinationCard removeDestCard(String gameName, DestinationCard card){

        return new DestinationCard("default", "default", 1);
    }
    public DestinationCard getDestCard(String gameName){

        return new DestinationCard("default", "default", 1);
    }
    public boolean addTrainCard(String gameName){

        return false;
    }
    public TrainCard removeTrainCard(String gameName, TrainCard card){

        return new TrainCard(TrainColor.Black);
    }
    public TrainCard getTrainCard(String gameName){

        return new TrainCard(TrainColor.Black);
    }
}
