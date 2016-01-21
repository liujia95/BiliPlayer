package me.liujia95.biliplayer.base;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.Semaphore;

import me.liujia95.biliplayer.utils.Constants;
import me.liujia95.biliplayer.utils.LogUtils;

/**
 * Created by Administrator on 2016/1/19 11:14.
 */
public abstract class BaseProtocol<T> {

    private Semaphore mSemaphoreThread = new Semaphore(0);

    public T loadData() throws Exception {
        return getDataFromNet();
    }

    String json;

    private T getDataFromNet() throws Exception {
        HttpUtils http = new HttpUtils();
        String url = Constants.BASE_SERVER + getInterfacePath();

        http.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                json = responseInfo.result;
                //释放信号量
                mSemaphoreThread.release();
            }

            @Override
            public void onFailure(HttpException error, String msg) {

            }
        });
        mSemaphoreThread.acquire();
        LogUtils.d("json::" + json);

        return parseJson(json);
    }

    protected T parseJson(String json) {
        //获取类上面的表象泛型
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取真实的泛型类型（第1个）
        Type t = type.getActualTypeArguments()[0];
        return new Gson().fromJson(json, t);
    }

    protected abstract String getInterfacePath();

}
