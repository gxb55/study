package com.atguigu.two;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "韩"), FIVE(5, "赵"), SIX(6, "魏");
    private int retNum;
    private String retMsg;

    CountryEnum(int retNum, String retMsg) {
        this.retNum = retNum;
        this.retMsg = retMsg;
    }

    public static CountryEnum getCountry(int retNum) {

        CountryEnum[] values = CountryEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (retNum == values[i].getRetNum()) {
                return values[i];
            }
        }

        return null;
    }
}
