package me.liujia95.biliplayer.base;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.client.HttpRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import me.liujia95.biliplayer.utils.Constants;
import me.liujia95.biliplayer.utils.IOUtils;
import me.liujia95.biliplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/19 11:14.
 */
public abstract class BaseProtocol<T> {

    public T loadData() throws Exception {

        T t = getDataFromLocal();

        if (t != null) {
            LogUtils.d("去本地加载数据");
            return t;
        }

        return getDataFromNet();
    }

    public T getDataFromLocal() throws Exception {
        File file = getCacheFile();
        if (!file.exists()) {
            //如果文件不存在，说明还没有缓存
            return null;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            //读取的第一行是时间戳
            String time = reader.readLine();
            if (Long.valueOf(time) + Constants.CACHE_TIME > System.currentTimeMillis()) {
                //如果还没超时，读本地的数据
                String json = reader.readLine();
                return parseJson(json);
            } else {
                //如果超时，去网络读取数据
                return null;
            }
        } finally {
            IOUtils.close(reader);
        }
    }

    private T getDataFromNet() throws Exception {
        HttpUtils http = new HttpUtils();
        String url = Constants.BASE_SERVER + getInterfacePath();

        String json = http.sendSync(HttpRequest.HttpMethod.GET, url).readString();

        //存到本地
        writeJson(json);

        return parseJson(json);
    }

    //些数据到缓存
    private void writeJson(String json) throws Exception {
        File file = getCacheFile();

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            //写时间戳
            writer.write(System.currentTimeMillis() + "");
            writer.newLine();
            //写数据
            writer.write(json);

        } finally {
            IOUtils.close(writer);
        }
    }

    protected T parseJson(String json) {
        //获取类上面的表象泛型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取真实的泛型类型（第1个）
        Type t = type.getActualTypeArguments()[0];
        return new Gson().fromJson(json, t);
    }

    protected abstract String getInterfacePath();


    public File getCacheFile() {
        String name = getInterfacePath();
        name = name.replace("/", "$");
        name = name.replace(".", "$");
        return new File(Constants.CACHE_DIR, name);
    }
}
