package com.example.demo;

/**
 * @author: luozijian
 * @date: 2021/12/31
 * @description:
 */

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * <p>
 * 使用LocalDateTime存取或判断
 * </p>
 *
 * @author luozijian
 * @date 2021-12-31
 * @see LocalDateTime
 */

public class LocalDateSlotUtil {

    private static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 判断两个时间段是否重叠
     *
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
         * 收尾时间点不能相同
         */
        return !(lt(previous, next) || gt(previous, next));
    }

    /**
     * 判断LocalDateTime是否重叠
     *
     * @param slot1
     * @param slot2
     * @return
     */
    public static boolean overlapped(DateTimeSlot slot1, DateTimeSlot slot2) {
        DateTimeSlot previous, next;
        previous = slot1.startTime.isBefore(slot2.startTime) ? slot1 : slot2;
        next = slot2.startTime.isAfter(slot1.startTime) ? slot2 : slot1;
        return !(lt(previous, next) || gt(previous, next));
    }

    /**
     * 构造一个时间段
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static TimeSlot buildSlot(LocalDate startTime, LocalDate endTime) {
        return new TimeSlot(startTime, endTime);
    }

    public static TimeSlot buildSlot(String startTime, String endTime) {
        return new TimeSlot(LocalDate.parse(startTime, DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN)),
                LocalDate.parse(endTime, DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN)));
    }

    public static DateTimeSlot buildSlot(LocalDateTime startTime, LocalDateTime endTime) {
        return new DateTimeSlot(startTime, endTime);
    }

    /**
     *
     * @param startTime 开始时间戳
     * @param endTime 结束时间戳
     * @return
     */
    public static DateTimeSlot buildSlot(Long startTime, Long endTime) {
        return new DateTimeSlot(LocalDateTime.ofEpochSecond(startTime, 0, ZoneOffset.of("+8")), LocalDateTime.ofEpochSecond(endTime, 0, ZoneOffset.of("+8")));
    }


    /**
     * less equal
     * 小于等于
     *
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
     *
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
     *
     * @param prev
     * @param next
     * @return
     */
    private static boolean gt(TimeSlot prev, TimeSlot next) {
        return prev.endTime.isBefore(next.startTime);
    }

    private static boolean gt(DateTimeSlot prev, DateTimeSlot next) {
        return prev.endTime.isBefore(next.startTime);
    }

    /**
     * less than
     * 小于
     *
     * @param prev
     * @param next
     * @return
     */
    private static boolean lt(TimeSlot prev, TimeSlot next) {
        return next.endTime.isBefore(prev.startTime);
    }

    private static boolean lt(DateTimeSlot prev, DateTimeSlot next) {
        return next.endTime.isBefore(prev.startTime);
    }

    /**
     * 时间段类
     */
    @Data
    @NoArgsConstructor
    @Builder
    public static class TimeSlot {

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

    /**
     * LocalDateTime时间段类
     */
    @Data
    @NoArgsConstructor
    @Builder
    public static class DateTimeSlot {

        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public DateTimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
            if (startTime.isAfter(endTime)) {
                this.startTime = endTime;
                this.endTime = startTime;
            } else {
                this.startTime = startTime;
                this.endTime = endTime;
            }
        }
    }

    public static void main(String[] args) {
        LocalDateSlotUtil.DateTimeSlot paramDateTimeSlot = LocalDateSlotUtil.buildSlot(LocalDateTime.parse("2022-02-10 00:00:00", DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2022-02-12 00:00:00", DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")));
        LocalDateSlotUtil.DateTimeSlot restrictDateTimeSlot = LocalDateSlotUtil.buildSlot(LocalDateTime.parse("2022-02-02 00:00:00", DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")),
                LocalDateTime.parse("2022-02-05 23:59:59", DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")));

        System.out.println(LocalDateSlotUtil.overlapped(paramDateTimeSlot, restrictDateTimeSlot));

        System.out.println(BigDecimal.ZERO.compareTo(new BigDecimal("0.00")) == 0);
    }

}
