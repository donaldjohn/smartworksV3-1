
<!-- Name 			: my_favorite_works.jsp				 					 -->
<!-- Description	: 현재사용자의 자주가는 업무들을 보여주는 화면  					 -->
<!-- Author			: Maninsoft, Inc.										 -->
<!-- Created Date	: 2011.9.												 -->

<%@page import="net.smartworks.util.SmartUtil"%>
<%@page import="net.smartworks.model.community.User"%>
<%@page import="net.smartworks.model.work.info.SmartWorkInfo"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ page import="net.smartworks.model.work.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	// 스마트웍스 서비스들을 사용하기위한 핸들러를 가져온다. 현재사용자 정보도 가져온다 
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	User cUser = SmartUtil.getCurrentUser();

	// 서버에서 현재사용자의 자주가는 업무들을 가져온다...
	SmartWorkInfo[] works = smartWorks.getMyFavoriteWorks();
	String iconType = null, workContext = null, targetContent = null;
%>
<fmt:setLocale value="<%=cUser.getLocale() %>" scope="request" />
<fmt:setBundle basename="resource.smartworksMessage" scope="request" />

<ul>
	<%
	if (works != null) {
		for (SmartWorkInfo work : works) {
			if (work.getType() == SmartWork.TYPE_PROCESS) {
				iconType = "ico_pworks";
				workContext = ISmartWorks.CONTEXT_PREFIX_PWORK_LIST + work.getId();
				targetContent = "pwork_list.sw";
			} else if (work.getType() == SmartWork.TYPE_INFORMATION) {
				iconType = "ico_iworks";
				workContext = ISmartWorks.CONTEXT_PREFIX_IWORK_LIST + work.getId();
				targetContent = "iwork_list.sw";//"information_work_list.sw";
			} else if (work.getType() == SmartWork.TYPE_SCHEDULE) {
				iconType = "ico_sworks";
				workContext = ISmartWorks.CONTEXT_PREFIX_SWORK_LIST + work.getId();
				targetContent = "swork_list.sw";//"schedule_work_list.sw";
			}
			%>
			<li class="js_content">
				<a href="<%=targetContent%>?cid=<%=workContext%>" class="js_content" title="<%=work.getFullpathName()%>">
					<span class="<%=iconType%>"></span>
					<span><%=work.getName()%></span>
				</a>
				<div class="checkOption"><input title="<fmt:message key='common.button.delete'/>" class="js_check_favorite_work" workId="<%=work.getId() %>" type="checkbox"></div>
			</li>
		<%
			}
		}
		%>
</ul>