package com.emmettito.tickettoride.views.FinalResultsActivity;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.HardCoded.HardCodedData;
import com.emmettito.models.Player;
import com.emmettito.models.PlayerColor;
import com.emmettito.models.Route;
import com.emmettito.models.Tuple;

import java.util.ArrayList;
import java.util.List;

public class PlayerFinalResults {

    private String name;
    private PlayerColor color;

    private int points; // total points

    private int points_from_claimed_routes; // kept track of during game
    private int points_from_reached_dest; // should be >= 0
    private int points_from_unreached_dest; // should be <= 0

    private List<Integer> reached_dest;
    private List<Integer> unreached_dest;

    private boolean most_claimed_routes;
//    private boolean longest_path;

    private boolean isWinner;

    public PlayerFinalResults(Player player) {
        name = player.getPlayerName();
        color = player.getColor();

        points = 0;

        points_from_claimed_routes = player.getPoints();
        points_from_reached_dest = 0;
        points_from_unreached_dest = 0;

        reached_dest = new ArrayList<>();
        unreached_dest = new ArrayList<>();

        most_claimed_routes = false;
//        longest_path = false;

        isWinner = false;

        calcUserPoints(player.getClaimedRoutes(), player.getDestinationCards());
    }

    private void calcUserPoints(List<Integer> claimed_routes, List<DestinationCard> dest_cards) {

        List<Route> all_routes = new HardCodedData().getRoutes();

        for (int i = 0; i < dest_cards.size(); i++) {
            DestinationCard dest_card = dest_cards.get(i);
            boolean finished = didPlayerCompleteRoute(all_routes, claimed_routes, dest_card);

            if (finished) {
                reached_dest.add(dest_card.getCardID());
                points_from_reached_dest += dest_card.getPointValue();
            }
            else {
                unreached_dest.add(dest_card.getCardID());
                points_from_unreached_dest -= dest_card.getPointValue();
            }
        }

        points = points_from_claimed_routes + points_from_reached_dest + points_from_unreached_dest;
    }

    private boolean didPlayerCompleteRoute(List<Route> all_routes, List<Integer> claimed_routes, DestinationCard dest_card) {

        Tuple cityIDs = dest_card.getCityIDs();
        int startCity = ((Double) cityIDs.getX()).intValue();
        int endCity = ((Double) cityIDs.getY()).intValue();

        System.out.printf("This is the starting city: %s\n", startCity);
        System.out.printf("This is the ending city: %s\n", endCity);

        //City startCity = (City) cities.getX();
        //City endCity = (City) cities.getY();

        List<Route> routesClaimed = new ArrayList<>();

        List<Integer> currentCities = new ArrayList<>();
        ArrayList<Integer> endingCities = new ArrayList<>();

        for (int i = 0; i < claimed_routes.size(); i++) {
            int cityID = claimed_routes.get(i);

            Route currentRoute = all_routes.get(cityID);
            Tuple routeCities = currentRoute.getCities();

            int city1 = (Integer) routeCities.getX();
            int city2 = (Integer) routeCities.getY();

            System.out.printf("This is city 1 from the player's claimed route: %s\n", city1);
            System.out.printf("This is city 2 from the player's claimed route: %s\n", city2);

            if (city1 == startCity) {
                currentCities.add(city2);
            }
            else if (city2 == startCity) {
                currentCities.add(city1);
            }
            else {
                if (city1 == endCity || city2 == endCity) {
                    endingCities.add(city1);
                }
                routesClaimed.add(currentRoute);
            }
        }

        System.out.printf("This is the number of currentCities: %s\n", currentCities.size());

        if (currentCities.size() == 0 || endingCities.size() == 0) {        //One or both of the cities is unreachable
            return false;
        }

        while (currentCities.size() > 0) {
            Integer currentCity = currentCities.remove(0);

            System.out.printf("This is the current city: %s\n", currentCity);

            if (currentCity == endCity) {
                return true;        //Path exists from start city to end city
            }

            for (int i = 0; i < routesClaimed.size(); i++) {
                Route thisRoute = routesClaimed.get(i);
                Tuple routeCities = thisRoute.getCities();
                int city1 = (Integer) routeCities.getX();
                int city2 = (Integer) routeCities.getY();

                System.out.printf("While loop city 1: %s\n", city1);
                System.out.printf("While loop city 2: %s\n", city2);

                if (currentCity == city1) {
                    currentCities.add(city2);
                    routesClaimed.remove(i);
                    i--;
                }
                else if (currentCity == city2) {
                    currentCities.add(city1);
                    routesClaimed.remove(i);
                    i--;
                }
            }
        }

        return false;   //No path exists
    }
    /*

    Getters

     */

    public String getName() {
        return name;
    }

    public PlayerColor getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }

    public int getPointsFromClaimedRoutes() {
        return points_from_claimed_routes;
    }

    public int getPointsFromReachedDest() {
        return points_from_reached_dest;
    }

    public int getPointsFromUnreachedDest() {
        return points_from_unreached_dest;
    }

    public List<Integer> getReachedDest() {
        return reached_dest;
    }

    public List<Integer> getUnreachedDest() {
        return unreached_dest;
    }

    public boolean hasMostClaimedRoutesAward() {
        return most_claimed_routes;
    }

//    public boolean hasLongestPathAward() {
//        return longest_path;
//    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner() {
        isWinner = true;
    }

    /*

    Extra awards

     */

    public void awardPlayerMostClaimedRoutes() {
        most_claimed_routes = true;
        points += 10;
    }

//    public void awardPlayerLongestPath() {
//        longest_path = true;
//        points += 10;
//    }
}
