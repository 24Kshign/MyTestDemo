package com.share.javatest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaTest {

    private static String[] tabNames = {"首页", "资讯", "资料", "我的"};

    public static void main(String[] args) {
        System.out.println(getTabIndex("首页"));
        System.out.println(getTabIndex("资讯"));
        System.out.println(getTabIndex("资料"));
        System.out.println(getTabIndex("我的"));
    }

    private static int getTabIndex(String s) {
        for (int i = 0; i < tabNames.length; i++) {
            if (s.equals(tabNames[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}