package net.smartworks.server.service;

import javax.servlet.http.HttpServletRequest;

import net.smartworks.model.instance.BoardInstance;
import net.smartworks.model.instance.CommentInstance;
import net.smartworks.model.instance.Instance;
import net.smartworks.model.instance.WorkInstance;
import net.smartworks.model.instance.info.BoardInstanceInfo;
import net.smartworks.model.instance.info.InstanceInfo;
import net.smartworks.model.instance.info.InstanceInfoList;
import net.smartworks.model.instance.info.RequestParams;
import net.smartworks.util.LocalDate;

public interface IInstanceService {

	public BoardInstanceInfo[] getBoardInstances(LocalDate fromDate, LocalDate toDate) throws Exception;

	public InstanceInfo[] getMyRecentInstances() throws Exception;

	public BoardInstanceInfo[] getBoardInstances(LocalDate fromDate, int days) throws Exception;

	public Instance getInstanceById(String instanceId) throws Exception;

	public InstanceInfo[] getMyRunningInstances() throws Exception;

	public InstanceInfo[] searchMyRunningInstance(String key) throws Exception;

	public CommentInstance[] getRecentCommentsInWorkManual(String workId, int length) throws Exception;

	public InstanceInfoList getIWorkInstanceList(String workId, RequestParams params) throws Exception;

	public InstanceInfoList getPWorkInstanceList(String workId, RequestParams params) throws Exception;

	public WorkInstance getWorkInstanceById(String instanceId) throws Exception;

	public String setInformationWorkInstance(HttpServletRequest request) throws Exception;

	public String startProcessWorkInstance(HttpServletRequest request) throws Exception;

	public String setFileInstance(HttpServletRequest request) throws Exception;

	public String setEventInstance(HttpServletRequest request) throws Exception;

	public String setMemoInstance(HttpServletRequest request) throws Exception;

	public String setBoardInstance(HttpServletRequest request) throws Exception;

}
