package com.example.demo;

/**
 * @author: luozijian
 * @date: 2021/11/8 11:35:38
 * @description:
 */
import com.alibaba.fastjson.JSONObject;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Locale;

/**
 * @description:
 * @author: liangbo
 * @create 2021-01-20 15:38
 * @Version 1.0
 **/
public class PhoneUtil {
    private static PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    private static PhoneNumberToCarrierMapper carrierMapper = PhoneNumberToCarrierMapper.getInstance();

    private static PhoneNumberOfflineGeocoder geocoder = PhoneNumberOfflineGeocoder.getInstance();

    /**
     * check phone
     *
     * @param phone eg" 18012345678"
     * @param countryCode eg "86"
     * @return the result "true" or "false"
     */
    public static boolean checkPhoneNumber(Long phone, Integer countryCode) {


        Phonenumber.PhoneNumber pn = new Phonenumber.PhoneNumber();
        pn.setCountryCode(countryCode);
        pn.setNationalNumber(phone);

        return phoneNumberUtil.isValidNumber(pn);
    }

    /**
     * check phone that it brings country code
     *
     * @param phoneNumber eg" +8618012345678"
     * @return the result "true" or "false"
     * @throws NumberParseException handle phone that it can't resolve
     */
    public static boolean checkPhoneNumberBringCountryCode(String phoneNumber) throws NumberParseException {
        Phonenumber.PhoneNumber cn = phoneNumberUtil.parse(phoneNumber, "CN");
        return phoneNumberUtil.isValidNumber(cn);
    }

    public static JSONObject getPhoneNumberInfo(String phoneNumber) throws Exception {

        Phonenumber.PhoneNumber referencePhonenumber;
        try {
            String language = "US";
            referencePhonenumber = phoneNumberUtil.parse(phoneNumber, language);
        } catch (NumberParseException e) {
            throw new Exception(e.getMessage());
        }
        String regionCodeForNumber = phoneNumberUtil.getRegionCodeForNumber(referencePhonenumber);

        if (regionCodeForNumber == null) {
            throw new Exception("Missing region code by phone number " + phoneNumber);
        }

        boolean checkSuccess = PhoneUtil.checkPhoneNumber(referencePhonenumber.getNationalNumber(), referencePhonenumber.getCountryCode());
        if (!checkSuccess) {
            throw new Exception("Not an active number:" + phoneNumber);
        }

        String description = geocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA);

        int countryCode = referencePhonenumber.getCountryCode();
        long nationalNumber = referencePhonenumber.getNationalNumber();
        JSONObject resultObject = new JSONObject();
        // 区域编码 Locale : HK, US, CN ...
        resultObject.put("regionCode", regionCodeForNumber);
        // 国号: 86, 1, 852 ... @link: https://blog.csdn.net/wzygis/article/details/45073327
        resultObject.put("countryCode", countryCode);
        // 去掉+号 和 国号/区号 后的实际号码
        resultObject.put("nationalNumber", nationalNumber);
        // 所在地区描述信息
        resultObject.put("description", description);
        // 去掉+号后的号码 (用于阿里云发送短信)
        resultObject.put("number", String.valueOf(countryCode) + nationalNumber);
        resultObject.put("fullNumber", phoneNumber);

        return resultObject;

    }

    public static void main(String[] args) {
        try {
//            System.out.println(getPhoneNumberInfo("+1-2843418176)").toJSONString());
//            System.out.println(getPhoneNumberInfo("+61-402596083").toJSONString());
//
//            System.out.println(getPhoneNumberInfo("+3162329063").toJSONString());



            ArrayList list = new ArrayList();
            list.set(1, 10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}