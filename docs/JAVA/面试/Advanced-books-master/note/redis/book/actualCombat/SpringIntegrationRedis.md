## SpringBoot 集成 Redis

> SpringBoot 2.3.1 

### 添加依赖
这里我们采用的是 `lettuce` 所以必须引入`commons-pool2`

```java
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-data-redis</artifactId>
 </dependency>
 <dependency>
     <groupId>org.apache.commons</groupId>
     <artifactId>commons-pool2</artifactId>
 </dependency>
```

### 配置文件
```java
spring: 
  redis:
    host: 127.0.0.1
    port: 6379
    database: 0
#    password:
    timeout: 6000
# 链接池
    lettuce:
      pool:
        max-active: 10
        max-idle: 10
        max-wait: 0
```

### 自定义 RedisConfiguration
```java
public class RedisConfiguration {

    private final LettuceConnectionFactory lettuceConnectionFactory;

    public RedisConfiguration(LettuceConnectionFactory lettuceConnectionFactory) {
        this.lettuceConnectionFactory = lettuceConnectionFactory;
    }

    @Bean("redisTemplate")
    @ConditionalOnProperty(name = "spring.redis.host", matchIfMissing = true)
    public RedisTemplate<String, Object> getSingleRedisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        RedisSerializer redisObjectSerializer = new RedisObjectSerializer();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(redisObjectSerializer);
        redisTemplate.setHashValueSerializer(redisObjectSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public HashOperations<String, String, String> hashOperations(StringRedisTemplate stringRedisTemplate) {
        return stringRedisTemplate.opsForHash();
    }

    @Bean
    public ListOperations<String,Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForList();
    }

    @Bean
    public ZSetOperations<String,Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForZSet();
    }

    @Bean
    public SetOperations<String,Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForSet();
    }

    @Bean
    public ValueOperations<String,Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
        return redisTemplate.opsForValue();
    }

    /**
     * redis工具类
     */
    @Bean("redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate,
                               StringRedisTemplate stringRedisTemplate,
                               HashOperations<String, String, String> hashOperations,
                               ListOperations<String,Object> listOperations,
                               ZSetOperations<String,Object>zSetOperations,
                               SetOperations<String,Object> setOperations,
                               ValueOperations<String,Object> valueOperations) {
        return new RedisUtil(redisTemplate, stringRedisTemplate,hashOperations,listOperations,
                zSetOperations,setOperations,valueOperations);
    }
}

```

### 自定义 RedisUtil

这里只截取了一部分，更多的源码请移步source-code 内的redis 目录
```java

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    boolean expire(K key, long time);

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    long getExpire(K key);

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    boolean hasKey(K key);

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    void del(K... key);

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    V get(K key);

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    boolean set(K key, V value);

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    boolean set(K key, V value, long time);

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    long incr(K key, long delta);

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    long decr(K key, long delta);

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    V hget(K key, K item);

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    Map<K, V> hmget(K key);

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    boolean hmset(K key, Map<K, V> map);

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    boolean hmset(K key, Map<K, V> map, long time);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    boolean hset(K key, K item, V value);

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    boolean hset(K key, K item, V value, long time);

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    void hdel(K key, V... item);

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    boolean hHasKey(K key, K item);

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    double hincr(K key, K item, double by);

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    double hdecr(K key, K item, double by);

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    Set<V> sGet(K key);

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    boolean sHasKey(K key, V value);

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    long sSet(K key, V... values);

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    long sSetAndTime(K key, long time, V... values);

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    long sGetSetSize(K key);

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    long setRemove(K key, V... values);

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @return
     */
    List<V> lGet(K key, long start, long end);

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    long lGetListSize(K key);

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    V lGetIndex(K key, long index);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    boolean lSet(K key, V value);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    boolean lSet(K key, V value, long time);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @return
     */
    boolean lSet(K key, List<V> value);

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    boolean lSet(K key, List<V> value, long time);

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    boolean lUpdateIndex(K key, long index, V value);

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    long lRemove(K key, long count, V value);

    /**
     * 模糊查询获取key值
     *
     * @param pattern
     * @return
     */
    Set keys(K pattern);

    /**
     * 使用Redis的消息队列
     *
     * @param channel
     * @param message 消息内容
     */
    void convertAndSend(K channel, V message);

    /**
     * 将数据添加到Redis的list中（从右边添加）
     *
     * @param listKey
     * @param expireEnum 有效期的枚举类
     * @param values     待添加的数据
     */
    void addToListRight(K listKey, Status.ExpireEnum expireEnum, V... values);

    /**
     * 根据起始结束序号遍历Redis中的list
     *
     * @param listKey
     * @param start   起始序号
     * @param end     结束序号
     * @return
     */
    List<V> rangeList(K listKey, long start, long end);

    /**
     * 弹出右边的值 --- 并且移除这个值
     *
     * @param listKey
     */
    V rifhtPop(K listKey);

```

### 测试

```java
@RestController
@RequestMapping(value = "/redis/")
public class RedisApiWeb {
    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("test")
    public void redisTest(){
        redisUtil.set("redis","Hello World");
        System.out.println(redisUtil.get("redis"));
        Map<String,Object> map = new HashMap<>();
        map.put("hash01","hash02");
        redisUtil.hmset("myHash",map,100);
        System.out.println(redisUtil.hmget("myHash"));
    }


}
```