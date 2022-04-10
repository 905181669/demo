package com.example.demo;



import cn.hutool.core.date.DateUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;
import sun.plugin.dom.core.CoreConstants;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;



/**
 * @author: luozijian
 * @date: 2021/12/20
 * @description:
 */
public class TimeSlotUtil {

    public static void main(String[] args) throws Exception{
        List<TimeIntervalModel> timeIntervals = Lists.newArrayList();


        timeIntervals.add(new TimeIntervalModel(){{setStartTime("15:00");setEndTime("17:00");}} );
        timeIntervals.add(new TimeIntervalModel(){{setStartTime("00:00");setEndTime("07:00");}} );
        timeIntervals.add(new TimeIntervalModel(){{setStartTime("06:00");setEndTime("07:00");}} );

        verifyRepeatTime(timeIntervals);
    }

    public static void verifyRepeatTime(List<TimeIntervalModel> timeIntervals) throws Exception {
        List<Pub<Integer,Integer,TimeIntervalModel>> intervals = Lists.newArrayList();
        //开始时间必须小于结束时间
        for(TimeIntervalModel data:timeIntervals){
            Integer start = LocalTime.parse(data.getStartTime()).toSecondOfDay();
            Integer end = LocalTime.parse(data.getEndTime()).toSecondOfDay();
            if(start > end){
                throw new Exception("The start time must be less than the end time");
            }
            intervals.add(new Pub(start,end,data));
        }
        //验证时间段是否重合
        Iterator<Pub<Integer,Integer,TimeIntervalModel>> iterator = intervals.iterator();
        while (iterator.hasNext()){
            Pub<Integer,Integer,TimeIntervalModel> data = iterator.next();
            iterator.remove();
            for(Pub<Integer,Integer,TimeIntervalModel> compareData:intervals){
                boolean is = data.getF2()<=compareData.getF1() || compareData.getF2()<=data.getF1();
                if(!is) {
                    System.out.println("时间有重叠！");
                    throw new Exception("【" + Joiner.on("-").join(compareData.getF3().getStartTime(),compareData.getF3().getEndTime())+"】和" + "【"
                    + Joiner.on("-").join(data.getF3().getStartTime(),data.getF3().getEndTime())+"】之间重合");

                }
            }
        }

        System.out.println("时间无重叠");
    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Pub<F1,F2,F3> {
    private F1 f1;
    private F2 f2;
    private F3 f3;
}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class TimeIntervalModel{

    private String startTime;

    private String endTime;
}