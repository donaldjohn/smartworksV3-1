<%@page import="net.smartworks.util.SmartUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String cid = request.getParameter("cid");
	if (SmartUtil.isBlankObject(cid))
		session.setAttribute("cid", ISmartWorks.CONTEXT_HOME);
	else
		session.setAttribute("cid", cid);
	String wid = request.getParameter("wid");
	if (SmartUtil.isBlankObject(wid))
		session.setAttribute("wid", SmartUtil.getCurrentUser().getId());
	else
		session.setAttribute("wid", wid);
%>

Content for File Space ~!!!! contextId =
<%=session.getAttribute("cid")%>
workSpaceId =
<%=session.getAttribute("wid")%>