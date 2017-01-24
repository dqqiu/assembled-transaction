<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<c:set var="staticPath"
	value="${pageContext.request.contextPath}/static" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>事务管理后台</title>
<link rel="stylesheet" type="text/css"
	href="${staticPath}/css/manage.css" />
</head>
<body>
	<div class="search-console">
		<div class="search-box">
			<div class="search-region">
				<span class="search-key">事务域：</span></span><input type="text"
					class="input-search" name="region" /><input type="button"
					class="btn-search" value="搜索" />
			</div>
		</div>
		<div class="search-result">
			<div class="result-container">
				<table border="1" class="result-table">
					<tr>
						<th>事务域</th>
						<th>产生时间</th>
						<th>事务分支</th>
						<th>全局格式化ID</th>
						<th>重试次数</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<tr>
						<td>ORDER</td>
						<td>2016-12-16 22:53:02</td>
						<td>1021000102202110220211022021</td>
						<td>35222</td>
						<td>4</td>
						<td>TRYING</td>
						<td>恢复</td>
					</tr>
					<tr>
						<td>REDPACKET</td>
						<td>2016-12-18 12:23:42</td>
						<td>1021022102202110220211022021</td>
						<td>35277</td>
						<td>0</td>
						<td>COMMIT</td>
						<td>查看</td>
					</tr>
					<tr>
						<td>TRADE</td>
						<td>2017-01-01 15:33:32</td>
						<td>1022021102202110220211022021</td>
						<td>35866</td>
						<td>1</td>
						<td>CONFIRMING</td>
						<td>恢复</td>
					</tr>
					<tr>
						<td>ORDER</td>
						<td>2016-12-16 22:53:02</td>
						<td>1021000102202110220211022021</td>
						<td>35222</td>
						<td>4</td>
						<td>TRYING</td>
						<td>恢复</td>
					</tr>
					<tr>
						<td>REDPACKET</td>
						<td>2016-12-18 12:23:42</td>
						<td>1021022102202110220211022021</td>
						<td>35277</td>
						<td>0</td>
						<td>COMMIT</td>
						<td>查看</td>
					</tr>
					<tr>
						<td>TRADE</td>
						<td>2017-01-01 15:33:32</td>
						<td>1022021102202110220211022021</td>
						<td>35866</td>
						<td>1</td>
						<td>CONFIRMING</td>
						<td>恢复</td>
					</tr>
					<tr>
						<td>ORDER</td>
						<td>2016-12-16 22:53:02</td>
						<td>1021000102202110220211022021</td>
						<td>35222</td>
						<td>4</td>
						<td>TRYING</td>
						<td>恢复</td>
					</tr>
					<tr>
						<td>REDPACKET</td>
						<td>2016-12-18 12:23:42</td>
						<td>1021022102202110220211022021</td>
						<td>35277</td>
						<td>0</td>
						<td>COMMIT</td>
						<td>查看</td>
					</tr>
					<tr>
						<td>TRADE</td>
						<td>2017-01-01 15:33:32</td>
						<td>1022021102202110220211022021</td>
						<td>35866</td>
						<td>1</td>
						<td>CONFIRMING</td>
						<td>恢复</td>
					</tr>
					<tr>
						<td>TRADE</td>
						<td>2017-01-01 15:33:32</td>
						<td>1022021102202110220211022021</td>
						<td>35866</td>
						<td>1</td>
						<td>CONFIRMING</td>
						<td>恢复</td>
					</tr>
				</table>
				<div class="pager">
					<div class="pager-info">总记录128条，每页10条，当前第1/13页</div>
					<div class="pager-items">
						<a class="pager-item prev">&nbsp;</a> <a class="pager-item active"
							href="javascript:void(0);">1</a> <a class="pager-item">2</a> <a
							class="pager-item">3</a> <a class="pager-item">4</a> <a
							class="pager-item">5</a> <a class="pager-item">6</a> <a
							class="pager-item">7</a> <a class="pager-item">8</a> <a
							class="pager-item">9</a> <a class="pager-item">10</a> <a
							class="pager-item next">&nbsp;</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>