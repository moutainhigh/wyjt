<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>应用激活管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				onfocusout: function(element){
			        $(element).valid();
			    },
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
		//				error.insertAfter(element);
						error.appendTo(element.next());
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/app/appInst/">应用激活列表</a></li>
		<shiro:hasPermission name="app:appInst:edit">
		<li class="active"><a href="${ctx}/app/appInst/add?id=${appInst.id}">应用激活添加</a></li>
		</shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="appInst" action="${ctx}/app/appInst/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label style="text-align:right;width:150px">系统类型：</label>
			<form:input path="osType" style="width:230px" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">系统版本：</label>
			<form:input path="osVersion" style="width:230px" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">应用版本：</label>
			<form:input path="appVersion" style="width:230px" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">ak：</label>
			<form:input path="ak" style="width:230px" htmlEscape="false" maxlength="16" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">设备型号：</label>
			<form:input path="deviceModel" style="width:230px" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">设备号：</label>
			<form:input path="deviceToken" style="width:230px" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">推送码：</label>
			<form:input path="pushToken" style="width:230px" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">渠道编号：</label>
			<form:input path="channeId" style="width:230px" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label style="text-align:right;width:150px">最后登录IP：</label>
			<form:input path="loginIp" style="width:230px" htmlEscape="false" maxlength="15" class="input-xlarge required"/>
			<span class="help-inline"  style="width:150px"><font color="red">*</font> </span>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="app:appInst:edit"><input id="btnSubmit" class="btn btn-primary" style="width:80px;" type="submit" value="保   存"/>&nbsp;</shiro:hasPermission>
			<label style="text-align:right;width:50px"></label>
			<input id="btnCancel" class="btn btn-primary" type="button" style="width:80px;" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>