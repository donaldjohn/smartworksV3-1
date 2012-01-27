<%@page import="java.util.Calendar"%>
<%@page import="net.smartworks.model.calendar.CompanyCalendar"%>
<%@page import="net.smartworks.model.community.User"%>
<%@page import="net.smartworks.util.SmartUtil"%>
<%@page import="net.smartworks.util.LocalDate"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	User cUser = SmartUtil.getCurrentUser();

	User user = (User)session.getAttribute("user");

	LocalDate today = new LocalDate();

	String selectedDayStr = request.getParameter("selectedDay");
	int selectedDay = SmartUtil.isBlankObject(selectedDayStr) ? 6 : Integer.parseInt(selectedDayStr);

	String startDateStr = request.getParameter("startDate");
	LocalDate startDate = SmartUtil.isBlankObject(startDateStr) 
			? new LocalDate(today.getTime()-LocalDate.ONE_DAY*6) : LocalDate.convertLocalSimpleStringToLocalDate(startDateStr);
	LocalDate endDate = new LocalDate(startDate.getTime()+LocalDate.ONE_DAY*6);
	LocalDate weekLaterDate = new LocalDate(endDate.getTime()+LocalDate.ONE_DAY*6);
	
	CompanyCalendar[] calendars = (!SmartUtil.isBlankObject(startDateStr) && startDateStr.equals((String)session.getAttribute("startDate")))
									? (CompanyCalendar[])session.getAttribute("calendars") : smartWorks.getCompanyCalendars(startDate, endDate);
	startDateStr = startDate.toLocalDateSimpleString();
	session.setAttribute("startDate", startDateStr);
	session.setAttribute("calendars", calendars);
	
%>
<!--  다국어 지원을 위해, 로케일 및 다국어 resource bundle 을 설정 한다. -->
<fmt:setLocale value="<%=cUser.getLocale() %>" scope="request" />
<fmt:setBundle basename="resource.smartworksMessage" scope="request" />

<!--탭-->
<div class="tab">

	<!--Prev arrow -->
	<a href="" class="btn_arr_prev2"></a> <a href="" class="btn_arr_prev"></a>
	<!--Prev arrow //-->

	<ul>
		<li><span class="intab"> <a href="#">1월</a> </span></li>
		<li><span class="intab"> <a href="#">2월</a> </span></li>
		<li><span class="intab"> <a href="#">3월</a> </span></li>
		<li><span class="intab"> <a href="#">4월</a> </span></li>
		<li><span class="intab"> <a href="#">5월</a> </span></li>
		<li><span class="intab"> <a href="#">6월</a> </span></li>
		<li><span class="intab"> <a href="#">7월</a> </span></li>
		<li><span class="intab"> <a href="#">8월</a> </span></li>
		<li><span class="intab"> <a href="#">9월</a> </span></li>
		<li><span class="intab"> <a href="#">10월</a> </span></li>
		<li><span class="intab"> <a href="#">11월</a> </span></li>
		<li class="current"><span class="intab"> <a href="#">2011.12월</a>
		</span></li>
	</ul>

	<!--Next arrow -->
	<a href="" class="btn_arr_next"></a> <a href="" class="btn_arr_next2"></a>
	<!--Next arrow //-->

	<div class="option_section">
  		<span class="sel_date_section">2011.01.27<input type="hidden" class="js_space_datepicker" value=""><a href="space_tab_monthly.sw" class="btn_calendar js_space_datepicker_button"></a></span> 
		<select class="js_space_select_scope">
			<option value="space_tab_dayly.sw"><fmt:message key="space.title.tab_dayly"/></option>
			<option value="space_tab_weekly.sw"><fmt:message key="space.title.tab_weekly"/></option>
			<option selected><fmt:message key="space.title.tab_monthly"/></option>
		</select>
	</div>

</div>
<!--탭//-->


<!-- 컨텐츠 레이아웃-->
<div class="section_portlet ">
	<div class="portlet_t">
		<div class="portlet_tl"></div>
	</div>
	<div class="portlet_l" style="display: block;">
		<ul class="portlet_r" style="display: block;">

			<!-- 컨텐츠 -->
			<div class="contents_space">

				<!-- 1주 -->
				<div class="space_section">
					<div class="title">
						12.1 목요일 ~ 12.4 <span class="t_sunday">일요일</span>
					</div>
					<ul>
						<li class="t_nowork">업무가 없습니다</li>
					</ul>
				</div>
				<!-- 1주//-->

				<!-- 2주 -->
				<div class="space_section margin_t10">
					<div class="title">
						12.5 월요일</span> ~ 12.11 <span class="t_sunday">일요일</span>
					</div>
					<ul>
						<li>
							<div class="det_title">
								<div class="noti_pic">
									<img src="../images/pic_size_48.jpg">
								</div>
								<div class="noti_in_m">
									<span class="t_name">Minashin</span><span class="arr">▶</span><span
										class="ico_division_s">마케팅/디자인팀</span>
									<div>메모입니다...메모입니다..</div>
									<div>
										<span class="t_date"> 2011.10.13</span> <a href=""><span
											class="repl_write">댓글달기</span>
										</a>
									</div>
								</div>
							</div></li>

						<li>
							<div class="det_title">
								<div class="noti_pic">
									<img src="../images/pic_size_48.jpg">
								</div>
								<div class="noti_in_m">
									<span class="t_name">Minashin</span><span class="arr">▶</span><span
										class="ico_division_s">마케팅/디자인팀</span>
									<div>
										<strong>이미지이미지이미지이미지</strong>
										<div>이미지 파일에 대한 설명 내용이 있다면 이 곳에 들어갑니다..</div>
										<div class="imag_area">
											<img src="../images/up_image.jpg" />
										</div>
										<div>
											<span class="t_date"> 2011.10.13</span> <a href=""><span
												class="repl_write">댓글달기</span>
											</a>
										</div>

										<!-- 댓글 -->
										<div class="replay_point posit_replay"></div>
										<div class="replay_section">

											<div class="list_replay">
												<ul>
													<li><img class="repl_tinfo"><a href=""><strong>7</strong>개의
															댓글 모두 보기</a>
													</li>
													<li>
														<div class="noti_pic">
															<img src="../images/pic_size_29.jpg" alt="신민아"
																align="bottom" />
														</div>
														<div class="noti_in">
															<span class="t_name">Minashin</span><span class="t_date">
																2011.10.13</span>
															<div>와우~ 멋져요~</div>
														</div></li>
													<li>
														<div class="noti_pic">
															<img src="../images/pic_size_29.jpg" alt="신민아"
																align="bottom" />
														</div>
														<div class="noti_in">
															<span class="t_name">Minashin</span><span class="t_date">
																2011.10.13</span>
															<div>재미있었겠네요~</div>
														</div></li>
													<li>
														<div class="det_title">
															<div class="noti_pic">
																<img src="../images/pic_size_29.jpg" alt="신민아"
																	align="bottom" />
															</div>
															<div class="noti_in">
																<span class="t_name">Minashin</span><span class="t_date">
																	2011.10.13</span>
																<div>가을이 다 지나가부렀네요~~--;</div>
															</div>
														</div></li>
												</ul>
											</div>

											<div class="replay_input">
												<textarea class="up_textarea" rows="1" cols=""
													name="txtaEventContent">댓글을 입력하세요!</textarea>
											</div>

										</div>
										<!-- 댓글 //-->

									</div>
								</div>
							</div></li>
					</ul>
				</div>
				<!-- 2주//-->

				<!-- 3주 -->
				<div class="space_section margin_t10">
					<div class="title_off">
						12.12 월요일 ~ 12.18 <span class="t_sunday">일요일</span>
					</div>
				</div>
				<!-- 3주//-->

				<!-- 4주 -->
				<div class="space_section margin_t10">
					<div class="title_off">
						12.19 월요일 ~ 12.24 <span class="t_sunday">일요일</span>
					</div>
				</div>
				<!-- 4주//-->

				<!-- 5주 -->
				<div class="space_section margin_t10">
					<div class="title_off">
						12.25 월요일 ~ 12.31 <span class="t_sunday">일요일</span>
					</div>
				</div>
				<!-- 5주//-->

			</div>
			<!-- 컨텐츠 //-->

		</ul>
	</div>
	<div class="portlet_b" style="display: block;"></div>
</div>
<!-- 컨텐츠 레이아웃//-->

<script type="text/javascript">
	$.datepicker.setDefaults($.datepicker.regional[currentUser.locale]);
	$('.js_space_datepicker').datepicker(
			{
				defaultDate : new Date(),
				dateFormat : 'yy.mm.dd',
				onSelect : function(date) {
					var selectedDate = new Date(date);
					var today = new Date();
					if (selectedDate > today) {
						smartPop.showInfo(smartPop.WARN, smartMessage
								.get('spaceOverDateSeleted'));
						return false;
					}
					var input = $(this);
					var target = input.parents('.js_user_space_instance_list');
					var url = input.next().attr('href');
					var startDate = new Date(selectedDate.toString());
					startDate.setDate(selectedDate.getDate() - 6);
					console.log('selected=', selectedDate.toDateString(),
							'start=', startDate.toDateString());
					$.ajax({
						url : url,
						data : {
							startDate : startDate.format('yyyy.mm.dd'),
							selectedDay : 6
						},
						success : function(data, status, jqXHR) {
							target.html(data);
						},
						error : function(xhr, ajaxOptions, thrownError) {
						}
					});
				}
			});
</script>
