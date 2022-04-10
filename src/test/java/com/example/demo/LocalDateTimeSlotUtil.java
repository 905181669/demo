package com.example.demo;

/**
 * @author: luozijian
 * @date: 2021/12/31
 * @description:
 */

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * <p>
 *     使用LocalDateTime存取或判断
 * </p>
 * @see LocalDateTime
 * @author luozijian
 * @date 2021-12-31
 */

public class LocalDateTimeSlotUtil {

    public static void main(String[] args) {
        TimeSlot timeSlot1 = buildSlot("2021-12-13", "2021-12-21", "yyyy-MM-dd");
        TimeSlot timeSlot2 = buildSlot("2021-12-21", "2021-12-24", "yyyy-MM-dd");
        System.out.println(overlapped(timeSlot1, timeSlot2));

    }
    /**
     * 判断两个时间段是否重叠
     * @param slot1
     * @param slot2
     * @return
     */
    public static boolean overlapped(TimeSlot slot1, TimeSlot slot2) {
        TimeSlot previous, next;
        previous = slot1.startTime.isBefore(slot2.startTime) ? slot1 : slot2;
        next = slot2.startTime.isAfter(slot1.startTime) ? slot2 : slot1;
        /**
         * 这里业务需要，允许时间点的重叠
         * 例如某个时间段的起始时间：2020-06-29 00:00:00
         * 和另一个时间段的终止时间：2020-06-29 00:00:00
         * 它们俩可以有交点。如果不需要这种逻辑只把le改成lt，ge改成gt就可
         */
        return !(lt(previous, next) || gt(previous, next));
    }

    /**
     * 构造一个时间段
     * @param startTime
     * @param endTime
     * @return
     */
    public static TimeSlot buildSlot(LocalDate startTime, LocalDate endTime) {
        return new TimeSlot(startTime, endTime);
    }



    public static TimeSlot buildSlot(String startTime, String endTime, String format) {
        return new TimeSlot(LocalDate.parse(startTime, DateTimeFormatter.ofPattern(format)),
                LocalDate.parse(endTime, DateTimeFormatter.ofPattern(format)));
    }


    /**
     * less equal
     * 小于等于
     * @param prev
     * @param next
     * @return
     */
    private static boolean le(TimeSlot prev, TimeSlot next) {
        return lt(prev, next) || next.endTime.isEqual(prev.startTime);
    }

    /**
     * greater equal
     * 大于等于
     * @param prev
     * @param next
     * @return
     */
    private static boolean ge(TimeSlot prev, TimeSlot next) {
        return gt(prev, next) || prev.endTime.isEqual(next.startTime);
    }

    /**
     * greater than
     * 大于
     * @param prev
     * @param next
     * @return
     */
    private static boolean gt(TimeSlot prev, TimeSlot next) {
        return prev.endTime.isBefore(next.startTime);
    }

    /**
     * less than
     * 小于
     * @param prev
     * @param next
     * @return
     */
    private static boolean lt(TimeSlot prev, TimeSlot next) {
        return next.endTime.isBefore(prev.startTime);
    }

    /**
     * 时间段类
     */
    @Data
    @NoArgsConstructor
    @Builder
    public static class TimeSlot{

        private LocalDate startTime;
        private LocalDate endTime;

        public TimeSlot(LocalDate startTime, LocalDate endTime) {
            if (startTime.isAfter(endTime)) {
                this.startTime = endTime;
                this.endTime = startTime;
            } else {
                this.startTime = startTime;
                this.endTime = endTime;
            }
        }
    }
}
