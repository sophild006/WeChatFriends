package com.stworks.common.utils;

/**
 * Created by wwq on 2018/7/4.
 * 文件相关的Utils
 */

public class FileUtils {
    /**
     * @param path 完整路径
     * @return 截取 / 之后的内容
     */
    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf("/") + 1);
    }

}
