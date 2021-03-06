<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/WEB-INF/views/common/taglibs.jspf"%>
<html>
<head>
  <title>role 编辑页面</title>
</head>
<body>
<div class="portlet box editBox">
    <div class="portlet-title"><span>角色信息</span></div>
    <div class="portlet-body form">
        <form:form id="inputForm"  modelAttribute="entity" action="#" method="post">
        <div class="form-body">
            <input type="hidden" name="id" value="${entity.id}"/>
            <div id="messageBox" class="alert alert-error form-control controls" style="display:none">输入有误，请先更正。</div>
            <div class="control-group">
                <label for="name" class="control-label">角色名称:</label>
                <div class="controls">
                    <input type="text" id="name" name="name" value="${entity.name}" class="form-control required"/>
                </div>
            </div>
            <div class="control-group">
                <label for="permissions" class="control-label">具备权限:</label>
                <div class="controls">
                    <textarea id="permissions" name="permissions" class="form-control required">${entity.permissions}</textarea>
                </div>
            </div>
            <div class="form-actions">
                <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;
                <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
                <p class="help-block">(点击提交保存信息.)</p>
            </div>
            </form:form>
        </div>
    </div>
</div>
<script>
    $cy.handleUniform();
</script>
</body>
</html>
