package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: luozijian
 * @date: 2022/1/25
 * @description:
 */
public class AreaUtil {

    /**
     * 判断点是否在多边形区域内
     *
     * @param lon
     * @param lat
     * @return
     */
    public static boolean inArea(String lon, String lat, String rang) {
        List<Point> areaList = Lists.newArrayList();
        JSONArray jsonArray = JSONArray.parseArray(rang);
        jsonArray.stream().forEach(point -> {
            JSONArray pointArr = (JSONArray) point;
            areaList.add(Point
                    .builder()
                    .lon(Double.valueOf(pointArr.get(0).toString()))
                    .lat(Double.valueOf(pointArr.get(1).toString()))
                    .build());
        });

        Point[] areaArr = new Point[areaList.size()];
        return inArea(Double.valueOf(lon), Double.valueOf(lat), areaList.toArray(areaArr));
    }


    public static boolean inArea(double lon, double lat, Point[] ps) {
        int iSum, iCount, iIndex;
        double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
        if (ps.length < 3) {
            return false;
        }
        iSum = 0;
        iCount = ps.length;
        for (iIndex = 0; iIndex < iCount; iIndex++) {
            if (iIndex == iCount - 1) {
                dLon1 = ps[iIndex].getLon();
                dLat1 = ps[iIndex].getLat();
                dLon2 = ps[0].getLon();
                dLat2 = ps[0].getLat();
            } else {
                dLon1 = ps[iIndex].getLon();
                dLat1 = ps[iIndex].getLat();
                dLon2 = ps[iIndex + 1].getLon();
                dLat2 = ps[iIndex + 1].getLat();
            }
            // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
            if (((lat >= dLat1) && (lat < dLat2)) || ((lat >= dLat2) && (lat < dLat1))) {
                if (Math.abs(dLat1 - dLat2) > 0) {
                    // 得到A点向左射线与边的交点的x坐标：
                    dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - lat)) / (dLat1 - dLat2);
                    // 如果交点在A点左侧（说明是做射线与 边的交点），则射线与边的全部交点数加一：
                    if (dLon < lon) {
                        iSum++;
                    }
                }
            }
        }
        if ((iSum % 2) != 0) {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Point[] ps = new Point[]{new Point(114.309914, 30.599556),//114.309914,30.599556
                new Point(114.295688, 30.592879),//114.295688,30.592879
                new Point(114.292812, 30.587726), //114.292812,30.587726
                new Point(114.292812, 30.587726), //114.292812,30.587726
                new Point(114.30058, 30.580318),//114.30058,30.580318
                new Point(114.303606, 30.586959),//114.303606,30.586959
                new Point(114.304534, 30.594751),//114.304534,30.594751
                new Point(114.30838, 30.590131),//114.30838,30.590131
                new Point(114.308651, 30.584182),//114.308651,30.584182
                new Point(114.304495, 30.584015),//114.304495,30.584015
                new Point(114.301301, 30.578759),//114.301301,30.578759
                new Point(114.309437, 30.578528),//114.309437,30.578528
                new Point(114.323282, 30.592786)};//114.323282,30.592786

        Point n1 = new Point(114.303217, 30.583553);
        Point n2 = new Point(114.307336, 30.597592);
        Point n3 = new Point(114.286565, 30.590056);
        Point y1 = new Point(114.227342, 30.587987);
        Point y2 = new Point(120.1866, 30.2672);
        Point y4 = new Point(120.1869, 30.2718);
//        System.out.println( "n1:" + isPtInPoly(n1.getX() , n1.getY() , ps));
//        System.out.println("n2:" + inArea(n2.getLon(), n2.getLat(), ps));
//        System.out.println( "n3:" + isPtInPoly(n3.getX() , n3.getY() , ps));
//        System.out.println( "y1:" + isPtInPoly(y1.getX() , y1.getY() , ps));
//        System.out.println( "y2:" + isPtInPoly(y2.getX() , y2.getY() , ps));
//        System.out.println( "y4:" + isPtInPoly(y4.getX() , y4.getY() , ps));


        //[
        //  [
        //    109.526718,
        //    18.413754
        //  ],
        //  [
        //    109.570663,
        //    18.413754
        //  ],
        //  [
        //    109.535644,
        //    18.383131
        //  ],
        //  [
        //    109.535644,
        //    18.383131
        //  ]
        //]

        String str = "[[\n" +
                "  [\n" +
                "    109.347006,\n" +
                "    18.500858\n" +
                "  ],\n" +
                "  [\n" +
                "    109.735647,\n" +
                "    18.483927\n" +
                "  ],\n" +
                "  [\n" +
                "    109.701315,\n" +
                "    18.236285\n" +
                "  ],\n" +
                "  [\n" +
                "    109.31954,\n" +
                "    18.233676\n" +
                "  ],\n" +
                "  [\n" +
                "    109.31954,\n" +
                "    18.233676\n" +
                "  ]\n" +
                "]]";

        System.out.println(JSONArray.parseArray("").isEmpty());
        JSONArray.parseArray(str).stream().forEach(e -> {
            JSONArray point = (JSONArray) e;
            System.out.println(inArea("109.535644", "18.383131", point.toJSONString()));
        });


    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Point {
        private Double lon;
        private Double lat;
    }
}