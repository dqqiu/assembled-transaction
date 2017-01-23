package org.spirit.assembled.transaction.api.utils;

import net.sf.ehcache.CacheManager;

/**
 * @description 在此处添加描述
 * @author qiudequan
 * @createTime 2017年1月16日 下午9:32:41
 */
public class EhcacheUtils {
	private EhcacheUtils() {}
	private static final CacheManager CACHE_MANAGER = CacheManager.create(EhcacheUtils.class.getResource("/ehcache.xml"));
	
	public static CacheManager getCacheManager() {
		System.out.println(EhcacheUtils.class.getResource("/ehcache.xml").getPath());
		return CACHE_MANAGER;
	}
}
