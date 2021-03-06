<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账户统计管理</title>
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
		<li class="active"><a href="${ctx}/reportMemberActDaily?type=10">日用户统计列表</a></li>
		<li class="active"><a href="${ctx}/reportMemberActDaily?type=7">月用户统计列表</a></li>
		<li class="active"><a href="${ctx}/reportMemberActDaily?type=4">年用户统计列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="reportMemberActDaily" action="${admin}/reportMemberActDaily/?type=10" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center">日期</th>
				<th style="text-align:center">可用余额</th>
				<th style="text-align:center">借款账户</th>
				<th style="text-align:center">冻结账户</th>
				<th style="text-align:center">红包账户</th>
<!-- 				<th style="text-align:center">待还账户</th> -->
<!-- 				<th style="text-align:center">待收账户</th>				 -->
                <th style="text-align:center">统计时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportMemberActDaily">
			<tr>
				<td style="text-align:center">
					${reportMemberActDaily.date}
				</td>
				<td style="text-align:center">
					${fns:decimalToStr(reportMemberActDaily.avlBal,2)}
				</td>
				<td style="text-align:center">
					${fns:decimalToStr(reportMemberActDaily.loanBal,2)}
				</td>
				<td style="text-align:center">
					${fns:decimalToStr(reportMemberActDaily.freezenBal,2)}
				</td>
				<td style="text-align:center">
					${fns:decimalToStr(reportMemberActDaily.redbagBal,2)}
				</td>
<!-- 				<td style="text-align:center"> -->
<%-- 					${reportMemberActDaily.pendingRepayment} --%>
<!-- 				</td> -->
<!-- 				<td style="text-align:center"> -->
<%-- 					${reportMemberActDaily.pendingReceiver} --%>
<!-- 				</td> -->
                <td style="text-align:center">
                <fmt:formatDate value="${reportMemberActDaily.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>