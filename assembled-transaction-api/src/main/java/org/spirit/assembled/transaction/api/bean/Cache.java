package org.spirit.assembled.transaction.api.bean;

import java.util.Map;
import java.util.Map.Entry;

import org.spirit.assembled.transaction.api.utils.EhcacheUtils;

import net.sf.ehcache.Element;

/**
 * @description ehcache
 * @author qiudequan
 * @createTime 2017年1月11日 下午12:35:20 
 */
public class Cache<K, V>{
  private net.sf.ehcache.Cache cache;
  private int expire  = 300;
  
  public Cache(String cacheName) {
    cache = EhcacheUtils.getCacheManager().getCache(cacheName);
  }
  
  public Cache(String cacheName, int expire) {
    cache = EhcacheUtils.getCacheManager().getCache(cacheName);
    this.expire = expire;
  }
  
  /**
   *  @description 放入缓存
   *  @param key 键
   *  @param value 值	
   *  @return void
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  public void put(K key, V value) {
    Element element = new Element(key, value, expire, expire);
    cache.put(element);
  }
  
  /**
   *  @description 全部放入缓存
   *  @param map map集合
   *  @return void
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  public void putAll(Map<? extends K, ? extends V> map) {
    for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
      put(entry.getKey(), entry.getValue());
    }
  }
  
  /**
   *  @description 移除缓存
   *  @param key 键
   *  @return boolean true：移除成功，false：移除失败
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  public boolean remove(K key) {
    return cache.remove(key);
  }
  
  /**
   *  @description 移除所有缓存
   *  @return void
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  public void removeAll() {
    cache.removeAll();
  }
  
  /**
   *  @description 根据键获取缓存
   *  @return V 缓存内容
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  @SuppressWarnings("unchecked")
  public V get(K key) {
    Element element = cache.get(key);
    return element == null ? null : (V) element.getObjectValue();
  }
  
  /**
   *  @description 根据键判断缓存是否存在
   *  @param key 键
   *  @return boolean true：存在，false：不存在
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  public boolean exist(K key) {
    return cache.isKeyInCache(key);
  }
  
  /**
   *  @description 根据键判断缓存是否过期
   *  @param key 键
   *  @return boolean true：过期，false：未过期
   *  @createTime 2017年1月11日 下午1:05:59 
   *  @author qiudequan
   */
  public boolean isExpired(K key) {
    Element element = cache.get(key);
    return cache.isExpired(element);
  }
  
}
