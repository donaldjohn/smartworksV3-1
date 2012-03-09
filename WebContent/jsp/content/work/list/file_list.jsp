<%@page import="net.smartworks.server.engine.process.xpdl.xpdl2.Category"%>
<%@page import="net.smartworks.model.work.info.FileCategoryInfo"%>
<%@page import="net.smartworks.model.work.FileCategory"%>
<%@page import="net.smartworks.model.community.WorkSpace"%>
<%@page import="net.smartworks.model.filter.info.SearchFilterInfo"%>
<%@page import="net.smartworks.model.work.ProcessWork"%>
<%@page import="net.smartworks.model.work.FormField"%>
<%@page import="net.smartworks.model.work.SmartForm"%>
<%@page import="net.smartworks.model.filter.SearchFilter"%>
<%@page import="net.smartworks.model.community.User"%>
<%@page import="net.smartworks.model.security.EditPolicy"%>
<%@page import="net.smartworks.model.security.WritePolicy"%>
<%@page import="net.smartworks.model.security.AccessPolicy"%>
<%@page import="net.smartworks.model.work.InformationWork"%>
<%@page import="net.smartworks.model.work.SmartWork"%>
<%@page import="net.smartworks.model.work.Work"%>
<%@page import="net.smartworks.util.SmartUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	getIntanceList = function(paramsJson, progressSpan, isGray){
		if(isEmpty(progressSpan))
			progressSpan = $('.js_work_list_title').find('.js_progress_span:first');
		if(isGray)
			smartPop.progressContGray(progressSpan);
		else
			smartPop.progressCont(progressSpan);
		console.log(JSON.stringify(paramsJson));
		var url = "set_instance_list_params.sw";
		$.ajax({
			url : url,
			contentType : 'application/json',
			type : 'POST',
			data : JSON.stringify(paramsJson),
			success : function(data, status, jqXHR) {
				$('#pwork_instance_list_page').html(data);
				smartPop.closeProgress();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				smartPop.closeProgress();
				smartPop.showInfo(smartPop.ERROR, smartMessage.get('pworkListError'));
			}
		});
	};
	
	saveAsSearchFilter = function(filterId){
		var pworkList = $('.js_pwork_list_page');
		var searchFilter = $('.js_search_filter_page');
		var url = "set_pwork_search_filter.sw";
		if(isEmpty(filterId)){
			url = "create_pwork_search_filter.sw";
			searchFilter.find('input[name="txtNewFilterId"]').addClass('required');
		}

		if (!SmartWorks.GridLayout.validate(searchFilter.find('form.js_validation_required'), $('.js_filter_error_message'))) return;

		var paramsJson = {};
		var workId = iworkList.attr('workId');
		var searchFilters = searchFilter.find('form[name="frmSearchFilter"]');
		paramsJson['workId'] = workId;
		if(!isEmpty(filterId))
			paramsJson['filterId'] = filterId;
		if(!isEmpty(searchFilters)){
			var searchFilterArray = new Array();
			for(var i=0; i<searchFilters.length; i++){
				var searchFilter = $(searchFilters[i]);
				if(searchFilter.is(':visible'))
					searchFilterArray.push(searchFilter.find(':visible').serializeObject());
			}
			paramsJson['frmSearchFilters'] = searchFilterArray;
		}
		var progressSpan = searchFilter.find('span.js_progress_span:first');
		smartPop.progressCont(progressSpan);
		$.ajax({
			url : url,
			contentType : 'application/json',
			type : 'POST',
			data : JSON.stringify(paramsJson),
			success : function(data, status, jqXHR) {
				$('.js_search_filter_list_box:first').html(data);
				$('a.js_search_filter_close').click();
				smartPop.closeProgress();
				smartPop.showInfo(smartPop.INFORM, smartMessage.get('setFilterSucceed'));
			},
			error : function(xhr, ajaxOptions, thrownError) {
				smartPop.closeProgress();
				if(xhr.status == httpStatus.InternalServerError){
					var message = smartMessage.get(xhr.responseText);
					if(!isEmpty(message)){
						smartPop.showInfo(smartPop.ERROR, message);
						return;
					}
				}
				smartPop.showInfo(smartPop.ERROR, smartMessage.get('setFilterError'));
			}
		});
	};
	
	saveSearchFilter = function(){
		var searchFilter = $('.js_search_filter_page');
		var filterId = searchFilter.attr('filterId');
		if(isEmpty(filterId)) searchFilter.find('input[name="txtNewFilterId"]').removeClass('required');
		saveAsSearchFilter(filterId);
	};

	selectListParam = function(progressSpan, isGray){
		var pworkList = $('.js_pwork_list_page');
		var forms = pworkList.find('form:visible');
		var paramsJson = {};
		var workId = pworkList.attr('workId');
		paramsJson["href"] = "jsp/content/work/list/pwork_instance_list.jsp?workId=" + workId;
		var searchFilters = pworkList.find('form[name="frmSearchFilter"]');
		for(var i=0; i<forms.length; i++){
			var form = $(forms[i]);
			if(form.attr('name') !== "frmSearchFilter" && !(!isEmpty(searchFilters) && form.attr('name') === "frmSearchInstance")){
				paramsJson[form.attr('name')] = mergeObjects(form.serializeObject(), SmartWorks.GridLayout.serializeObject(form));
			}
		}
		if(!isEmpty(searchFilters)){
			var searchFilterArray = new Array();
			for(var i=0; i<searchFilters.length; i++){
				var searchFilter = $(searchFilters[i]);
				if(searchFilter.is(':visible'))
					searchFilterArray.push(searchFilter.find(':visible').serializeObject());
			}
			paramsJson['frmSearchFilters'] = searchFilterArray;
		}
		if(isEmpty(progressSpan)) grogressSpan = pworkList.find('.js_search_filter').next('span.js_progress_span:first');
		getIntanceList(paramsJson, progressSpan, isGray);		
	};
</script>
<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String cid = request.getParameter("cid");
	String wid = request.getParameter("wid");
	session.setAttribute("cid", cid);
	session.setAttribute("wid", wid);
	
	User cUser = SmartUtil.getCurrentUser();
	WorkSpace workSpace = smartWorks.getWorkSpaceById(wid);
	String workSpaceName = (SmartUtil.isBlankObject(wid)) ? cUser.getCompany() : workSpace.getName();
	int displayType = (SmartUtil.isBlankObject(wid)) ? FileCategory.DISPLAY_ALL : FileCategory.DISPLAY_BY_CATEGORY;

%>
<fmt:setLocale value="<%=cUser.getLocale() %>" scope="request" />
<fmt:setBundle basename="resource.smartworksMessage" scope="request" />

<%
if(!SmartUtil.isBlankObject(wid)){
%>
	<jsp:include page="/jsp/content/upload/select_upload_action.jsp"></jsp:include>
<%
}
%>

<!-- 컨텐츠 레이아웃-->
<div class="section_portlet js_file_list_page" displayType="<%=FileCategory.DISPLAY_BY_CATEGORY%>" workSpaceId="<%=wid%>" categoryId="">
	<div class="portlet_t"><div class="portlet_tl"></div></div>
	<div class="portlet_l" style="display: block;">
		<ul class="portlet_r" style="display: block;">

			<!-- 타이틀 -->
			<div class="body_titl">
				<div class="body_titl_iworks title">
					<div class="title myspace_h"><%=workSpaceName %>
						<span class="bul_space"><fmt:message key="space.title.files"/></span>
					</div>
				</div>
				<!-- 우측 버튼 -->
				<div class="txt_btn">
				</div>
				<div class="solid_line"></div>
			</div>
			<!-- 타이틀 -->

			<div class=" contents_space">  
				<%
				if(displayType!=FileCategory.DISPLAY_ALL){
				%>          
					<!-- Left -->
					<div class="left30 ">
						<table style="min-width:inherit">
							<tbody>
								<tr class="tit_bg">
									<th class="" style="height:1px; padding:0; border-bottom:0"></th>
								</tr>
								<tr>
									<td class="">
										<div class="">
											<!-- 필 터 -->
											<div class="float_left">
											  <select class="js_file_display_by">
											    <option value=<%=FileCategory.DISPLAY_BY_CATEGORY %>><fmt:message key="space.title.by_category"/></option>
											    <option value=<%=FileCategory.DISPLAY_BY_WORK %>><fmt:message key="space.title.by_work"/></option>
											    <option value=<%=FileCategory.DISPLAY_BY_YEAR %>><fmt:message key="space.title.by_year"/></option>
											    <option value=<%=FileCategory.DISPLAY_BY_OWNER %>><fmt:message key="space.title.by_owner"/></option>
											    <option value=<%=FileCategory.DISPLAY_BY_FILE_TYPE %>><fmt:message key="space.title.by_filetype"/></option>
											  </select>
											</div>
											<!-- 필 터 //-->
											<!-- 우측 구분 -->
											<div class="categ_link m0p0">검색공간</div>
											<!-- 우측 구분 //-->
											<!-- 카테고리 -->
											<div class="pop_list_area ">
												<ul class="js_file_categories">
													<jsp:include page="/jsp/content/work/list/categories_by_type.jsp">
														<jsp:param value="<%=displayType%>" name="displayType"/>
														<jsp:param value="<%=wid%>" name="wid"/>
														<jsp:param value="" name="parentId"/>
													</jsp:include>
												</ul>
											</div>
											<!-- 카테고리 //-->
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- Left//-->
					<!-- Right -->	
					<div class="right70">
					<!-- 목록보기 -->
						<!-- 목록보기 타이틀-->
						<div class="list_title_space js_work_list_title margin_t15">
							<div class="title"><fmt:message key="common.title.instance_list" /></div>					
								<div class="titleLineOptions">
									<form name="frmSearchInstance" class="po_left">
										<span class="js_progress_span"></span>
										<div class="srch_wh srch_wsize">
											<input name="txtSearchInstance" class="nav_input" type="text" placeholder="<fmt:message key='search.search_instance' />">
											<button title="<fmt:message key='search.search_instance'/>" onclick="selectListParam($('.js_work_list_title').find('.js_progress_span:first'), false);return false;"></button>
										</div>
									</form>					
								</div>
						</div>
						<!-- 목록보기 타이틀-->
							
						<!-- 목록 테이블 -->
						<div class="list_contents js_file_instance_list">
							<jsp:include page="/jsp/content/work/list/file_instance_list.jsp">
								<jsp:param value="<%=FileCategory.DISPLAY_BY_WORK %>" name="displayType"/>
								<jsp:param value="<%=wid %>" name="workSpaceId"/>
							</jsp:include>
						</div>
						<!-- 목록 테이블 //-->
					</div>
					<!-- 목록보기 -->
				<%
				}else{
				%>
					<!-- Right -->	
					<div>
					<!-- 목록보기 -->
						<!-- 목록보기 타이틀-->
						<div class="list_title_space js_work_list_title margin_t15">
							<div class="title"><fmt:message key="common.title.instance_list" /></div>					
								<div class="titleLineOptions">
									<form name="frmSearchInstance" class="po_left">
										<span class="js_progress_span"></span>
										<div class="srch_wh srch_wsize">
											<input name="txtSearchInstance" class="nav_input" type="text" placeholder="<fmt:message key='search.search_instance' />">
											<button title="<fmt:message key='search.search_instance'/>" onclick="selectListParam($('.js_work_list_title').find('.js_progress_span:first'), false);return false;"></button>
										</div>
									</form>					
								</div>
						</div>
						<!-- 목록보기 타이틀-->
							
						<!-- 목록 테이블 -->
						<div class="list_contents">
							<div id='work_instance_list_page'>
								<jsp:include page="/jsp/content/work/list/file_instance_list.jsp">
									<jsp:param value="<%=FileCategory.DISPLAY_ALL %>" name="displayType"/>
								</jsp:include>
							</div>
						</div>
						<!-- 목록 테이블 //-->
					</div>
					<!-- 목록보기 -->
				<%
				}
				%>
			</div>
		</ul>
	</div>
	<div class="portlet_b" style="display: block;"></div>
</div>
<!-- 컨텐츠 레이아웃//-->
