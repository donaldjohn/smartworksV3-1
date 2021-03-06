/*	
 * $Id: WorkInstanceController.java,v 1.1 2011/10/29 00:34:23 ysjung Exp $
 * created by    : SHIN HYUN SEONG
 * creation-date : 2011. 10. 15.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.smartworks.model.community.info.UserInfo;
import net.smartworks.model.filter.SearchFilter;
import net.smartworks.model.instance.CommentInstance;
import net.smartworks.model.instance.info.EventInstanceInfo;
import net.smartworks.model.instance.info.RequestParams;
import net.smartworks.model.report.Data;
import net.smartworks.model.work.FileCategory;
import net.smartworks.model.work.SmartWork;
import net.smartworks.server.engine.common.util.CommonUtil;
import net.smartworks.server.engine.infowork.domain.model.SwdRecord;
import net.smartworks.service.ISmartWorks;
import net.smartworks.service.impl.SmartWorks;
import net.smartworks.util.LocalDate;
import net.smartworks.util.SmartUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkInstanceController extends ExceptionInterceptor {

	ISmartWorks smartworks;

	@Autowired
	public void setSmartworks(ISmartWorks smartworks) {
		this.smartworks = smartworks;
	}

	@RequestMapping("/iwork_space")
	public ModelAndView iworkSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/iwork_space.jsp", "iwork_space.tiles");
	}

	@RequestMapping("/pwork_space")
	public ModelAndView pworkSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/pwork_space.jsp", "pwork_space.tiles");
	}

	@RequestMapping("/swork_space")
	public ModelAndView sworkSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/swork_space.jsp", "swork_space.tiles");
	}

	@RequestMapping("/board_space")
	public ModelAndView boardSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/board_space.jsp", "board_space.tiles");
	}

	@RequestMapping("/event_space")
	public ModelAndView eventSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/event_space.jsp", "event_space.tiles");
	}

	@RequestMapping("/file_space")
	public ModelAndView fileSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/file_space.jsp", "file_space.tiles");
	}

	@RequestMapping("/image_space")
	public ModelAndView imageSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/image_space.jsp", "image_space.tiles");
	}

	@RequestMapping("/memo_space")
	public ModelAndView memoSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/memo_space.jsp", "memo_space.tiles");
	}

	@RequestMapping("/mail_space")
	public ModelAndView mailSpace(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/mail_space.jsp", "mail_space.tiles");
	}

	@RequestMapping("/new_file")
	public ModelAndView newFile(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/start/new_file.jsp", "");
	}

	@RequestMapping("/new_picture")
	public ModelAndView newPicture(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/start/new_picture.jsp", "");
	}

	@RequestMapping("/new_event")
	public ModelAndView newEvent(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/start/new_event.jsp", "");
	}

	@RequestMapping("/new_memo")
	public ModelAndView newMemo(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/start/new_memo.jsp", "");
	}

	@RequestMapping("/new_board")
	public ModelAndView newBoard(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/start/new_board.jsp", "");
	}

	@RequestMapping("/more_space_sub_instances")
	public ModelAndView moreSpaceSubInstances(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/space/more_space_sub_instances.jsp", "");
	}

	@RequestMapping("/image_instance_list")
	public ModelAndView imageInstanceList(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/list/image_instance_list.jsp", "");
	}
	@RequestMapping("/more_image_instance_list")
	public ModelAndView moreImageInstanceList(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/list/more_image_instance_list.jsp", "");
	}

	@RequestMapping("/categories_by_type")
	public ModelAndView categoriesByType(HttpServletRequest request, HttpServletResponse response) {

		return SmartUtil.returnMnv(request, "jsp/content/work/list/categories_by_type.jsp", "");
	}

	@RequestMapping("/sub_instances_in_instance")
	public ModelAndView subInstancesInInstance(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("subComments", null);
		return SmartUtil.returnMnv(request, "jsp/content/work/list/sub_instances_in_instance.jsp", "");
	}

	@RequestMapping(value = "/set_file_instance_list", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView setIworkSearchFilter(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filterId = smartworks.setIWorkSearchFilter(requestBody, request);
		String workId = (String)requestBody.get("workId");
		ISmartWorks smartworks = (ISmartWorks)SmartUtil.getBean("smartWorks", request);
		ModelAndView mnv = new ModelAndView();
		mnv.addObject(smartworks);
		mnv.setViewName("jsp/content/work/list/file_instance_list.jsp?displayType=" + workId + "&filterId=" + filterId);
		return mnv;
	}

//	@RequestMapping(value = "/refresh_data_fields", method = RequestMethod.POST)
//	@ResponseStatus(HttpStatus.CREATED)
//	public @ResponseBody Map<String, Object> refreshDataFields(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String instanceId = smartworks.refreshDataFields(requestBody);
//		// TO DO : Exception handler
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("href", "iwork_space.sw?cid=" + SmartWorks.CONTEXT_PREFIX_IWORK_SPACE + instanceId + "&wid=" + request.getParameter("selWorkSpace"));
//		return map;
//	}
	
	@RequestMapping(value = "/create_new_iwork", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> createNewIwork(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		String workId = (String)requestBody.get("workId");
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "iwork_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_IWORK_LIST + workId);
		return map;
	}

	@RequestMapping(value = "/upload_new_picture", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> uploadNewPicture(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		// TO DO : Exception handler
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		String workId = (String)requestBody.get("workId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "image_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_IMAGE_LIST + SmartWork.ID_FILE_MANAGEMENT + "&wid=" + workSpaceId);
		return map;
	}

	@RequestMapping(value = "/start_new_pwork", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> startNewPwork(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.startProcessWorkInstance(requestBody, request);
		// TO DO : Exception handler
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		String workId = (String)requestBody.get("workId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "pwork_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_PWORK_LIST + workId);
		return map;
	}

	@RequestMapping(value = "/upload_new_file", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> uploadNewFile(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		// TO DO : Exception handler
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		String workId = (String)requestBody.get("workId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "file_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_FILE_LIST + SmartWork.ID_FILE_MANAGEMENT + "&wid=" + workSpaceId);
		return map;
	}

	@RequestMapping(value = "/create_new_event", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> createNewEvent(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		String workId = (String)requestBody.get("workId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "event_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_EVENT_LIST + SmartWork.ID_EVENT_MANAGEMENT + "&wid=" + workSpaceId);
		return map;
	}

	@RequestMapping(value = "/create_new_memo", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> createNewMemo(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		// TO DO : Exception handler
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		String workId = (String)requestBody.get("workId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "memo_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_MEMO_LIST + SmartWork.ID_MEMO_MANAGEMENT + "&wid=" + workSpaceId);
		return map;
	}

	@RequestMapping(value = "/create_new_board", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> createNewBoard(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		// TO DO : Exception handler
		String workSpaceId = (String)((Map<String, Object>)requestBody.get("frmAccessSpace")).get("selWorkSpace");
		String workId = (String)requestBody.get("workId");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "board_list.sw?cid=" + SmartWorks.CONTEXT_PREFIX_BOARD_LIST + SmartWork.ID_BOARD_MANAGEMENT + "&wid=" + workSpaceId);
		return map;
	}

	@RequestMapping(value = "/set_iwork_instance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody void setIworkInstance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.setInformationWorkInstance(requestBody, request);
	}

	@RequestMapping(value = "/remove_iwork_instance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody void removeIworkInstance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.removeInformationWorkInstance(requestBody, request);
	}

	@RequestMapping(value = "/forward_iwork_instance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody Map<String, Object> forwardIworkInstance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String instanceId = smartworks.setInformationWorkInstance(requestBody, request);
		// TO DO : Exception handler
		return null;
	}

	@RequestMapping(value = "/update_my_profile", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> updateMyProfile(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.setMyProfile(requestBody, request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("href", "my_profile.sw");
		return map;
	}

	@RequestMapping(value = "/set_instance_list_params", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView setInstanceListParams(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestParams requestParams = smartworks.setInstanceListParams(requestBody, request);
		String href = (String)requestBody.get("href");
		ISmartWorks smartworks = (ISmartWorks)SmartUtil.getBean("smartWorks", request);
		ModelAndView mnv = new ModelAndView();
		mnv.addObject(smartworks);
		mnv.addObject("requestParams", requestParams);
		mnv.setViewName(href);
		return mnv;

	}

	@RequestMapping(value = "/set_file_list_params", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView setFileListParams(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RequestParams requestParams = smartworks.setInstanceListParams(requestBody, request);
		int displayType = Integer.parseInt((String)requestBody.get("displayType"));
		String categoryId = (String)requestBody.get("categoryId");
		SearchFilter searchFilter = null;
		switch(displayType){
		case FileCategory.DISPLAY_BY_CATEGORY:
			searchFilter = SearchFilter.getByFileCategoryIdFilter(categoryId);
			break;
		case FileCategory.DISPLAY_BY_WORK:
			searchFilter = SearchFilter.getByWorkIdFilter(categoryId);
			break;
		case FileCategory.DISPLAY_BY_YEAR:
			searchFilter = SearchFilter.getByCreatedDateFilter(categoryId);
			break;
		case FileCategory.DISPLAY_BY_OWNER:
			searchFilter = SearchFilter.getByOwnerFilter(categoryId);
			break;
		case FileCategory.DISPLAY_BY_FILE_TYPE:
			searchFilter = SearchFilter.getByFileTypeFilter(categoryId);
			break;
		}
		requestParams.setSearchFilter(searchFilter);
		String href = (String)requestBody.get("href");
		ISmartWorks smartworks = (ISmartWorks)SmartUtil.getBean("smartWorks", request);
		ModelAndView mnv = new ModelAndView();
		mnv.addObject(smartworks);
		mnv.addObject("requestParams", requestParams);
		mnv.setViewName(href);
		return mnv;

	}

	@RequestMapping(value = "/add_comment_on_work", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> addCommentOnWork(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.addCommentOnWork(requestBody, request);
		return null;
	}

	@RequestMapping(value = "/update_comment_on_work", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> updateCommentOnWork(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.updateCommentOnWork(requestBody, request);
		return null;
	}

	@RequestMapping(value = "/remove_comment_on_work", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> removeCommentOnWork(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.removeCommentOnWork(requestBody, request);
		return null;
	}

	@RequestMapping(value = "/add_comment_on_instance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> addCommentOnInstance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.addCommentOnInstance(requestBody, request);
		return null;
	}

	@RequestMapping(value = "/update_comment_on_instance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> updateCommentOnInstance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.updateCommentOnInstance(requestBody, request);
		return null;
	}

	@RequestMapping(value = "/remove_comment_on_instance", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> removeCommentOnInstance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smartworks.removeCommentOnInstance(requestBody, request);
		return null;
	}

	@RequestMapping(value = "/get_events_by_dates", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Map<String, Object> getEventsByDates(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String workSpaceId = request.getParameter("workSpaceId");
		LocalDate fromDate = LocalDate.convertLocalDateStringToLocalDate(request.getParameter("fromDate"));
		LocalDate toDate = LocalDate.convertLocalDateStringToLocalDate(request.getParameter("toDate"));
		
		EventInstanceInfo[] eventInstances = smartworks.getEventInstanceList(workSpaceId, fromDate, toDate);
		
		class EventInfo{
			String id;
			String name;
			String start;
			String end;
			String ownerId;
			String ownerName;
			String ownerPicture;
			String workSpaceId;
			
			public String getId() {
				return id;
			}
			public String getName() {
				return name;
			}
			public String getStart() {
				return start;
			}
			public String getEnd() {
				return end;
			}
			public String getOwnerId() {
				return ownerId;
			}
			public String getOwnerName() {
				return ownerName;
			}
			public String getOwnerPicture() {
				return ownerPicture;
			}
			public String getWorkSpaceId() {
				return workSpaceId;
			}
			public String getWorkId() {
				return SmartWork.ID_EVENT_MANAGEMENT;
			}
			public EventInfo(){
			}
		}

		EventInfo[] events = new EventInfo[eventInstances.length];
		for(int i=0; i<eventInstances.length; i++){
			EventInstanceInfo eventInstance = eventInstances[i];
			EventInfo event = new EventInfo();
			event.id = eventInstance.getId();
			event.name = eventInstance.getSubject();
			event.start = eventInstance.getStart().toLocalDateTimeSimpleString();
			event.end = eventInstance.getEnd() != null ? eventInstance.getEnd().toLocalDateTimeSimpleString() : null;
			event.ownerId = eventInstance.getOwner().getId();
			event.ownerName = eventInstance.getOwner().getLongName();
			event.ownerPicture = eventInstance.getOwner().getMinPicture();
			event.workSpaceId = eventInstance.getWorkSpace().getId();
			events[i] = event;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("events", events);
		return map;
	}	
}
