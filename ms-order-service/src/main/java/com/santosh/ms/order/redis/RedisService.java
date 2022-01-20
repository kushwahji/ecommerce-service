/**
 * 
 */
package com.santosh.ms.order.redis;
import java.util.List;

/**
 * @author santosh.kushwah
 * @since 14-01-2022
 */
public interface RedisService {
	public void addItemToCart(String key, Object item);
    public List<Object> getCart(String key ,Class type);
    public void deleteItemFromCart(String key, Object item);
    public void deleteCart(String key);
}
