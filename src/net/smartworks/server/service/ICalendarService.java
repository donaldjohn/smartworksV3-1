package net.smartworks.server.service;

import net.smartworks.model.calendar.CompanyCalendar;
import net.smartworks.model.calendar.WorkHourPolicy;
import net.smartworks.model.instance.info.EventInstanceInfo;
import net.smartworks.util.LocalDate;

public interface ICalendarService {

	public abstract EventInstanceInfo[] getEventInstanceInfosByWorkSpaceId(String workSpaceId, LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract EventInstanceInfo[] getMyEventInstances(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract EventInstanceInfo[] getMyEventInstances(LocalDate fromDate, int days) throws Exception;

	public abstract CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, LocalDate toDate) throws Exception;

	public abstract CompanyCalendar[] getCompanyCalendars(LocalDate fromDate, int days) throws Exception;

	public abstract CompanyCalendar getCompanyEventBox(LocalDate date) throws Exception;

	public abstract EventInstanceInfo[] getCompanyEventsByDate(LocalDate date, int maxEvents) throws Exception;

	public abstract EventInstanceInfo[] getMyEventsByDate(LocalDate date, int maxEvents) throws Exception;

	public abstract WorkHourPolicy getCompanyWorkHourPolicy() throws Exception;
}