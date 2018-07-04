package com.stworks.common.utils;

import android.content.Context;

import com.google.gson.Gson;
import com.stworks.common.base.bean.BaseBean;
import com.stworks.common.base.bean.BaseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wwq on 2018/7/4.
 */

public class JsonConvert {

    /**
     * 传class 获取json
     */
    public static <T> BaseEntity<T> fromJson(String fileName, Context context, Class<T> clazz) {
        return new Gson().fromJson(createData(fileName, context), type(BaseEntity.class, clazz));
    }

    /**
     * 通过读流获取内容
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }


    public static String createData(String path, Context context) {
        String assetFilePath = FileUtils.getFileName(path) + ".json";
        try {
            InputStream inputStream = context.getAssets().open(assetFilePath);
            String response = inputStream2String(inputStream);
            inputStream.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> ParameterizedType type(final Class<T> raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
