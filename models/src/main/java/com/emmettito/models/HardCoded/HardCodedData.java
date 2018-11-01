package com.emmettito.models.HardCoded;

import com.emmettito.models.Cards.DestinationCard;
import com.emmettito.models.City;
import com.emmettito.models.Route;
import com.emmettito.models.Space;
import com.emmettito.models.Tuple;

import java.util.ArrayList;
import java.util.List;

import sun.security.krb5.internal.crypto.Des;

public class HardCodedData {

    public HardCodedData() {
    }

    public List<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City(0, "Vancouver", null, 0.073249675f, 0.10081979f));
        cities.add(new City(1, "Calgary", null, 0.21200185f, 0.06917213f));
        cities.add(new City(2, "Winnipeg", null, 0.45044914f, 0.0879295f));
        cities.add(new City(3, "Sault St. Marie", null, 0.7042111f, 0.17580727f));
        cities.add(new City(4, "Montreal", null, 0.90780705f, 0.059807878f));
        cities.add(new City(5, "Boston", null, 0.9830983f, 0.16408794f));
        cities.add(new City(6, "New York", null, 0.9280297f, 0.29770005f));
        cities.add(new City(7, "Washington DC", null, 0.9370808f, 0.45945835f));
        cities.add(new City(8, "Raleigh", null, 0.8743249f, 0.58017844f));
        cities.add(new City(9, "Charleston", null, 0.90433633f, 0.6961901f));
        cities.add(new City(10, "Miami", null, 0.93847454f, 0.97748786f));
        cities.add(new City(11, "New Orleans", null, 0.7021396f, 0.9106814f));
        cities.add(new City(12, "Houston", null, 0.60311955f, 0.9329245f));
        cities.add(new City(13, "El Paso", null, 0.3674815f, 0.9036574f));
        cities.add(new City(14, "Phoenix", null, 0.24128103f, 0.83801043f));
        cities.add(new City(15, "Los Angeles", null, 0.11367051f, 0.8262771f));
        cities.add(new City(16, "San Francisco", null, 0.03140788f, 0.6399223f));
        cities.add(new City(17, "Portland", null, 0.04674706f, 0.28717455f));
        cities.add(new City(18, "Seattle", null, 0.0690576f, 0.19808981f));
        cities.add(new City(19, "Helena", null, 0.31794968f, 0.30473554f));
        cities.add(new City(20, "Duluth", null, 0.56964284f, 0.29420033f));
        cities.add(new City(21, "Toronto", null, 0.8206718f, 0.21566223f));
        cities.add(new City(22, "Pittsburgh", null, 0.83808255f, 0.37858006f));
        cities.add(new City(23, "Atlanta", null, 0.8039362f, 0.68330073f));
        cities.add(new City(24, "Little Rock", null, 0.63310647f, 0.71141005f));
        cities.add(new City(25, "Nashville", null, 0.7509337f, 0.6200211f));
        cities.add(new City(26, "Dallas", null, 0.55919534f, 0.86142445f));
        cities.add(new City(27, "Oklahoma City", null, 0.53828394f, 0.704386f));
        cities.add(new City(28, "Santa Fe", null, 0.3730428f, 0.7430692f));
        cities.add(new City(29, "Las Vegas", null, 0.1820258f, 0.7219724f));
        cities.add(new City(30, "Salt Lake City", null, 0.24196701f, 0.52506673f));
        cities.add(new City(31, "Denver", null, 0.38071373f, 0.58133715f));
        cities.add(new City(32, "Omaha", null, 0.53756535f, 0.4582874f));
        cities.add(new City(33, "Chicago", null, 0.70003265f, 0.40435892f));
        cities.add(new City(34, "Kansas City", null, 0.5591981f, 0.54970264f));
        cities.add(new City(35, "Saint Louis", null, 0.6519191f, 0.55203056f));

        return cities;
    }

    public List<DestinationCard> getDestinationCards() {
        ArrayList<DestinationCard> cards = new ArrayList<>();

        cards.add(new DestinationCard(0, 15, "Los Angeles", 6, "New York", 21));
        cards.add(new DestinationCard(1, 20, "Duluth", 12, "Houston", 8));
        cards.add(new DestinationCard(2, 3, "Sault St. Marie", 25, "Nashville", 8));
        cards.add(new DestinationCard(3, 6, "New York", 23, "Atlanta", 6));
        cards.add(new DestinationCard(4, 17, "Portland", 25, "Nashville", 17));
        cards.add(new DestinationCard(5, 0, "Vancouver", 4, "Montreal", 20));
        cards.add(new DestinationCard(6, 20, "Duluth", 13, "El Paso", 10));
        cards.add(new DestinationCard(7, 21, "Toronto", 10, "Miami", 10));
        cards.add(new DestinationCard(8, 17, "Portland", 14, "Phoenix", 11));
        cards.add(new DestinationCard(9, 26, "Dallas", 6, "New York", 11));
        cards.add(new DestinationCard(10, 1, "Calgary", 30, "Salt Lake City", 7));
        cards.add(new DestinationCard(11, 1, "Calgary", 14, "Phoenix", 13));
        cards.add(new DestinationCard(12, 15, "Los Angeles", 10, "Miami", 20));
        cards.add(new DestinationCard(13, 2, "Winnipeg", 24, "Little Rock", 11));
        cards.add(new DestinationCard(14, 16, "San Francisco", 23, "Atlanta", 17));
        cards.add(new DestinationCard(15, 34, "Kansas City", 12, "Houston", 5));
        cards.add(new DestinationCard(16, 15, "Los Angeles", 33, "Chicago", 16));
        cards.add(new DestinationCard(17, 31, "Denver", 22, "Pittsburgh", 11));
        cards.add(new DestinationCard(18, 33, "Chicago", 28, "Santa Fe", 9));
        cards.add(new DestinationCard(19, 0, "Vancouver", 28, "Santa Fe", 13));
        cards.add(new DestinationCard(20, 5, "Boston", 10, "Miami", 12));
        cards.add(new DestinationCard(21, 33, "Chicago", 11, "New Orleans", 7));
        cards.add(new DestinationCard(22, 4, "Montreal", 23, "Atlanta", 9));
        cards.add(new DestinationCard(23, 18, "Seattle", 6, "New York", 22));
        cards.add(new DestinationCard(24, 31, "Denver", 13, "El Paso", 4));
        cards.add(new DestinationCard(25, 19, "Helena", 15, "Los Angeles", 8));
        cards.add(new DestinationCard(26, 2, "Winnipeg", 12, "Houston", 12));
        cards.add(new DestinationCard(27, 4, "Montreal", 11, "New Orleans", 13));
        cards.add(new DestinationCard(28, 3, "Sault St. Marie", 27, "Oklahoma City", 9));
        cards.add(new DestinationCard(29, 18, "Seattle", 15, "Los Angeles", 9));

        return cards;
    }

    public List<Route> getRoutes() {
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Space> spaces;

        // Route 0: Vancouver 0 - Calgary 1
        spaces = new ArrayList<>();
        spaces.add(new Space(0.1046385f, 0.08794094f, -7.5f));
        spaces.add(new Space(0.14296874f, 0.07972306f, -7.5f));
        spaces.add(new Space(0.18061303f, 0.07268587f, -5.0f));
        routes.add(new Route(0, new Tuple(0, 1), spaces, false, null, null));

        // Route 1: Calgary 1 - Winnipeg 2
        spaces = new ArrayList<>();
        spaces.add(new Space(0.24057873f, 0.051575135f, -22.5f));
        spaces.add(new Space(0.27683744f, 0.031673957f, -12.5f));
        spaces.add(new Space(0.31448987f, 0.024623612f, -2.5f));
        spaces.add(new Space(0.3528283f, 0.025806865f, 5.0f));
        spaces.add(new Space(0.39115855f, 0.0375016f, 17.5f));
        spaces.add(new Space(0.4267367f, 0.059807878f, 25.0f));
        routes.add(new Route(1, new Tuple(1, 2), spaces, false, null, null));

        // Route 2: Winnipeg 2 - Sault St. Marie 3
        spaces = new ArrayList<>();
        spaces.add(new Space(0.48876306f, 0.090259135f, 12.5f));
        spaces.add(new Space(0.5264264f, 0.1043441f, 12.5f));
        spaces.add(new Space(0.5633874f, 0.117208086f, 12.5f));
        spaces.add(new Space(0.600343f, 0.13010009f, 12.5f));
        spaces.add(new Space(0.6372986f, 0.14416046f, 12.5f));
        spaces.add(new Space(0.67564785f, 0.15705161f, 12.5f));
        routes.add(new Route(2, new Tuple(2, 3), spaces, false, null, null));

        // Route 3: Sault St. Marie 3 - Montreal 4
        spaces = new ArrayList<>();
        spaces.add(new Space(0.7286395f, 0.14064844f, -37.5f));
        spaces.add(new Space(0.7600038f, 0.10433267f, -27.5f));
        spaces.add(new Space(0.79486877f, 0.0785381f, -20.0f));
        spaces.add(new Space(0.83181894f, 0.060952537f, -12.5f));
        spaces.add(new Space(0.87016004f, 0.053940784f, 0.0f));
        routes.add(new Route(3, new Tuple(3, 4), spaces, false, null, null));

        // Route 4: Montreal 4 - Boston 5
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9363839f, 0.083221935f, 40.0f));
        spaces.add(new Space(0.96496624f, 0.12423384f, 40.0f));
        routes.add(new Route(4, new Tuple(4, 5), spaces, true, null, null));

        // Route 5: Montreal 4 - Boston 5
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9280188f, 0.10083208f, 40.0f));
        spaces.add(new Space(0.9566066f, 0.14067474f, 40.0f));
        routes.add(new Route(5, new Tuple(4, 5), spaces, true, null, null));

        // Route 6: Montreal 4 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(0.87991613f, 0.082063265f, -25.0f));
        spaces.add(new Space(0.8485273f, 0.11837904f, -42.5f));
        spaces.add(new Space(0.8234511f, 0.16760083f, -57.5f));
        routes.add(new Route(6, new Tuple(4, 21), spaces, false, null, null));

        // Route 7: Sault St. Marie 3 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(0.73975664f, 0.18518724f, 12.5f));
        spaces.add(new Space(0.77673674f, 0.19925992f, 15.0f));
        routes.add(new Route(7, new Tuple(3, 21), spaces, false, null, null));

        // Route 8: Sault St. Marie 3 - Duluth 20
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6052047f, 0.25316212f, -25.0f));
        spaces.add(new Space(0.640067f, 0.22739384f, -22.5f));
        spaces.add(new Space(0.67493194f, 0.20161328f, -22.5f));
        routes.add(new Route(8, new Tuple(3, 20), spaces, false, null, null));

        // Route 9: Winnipeg 2 - Duluth 20
        spaces = new ArrayList<>();
        spaces.add(new Space(0.46927264f, 0.12308833f, 42.5f));
        spaces.add(new Space(0.4964694f, 0.1676114f, 45.0f));
        spaces.add(new Space(0.52362806f, 0.21096694f, 42.5f));
        spaces.add(new Space(0.55081934f, 0.25550404f, 45.0f));
        routes.add(new Route(9, new Tuple(2, 20), spaces, false, null, null));

        // Route 10: Winnipeg 2 - Helena 19
        spaces = new ArrayList<>();
        spaces.add(new Space(0.34375817f, 0.25904062f, -45.0f));
        spaces.add(new Space(0.37025532f, 0.2133203f, -45.0f));
        spaces.add(new Space(0.39675522f, 0.16879638f, -45.0f));
        spaces.add(new Space(0.42464885f, 0.12424528f, -45.0f));
        routes.add(new Route(10, new Tuple(2, 19), spaces, false, null, null));

        // Route 11: Calgary 1 - Helena 19
        spaces = new ArrayList<>();
        spaces.add(new Space(0.23152222f, 0.11134356f, 50.0f));
        spaces.add(new Space(0.25589612f, 0.15940496f, 50.0f));
        spaces.add(new Space(0.28031087f, 0.20862502f, 50.0f));
        spaces.add(new Space(0.30541974f, 0.2578697f, 50.0f));
        routes.add(new Route(11, new Tuple(1, 19), spaces, false, null, null));

        // Route 12: Calgary 1 - Seattle 18
        spaces = new ArrayList<>();
        spaces.add(new Space(0.10114329f, 0.19573303f, 0.0f));
        spaces.add(new Space(0.14086726f, 0.19103947f, -7.5f));
        spaces.add(new Space(0.17642367f, 0.16645445f, -37.5f));
        spaces.add(new Space(0.20084931f, 0.117208086f, -62.5f));
        routes.add(new Route(12, new Tuple(1, 18), spaces, false, null, null));

        // Route 13: Vancouver 0 - Seattle 18
        spaces = new ArrayList<>();
        spaces.add(new Space(0.0634963f, 0.14886889f, 90.0f));
        routes.add(new Route(13, new Tuple(0, 18), spaces, true, null, null));

        // Route 14: Vancouver 0 - Seattle 18
        spaces = new ArrayList<>();
        spaces.add(new Space(0.07741997f, 0.14768563f, 87.5f));
        routes.add(new Route(14, new Tuple(0, 18), spaces, true, null, null));

        // Route 15: Portland 17 - Seattle 18
        spaces = new ArrayList<>();
        spaces.add(new Space(0.050920077f, 0.2379422f, -67.5f));
        routes.add(new Route(15, new Tuple(17, 18), spaces, true, null, null));

        // Route 16: Portland 17 - Seattle 18
        spaces = new ArrayList<>();
        spaces.add(new Space(0.06418772f, 0.24615036f, -67.5f));
        routes.add(new Route(16, new Tuple(17, 18), spaces, true, null, null));

        // Route 17: Seattle 18 - Helena 19
        spaces = new ArrayList<>();
        spaces.add(new Space(0.09975501f, 0.22974806f, 12.5f));
        spaces.add(new Space(0.13669969f, 0.2438093f, 12.5f));
        spaces.add(new Space(0.17434397f, 0.2578697f, 12.5f));
        spaces.add(new Space(0.21131043f, 0.27074766f, 12.5f));
        spaces.add(new Space(0.24826056f, 0.285979f, 12.5f));
        spaces.add(new Space(0.28590757f, 0.2988956f, 12.5f));
        routes.add(new Route(17, new Tuple(18, 19), spaces, false, null, null));

        // Route 18: Helena 19 - Duluth 20
        spaces = new ArrayList<>();
        spaces.add(new Space(0.35005173f, 0.3024085f, 0.0f));
        spaces.add(new Space(0.3883847f, 0.30123752f, 0.0f));
        spaces.add(new Space(0.4260426f, 0.3012112f, 0.0f));
        spaces.add(new Space(0.46440005f, 0.30004025f, 0.0f));
        spaces.add(new Space(0.50272214f, 0.2988956f, 0.0f));
        spaces.add(new Space(0.54109323f, 0.29771063f, 0.0f));
        routes.add(new Route(18, new Tuple(19, 20), spaces, false, null, null));

        // Route 19: Duluth 20 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5982306f, 0.285979f, -10.0f));
        spaces.add(new Space(0.6358776f, 0.2754438f, -10.0f));
        spaces.add(new Space(0.6742487f, 0.26490688f, -10.0f));
        spaces.add(new Space(0.7111743f, 0.25316212f, -10.0f));
        spaces.add(new Space(0.74884856f, 0.24262518f, -7.5f));
        spaces.add(new Space(0.786501f, 0.230919f, -10.0f));
        routes.add(new Route(19, new Tuple(20, 21), spaces, false, null, null));

        // Route 20: Montreal 4 - New York 6
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9050305f, 0.119550005f, -97.5f));
        spaces.add(new Space(0.9105836f, 0.1805034f, 82.5f));
        spaces.add(new Space(0.916869f, 0.24263833f, 80.0f));
        routes.add(new Route(20, new Tuple(4, 6), spaces, false, null, null));

        // Route 21: Boston 5 - New York 6
        spaces = new ArrayList<>();
        spaces.add(new Space(0.96358615f, 0.20511214f, -57.5f));
        spaces.add(new Space(0.94407123f, 0.2578697f, -57.5f));
        routes.add(new Route(21, new Tuple(5, 6), spaces, true, null, null));

        // Route 22: Boston 5 - New York 6
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9747223f, 0.21685776f, -57.5f));
        spaces.add(new Space(0.9552129f, 0.2707468f, -57.5f));
        routes.add(new Route(22, new Tuple(5, 6), spaces, true, null, null));

        // Route 23: Helena 19 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(0.35911095f, 0.33873567f, 22.5f));
        spaces.add(new Space(0.393946f, 0.3621506f, 22.5f));
        spaces.add(new Space(0.42883003f, 0.38560238f, 22.5f));
        spaces.add(new Space(0.46437827f, 0.41022602f, 22.5f));
        spaces.add(new Space(0.49993467f, 0.436018f, 22.5f));
        routes.add(new Route(23, new Tuple(19, 32), spaces, false, null, null));

        // Route 24: Helena 19 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(0.32980728f, 0.34928748f, 67.5f));
        spaces.add(new Space(0.34444958f, 0.40670082f, 67.5f));
        spaces.add(new Space(0.35911095f, 0.46531144f, 67.5f));
        spaces.add(new Space(0.3730455f, 0.52506673f, 67.5f));
        routes.add(new Route(24, new Tuple(19, 32), spaces, false, null, null));

        // Route 25: Helena 19 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(0.30052266f, 0.35279778f, -60.0f));
        spaces.add(new Space(0.28169915f, 0.40671313f, -60.0f));
        spaces.add(new Space(0.26221144f, 0.461774f, -60.0f));
        routes.add(new Route(25, new Tuple(19, 30), spaces, false, null, null));

        // Route 26: Duluth 20 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5383003f, 0.40201527f, -67.5f));
        spaces.add(new Space(0.5522104f, 0.34343266f, -67.5f));
        routes.add(new Route(26, new Tuple(20, 32), spaces, true, null, null));

        // Route 27: Duluth 20 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5654671f, 0.3527969f, -67.5f));
        spaces.add(new Space(0.5515135f, 0.41139525f, -67.5f));
        routes.add(new Route(27, new Tuple(20, 32), spaces, true, null, null));

        // Route 28: Duluth 20 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5933716f, 0.32466388f, 30.0f));
        spaces.add(new Space(0.6282366f, 0.3527969f, 20.0f));
        spaces.add(new Space(0.66518676f, 0.37034732f, 15.0f));
        routes.add(new Route(28, new Tuple(20, 33), spaces, false, null, null));

        // Route 29: Toronto 21 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(0.70423293f, 0.35162598f, -45.0f));
        spaces.add(new Space(0.7342171f, 0.30942023f, -32.5f));
        spaces.add(new Space(0.7683689f, 0.28131002f, -17.5f));
        spaces.add(new Space(0.8025261f, 0.2531638f, -37.5f));
        routes.add(new Route(29, new Tuple(21, 33), spaces, false, null, null));

        // Route 30: Toronto 21 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(0.8297283f, 0.25901604f, 90.0f));
        spaces.add(new Space(0.8311275f, 0.32346833f, 85.0f));
        routes.add(new Route(30, new Tuple(21, 22), spaces, false, null, null));

        // Route 31: Pittsburgh 22 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(0.72512794f, 0.37035877f, -15.0f));
        spaces.add(new Space(0.7627668f, 0.35748076f, -10.0f));
        spaces.add(new Space(0.80113786f, 0.35395646f, 0.0f));
        routes.add(new Route(31, new Tuple(22, 33), spaces, true, null, null));

        // Route 32: Pittsburgh 22 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(0.7300196f, 0.39262643f, -15.0f));
        spaces.add(new Space(0.76697516f, 0.37975016f, -10.0f));
        spaces.add(new Space(0.80461675f, 0.37740824f, 0.0f));
        routes.add(new Route(32, new Tuple(22, 33), spaces, true, null, null));

        // Route 33: Omaha 32 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5640979f, 0.4418588f, -35.0f));
        spaces.add(new Space(0.5947762f, 0.40437207f, -35.0f));
        spaces.add(new Space(0.6324042f, 0.39031082f, 10.0f));
        spaces.add(new Space(0.6693652f, 0.39967763f, 10.0f));
        routes.add(new Route(33, new Tuple(32, 33), spaces, false, null, null));

        // Route 34: New York 6 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(0.86179495f, 0.3363709f, -32.5f));
        spaces.add(new Space(0.8945394f, 0.3047601f, -32.5f));
        routes.add(new Route(34, new Tuple(6, 22), spaces, true, null, null));

        // Route 35: New York 6 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(0.8687745f, 0.3551397f, -32.5f));
        spaces.add(new Space(0.90151894f, 0.32349205f, -32.5f));
        routes.add(new Route(35, new Tuple(6, 22), spaces, true, null, null));

        // Route 36: New York 6 - Washington DC 7
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9273192f, 0.3469324f, 87.5f));
        spaces.add(new Space(0.92940986f, 0.4102137f, 87.5f));
        routes.add(new Route(36, new Tuple(6, 7), spaces, true, null, null));

        // Route 37: New York 6 - Washington DC 7
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9412892f, 0.34459049f, 87.5f));
        spaces.add(new Space(0.94337434f, 0.40906733f, 87.5f));
        routes.add(new Route(37, new Tuple(6, 7), spaces, true, null, null));

        // Route 38: Washington DC 7 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(0.8708651f, 0.4078841f, 27.5f));
        spaces.add(new Space(0.90433365f, 0.43717667f, 27.5f));
        routes.add(new Route(38, new Tuple(7, 22), spaces, false, null, null));

        // Route 39: Washington DC 7 - Raleigh 8
        spaces = new ArrayList<>();
        spaces.add(new Space(0.88968587f, 0.5368f, -50.0f));
        spaces.add(new Space(0.9133629f, 0.48876324f, -50.0f));
        routes.add(new Route(39, new Tuple(7, 8), spaces, true, null, null));

        // Route 40: Washington DC 7 - Raleigh 8
        spaces = new ArrayList<>();
        spaces.add(new Space(0.9008221f, 0.55202967f, -50.0f));
        spaces.add(new Space(0.9245454f, 0.50397086f, -50.0f));
        routes.add(new Route(40, new Tuple(7, 8), spaces, true, null, null));

        // Route 41: Raleigh 8 - Nashville 25
        spaces = new ArrayList<>();
        spaces.add(new Space(0.77462435f, 0.5895436f, -32.5f));
        spaces.add(new Space(0.81017804f, 0.56610495f, -12.5f));
        spaces.add(new Space(0.8471581f, 0.5590677f, 0.0f));
        routes.add(new Route(41, new Tuple(8, 25), spaces, false, null, null));

        // Route 42: Raleigh 8 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(0.8513393f, 0.44419986f, 77.5f));
        spaces.add(new Space(0.8604067f, 0.5075058f, 77.5f));
        routes.add(new Route(42, new Tuple(8, 22), spaces, false, null, null));

        // Route 43: Pittsburgh 22 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.67772484f, 0.5414962f, -30.0f));
        spaces.add(new Space(0.71189296f, 0.5098477f, -30.0f));
        spaces.add(new Space(0.7446456f, 0.47818944f, -30.0f));
        spaces.add(new Space(0.77742815f, 0.44654265f, -30.0f));
        spaces.add(new Space(0.8109103f, 0.41375118f, -30.0f));
        routes.add(new Route(43, new Tuple(22, 35), spaces, false, null, null));

        // Route 44: Pittsburgh 22 - Nashville 25
        spaces = new ArrayList<>();
        spaces.add(new Space(0.7481544f, 0.5801687f, -60.0f));
        spaces.add(new Space(0.7690603f, 0.5262402f, -52.5f));
        spaces.add(new Space(0.7969539f, 0.48170146f, -32.5f));
        spaces.add(new Space(0.824148f, 0.43833363f, -57.5f));
        routes.add(new Route(44, new Tuple(22, 25), spaces, false, null, null));

        // Route 45: Raleigh 8 - Charleston 9
        spaces = new ArrayList<>();
        spaces.add(new Space(0.89663f, 0.60827553f, 35.0f));
        spaces.add(new Space(0.9140679f, 0.65283716f, -65.0f));
        routes.add(new Route(45, new Tuple(8, 9), spaces, false, null, null));

        // Route 46: Raleigh 8 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(0.82063645f, 0.64344746f, -42.5f));
        spaces.add(new Space(0.8492187f, 0.60244787f, -40.0f));
        routes.add(new Route(46, new Tuple(8, 23), spaces, true, null, null));

        // Route 47: Raleigh 8 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(0.83042246f, 0.6598744f, -40.0f));
        spaces.add(new Space(0.8589939f, 0.6188502f, -40.0f));
        routes.add(new Route(47, new Tuple(8, 23), spaces, true, null, null));

        // Route 48: Chicago 33 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6554306f, 0.499287f, -57.5f));
        spaces.add(new Space(0.67564785f, 0.44302976f, -57.5f));
        routes.add(new Route(48, new Tuple(33, 35), spaces, true, null, null));

        // Route 49: Chicago 33 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6665723f, 0.510994f, -57.5f));
        spaces.add(new Space(0.68816966f, 0.45594633f, -57.5f));
        routes.add(new Route(49, new Tuple(33, 35), spaces, true, null, null));

        // Route 50: Kansas City 34 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5863812f, 0.53445816f, 0.0f));
        spaces.add(new Space(0.6247305f, 0.53328717f, 0.0f));
        routes.add(new Route(50, new Tuple(34, 35), spaces, true, null, null));

        // Route 51: Kansas City 34 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.58777493f, 0.5578968f, 0.0f));
        spaces.add(new Space(0.6254246f, 0.5567118f, 0.0f));
        routes.add(new Route(51, new Tuple(34, 35), spaces, true, null, null));

        // Route 52: Omaha 32 - Kansas City 34
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5431539f, 0.5086644f, 62.5f));
        routes.add(new Route(52, new Tuple(32, 34), spaces, true, null, null));

        // Route 53: Omaha 32 - Kansas City 34
        spaces = new ArrayList<>();
        spaces.add(new Space(0.55640244f, 0.499287f, 62.5f));
        routes.add(new Route(53, new Tuple(32, 34), spaces, true, null, null));

        // Route 54: Denver 31 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(0.40163326f, 0.5438364f, -37.5f));
        spaces.add(new Space(0.4337217f, 0.50984937f, -25.0f));
        spaces.add(new Space(0.4699695f, 0.48640904f, -15.0f));
        spaces.add(new Space(0.50761104f, 0.47116625f, -10.0f));
        routes.add(new Route(54, new Tuple(31, 32), spaces, false, null, null));

        // Route 55: Denver 31 - Kansas City 34
        spaces = new ArrayList<>();
        spaces.add(new Space(0.41697246f, 0.58249325f, 5.0f));
        spaces.add(new Space(0.45461673f, 0.5824941f, -5.0f));
        spaces.add(new Space(0.49296603f, 0.5742991f, -12.5f));
        spaces.add(new Space(0.5292084f, 0.5543725f, -25.0f));
        routes.add(new Route(55, new Tuple(31, 34), spaces, true, null, null));

        // Route 56: Denver 31 - Kansas City 34
        spaces = new ArrayList<>();
        spaces.add(new Space(0.41696155f, 0.6059467f, 5.0f));
        spaces.add(new Space(0.4553136f, 0.6059467f, -5.0f));
        spaces.add(new Space(0.49296603f, 0.59658074f, -12.5f));
        spaces.add(new Space(0.52989984f, 0.5777997f, -25.0f));
        routes.add(new Route(56, new Tuple(31, 34), spaces, true, null, null));

        // Route 57: Salt Lake City 30 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(0.27195123f, 0.5215547f, 10.0f));
        spaces.add(new Space(0.30959004f, 0.53330034f, 10.0f));
        spaces.add(new Space(0.34584877f, 0.5438118f, 12.5f));
        routes.add(new Route(57, new Tuple(30, 31), spaces, true, null, null));

        // Route 58: Salt Lake City 30 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(0.26986608f, 0.5438364f, 10.0f));
        spaces.add(new Space(0.307494f, 0.5555434f, 10.0f));
        spaces.add(new Space(0.34445503f, 0.56843287f, 12.5f));
        routes.add(new Route(58, new Tuple(30, 31), spaces, true, null, null));

        // Route 59: Portland 17 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(0.07672855f, 0.29655367f, 12.5f));
        spaces.add(new Space(0.11368957f, 0.3141015f, 20.0f));
        spaces.add(new Space(0.14854911f, 0.34341952f, 32.5f));
        spaces.add(new Space(0.17990527f, 0.3809194f, 40.0f));
        spaces.add(new Space(0.20710203f, 0.4266143f, 47.5f));
        spaces.add(new Space(0.22874293f, 0.4770079f, 60.0f));
        routes.add(new Route(59, new Tuple(17, 30), spaces, false, null, null));

        // Route 60: San Francisco 16 - Portland 17
        spaces = new ArrayList<>();
        spaces.add(new Space(0.029322736f, 0.33168876f, -67.5f));
        spaces.add(new Space(0.018867051f, 0.39382285f, -80.0f));
        spaces.add(new Space(0.013273084f, 0.4582611f, -90.0f));
        spaces.add(new Space(0.013273084f, 0.52272564f, -92.5f));
        spaces.add(new Space(0.020252613f, 0.58834803f, -107.5f));
        routes.add(new Route(60, new Tuple(16, 17), spaces, true, null, null));

        // Route 61: San Francisco 16 - Portland 17
        spaces = new ArrayList<>();
        spaces.add(new Space(0.043273628f, 0.3352148f, -67.5f));
        spaces.add(new Space(0.032126524f, 0.39730942f, -80.0f));
        spaces.add(new Space(0.027923563f, 0.46060303f, -87.5f));
        spaces.add(new Space(0.029309124f, 0.525093f, -95.0f));
        spaces.add(new Space(0.034919426f, 0.58834803f, -107.5f));
        routes.add(new Route(61, new Tuple(16, 17), spaces, true, null, null));

        // Route 62: San Francisco 16 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(0.06558145f, 0.610642f, -20.0f));
        spaces.add(new Space(0.10182655f, 0.5895313f, -20.0f));
        spaces.add(new Space(0.137402f, 0.5696301f, -20.0f));
        spaces.add(new Space(0.17226699f, 0.549678f, -17.5f));
        spaces.add(new Space(0.20779344f, 0.52976286f, -17.5f));
        routes.add(new Route(62, new Tuple(16, 30), spaces, true, null, null));

        // Route 63: San Francisco 16 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(0.07117269f, 0.6305695f, -20.0f));
        spaces.add(new Space(0.10672637f, 0.60945874f, -20.0f));
        spaces.add(new Space(0.14225556f, 0.59070224f, -20.0f));
        spaces.add(new Space(0.17782284f, 0.5707871f, -20.0f));
        spaces.add(new Space(0.21339014f, 0.550885f, -17.5f));
        routes.add(new Route(63, new Tuple(16, 30), spaces, true, null, null));

        // Route 64: Los Angeles 15 - San Francisco 16
        spaces = new ArrayList<>();
        spaces.add(new Space(0.039772976f, 0.69386226f, 65.0f));
        spaces.add(new Space(0.058601916f, 0.75009406f, 55.0f));
        spaces.add(new Space(0.08301666f, 0.7969853f, 45.0f));
        routes.add(new Route(64, new Tuple(15, 16), spaces, true, null, null));

        // Route 65: Los Angeles 15 - San Francisco 16
        spaces = new ArrayList<>();
        spaces.add(new Space(0.050920077f, 0.67743534f, 65.0f));
        spaces.add(new Space(0.06974357f, 0.73721606f, 55.0f));
        spaces.add(new Space(0.09485518f, 0.7841074f, 47.5f));
        routes.add(new Route(65, new Tuple(15, 16), spaces, true, null, null));

        // Route 66: Los Angeles 15 - Las Vegas 29
        spaces = new ArrayList<>();
        spaces.add(new Space(0.124145254f, 0.76885056f, -65.0f));
        spaces.add(new Space(0.15341899f, 0.72782725f, -15.0f));
        routes.add(new Route(66, new Tuple(15, 29), spaces, false, null, null));

        // Route 67: Las Vegas 29 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(0.20782611f, 0.69383854f, -45.0f));
        spaces.add(new Space(0.23012848f, 0.6422888f, -67.5f));
        spaces.add(new Space(0.2398873f, 0.5790067f, -82.5f));
        routes.add(new Route(67, new Tuple(29, 30), spaces, false, null, null));

        // Route 68: Phoenix 14 - Los Angeles 15
        spaces = new ArrayList<>();
        spaces.add(new Space(0.14087816f, 0.80988705f, -7.5f));
        spaces.add(new Space(0.17851426f, 0.80519265f, 0.0f));
        spaces.add(new Space(0.21686085f, 0.81455773f, 15.0f));
        routes.add(new Route(68, new Tuple(14, 15), spaces, false, null, null));

        // Route 69: El Paso 13 - Los Angeles 15
        spaces = new ArrayList<>();
        spaces.add(new Space(0.14018129f, 0.8567415f, 35.0f));
        spaces.add(new Space(0.17362532f, 0.89074165f, 25.0f));
        spaces.add(new Space(0.20991942f, 0.91419345f, 12.5f));
        spaces.add(new Space(0.24752831f, 0.9259013f, 7.5f));
        spaces.add(new Space(0.28590757f, 0.9259013f, -5.0f));
        spaces.add(new Space(0.3242514f, 0.9165214f, -12.5f));
        routes.add(new Route(69, new Tuple(13, 15), spaces, false, null, null));

        // Route 70: El Paso 13 - Phoenix 14
        spaces = new ArrayList<>();
        spaces.add(new Space(0.26847234f, 0.8532286f, 15.0f));
        spaces.add(new Space(0.30473104f, 0.8708405f, 15.0f));
        spaces.add(new Space(0.34098977f, 0.88838744f, 17.5f));
        routes.add(new Route(70, new Tuple(13, 14), spaces, false, null, null));

        // Route 71: Phoenix 14 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(0.2524254f, 0.7922892f, -67.5f));
        spaces.add(new Space(0.2691556f, 0.7360451f, -60.0f));
        spaces.add(new Space(0.29009962f, 0.68213147f, -55.0f));
        spaces.add(new Space(0.3151677f, 0.6340824f, -37.5f));
        spaces.add(new Space(0.34793392f, 0.60244787f, -20.0f));
        routes.add(new Route(71, new Tuple(14, 31), spaces, false, null, null));

        // Route 72: Phoenix 14 - Santa Fe 28
        spaces = new ArrayList<>();
        spaces.add(new Space(0.27612153f, 0.81105804f, -22.5f));
        spaces.add(new Space(0.31031686f, 0.7852783f, -22.5f));
        spaces.add(new Space(0.34448224f, 0.75948375f, -22.5f));
        routes.add(new Route(72, new Tuple(14, 28), spaces, false, null, null));

        // Route 73: El Paso 13 - Santa Fe 28
        spaces = new ArrayList<>();
        spaces.add(new Space(0.3681729f, 0.85321885f, 92.5f));
        spaces.add(new Space(0.3709522f, 0.789949f, 95.0f));
        routes.add(new Route(73, new Tuple(13, 28), spaces, false, null, null));

        // Route 74: Santa Fe 28 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(0.3730319f, 0.695034f, -87.5f));
        spaces.add(new Space(0.3751579f, 0.6317159f, -87.5f));
        routes.add(new Route(74, new Tuple(28, 31), spaces, false, null, null));

        // Route 75: Oklahoma City 27 - Santa Fe 28
        spaces = new ArrayList<>();
        spaces.add(new Space(0.4037402f, 0.74072725f, -7.5f));
        spaces.add(new Space(0.44065222f, 0.73136467f, -7.5f));
        spaces.add(new Space(0.47903964f, 0.72433805f, -7.5f));
        routes.add(new Route(75, new Tuple(27, 28), spaces, false, null, null));

        // Route 76: Oklahoma City 27 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(0.3988567f, 0.6328851f, 45.0f));
        spaces.add(new Space(0.43093422f, 0.67159367f, 25.0f));
        spaces.add(new Space(0.4678517f, 0.69035023f, 5.0f));
        spaces.add(new Space(0.50549597f, 0.6950332f, 0.0f));
        routes.add(new Route(76, new Tuple(27, 31), spaces, false, null, null));

        // Route 77: El Paso 13 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(0.39465103f, 0.88487715f, -17.5f));
        spaces.add(new Space(0.4309179f, 0.8602798f, -27.5f));
        spaces.add(new Space(0.46506152f, 0.827448f, -35.0f));
        spaces.add(new Space(0.49434343f, 0.78762025f, -45.0f));
        spaces.add(new Space(0.5194659f, 0.73721606f, -55.0f));
        routes.add(new Route(77, new Tuple(13, 27), spaces, false, null, null));

        // Route 78: Houston 12 - El Paso 13
        spaces = new ArrayList<>();
        spaces.add(new Space(0.38978386f, 0.9317684f, 32.5f));
        spaces.add(new Space(0.42603442f, 0.95988744f, 17.5f));
        spaces.add(new Space(0.46299815f, 0.97395045f, 7.5f));
        spaces.add(new Space(0.49992922f, 0.978658f, 0.0f));
        spaces.add(new Space(0.53898084f, 0.97513366f, -12.5f));
        spaces.add(new Space(0.57593095f, 0.95753497f, -20.0f));
        routes.add(new Route(78, new Tuple(12, 13), spaces, false, null, null));

        // Route 79: El Paso 13 - Dallas 26
        spaces = new ArrayList<>();
        spaces.add(new Space(0.41559234f, 0.904802f, -7.5f));
        spaces.add(new Space(0.45323116f, 0.8954378f, -7.5f));
        spaces.add(new Space(0.49087816f, 0.88604635f, -7.5f));
        spaces.add(new Space(0.52852786f, 0.87549716f, -10.0f));
        routes.add(new Route(79, new Tuple(13, 26), spaces, false, null, null));

        // Route 80: Houston 12 - Dallas 26
        spaces = new ArrayList<>();
        spaces.add(new Space(0.57455355f, 0.901304f, 50.0f));
        routes.add(new Route(80, new Tuple(12, 26), spaces, true, null, null));

        // Route 81: Houston 12 - Dallas 26
        spaces = new ArrayList<>();
        spaces.add(new Space(0.58498746f, 0.8848894f, 47.5f));
        routes.add(new Route(81, new Tuple(12, 26), spaces, true, null, null));

        // Route 82: Dallas 26 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5438453f, 0.7489231f, 82.5f));
        spaces.add(new Space(0.5487397f, 0.8134f, 82.5f));
        routes.add(new Route(82, new Tuple(26, 27), spaces, true, null, null));

        // Route 83: Dallas 26 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5584985f, 0.7489354f, 82.5f));
        spaces.add(new Space(0.5640897f, 0.8110457f, 82.5f));
        routes.add(new Route(83, new Tuple(26, 27), spaces, true, null, null));

        // Route 84: Oklahoma City 27 - Kansas City 34
        spaces = new ArrayList<>();
        spaces.add(new Space(0.54176015f, 0.6540099f, -72.5f));
        spaces.add(new Space(0.5522403f, 0.5930688f, -72.5f));
        routes.add(new Route(84, new Tuple(27, 34), spaces, true, null, null));

        // Route 85: Oklahoma City 27 - Kansas City 34
        spaces = new ArrayList<>();
        spaces.add(new Space(0.56548345f, 0.60010594f, -72.5f));
        spaces.add(new Space(0.5550278f, 0.66219f, -72.5f));
        routes.add(new Route(85, new Tuple(27, 34), spaces, true, null, null));

        // Route 86: Little Rock 24 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5675686f, 0.70321506f, 0.0f));
        spaces.add(new Space(0.60660386f, 0.7032282f, 0.0f));
        routes.add(new Route(86, new Tuple(24, 27), spaces, false, null, null));

        // Route 87: Little Rock 24 - Dallas 26
        spaces = new ArrayList<>();
        spaces.add(new Space(0.5884636f, 0.8028367f, -55.0f));
        spaces.add(new Space(0.61149555f, 0.7489494f, -57.5f));
        routes.add(new Route(87, new Tuple(24, 26), spaces, false, null, null));

        // Route 88: Little Rock 24 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6386923f, 0.6598621f, -75.0f));
        spaces.add(new Space(0.64844567f, 0.59773946f, -77.5f));
        routes.add(new Route(88, new Tuple(24, 35), spaces, false, null, null));

        // Route 89: New Orleans 11 - Houston 12
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6331228f, 0.9235594f, -10.0f));
        spaces.add(new Space(0.67073715f, 0.912997f, -7.5f));
        routes.add(new Route(89, new Tuple(11, 12), spaces, false, null, null));

        // Route 90: Nashville 25 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(0.67910767f, 0.58834803f, 17.5f));
        spaces.add(new Space(0.7167601f, 0.60945874f, 17.5f));
        routes.add(new Route(90, new Tuple(25, 35), spaces, false, null, null));

        // Route 91: New Orleans 11 - Little Rock 24
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6477488f, 0.751265f, 62.5f));
        spaces.add(new Space(0.66656685f, 0.80870384f, 62.5f));
        spaces.add(new Space(0.68469894f, 0.86497337f, 62.5f));
        routes.add(new Route(91, new Tuple(11, 24), spaces, false, null, null));

        // Route 92: Little Rock 24 - Nashville 25
        spaces = new ArrayList<>();
        spaces.add(new Space(0.6623693f, 0.704386f, -5.0f));
        spaces.add(new Space(0.7000218f, 0.6891555f, -22.5f));
        spaces.add(new Space(0.7321047f, 0.65400815f, -42.5f));
        routes.add(new Route(92, new Tuple(24, 25), spaces, false, null, null));

        // Route 93: Atlanta 23 - Nashville 25
        spaces = new ArrayList<>();
        spaces.add(new Space(0.77535385f, 0.65048295f, 35.0f));
        routes.add(new Route(93, new Tuple(23, 25), spaces, false, null, null));

        // Route 94: New Orleans 11 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(0.7112097f, 0.8602535f, -67.5f));
        spaces.add(new Space(0.7300332f, 0.8040103f, -57.5f));
        spaces.add(new Space(0.75231653f, 0.75009406f, -50.0f));
        spaces.add(new Space(0.7788137f, 0.70437455f, -42.5f));
        routes.add(new Route(94, new Tuple(11, 23), spaces, true, null, null));

        // Route 95: New Orleans 11 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(0.72234595f, 0.876669f, -67.5f));
        spaces.add(new Space(0.73978114f, 0.82043713f, -57.5f));
        spaces.add(new Space(0.7634691f, 0.7688391f, -50.0f));
        spaces.add(new Space(0.7885698f, 0.7219601f, -42.5f));
        routes.add(new Route(95, new Tuple(11, 23), spaces, true, null, null));

        // Route 96: Charleston 9 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(0.8380989f, 0.70205724f, 0.0f));
        spaces.add(new Space(0.875735f, 0.70321506f, 0.0f));
        routes.add(new Route(96, new Tuple(9, 23), spaces, false, null, null));

        // Route 97: Miami 10 - New Orleans 11
        spaces = new ArrayList<>();
        spaces.add(new Space(0.740478f, 0.9001182f, -32.5f));
        spaces.add(new Space(0.7760453f, 0.87433845f, -15.0f));
        spaces.add(new Space(0.8143946f, 0.866132f, 0.0f));
        spaces.add(new Space(0.85203344f, 0.8825598f, 32.5f));
        spaces.add(new Space(0.8820122f, 0.9177063f, 40.0f));
        spaces.add(new Space(0.9099031f, 0.9622434f, 50.0f));
        routes.add(new Route(97, new Tuple(10, 11), spaces, false, null, null));

        // Route 98: Miami 10 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(0.81997496f, 0.73369175f, 50.0f));
        spaces.add(new Space(0.84434617f, 0.78409505f, 52.5f));
        spaces.add(new Space(0.86807764f, 0.8344958f, 50.0f));
        spaces.add(new Space(0.892457f, 0.8848745f, 50.0f));
        spaces.add(new Space(0.9161667f, 0.93526554f, 52.5f));
        routes.add(new Route(98, new Tuple(10, 23), spaces, false, null, null));

        // Route 99: Charleston 9 - Miami 10
        spaces = new ArrayList<>();
        spaces.add(new Space(0.90573007f, 0.7419122f, 90.0f));
        spaces.add(new Space(0.9092008f, 0.80519265f, 82.5f));
        spaces.add(new Space(0.9175686f, 0.86847395f, 72.5f));
        spaces.add(new Space(0.93291867f, 0.92708373f, 60.0f));
        routes.add(new Route(99, new Tuple(9, 10), spaces, false, null, null));

        return routes;
    }
}
