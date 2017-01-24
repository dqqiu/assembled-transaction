package org.spirit.assembled.transaction.api.common;

import java.util.List;

/**
 * @description 分页包装类
 * @author qiudequan
 * @createTime 2017年1月15日 下午12:16:03
 */
@SuppressWarnings("unused")
public class Page<T> {
	// 当前页码
	private int currentPage;
	// 每页显示记录数
	private int pageSize;
	// 总页数
	private int pageCount;
	// 总记录数
	private int recordsCount;
	// 数据记录
	private List<T> records;
	
	public Page() {
		this.currentPage = 1;
		this.pageSize = 10;
	}
	
	public Page(int currentPage, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
	}
	
	public Page(int currentPage, int pageSize, int recordsCount) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordsCount = recordsCount;
	}
	
	public Page(int currentPage, int pageSize, int recordsCount, List<T> records) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordsCount = recordsCount;
		this.records = records;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		if(getRecordsCount() % getPageSize() == 0) {
			return getRecordsCount() / getPageSize();
		}
		return getRecordsCount() / getPageSize() + 1;
	}

	public int getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(int recordsCount) {
		this.recordsCount = recordsCount;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
	
}
