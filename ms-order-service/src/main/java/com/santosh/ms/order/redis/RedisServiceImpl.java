/**
 * 
 */
package com.santosh.ms.order.redis;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.santosh.ms.order.request.OrderItemDto;
import com.santosh.ms.order.utils.JsonUtil;
import redis.clients.jedis.Jedis;

/**
 * @author santosh.kushwah
 * @since 15-01-2022
 */
@Repository
public class RedisServiceImpl implements RedisService {

	private Jedis jedis = new Jedis();

	@Override
	public void addItemToCart(String key, Object item) {
		jedis.sadd(key, JsonUtil.writeValuesAsJson(item));
	}

	@Override
	public List<Object> getCart(String key, Class type) {
		List<Object> carts = new ArrayList<>();
		jedis.smembers(key).stream().forEach(i -> 
			carts.add(JsonUtil.readValuesAsObject(i, type))
		);
		return carts;
	}

	@Override
	public void deleteItemFromCart(String id, Object item) {
		jedis.srem(id, JsonUtil.writeValuesAsJson(item));
	}

	@Override
	public void deleteCart(String id) {
		jedis.del(id);
	}
}
