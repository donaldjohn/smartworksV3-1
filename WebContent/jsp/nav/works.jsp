<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<ul class="navi_tit">
	<li class='js_collapse_parent_siblings'>&gt; <fmt:message
			key="nav.works.my_works" />
	</li>
	<li class="nav_srch">
		<div class="srch">
			<input id="" class="input js_auto_complete" type="text"
				title="<fmt:message key='search.search_work'/>"
				placeholder="<fmt:message key='search.search_work'/>"
				href="work.sw" />
			<div class='srch_ico js_srch_x'></div>
		</div>
		<div style="display: none"></div>
	</li>
</ul>

<div class="navi_list js_collapsible">
	<!-- 내부 메뉴 -->
	<div class="categ_link js_nav_tab_work">
		<a href="my_recent_instances.sw" class="current"><fmt:message
				key="nav.works.my_recent_instances" /> </a>
		<a href="my_favorite_works.sw"><fmt:message
				key="nav.works.my_favorite_works" /> </a>
 		<a href="my_all_works.sw"><fmt:message key="nav.works.my_all_works" /> </a>
	</div>
	<div id='my_works'>
		<jsp:include page="my_recent_instances.jsp" />
	</div>
	<!--내부메뉴//-->
</div>
