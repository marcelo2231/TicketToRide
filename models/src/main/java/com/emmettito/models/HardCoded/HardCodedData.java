package com.emmettito.models.HardCoded;

import com.emmettito.models.City;
import com.emmettito.models.Route;
import com.emmettito.models.Space;
import com.emmettito.models.Tuple;

import java.util.ArrayList;
import java.util.List;

public class HardCodedData {

    public HardCodedData() {
    }

    public List<City> getCities() {
        ArrayList<City> cities = new ArrayList<>();

        cities.add(new City(0, "Vancouver", null, 105.11328f, 86.1001f));
        cities.add(new City(1, "Calgary", null, 304.22266f, 59.072998f));
        cities.add(new City(2, "Winnipeg", null, 646.39453f, 75.0918f));
        cities.add(new City(3, "Sault St. Marie", null, 1010.54297f, 150.1394f));
        cities.add(new City(4, "Montreal", null, 1302.7031f, 51.075928f));
        cities.add(new City(5, "Boston", null, 1410.7461f, 140.1311f));
        cities.add(new City(6, "New York", null, 1331.7227f, 254.23584f));
        cities.add(new City(7, "Washington DC", null, 1344.7109f, 392.37744f));
        cities.add(new City(8, "Raleigh", null, 1254.6562f, 495.4724f));
        cities.add(new City(9, "Charleston", null, 1297.7227f, 594.5464f));
        cities.add(new City(10, "Miami", null, 1346.7109f, 834.77466f));
        cities.add(new City(11, "New Orleans", null, 1007.5703f, 777.7219f));
        cities.add(new City(12, "Houston", null, 865.47656f, 796.7175f));
        cities.add(new City(13, "El Paso", null, 527.33594f, 771.7234f));
        cities.add(new City(14, "Phoenix", null, 346.23828f, 715.6609f));
        cities.add(new City(15, "Los Angeles", null, 163.11719f, 705.6406f));
        cities.add(new City(16, "San Francisco", null, 45.070312f, 546.49365f));
        cities.add(new City(17, "Portland", null, 67.08203f, 245.24707f));
        cities.add(new City(18, "Seattle", null, 99.09766f, 169.1687f));
        cities.add(new City(19, "Helena", null, 456.2578f, 260.24414f));
        cities.add(new City(20, "Duluth", null, 817.4375f, 251.24707f));
        cities.add(new City(21, "Toronto", null, 1177.6641f, 184.17554f));
        cities.add(new City(22, "Pittsburgh", null, 1202.6484f, 323.30737f));
        cities.add(new City(23, "Atlanta", null, 1153.6484f, 583.5388f));
        cities.add(new City(24, "Little Rock", null, 908.5078f, 607.5442f));
        cities.add(new City(25, "Nashville", null, 1077.5898f, 529.49805f));
        cities.add(new City(26, "Dallas", null, 802.4453f, 735.6565f));
        cities.add(new City(27, "Oklahoma City", null, 772.4375f, 601.54565f));
        cities.add(new City(28, "Santa Fe", null, 535.3164f, 634.58105f));
        cities.add(new City(29, "Las Vegas", null, 261.20703f, 616.56445f));
        cities.add(new City(30, "Salt Lake City", null, 347.22266f, 448.40698f));
        cities.add(new City(31, "Denver", null, 546.3242f, 496.4619f));
        cities.add(new City(32, "Omaha", null, 771.40625f, 391.37744f));
        cities.add(new City(33, "Chicago", null, 1004.5469f, 345.3225f));
        cities.add(new City(34, "Kansas City", null, 802.4492f, 469.44604f));
        cities.add(new City(35, "Saint Louis", null, 935.5039f, 471.43408f));

        return cities;
    }

    public List<Route> getRoutes() {
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Space> spaces;

        // Route 0: Vancouver 0 - Calgary 1
        spaces = new ArrayList<>();
        spaces.add(new Space(150.15625f, 75.10156f, -7.5f));
        spaces.add(new Space(205.16016f, 68.083496f, -7.5f));
        spaces.add(new Space(259.1797f, 62.07373f, -5.0f));
        routes.add(new Route(0, new Tuple(0, 1), spaces, false, null, null));

        // Route 1: Calgary 1 - Winnipeg 2
        spaces = new ArrayList<>();
        spaces.add(new Space(345.23047f, 44.045166f, -22.5f));
        spaces.add(new Space(397.26172f, 27.04956f, -12.5f));
        spaces.add(new Space(451.29297f, 21.028564f, -2.5f));
        spaces.add(new Space(506.3086f, 22.039062f, 5.0f));
        spaces.add(new Space(561.3125f, 32.026367f, 17.5f));
        spaces.add(new Space(612.3672f, 51.075928f, 25.0f));
        routes.add(new Route(1, new Tuple(1, 2), spaces, false, null, null));

        // Route 2: Winnipeg 2 - Sault St. Marie 3
        spaces = new ArrayList<>();
        spaces.add(new Space(701.375f, 77.0813f, 12.5f));
        spaces.add(new Space(755.4219f, 89.10986f, 12.5f));
        spaces.add(new Space(808.46094f, 100.0957f, 12.5f));
        spaces.add(new Space(861.4922f, 111.10547f, 12.5f));
        spaces.add(new Space(914.52344f, 123.11304f, 12.5f));
        spaces.add(new Space(969.5547f, 134.12207f, 12.5f));
        routes.add(new Route(2, new Tuple(2, 3), spaces, false, null, null));

        // Route 3: Sault St. Marie 3 - Montreal 4
        spaces = new ArrayList<>();
        spaces.add(new Space(1045.5977f, 120.11377f, -37.5f));
        spaces.add(new Space(1090.6055f, 89.1001f, -27.5f));
        spaces.add(new Space(1140.6367f, 67.07153f, -20.0f));
        spaces.add(new Space(1193.6602f, 52.053467f, -12.5f));
        spaces.add(new Space(1248.6797f, 46.06543f, 0.0f));
        routes.add(new Route(3, new Tuple(3, 4), spaces, false, null, null));

        // Route 4: Montreal 4 - Boston 5 [Upper]
        spaces = new ArrayList<>();
        spaces.add(new Space(1343.7109f, 71.07153f, 40.0f));
        spaces.add(new Space(1384.7266f, 106.0957f, 40.0f));
        routes.add(new Route(4, new Tuple(4, 5), spaces, true, null, null));

        // Route 5: Montreal 4 - Boston 5 [Lower]
        spaces = new ArrayList<>();
        spaces.add(new Space(1331.707f, 86.110596f, 40.0f));
        spaces.add(new Space(1372.7305f, 120.13623f, 40.0f));
        routes.add(new Route(5, new Tuple(4, 5), spaces, true, null, null));

        // Route 6: Montreal 4 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(1262.6797f, 70.08203f, -25.0f));
        spaces.add(new Space(1217.6367f, 101.0957f, -42.5f));
        spaces.add(new Space(1181.6523f, 143.1311f, -57.5f));
        routes.add(new Route(6, new Tuple(4, 21), spaces, false, null, null));

        // Route 7: Sault St. Marie 3 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(1061.5508f, 158.1499f, 12.5f));
        spaces.add(new Space(1114.6172f, 170.16797f, 15.0f));
        routes.add(new Route(7, new Tuple(3, 21), spaces, false, null, null));

        // Route 8: Duluth 20 - Sault St. Marie 3
        spaces = new ArrayList<>();
        spaces.add(new Space(868.46875f, 216.20044f, -25.0f));
        spaces.add(new Space(918.4961f, 194.19434f, -22.5f));
        spaces.add(new Space(968.52734f, 172.17773f, -22.5f));
        routes.add(new Route(8, new Tuple(3, 20), spaces, false, null, null));

        // Route 9: Winnipeg 2 - Duluth 20
        spaces = new ArrayList<>();
        spaces.add(new Space(673.40625f, 105.11743f, 42.5f));
        spaces.add(new Space(712.4336f, 143.14014f, 45.0f));
        spaces.add(new Space(751.40625f, 180.16577f, 42.5f));
        spaces.add(new Space(790.4258f, 218.20044f, 45.0f));
        routes.add(new Route(9, new Tuple(2, 20), spaces, false, null, null));

        // Route 10: Helena 19 - Winnipeg 2
        spaces = new ArrayList<>();
        spaces.add(new Space(493.29297f, 221.2207f, -45.0f));
        spaces.add(new Space(531.3164f, 182.17554f, -45.0f));
        spaces.add(new Space(569.34375f, 144.1521f, -45.0f));
        spaces.add(new Space(609.3711f, 106.10547f, -45.0f));
        routes.add(new Route(10, new Tuple(2, 19), spaces, false, null, null));

        // Route 11: Calgary 1 - Helena 19
        spaces = new ArrayList<>();
        spaces.add(new Space(332.23438f, 95.0874f, 50.0f));
        spaces.add(new Space(367.21094f, 136.13184f, 50.0f));
        spaces.add(new Space(402.2461f, 178.16577f, 50.0f));
        spaces.add(new Space(438.27734f, 220.2207f, 50.0f));
        routes.add(new Route(11, new Tuple(1, 19), spaces, false, null, null));

        // Route 12: Seattle 18 - Calgary 1
        spaces = new ArrayList<>();
        spaces.add(new Space(145.14062f, 167.156f, 0.0f));
        spaces.add(new Space(202.14453f, 163.1477f, -7.5f));
        spaces.add(new Space(253.16797f, 142.1521f, -37.5f));
        spaces.add(new Space(288.21875f, 100.0957f, -62.5f));
        routes.add(new Route(12, new Tuple(1, 18), spaces, false, null, null));

        // Route 13: Seattle 18 - Vancouver 0 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(91.11719f, 127.13403f, 90.0f));
        routes.add(new Route(13, new Tuple(0, 18), spaces, true, null, null));

        // Route 14: Seattle 18 - Vancouver 0 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(111.09766f, 126.123535f, 87.5f));
        routes.add(new Route(14, new Tuple(0, 18), spaces, true, null, null));

        // Route 15: Portland 17 - Seattle 18 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(73.07031f, 203.20264f, -67.5f));
        routes.add(new Route(15, new Tuple(17, 18), spaces, true, null, null));

        // Route 16: Portland 17 - Seattle 18 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(92.109375f, 210.2124f, -67.5f));
        routes.add(new Route(16, new Tuple(17, 18), spaces, true, null, null));

        // Route 17: Seattle 18 - Helena 19
        spaces = new ArrayList<>();
        spaces.add(new Space(143.14844f, 196.20483f, 12.5f));
        spaces.add(new Space(196.16406f, 208.21313f, 12.5f));
        spaces.add(new Space(250.1836f, 220.2207f, 12.5f));
        spaces.add(new Space(303.23047f, 231.2185f, 12.5f));
        spaces.add(new Space(356.2539f, 244.22607f, 12.5f));
        spaces.add(new Space(410.27734f, 255.25684f, 12.5f));
        routes.add(new Route(17, new Tuple(18, 19), spaces, false, null, null));

        // Route 18: Helena 19 - Duluth 20
        spaces = new ArrayList<>();
        spaces.add(new Space(502.32422f, 258.25684f, 0.0f));
        spaces.add(new Space(557.33203f, 257.25684f, 0.0f));
        spaces.add(new Space(611.3711f, 257.23438f, 0.0f));
        spaces.add(new Space(666.41406f, 256.23438f, 0.0f));
        spaces.add(new Space(721.40625f, 255.25684f, 0.0f));
        spaces.add(new Space(776.46875f, 254.24487f, 0.0f));
        routes.add(new Route(18, new Tuple(19, 20), spaces, false, null, null));

        // Route 19: Duluth 20 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(858.46094f, 244.22607f, -10.0f));
        spaces.add(new Space(912.4844f, 235.229f, -10.0f));
        spaces.add(new Space(967.5469f, 226.23047f, -10.0f));
        spaces.add(new Space(1020.53516f, 216.20044f, -10.0f));
        spaces.add(new Space(1074.5977f, 207.2019f, -7.5f));
        spaces.add(new Space(1128.6289f, 197.20483f, -10.0f));
        routes.add(new Route(19, new Tuple(20, 21), spaces, false, null, null));

        // Route 20: Montreal 4 - New York 6
        spaces = new ArrayList<>();
        spaces.add(new Space(1298.7188f, 102.0957f, -97.5f));
        spaces.add(new Space(1306.6875f, 154.1499f, 82.5f));
        spaces.add(new Space(1315.707f, 207.21313f, 80.0f));
        routes.add(new Route(20, new Tuple(4, 6), spaces, false, null, null));

        // Route 21: Boston 5 - New York 6 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(1382.7461f, 175.16577f, -57.5f));
        spaces.add(new Space(1354.7422f, 220.2207f, -57.5f));
        routes.add(new Route(21, new Tuple(5, 6), spaces, true, null, null));

        // Route 22: Boston 5 - New York 6 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(1398.7266f, 185.19653f, -57.5f));
        spaces.add(new Space(1370.7305f, 231.21777f, -57.5f));
        routes.add(new Route(22, new Tuple(5, 6), spaces, true, null, null));

        // Route 23: Helena 19 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(515.3242f, 289.28027f, 22.5f));
        spaces.add(new Space(565.3125f, 309.2766f, 22.5f));
        spaces.add(new Space(615.3711f, 329.30444f, 22.5f));
        spaces.add(new Space(666.3828f, 350.333f, 22.5f));
        spaces.add(new Space(717.40625f, 372.35938f, 22.5f));
        routes.add(new Route(23, new Tuple(19, 32), spaces, false, null, null));

        // Route 24: Helena 19 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(473.27344f, 298.2915f, 67.5f));
        spaces.add(new Space(494.28516f, 347.3225f, 67.5f));
        spaces.add(new Space(515.3242f, 397.37598f, 67.5f));
        spaces.add(new Space(535.3203f, 448.40698f, 67.5f));
        routes.add(new Route(24, new Tuple(19, 32), spaces, false, null, null));

        // Route 25: Helena 19 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(431.25f, 301.2893f, -60.0f));
        spaces.add(new Space(404.23828f, 347.333f, -60.0f));
        spaces.add(new Space(376.27344f, 394.35498f, -60.0f));
        routes.add(new Route(25, new Tuple(19, 30), spaces, false, null, null));

        // Route 26: Omaha 32 - Duluth 20 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(772.46094f, 343.32104f, -67.5f));
        spaces.add(new Space(792.4219f, 293.2915f, -67.5f));
        routes.add(new Route(26, new Tuple(20, 32), spaces, true, null, null));

        // Route 27: Omaha 32 - Duluth 20 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(811.4453f, 301.28857f, -67.5f));
        spaces.add(new Space(791.4219f, 351.33154f, -67.5f));
        routes.add(new Route(27, new Tuple(20, 32), spaces, true, null, null));

        // Route 28: Duluth 20 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(851.4883f, 277.26294f, 30.0f));
        spaces.add(new Space(901.51953f, 301.28857f, 20.0f));
        spaces.add(new Space(954.54297f, 316.2766f, 15.0f));
        routes.add(new Route(28, new Tuple(20, 33), spaces, false, null, null));

        // Route 29: Chicago 33 - Toronto 21
        spaces = new ArrayList<>();
        spaces.add(new Space(1010.5742f, 300.28857f, -45.0f));
        spaces.add(new Space(1053.6016f, 264.24487f, -32.5f));
        spaces.add(new Space(1102.6094f, 240.23877f, -17.5f));
        spaces.add(new Space(1151.625f, 216.2019f, -37.5f));
        routes.add(new Route(29, new Tuple(21, 33), spaces, false, null, null));

        // Route 30: Toronto 21 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(1190.6602f, 221.1997f, 90.0f));
        spaces.add(new Space(1192.668f, 276.24194f, 85.0f));
        routes.add(new Route(30, new Tuple(21, 22), spaces, false, null, null));

        // Route 31: Chicago 33 - Pittsburgh 22 [Upper]
        spaces = new ArrayList<>();
        spaces.add(new Space(1040.5586f, 316.28638f, -15.0f));
        spaces.add(new Space(1094.5703f, 305.28857f, -10.0f));
        spaces.add(new Space(1149.6328f, 302.2788f, 0.0f));
        routes.add(new Route(31, new Tuple(22, 33), spaces, true, null, null));

        // Route 32: Chicago 33 - Pittsburgh 22 [Lower]
        spaces = new ArrayList<>();
        spaces.add(new Space(1047.5781f, 335.30298f, -15.0f));
        spaces.add(new Space(1100.6094f, 324.30664f, -10.0f));
        spaces.add(new Space(1154.625f, 322.30664f, 0.0f));
        routes.add(new Route(32, new Tuple(22, 33), spaces, true, null, null));

        // Route 33: Omaha 32 - Chicago 33
        spaces = new ArrayList<>();
        spaces.add(new Space(809.48047f, 377.3474f, -35.0f));
        spaces.add(new Space(853.5039f, 345.33374f, -35.0f));
        spaces.add(new Space(907.5f, 333.32544f, 10.0f));
        spaces.add(new Space(960.53906f, 341.3247f, 10.0f));
        routes.add(new Route(33, new Tuple(32, 33), spaces, false, null, null));

        // Route 34: Pittsburgh 22 - New York 6 [Upper]
        spaces = new ArrayList<>();
        spaces.add(new Space(1236.6758f, 287.26074f, -32.5f));
        spaces.add(new Space(1283.6641f, 260.26514f, -32.5f));
        routes.add(new Route(34, new Tuple(6, 22), spaces, true, null, null));

        // Route 35: Pittsburgh 22 - New York 6 [Lower]
        spaces = new ArrayList<>();
        spaces.add(new Space(1246.6914f, 303.2893f, -32.5f));
        spaces.add(new Space(1293.6797f, 276.2622f, -32.5f));
        routes.add(new Route(35, new Tuple(6, 22), spaces, true, null, null));

        // Route 36: New York 6 - Washington DC 7 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(1330.7031f, 296.28027f, 87.5f));
        spaces.add(new Space(1333.7031f, 350.3225f, 87.5f));
        routes.add(new Route(36, new Tuple(6, 7), spaces, true, null, null));

        // Route 37: New York 6 - Washington DC 7 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(1350.75f, 294.28027f, 87.5f));
        spaces.add(new Space(1353.7422f, 349.3435f, 87.5f));
        routes.add(new Route(37, new Tuple(6, 7), spaces, true, null, null));

        // Route 38: Pittsburgh 22 - Washington DC 7
        spaces = new ArrayList<>();
        spaces.add(new Space(1249.6914f, 348.333f, 27.5f));
        spaces.add(new Space(1297.7188f, 373.34888f, 27.5f));
        routes.add(new Route(38, new Tuple(7, 22), spaces, false, null, null));

        // Route 39: Raleigh 8 - Washington DC 7 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(1276.6992f, 458.42725f, -50.0f));
        spaces.add(new Space(1310.6758f, 417.4038f, -50.0f));
        routes.add(new Route(39, new Tuple(7, 8), spaces, true, null, null));

        // Route 40: Raleigh 8 - Washington DC 7 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(1292.6797f, 471.43335f, -50.0f));
        spaces.add(new Space(1326.7227f, 430.3911f, -50.0f));
        routes.add(new Route(40, new Tuple(7, 8), spaces, true, null, null));

        // Route 41: Nashville 25 - Raleigh 8
        spaces = new ArrayList<>();
        spaces.add(new Space(1111.5859f, 503.4702f, -32.5f));
        spaces.add(new Space(1162.6055f, 483.4536f, -12.5f));
        spaces.add(new Space(1215.6719f, 477.44385f, 0.0f));
        routes.add(new Route(41, new Tuple(8, 25), spaces, false, null, null));

        // Route 42: Pittsburgh 22 - Raleigh 8
        spaces = new ArrayList<>();
        spaces.add(new Space(1221.6719f, 379.34668f, 77.5f));
        spaces.add(new Space(1234.6836f, 433.4099f, 77.5f));
        routes.add(new Route(42, new Tuple(8, 22), spaces, false, null, null));

        // Route 43: Saint Louis 35 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(972.53516f, 462.43774f, -30.0f));
        spaces.add(new Space(1021.5664f, 435.4099f, -30.0f));
        spaces.add(new Space(1068.5664f, 408.37378f, -30.0f));
        spaces.add(new Space(1115.6094f, 381.3474f, -30.0f));
        spaces.add(new Space(1163.6562f, 353.3435f, -30.0f));
        routes.add(new Route(43, new Tuple(22, 35), spaces, false, null, null));

        // Route 44: Nashville 25 - Pittsburgh 22
        spaces = new ArrayList<>();
        spaces.add(new Space(1073.6016f, 495.4641f, -60.0f));
        spaces.add(new Space(1103.6016f, 449.40918f, -52.5f));
        spaces.add(new Space(1143.6289f, 411.37305f, -32.5f));
        spaces.add(new Space(1182.6523f, 374.3369f, -57.5f));
        routes.add(new Route(44, new Tuple(22, 25), spaces, false, null, null));

        // Route 45: Raleigh 8 - Charleston 9
        spaces = new ArrayList<>();
        spaces.add(new Space(1286.6641f, 519.4673f, 35.0f));
        spaces.add(new Space(1311.6875f, 557.52295f, -65.0f));
        routes.add(new Route(45, new Tuple(8, 9), spaces, false, null, null));

        // Route 46: Atlanta 23 - Raleigh 8 [Top]
        spaces = new ArrayList<>();
        spaces.add(new Space(1177.6133f, 549.50415f, -42.5f));
        spaces.add(new Space(1218.6289f, 514.4905f, -40.0f));
        routes.add(new Route(46, new Tuple(8, 23), spaces, true, null, null));

        // Route 47: Atlanta 23 - Raleigh 8 [Bottom]
        spaces = new ArrayList<>();
        spaces.add(new Space(1191.6562f, 563.5327f, -40.0f));
        spaces.add(new Space(1232.6562f, 528.49805f, -40.0f));
        routes.add(new Route(47, new Tuple(8, 23), spaces, true, null, null));

        // Route 48: Saint Louis 35 - Chicago 33 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(940.54297f, 426.3911f, -57.5f));
        spaces.add(new Space(969.5547f, 378.3474f, -57.5f));
        routes.add(new Route(48, new Tuple(33, 35), spaces, true, null, null));

        // Route 49: Saint Louis 35 - Chicago 33 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(956.53125f, 436.38892f, -57.5f));
        spaces.add(new Space(987.52344f, 389.37817f, -57.5f));
        routes.add(new Route(49, new Tuple(33, 35), spaces, true, null, null));

        /// Route 50: Kansas City 34 - Saint Louis 35 [Top]
        spaces = new ArrayList<>();
        spaces.add(new Space(841.45703f, 456.42725f, 0.0f));
        spaces.add(new Space(896.4883f, 455.42725f, 0.0f));
        routes.add(new Route(50, new Tuple(34, 35), spaces, true, null, null));

        // Route 51: Kansas City 34 - Saint Louis 35 [Bottom]
        spaces = new ArrayList<>();
        spaces.add(new Space(843.45703f, 476.44385f, 0.0f));
        spaces.add(new Space(897.4844f, 475.4319f, 0.0f));
        routes.add(new Route(51, new Tuple(34, 35), spaces, true, null, null));

        // Route 52: Omaha 32 - Kansas City 34 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(779.4258f, 434.3994f, 62.5f));
        routes.add(new Route(52, new Tuple(32, 34), spaces, true, null, null));

        // Route 53: Omaha 32 - Kansas City 34 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(798.4375f, 426.3911f, 62.5f));
        routes.add(new Route(53, new Tuple(32, 34), spaces, true, null, null));

        // Route 54: Denver 31 - Omaha 32
        spaces = new ArrayList<>();
        spaces.add(new Space(576.34375f, 464.43628f, -37.5f));
        spaces.add(new Space(622.3906f, 435.41138f, -25.0f));
        spaces.add(new Space(674.40625f, 415.3933f, -15.0f));
        spaces.add(new Space(728.4219f, 402.37598f, -10.0f));
        routes.add(new Route(54, new Tuple(31, 32), spaces, false, null, null));

        // Route 55: Denver 31 - Kansas City 34 [Top]
        spaces = new ArrayList<>();
        spaces.add(new Space(598.35547f, 497.44922f, 5.0f));
        spaces.add(new Space(652.375f, 497.44995f, -5.0f));
        spaces.add(new Space(707.40625f, 490.45142f, -12.5f));
        spaces.add(new Space(759.41406f, 473.43408f, -25.0f));
        routes.add(new Route(55, new Tuple(31, 34), spaces, true, null, null));

        // Route 56: Denver 31 - Kansas City 34 [Bottom]
        spaces = new ArrayList<>();
        spaces.add(new Space(598.33984f, 517.4785f, 5.0f));
        spaces.add(new Space(653.375f, 517.4785f, -5.0f));
        spaces.add(new Space(707.40625f, 509.47998f, -12.5f));
        spaces.add(new Space(760.40625f, 493.44092f, -25.0f));
        routes.add(new Route(56, new Tuple(31, 34), spaces, true, null, null));

        // Route 57: Salt Lake City 30 - Denver 31 [Top]
        spaces = new ArrayList<>();
        spaces.add(new Space(390.25f, 445.4077f, 10.0f));
        spaces.add(new Space(444.26172f, 455.43848f, 10.0f));
        spaces.add(new Space(496.29297f, 464.41528f, 12.5f));
        routes.add(new Route(57, new Tuple(30, 31), spaces, true, null, null));

        // Route 58: Salt Lake City 30 - Denver 31 [Bottom]
        spaces = new ArrayList<>();
        spaces.add(new Space(387.2578f, 464.43628f, 10.0f));
        spaces.add(new Space(441.2539f, 474.43408f, 10.0f));
        spaces.add(new Space(494.29297f, 485.44165f, 12.5f));
        routes.add(new Route(58, new Tuple(30, 31), spaces, true, null, null));

        // Route 59: Portland 17 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(110.10547f, 253.25684f, 12.5f));
        spaces.add(new Space(163.14453f, 268.24268f, 20.0f));
        spaces.add(new Space(213.16797f, 293.28027f, 32.5f));
        spaces.add(new Space(258.16406f, 325.30518f, 40.0f));
        spaces.add(new Space(297.1914f, 364.3286f, 47.5f));
        spaces.add(new Space(328.2461f, 407.36475f, 60.0f));
        routes.add(new Route(59, new Tuple(17, 30), spaces, false, null, null));

        // Route 60: Portland 17 - San Francisco 16 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(42.078125f, 283.2622f, -67.5f));
        spaces.add(new Space(27.074219f, 336.3247f, -80.0f));
        spaces.add(new Space(19.046875f, 391.35498f, -90.0f));
        spaces.add(new Space(19.046875f, 446.4077f, -92.5f));
        spaces.add(new Space(29.0625f, 502.44922f, -107.5f));
        routes.add(new Route(60, new Tuple(16, 17), spaces, true, null, null));

        // Route 61: Portland 17 - San Francisco 16 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(62.097656f, 286.27344f, -67.5f));
        spaces.add(new Space(46.101562f, 339.30225f, -80.0f));
        spaces.add(new Space(40.070312f, 393.35498f, -87.5f));
        spaces.add(new Space(42.058594f, 448.42944f, -95.0f));
        spaces.add(new Space(50.109375f, 502.44922f, -107.5f));
        routes.add(new Route(61, new Tuple(16, 17), spaces, true, null, null));

        // Route 62: San Francisco 16 - Salt Lake City 30 [Top]
        spaces = new ArrayList<>();
        spaces.add(new Space(94.109375f, 521.4883f, -20.0f));
        spaces.add(new Space(146.1211f, 503.45972f, -20.0f));
        spaces.add(new Space(197.17188f, 486.4641f, -20.0f));
        spaces.add(new Space(247.20312f, 469.42505f, -17.5f));
        spaces.add(new Space(298.1836f, 452.41748f, -17.5f));
        routes.add(new Route(62, new Tuple(16, 30), spaces, true, null, null));

        // Route 63: San Francisco 16 - Salt Lake City 30 [Bottom]
        spaces = new ArrayList<>();
        spaces.add(new Space(102.13281f, 538.50635f, -20.0f));
        spaces.add(new Space(153.15234f, 520.4778f, -20.0f));
        spaces.add(new Space(204.13672f, 504.45972f, -20.0f));
        spaces.add(new Space(255.17578f, 487.45215f, -20.0f));
        spaces.add(new Space(306.21484f, 470.4558f, -17.5f));
        routes.add(new Route(63, new Tuple(16, 30), spaces, true, null, null));

        // Route 64: San Francisco 16 - Los Angeles 15 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(57.07422f, 592.55835f, 65.0f));
        spaces.add(new Space(84.09375f, 640.5803f, 55.0f));
        spaces.add(new Space(119.12891f, 680.6255f, 45.0f));
        routes.add(new Route(64, new Tuple(15, 16), spaces, true, null, null));

        // Route 65: San Francisco 16 - Los Angeles 15 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(73.07031f, 578.5298f, 65.0f));
        spaces.add(new Space(100.08203f, 629.5825f, 55.0f));
        spaces.add(new Space(136.11719f, 669.6277f, 47.5f));
        routes.add(new Route(65, new Tuple(15, 16), spaces, true, null, null));

        // Route 66: Los Angeles 15 - Las Vegas 29
        spaces = new ArrayList<>();
        spaces.add(new Space(178.14844f, 656.5984f, -65.0f));
        spaces.add(new Space(220.15625f, 621.56445f, -15.0f));
        routes.add(new Route(66, new Tuple(15, 29), spaces, false, null, null));

        // Route 67: Las Vegas 29 - Salt Lake City 30
        spaces = new ArrayList<>();
        spaces.add(new Space(298.23047f, 592.5381f, -45.0f));
        spaces.add(new Space(330.23438f, 548.51465f, -67.5f));
        spaces.add(new Space(344.23828f, 494.47168f, -82.5f));
        routes.add(new Route(67, new Tuple(29, 30), spaces, false, null, null));

        // Route 68: Los Angeles 15 - Phoenix 14
        spaces = new ArrayList<>();
        spaces.add(new Space(202.16016f, 691.64355f, -7.5f));
        spaces.add(new Space(256.16797f, 687.6345f, 0.0f));
        spaces.add(new Space(311.1953f, 695.6323f, 15.0f));
        routes.add(new Route(68, new Tuple(14, 15), spaces, false, null, null));

        // Route 69: Los Angeles 15 - El Paso 13
        spaces = new ArrayList<>();
        spaces.add(new Space(201.16016f, 731.6572f, 35.0f));
        spaces.add(new Space(249.15234f, 760.69336f, 25.0f));
        spaces.add(new Space(301.23438f, 780.7212f, 12.5f));
        spaces.add(new Space(355.20312f, 790.7197f, 7.5f));
        spaces.add(new Space(410.27734f, 790.7197f, -5.0f));
        spaces.add(new Space(465.30078f, 782.7092f, -12.5f));
        routes.add(new Route(69, new Tuple(13, 15), spaces, false, null, null));

        // Route 70: Phoenix 14 - El Paso 13
        spaces = new ArrayList<>();
        spaces.add(new Space(385.2578f, 728.6572f, 15.0f));
        spaces.add(new Space(437.28906f, 743.69775f, 15.0f));
        spaces.add(new Space(489.3203f, 758.68286f, 17.5f));
        routes.add(new Route(70, new Tuple(13, 14), spaces, false, null, null));

        // Route 71: Phoenix 14 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(362.23047f, 676.615f, -67.5f));
        spaces.add(new Space(386.23828f, 628.5825f, -60.0f));
        spaces.add(new Space(416.29297f, 582.5403f, -55.0f));
        spaces.add(new Space(452.26562f, 541.50635f, -37.5f));
        spaces.add(new Space(499.28516f, 514.4905f, -20.0f));
        routes.add(new Route(71, new Tuple(14, 31), spaces, false, null, null));

        // Route 72: Phoenix 14 - Santa Fe 28
        spaces = new ArrayList<>();
        spaces.add(new Space(396.23438f, 692.64355f, -22.5f));
        spaces.add(new Space(445.3047f, 670.6277f, -22.5f));
        spaces.add(new Space(494.33203f, 648.5991f, -22.5f));
        routes.add(new Route(72, new Tuple(14, 28), spaces, false, null, null));

        // Route 73: El Paso 13 - Santa Fe 28
        spaces = new ArrayList<>();
        spaces.add(new Space(528.3281f, 728.6489f, 92.5f));
        spaces.add(new Space(532.3164f, 674.61646f, 95.0f));
        routes.add(new Route(73, new Tuple(13, 28), spaces, false, null, null));

        // Route 74: Santa Fe 28 - Denver 31
        spaces = new ArrayList<>();
        spaces.add(new Space(535.3008f, 593.5591f, -87.5f));
        spaces.add(new Space(538.35156f, 539.48535f, -87.5f));
        routes.add(new Route(74, new Tuple(28, 31), spaces, false, null, null));

        // Route 75: Santa Fe 28 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(579.3672f, 632.58105f, -7.5f));
        spaces.add(new Space(632.33594f, 624.58545f, -7.5f));
        spaces.add(new Space(687.4219f, 618.5847f, -7.5f));
        routes.add(new Route(75, new Tuple(27, 28), spaces, false, null, null));

        // Route 76: Denver 31 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(572.3594f, 540.4839f, 45.0f));
        spaces.add(new Space(618.3906f, 573.541f, 25.0f));
        spaces.add(new Space(671.3672f, 589.5591f, 5.0f));
        spaces.add(new Space(725.3867f, 593.55835f, 0.0f));
        routes.add(new Route(76, new Tuple(27, 31), spaces, false, null, null));

        // Route 77: El Paso 13 - Oklahoma City 27
        spaces = new ArrayList<>();
        spaces.add(new Space(566.3242f, 755.68506f, -17.5f));
        spaces.add(new Space(618.3672f, 734.67896f, -27.5f));
        spaces.add(new Space(667.3633f, 706.6406f, -35.0f));
        spaces.add(new Space(709.3828f, 672.6277f, -45.0f));
        spaces.add(new Space(745.4336f, 629.5825f, -55.0f));
        routes.add(new Route(77, new Tuple(13, 27), spaces, false, null, null));

        // Route 78: El Paso 13 - Houston 12
        spaces = new ArrayList<>();
        spaces.add(new Space(559.33984f, 795.7302f, 32.5f));
        spaces.add(new Space(611.3594f, 819.7439f, 17.5f));
        spaces.add(new Space(664.40234f, 831.75366f, 7.5f));
        spaces.add(new Space(717.39844f, 835.7739f, 0.0f));
        spaces.add(new Space(773.4375f, 832.76416f, -12.5f));
        spaces.add(new Space(826.46094f, 817.73486f, -20.0f));
        routes.add(new Route(78, new Tuple(12, 13), spaces, false, null, null));

        // Route 79: El Paso 13 - Dallas 26
        spaces = new ArrayList<>();
        spaces.add(new Space(596.375f, 772.7009f, -7.5f));
        spaces.add(new Space(650.3867f, 764.70386f, -7.5f));
        spaces.add(new Space(704.41016f, 756.6836f, -7.5f));
        spaces.add(new Space(758.4375f, 747.67456f, -10.0f));
        routes.add(new Route(79, new Tuple(13, 26), spaces, false, null, null));

        // Route 80: Dallas 26 - Houston 12 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(824.4844f, 769.7136f, 50.0f));
        routes.add(new Route(80, new Tuple(12, 26), spaces, true, null, null));

        // Route 81: Dallas 26 - Houston 12 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(839.45703f, 755.69556f, 47.5f));
        routes.add(new Route(81, new Tuple(12, 26), spaces, true, null, null));

        // Route 82: Oklahoma City 27 - Dallas 26 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(780.41797f, 639.5803f, 82.5f));
        spaces.add(new Space(787.4414f, 694.64355f, 82.5f));
        routes.add(new Route(82, new Tuple(26, 27), spaces, true, null, null));

        // Route 83: Oklahoma City 27 - Dallas 26 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(801.4453f, 639.5908f, 82.5f));
        spaces.add(new Space(809.46875f, 692.63306f, 82.5f));
        routes.add(new Route(83, new Tuple(26, 27), spaces, true, null, null));

        // Route 84: Oklahoma City 27 - Kansas City 34 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(777.4258f, 558.5244f, -72.5f));
        spaces.add(new Space(792.46484f, 506.4807f, -72.5f));
        routes.add(new Route(84, new Tuple(27, 34), spaces, true, null, null));

        // Route 85: Oklahoma City 27 - Kansas City 34 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(811.46875f, 512.4905f, -72.5f));
        spaces.add(new Space(796.46484f, 565.51025f, -72.5f));
        routes.add(new Route(85, new Tuple(27, 34), spaces, true, null, null));

        // Route 86: Oklahoma City 27 - Little Rock 24
        spaces = new ArrayList<>();
        spaces.add(new Space(814.46094f, 600.54565f, 0.0f));
        spaces.add(new Space(870.47656f, 600.5569f, 0.0f));
        routes.add(new Route(86, new Tuple(24, 27), spaces, false, null, null));

        // Route 87: Dallas 26 - Little Rock 24
        spaces = new ArrayList<>();
        spaces.add(new Space(844.4453f, 685.62256f, -55.0f));
        spaces.add(new Space(877.4961f, 639.6028f, -57.5f));
        routes.add(new Route(87, new Tuple(24, 26), spaces, false, null, null));

        // Route 88: Little Rock 24 - Saint Louis 35
        spaces = new ArrayList<>();
        spaces.add(new Space(916.52344f, 563.5222f, -75.0f));
        spaces.add(new Space(930.51953f, 510.46948f, -77.5f));
        routes.add(new Route(88, new Tuple(24, 35), spaces, false, null, null));

        // Route 89: Houston 12 - New Orleans 11
        spaces = new ArrayList<>();
        spaces.add(new Space(908.53125f, 788.7197f, -10.0f));
        spaces.add(new Space(962.5078f, 779.69946f, -7.5f));
        routes.add(new Route(89, new Tuple(11, 12), spaces, false, null, null));

        // Route 90: Saint Louis 35 - Nashville 25
        spaces = new ArrayList<>();
        spaces.add(new Space(974.51953f, 502.44922f, 17.5f));
        spaces.add(new Space(1028.5508f, 520.4778f, 17.5f));
        routes.add(new Route(90, new Tuple(25, 35), spaces, false, null, null));

        // Route 91: Little Rock 24 - New Orleans 11
        spaces = new ArrayList<>();
        spaces.add(new Space(929.51953f, 641.5803f, 62.5f));
        spaces.add(new Space(956.52344f, 690.63306f, 62.5f));
        spaces.add(new Space(982.54297f, 738.68726f, 62.5f));
        routes.add(new Route(91, new Tuple(11, 24), spaces, false, null, null));

        // Route 92: Little Rock 24 - Nashville 25
        spaces = new ArrayList<>();
        spaces.add(new Space(950.5f, 601.54565f, -5.0f));
        spaces.add(new Space(1004.53125f, 588.5388f, -22.5f));
        spaces.add(new Space(1050.5703f, 558.52295f, -42.5f));
        routes.add(new Route(92, new Tuple(24, 25), spaces, false, null, null));

        // Route 93: Nashville 25 - Atlanta 23
        spaces = new ArrayList<>();
        spaces.add(new Space(1112.6328f, 555.51245f, 35.0f));
        routes.add(new Route(93, new Tuple(23, 25), spaces, false, null, null));

        // Route 94: New Orleans 11 - Atlanta 23 [Left]
        spaces = new ArrayList<>();
        spaces.add(new Space(1020.58594f, 734.6565f, -67.5f));
        spaces.add(new Space(1047.5977f, 686.62476f, -57.5f));
        spaces.add(new Space(1079.5742f, 640.5803f, -50.0f));
        spaces.add(new Space(1117.5977f, 601.5359f, -42.5f));
        routes.add(new Route(94, new Tuple(11, 23), spaces, true, null, null));

        // Route 95: New Orleans 11 - Atlanta 23 [Right]
        spaces = new ArrayList<>();
        spaces.add(new Space(1036.5664f, 748.6753f, -67.5f));
        spaces.add(new Space(1061.5859f, 700.6533f, -57.5f));
        spaces.add(new Space(1095.5781f, 656.5886f, -50.0f));
        spaces.add(new Space(1131.5977f, 616.55396f, -42.5f));
        routes.add(new Route(95, new Tuple(11, 23), spaces, true, null, null));

        // Route 96: Atlanta 23 - Charleston 9
        spaces = new ArrayList<>();
        spaces.add(new Space(1202.6719f, 599.5569f, 0.0f));
        spaces.add(new Space(1256.6797f, 600.54565f, 0.0f));
        routes.add(new Route(96, new Tuple(9, 23), spaces, false, null, null));

        // Route 97: New Orleans 11 - Miami 10
        spaces = new ArrayList<>();
        spaces.add(new Space(1062.5859f, 768.7009f, -32.5f));
        spaces.add(new Space(1113.625f, 746.68506f, -15.0f));
        spaces.add(new Space(1168.6562f, 739.67676f, 0.0f));
        spaces.add(new Space(1222.668f, 753.70605f, 32.5f));
        spaces.add(new Space(1265.6875f, 783.7212f, 40.0f));
        spaces.add(new Space(1305.7109f, 821.75586f, 50.0f));
        routes.add(new Route(97, new Tuple(10, 11), spaces, false, null, null));

        // Route 98: Atlanta 23 - Miami 10
        spaces = new ArrayList<>();
        spaces.add(new Space(1176.6641f, 626.57275f, 50.0f));
        spaces.add(new Space(1211.6367f, 669.6172f, 52.5f));
        spaces.add(new Space(1245.6914f, 712.6594f, 50.0f));
        spaces.add(new Space(1280.6758f, 755.68286f, 50.0f));
        spaces.add(new Space(1314.6992f, 798.7168f, 52.5f));
        routes.add(new Route(98, new Tuple(10, 23), spaces, false, null, null));

        // Route 99: Charleston 9 - Miami 10
        spaces = new ArrayList<>();
        spaces.add(new Space(1299.7227f, 633.593f, 90.0f));
        spaces.add(new Space(1304.7031f, 687.6345f, 82.5f));
        spaces.add(new Space(1316.7109f, 741.67676f, 72.5f));
        spaces.add(new Space(1338.7383f, 791.7295f, 60.0f));
        routes.add(new Route(99, new Tuple(9, 10), spaces, false, null, null));

        return routes;
    }
}
