package top.alexmmd.redis.utils;

import cn.hutool.json.JSONConverter;
import cn.hutool.json.JSONUtil;

import java.lang.reflect.Method;

/**
 * @author 汪永晖
 * @date 2021/12/22 14:43
 */
@SuppressWarnings("ALL")
public class ReflectUtil extends cn.hutool.core.util.ReflectUtil {

    /**
     * 转换对象类型方法
     *
     * @param object source
     * @param clazz  target
     * @param <T>    T
     * @return t
     */
    public static <T> T cast(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }
        if (clazz.isInstance(object)) {
            return clazz.cast(object);
        }
        if (object instanceof String) {
            return JSONUtil.toBean((String) object, clazz);
        }
        Method convertMethod = ReflectUtil.getMethodByName(JSONConverter.class, "jsonConvert");
        return ReflectUtil.invokeStatic(convertMethod, clazz, object, true);
    }
}
