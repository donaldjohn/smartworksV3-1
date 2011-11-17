<%@page import="net.smartworks.util.SmartUtil"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="net.smartworks.service.ISmartWorks"%>
<%
	ISmartWorks smartWorks = (ISmartWorks) request.getAttribute("smartWorks");
	String cid = request.getParameter("cid");
	if (cid == null)
		session.setAttribute("cid", ISmartWorks.CONTEXT_HOME);
	else
		session.setAttribute("cid", cid);
	String wid = request.getParameter("wid");
	if (wid == null)
		session.setAttribute("wid", SmartUtil.getCurrentUser().getId());
	else
		session.setAttribute("wid", wid);
%>

<div id="content">

<!-- 컨텐츠 레이아웃-->
<div class="up_wrap">
    	<div class="form_wrap up up_padding">
            
            <!-- 타이틀 -->
            	<div class="form_title">
                	<div class="titic_iworks title_noico">신제품 기획 TFT > TFT 회의록 </div> 
                	<div class="solid_line"></div>
                </div>
            <!-- 타이틀 -->
            
<!-- 상세보기 컨텐츠 -->
<div class="contents_space">
            	
            <!-- 2dep- 타이틀 -->
            <div class="det_title">
                <div class="noti_pic"><img src="../images/pic_size_29.jpg" alt="신민아" align="bottom"/></div>
                <div class="noti_in">
                    <span class="t_name">Minashin</span><span class="arr">▶</span><span class="ico_division_s">마케팅/디자인팀</span><span class="t_date"> 2011.10.13</span>
                    <span class="noti_tit">하반기 해외 B2B마케팅 성공사례 세미나하반기 해외 B2B마케팅 성공사례 </span>
                </div>
            </div>
            
            <div class="txt_btn">
                <div class="po_right"><a href=""><img src="../images/btn_referw.gif" alt="참조자 지정" /></a></div>
                <div class="po_right"><a href=""><img src="../images/btn_approvep.gif" alt="전자결재" /></a></div>
                <div class="po_right"><a href=""><img src="../images/btn_referw.gif" alt="참조자 지정" /></a></div>
                <div class="po_right"><a href=""><img src="../images/btn_approvep.gif" alt="전자결재" /></a></div>
            </div>
            
            <div class="txt_btn">
                <div class="po_right"><a href="">주소복사</a></div>
            </div>
            <!-- 2dep- 타이틀 //-->
            
            <!-- 2dep- 컨텐츠 --> 
            <div class="up det_contents">      
                    <table>
                        <colgroup>
                            <col class="item">
                            <col class="field">
                            <col class="item">
                            <col class="field">
                        </colgroup>
                    <tbody>
                        <tr>
                            <td>컨텐츠 내용</td>
                        </tr>
                    </tbody>
                    </table> 
          </div>
          <!-- 2dep- 컨텐츠 //--> 
</div>

<!-- 버튼 영역 -->
<div class="glo_btn_space">

<div class="txt_btn info_repl">
    <div class="po_left"><a href="">참조업무(20)</a></div>
    <div class="po_left"><a href="">수정이력(16)</a></div>
    <div class="po_left">최종수정: <a href=""><img src="../images/pic_size_24.gif" alt="size24" width="24" height="24" /> 선임 신현성</a> <span class="t_date"> 15:57</span></div>
</div>     
<!-- 수정, 삭제버튼 -->
    <div class="float_right">
        <span class="btn_gray">
            <span class="Btn01Start"></span>
            <span class="Btn01Center">수정하기</span>
            <span class="Btn01End"></span>
            </span>

         <span class="btn_gray space_l5">
            <span class="Btn01Start"></span>
            <span class="Btn01Center">삭제하기</span>
            <span class="Btn01End"></span></span>
     </div>
<!-- 수정, 삭제버튼 //-->    
  
</div>
<!-- 버튼 영역 //-->     

</div>  
<!-- 컨텐츠 레이아웃//-->







<!--상세보기 up&list -->
<div class="contents_upspace">

	<!-- 올리기 -->
	<div id="upload">
    <div class="up_works"><a class="" href="">새업무</a></div>
    <div class="up_file"><a class="" href="">파일</a></div>
    <div class="up_event"><a class="" href="">이벤트</a></div>
    <div class="up_memo"><a class="" href="">메모</a></div>
    <div class="up_board"><a class="" href="">공지</a></div>
  </div>
  
  <div class="up_wrap" style="margin:0 3px;">
	<div class="up_point posit_works"></div>
    
    <!-- 폼- 디폴트-->
    	<div class="start_worksinput">
		<div class="input_size srch">
			<input class="js_auto_complete" type="text" href="work_name.sw"
				placeholder="<fmt:message key='common.upload.message.work'/>">
			<div class="srch_ico js_srch_x"></div>

		</div>

		<div class="btn_gray btn_right" id="all_work_btn" style="display: none">
			<a href=""> <span class="Btn01Start"></span> <span
				class="Btn01Center"><fmt:message
						key="common.upload.button.all_works" /> </span> <span class="Btn01End"></span>
			</a>
		</div>


		<!--검색 자동완성어 리스트-->
		<div class="srch_list" id="upload_work_list" style="display: none">
		</div>
		<!--검색 자동완성어 리스트//-->

	</div>
    <!-- 폼- 디폴트//-->   
         
   </div>
  <!-- 올리기 //-->
  
  <!-- 댓글 목록 -->
  <div class="section_portlet">
    <div class="portlet_t">
      <div class="portlet_tl"></div>
    </div>
    <div class="portlet_l" style="display: block;">
    <ul class="portlet_r" style="display: block;">
    
<!-- 목록시작 -->
<div class="replay">
    <ul>
    <li>
        <div class="det_title">
            <div class="noti_pic"><img src="../images/pic_size_29.jpg" alt="신민아" align="bottom"/></div>
            <div class="noti_in">
                <span class="t_name">Minashin</span><span class="arr">▶</span><span class="ico_division_s">마케팅/디자인팀</span><span class="t_date"> 2011.10.13</span>
                <div>회의록 내용 중 빠진 부분이나 수정할 사항이 있으시면 참석자 누구든 수정해주시기 바랍니다^^</div>
            </div>
        </div>
    </li>
    <li>
        <div class="det_title">
            <div class="noti_pic"><img src="../images/pic_size_29.jpg" alt="신민아" align="bottom"/></div>
            <div class="noti_in">
                <span class="t_name">Minashin</span><span class="arr">▶</span><span class="ico_division_s">마케팅/디자인팀</span><span class="t_date"> 2011.10.13</span>
                <div>회의록 내용 중 빠진 부분이나 수정할 사항이 있으시면 참석자 누구든 수정해주시기 바랍니다^^</div>
            </div>
        </div>
    </li>
    <li>
        <div class="det_title">
            <div class="noti_pic"><img src="../images/pic_size_29.jpg" alt="신민아" align="bottom"/></div>
            <div class="noti_in">
                <span class="t_name">Minashin</span><span class="arr">▶</span><span class="ico_division_s">마케팅/디자인팀</span><span class="t_date"> 2011.10.13</span>
                <div>회의록 내용 중 빠진 부분이나 수정할 사항이 있으시면 참석자 누구든 수정해주시기 바랍니다^^</div>
            </div>
        </div>
    </li>
    </ul>
</div>
<!-- 목록 끝 //-->

    </ul>
    </div>
    <div class="portlet_b" style="display: block;"></div>
    </div>
  <!-- 댓글 목록 //-->
  
</div>
<!-- 상세보기 up&list //-->

<!-- 목록 버튼 -->
<div class="" style=" text-align:center">
<div class="btn_gray" >
    <a href=""> <span class="Btn01Start"></span> <span
        class="Btn01Center">목 록</span> <span class="Btn01End"></span>
    </a>
</div>
</div>
<!-- 목록 버튼//-->


</div>
