<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>好友关系管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mem/memberFriendRelation/">好友关系列表</a></li>
		<shiro:hasPermission name="mem:memberFriendRelation:edit"><li><a href="${ctx}/mem/memberFriendRelation/add">好友关系添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="memberFriendRelation" action="${ctx}/mem/memberFriendRelation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><label style="text-align:right;width:50px"></label><input id="btnSubmit" class="btn btn-primary " style="width:80px;" type="submit" value="查   询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th style="text-align:center">姓名</th>
				<th style="text-align:center">手机号</th>
				<th style="text-align:center">未读消息</th>				
				<th style="text-align:center">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="memberFriendRelation">
			<tr>
				<td style="text-align:center">
					${memberFriendRelation.friend.name}
				</td>
				<td style="text-align:center">
					${memberFriendRelation.friend.username}
				</td>
				<td style="text-align:center">
					${memberFriendRelation.unread}
				</td>
				<td  style="text-align:center">
    				<a href="${ctx}/mem/memberFriendRelation/update?id=${memberFriendRelation.id}">修改</a>
					<a href="${ctx}/mem/memberFriendRelation/delete?id=${memberFriendRelation.id}" onclick="return confirmx('确认要删除该好友关系吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>