<%@page import="net.smartworks.model.work.info.WorkInfo"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.work.*"%>

<%
	String companyId = (String) session.getAttribute("companyId");
	String userId = (String) session.getAttribute("userId");

	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	WorkInfo[] works = smartWorks.getMyAllWorksByCategoryId(companyId, userId, request.getParameter("groupId"));
	String iconType = null;
	String classType = "js_content";
	String workContext = null;
	String targetContent = null;
%>

<ul>
	<%
		if (works != null) {
			for (WorkInfo work : works) {
				if (work.getType() == SmartWork.TYPE_PROCESS) {
					iconType = "ico_pworks";
					workContext = ISmartWorks.CONTEXT_PREFIX_PWORK_LIST + work.getId();
					targetContent = "pwork_list.sw";
				} else if (work.getType() == SmartWork.TYPE_INFORMATION) {
					iconType = "ico_iworks";
					workContext = ISmartWorks.CONTEXT_PREFIX_IWORK_LIST + work.getId();
					targetContent = "iwork_list.sw";
				} else if (work.getType() == SmartWork.TYPE_SCHEDULE) {
					iconType = "ico_sworks";
					workContext = ISmartWorks.CONTEXT_PREFIX_SWORK_LIST + work.getId();
					targetContent = "swork_list.sw";
				}
				if (work.getType() != WorkCategory.TYPE_CATEGORY) {
	%>
	<li class="<%=iconType%>"><a
		href="<%=targetContent%>?cid=<%=workContext%>" class="<%=classType%>"><%=work.getName()%></a>
	</li>
	<%
		}
	%>
	<%
		}
		}
	%>
</ul>