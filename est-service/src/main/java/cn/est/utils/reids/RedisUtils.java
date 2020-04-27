package cn.est.utils.reids;


import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis工具 类
 */
@Component
@EnableCaching
public class RedisUtils {

    static Logger log = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 存储key-value
     * @param key
     * @param value
     * @throws Exception
     */
    public void putValue(String key, String value) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, value);
            log.info("[REDIS] put k-v key=%s, value=%s", key, value);
        } catch (Exception e) {
            log.error("[REDIS-ERROR] put k-v key=%s, value=%s", key, value);
        }
    }

    /**
     * 存储key-value
     *
     * @param key
     * @param value
     * @param timeout 单位为毫秒
     * @throws Exception
     */
    public void putValue(String key, String value, long timeout) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.set(key, value, timeout, TimeUnit.SECONDS);
            log.info("[REDIS] put k-v  key=%s, value=%s, timeout=%s", key, value, timeout);
        } catch (Exception e) {
            log.info("[REDIS--ERROR] put k-v  key=%s, value=%s, timeout=%s", key, value, timeout);
        }
    }

    /**
     * 批量存储key-value
     *
     * @param values
     * @throws Exception
     */
    public void putValue(Map<String, String> values) {
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            valueOps.multiSet(values);
            log.info("[REDIS] put map Map=%s", values);
        } catch (Exception e) {
            log.error("[REDIS--ERROR] put map Map=%s", values);
        }

    }

    /**
     * 获取value
     *
     * @param key
     * @return 不存在时，返回null
     * @throws Exception
     */
    public String getValue(String key) {
        String result = null;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            result = valueOps.get(key);
            log.info("[REDIS] Get k-v key={}, value={}", key, result);
        } catch (Exception e) {
            log.error("[REDIS--ERROR]-->getValue(String key)错误:{}", e);
        }
        return result;
    }

    /**
     * 批量获取值
     *
     * @param keys
     * @return
     * @throws Exception
     */
    public Map<String, String> multiGetValue(List<String> keys) {
        Map<String, String> results = null;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            List<String> values = valueOps.multiGet(keys);
            results = new HashMap<>();
            if (values != null && !keys.isEmpty()) {
                for (int i = 0; i < keys.size(); i++) {
                    String fieldName = keys.get(i);
                    String fieldValue = values.get(i);
                    // 如果对应的字段为null，则不需要放入
                    if (!StringUtils.isBlank(fieldValue)) {
                        results.put(fieldName, fieldValue);
                    }
                }
            }
            log.info("[REDIS] Get Map map=%s", results);
        } catch (Exception e) {
            log.error("[REDIS--ERROR] Get Map map=%s", results);
        }

        return results;
    }

    /**
     * 批量删除key
     *
     * @param keys
     * @throws Exception
     */

    public void delete(Collection<String> keys) {
        try {
            redisTemplate.delete(keys);
            log.info("[REDIS] Delete keys=%s", keys);
        } catch (Exception e) {
            log.error("[REDIS] Delete keys=%s", keys);
        }
    }

    /**
     * 删除key
     *
     * @param key
     * @throws Exception
     */
    public void delete(String key) {
        try {
            redisTemplate.delete(key);
            log.info("[REDIS] Delete key=%s", key);
        } catch (Exception e) {
            log.error("[REDIS] Delete key=%s", key);
        }
    }

    /**
     * 更新hash里面的属性字段
     *
     * @param key     键
     * @param hashKey 属性名称
     * @param value   属性值
     * @throws Exception
     */
    public void putHash(String key, String hashKey, String value) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.put(key, hashKey, value);
            log.info("[REDIS] Put hash key=%s,hashKey=%s,value=%s", key, hashKey, value);
        } catch (Exception e) {
            log.error("[REDIS] Put hash key=%s,hashKey=%s,value=%s", key, hashKey, value);
        }
    }

    /**
     * 批量更新hash里面的属性字段
     *
     * @param key
     * @param hashProperties 属性集合
     * @throws Exception
     */
    public void putHash(String key, Map<String, String> hashProperties) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.putAll(key, hashProperties);
            log.info("[REDIS] Put hash key=%s,hashKey-value=%s", key, hashProperties);
        } catch (Exception e) {
            log.error("[REDIS] Put hash key=%s,hashKey-value=%s", key, hashProperties);
        }
    }

    /**
     * 获取hash的字段值
     *
     * @param key
     * @param hashKey
     * @throws Exception
     */
    public String getHash(String key, String hashKey) {
        String result = null;
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            result = hashOps.get(key, hashKey);
            log.info("[REDIS] Get hash key=%s,hashKey=%s,value=%s", key, hashKey, result);
            return StringUtils.isBlank(result) ? null : result;
        } catch (Exception e) {
            log.error("[REDIS] Get hash key=%s,hashKey=%s,value=%s", key, hashKey, result);
        }
        return result;
    }

    /**
     * 批量获取hash的字段值
     *
     * @param key
     * @param hashKeys
     * @return filed的Map集合, filedName-filedValue
     * @throws Exception
     */
    public Map<String, String> multiGetHash(String key, List<String> hashKeys) {
        HashMap<String, String> results = null;
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            List<String> values = hashOps.multiGet(key, hashKeys);
            results = new HashMap<String, String>();
            if (values != null && !values.isEmpty()) {
                for (int i = 0; i < hashKeys.size(); i++) {
                    String fieldName = hashKeys.get(i);
                    String fieldValue = values.get(i);
                    // 如果对应的字段为null，则不需要放入
                    if (!StringUtils.isBlank(fieldValue)) {
                        results.put(fieldName, fieldValue);
                    }
                }
            }
            log.info("[REDIS] Get hash key=%s,hashKey-value=%s", key, results);
        } catch (Exception e) {
            log.error("[REDIS] Get hash key=%s,hashKey-value=%s", key, results);
        }
        return results;
    }

    /**
     * 获取结果
     *
     * @param key
     * @return
     * @throws Exception
     */
    public Map<String, String> getHashValues(String key) {
        Map<String, String> results = null;
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            results = hashOps.entries(key);
            log.info("[REDIS] Get hash key=%s,hashKey-value=%s", key, results);
        } catch (Exception e) {
            log.error("[REDIS] Get hash key=%s,hashKey-value=%s", key, results);
        }

        return results;
    }

    /**
     * 从hash表里面删除对应的值
     *
     * @param key
     * @param hashKey
     * @throws Exception
     */
    public void deleteHash(String key, String hashKey) {
        try {
            HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
            hashOps.delete(key, hashKey);
            log.info("[REDIS] Delete hash key=%s, hashKey=%s", key, hashKey);
        } catch (Exception e) {
            log.error("[REDIS] Delete hash key=%s, hashKey=%s", key, hashKey);
        }
    }

    /**
     * 为key设置超时时间
     *
     * @param key
     * @param timeout
     */
    public void expire(String key, long timeout) {
        try {
            boolean expore = redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            if (expore) {
                log.info("[REDIS] Expire success key=%s, timeout=%s", key, timeout);
            } else {
                log.info("[REDIS] Expire fail key=%s, timeout=%s", key, timeout);
            }
        } catch (Exception e) {
            log.error("[REDIS] Expire fail key=%s, timeout=%s", key, timeout);
        }
    }

    /**
     * 进行递增
     *
     * @param key
     * @param delta
     * @param timeout
     * @return
     * @throws Exception
     */
    public long incrementDelta(String key, long delta, long timeout) {
        long result = -1L;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            result = valueOps.increment(key, delta);
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            log.info("[REDIS] increment key=%s, result=%s, timeout=%s", key, result, timeout);
        } catch (Exception e) {
            log.error("[REDIS] increment key=%s, result=%s, timeout=%s", key, result, timeout);
        }
        return result;
    }

    /**
     * 进行递增
     *
     * @param key
     * @param delta
     * @return
     * @throws Exception
     */
    public long incrementDelta(String key, long delta) {
        long result = -1L;
        try {
            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
            result = valueOps.increment(key, delta);
            log.info("[REDIS] increment key=%s, result=%s", key, result);
        } catch (Exception e) {
            log.error("[REDIS] increment key=%s, result=%s", key, result);
        }
        return result;
    }

    /**
     * 从左推送到list中
     */
    public void leftPush(String key, String value) {
        try {
            BoundListOperations<String, String> stringStringBoundListOperations = redisTemplate.boundListOps(key);
            stringStringBoundListOperations.leftPush(value);
            log.info("[REDIS] leftPush list key=%s,value=%s", key, value);
        } catch (Exception e) {
            log.error("[REDIS] leftPush list key=%s,value=%s", key, value);
        }
    }

    /**
     * 从list右边弹出
     */
    public String rightPop(String key) {
        String result = null;
        try {
            BoundListOperations<String, String> stringStringBoundListOperations = redisTemplate.boundListOps(key);
            result = stringStringBoundListOperations.rightPop();
            log.info("[REDIS] rightPop list key=%s,result=%s", key, result);
        } catch (Exception e) {
            log.error("[REDIS] rightPop list key=%s,result=%s", key, result);
        }
        return result;
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value.toString(), time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public <T> T getJson(String key, Class<T> entityClass) {
        try {
            String value = getValue(key);
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return JSON.parseObject(value, entityClass);
        } catch (Exception e) {
            log.error("从redis获取json对象失败:%s,异常信息为: %s", key, ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    public boolean setJson(String key, Object value, long time) {
        try {
            if (time > 0) {
                putValue(key, JSON.toJSONString(value), time);
            } else {
                putValue(key, JSON.toJSONString(value));
            }
            return true;
        } catch (Exception e) {
            log.error("往redis放入json对象失败:%s,异常信息为: %s", key, ExceptionUtils.getStackTrace(e));
            return false;
        }
    }

    public <T> List<T> getJsonList(String key, Class<T> entityClass) {
        try {
            String value = getValue(key);
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return JSON.parseArray(value, entityClass);
        } catch (Exception e) {
            log.error("从redis获取json对象失败:%s", key, ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
}