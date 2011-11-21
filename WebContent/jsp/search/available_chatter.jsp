<%@page import="net.smartworks.model.community.info.UserInfo"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.community.*"%>
<%
	String companyId = (String) session.getAttribute("companyId");
	String userId = (String) session.getAttribute("userId");

	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String key = request.getParameter("key");
	UserInfo[] chatters = smartWorks.searchAvailableChatter(companyId, userId, key);
%>

<ul>
	<%
		if(chatters != null){
			for (UserInfo chatter : chatters) {
	%>
	<li><img src="<%=chatter.getMinPicture()%>" border="0"><a
		title="<%=chatter.getDepartment()%>"><%=chatter.getPosition()%> <%=chatter.getName()%></a>
	</li>
	<%
			}
		}
	%>
</ul>
