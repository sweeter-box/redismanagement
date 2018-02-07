<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"
%><%@ tagliburi="http://java.sun.com/jsp/jstl/core" prefix="c" 
%><%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()			+ path + "/";
%><%
	int pagesum = Integer.parseInt(request.getParameter("pagesum"));
%>
<!DOCTYPE html>
<html>
<head>
 <c:set var="baseurl" value="<%=basePath%>"></c:set>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${baseurl}js/jquery-2.2.1.min.js"></script>
<link href="${baseurl}css/bootstrap.min.css" rel="stylesheet">
<script src="${baseurl}js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${baseurl}css/backstage.css">
</head>
<body style="">

	<div class="navitagorDiv">
		<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
			<a class="navbar-brand" href="index">学生信息管理</a> 
			
		</nav>
	</div>

	<title>学生信息管理</title>
	<div class="workingArea">
		<h1 class="label label-info">学生信息管理</h1>
		<br> <br>


		<div class="listDataTableDiv">
			<table
				class="table table-striped table-bordered table-hover  table-condensed">
				<thead>
					<tr class="success">
						<th>序号</th>
						<th>ID</th>
						<th>姓名</th>
						<th>出生日期</th>
						<th>平均分</th>
						<th>备注</th>
						<th>操作</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="student" items="${studentList}" varStatus="st">

						<tr>
							<td>${st.index+1}</td>
							<td>${student.id}</td>
							<td>${student.name}</td>
						   <td>${student.birthday}</td>
						   <td>${student.avgscore}</td>
							<td>${student.description}</td>				
							<td>
								<button type="button" class="btn btn-default"
									data-toggle="modal" data-target="#myModal${student.id}">编辑</button> <!--编辑  -->
								<div class="modal fade" id="myModal${student.id}" tabindex="-1"
									role="dialog" aria-labelledby="myModalLabel">
									<div class="modal-dialog">
										<form action="updateController?pagenum=${sessionScope.pagenum}" method="post">
											<div class="modal-content">
												<div class="modal-header">
													<button data-dismiss="modal" class="close" type="button">
														<span aria-hidden="true">×</span><span class="sr-only">Close</span>
													</button>
													<h4 class="modal-title">信息修改</h4>
												</div>
												<div class="modal-body">
													<p><strong>学号：${student.id}</strong></p>
													<p>
													<input type="hidden" name="id" value="${student.id}">
													</p>
													<p>
														姓名:<input type="text" name="name" value="${student.name}">
													</p>
				
													<p>
														出生日期:<input type="text" name="birthday" value="${student.birthday}">
													</p>
													<p>
														平均分:<input type="text" name="avgscore" value="${student.avgscore}">
													</p>
													<p>
														备注:<input type="text" name="description" value="${student.description}">
													</p>
												</div>
												<div class="modal-footer">
													<button data-dismiss="modal" class="btn btn-default"
														type="button">关闭</button>
													<button class="btn btn-primary" type="submit">提交</button>
												</div>
											</div>
										</form>
									</div>
								</div> 
								
								<a deletelink="true" href="deleteController?id=${student.id}&pagenum=${sessionScope.pagenum}">
								<button type="button" class="btn btn-default">删除</button></a>
							</td>

						</tr>

					</c:forEach>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>
						<button type="button" class="btn btn-default"
								data-toggle="modal" data-target="#myModal">添加学生</button>
								<button type="button" class="btn btn-default"
								data-toggle="modal" data-target="#myModal">批量操作</button>
								</td>
				</tbody>
			</table>
		</div>

		<div class="pageDiv">


<script>
$(function() {
$("ul.pagination li.disabled a").click(function() {
		return false;
		});
	});
</script>


<span>共${total}条数据</span>
			

<!---- 页码-- -->
<nav>
<ul class="pagination">
	<li><a href="index?pagenum=1" aria-label="Previous"> <span aria-hidden="true">«</span>
		</a></li>

					<%
						for (int i = 1; i <= pagesum; i++) {
					%>
					
					<li><a
						href="index?pagenum=<%=i %>" class="current"> <%=i%></a></li>
					<%
						}
					%>
					<li><a
						href="index?pagenum=<%=pagesum%>" aria-label="Next"> <span aria-hidden="true">»</span>
					</a></li>
				</ul>
			</nav>
		</div>

<!-- 添加 -->

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<form method="post" id="addForm" action="insertController">
				<div class="modal-content">
					<div class="modal-header">
						<button data-dismiss="modal" class="close" type="button">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">添加学生信息</h4>
					</div>
					<div class="modal-body">
						<table class="addTable">
							<tbody>
								<tr>
									<td>姓名</td>
									<td><input id="name" name="name" type="text"
										class="form-control"></td>
								</tr>
								<tr>
									<td>出生日期</td>
									<td><input id="birthday" name="birthday" type="text"
										class="form-control"></td>
								</tr>
								<tr>
									<td>平均分</td>
									<td><input id="avgscore" name="avgscore" type="text"
										class="form-control"></td>
								</tr>

								<tr>
									<td>备注</td>
									<td><input id="description" name="description" type="text"
										class="form-control"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
						<button class="btn btn-success" type="submit">提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer"></div>
	
	<script>
	function checkEmpty(id, name) {
		var value = $("#" + id).val();
		if (value.length == 0) {
			alert(name + "不能为空");
			$("#" + id)[0].focus();
			return false;
		}
		return true;
	}
	function checkNumber(id, name) {
		var value = $("#" + id).val();
		if (value.length == 0) {
			alert(name + "不能为空");
			$("#" + id)[0].focus();
			return false;
		}
		if (isNaN(value)) {
			alert(name + "必须是数字");
			$("#" + id)[0].focus();
			return false;
		}

		return true;
	}
	function checkInt(id, name) {
		var value = $("#" + id).val();
		if (value.length == 0) {
			alert(name + "不能为空");
			$("#" + id)[0].focus();
			return false;
		}
		if (parseInt(value) != value) {
			alert(name + "必须是整数");
			$("#" + id)[0].focus();
			return false;
		}

		return true;
	}

	$(function() {
		$("a").click(function() {
			var deleteLink = $(this).attr("deleteLink");
			console.log(deleteLink);
			if ("true" == deleteLink) {
				var confirmDelete = confirm("确认要删除");
				if (confirmDelete)
					return true;
				return false;

			}
		});
	})
</script>
	<script>
		$(function() {
			$("#addForm").submit(function() {
				if (!checkEmpty("name", "姓名"))
					return false;
				if (!checkNumber("avgscore", "平均分"))
					return false;
				if (!checkInt("avgscore", "平均分"))
					return false;
				return true;
			});
		});
	</script>
</body>
</html>