package com.zrk.util;


import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @Author: zrk
 * @Date: 2020/6/6
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    /**
     * 计算level值
     * 0
     * 0.1
     * 0.1.2
     * 0.3
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel,Long parentId){
        if(StringUtils.isEmpty(parentLevel)){
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

}
