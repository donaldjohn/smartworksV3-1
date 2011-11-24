<%@page import="net.smartworks.util.SmartUtil"%>
<%@page import="net.smartworks.model.community.info.DepartmentInfo"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.community.*"%>

<%
	User cUser = SmartUtil.getCurrentUser(request, response);

	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	DepartmentInfo[] departments = smartWorks.getMyDepartments(cUser.getCompanyId(), cUser.getId());
%>

<ul>
	<%
		if (departments != null) {
			for (DepartmentInfo department : departments) {
				String departmentContext = ISmartWorks.CONTEXT_PREFIX_DEPARTMENT_SPACE + department.getId();
	%>
	<li class="ico_depart"><a
		href="department_space.sw?cid=<%=departmentContext%>"><%=department.getName()%></a>
	</li>
	<%
		}
		}
	%>
</ul>
