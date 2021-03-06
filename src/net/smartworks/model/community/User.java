package net.smartworks.model.community;

import java.util.TimeZone;

import net.smartworks.model.community.info.DepartmentInfo;
import net.smartworks.model.community.info.UserInfo;
import net.smartworks.util.LocalDate;
import net.smartworks.util.LocaleInfo;
import net.smartworks.util.SmartUtil;

public class User extends WorkSpace {

	public static final int USER_LEVEL_EXTERNAL_USER = 0;
	public static final int USER_LEVEL_INTERNAL_USER = 1;
	public static final int USER_LEVEL_AMINISTRATOR = 2;
	public static final int USER_LEVEL_SYSMANAGER = 3;
	public static final int USER_LEVEL_DEFAULT = USER_LEVEL_INTERNAL_USER;

	public static final int USER_ROLE_LEADER = 1;
	public static final int USER_ROLE_MEMBER = 2;
	public static final String  NO_USER_PICTURE  = "no_user_picture";

	public static final String USER_ID_SYSTEM = "system@smartworks.net";
	public static final String USER_ID_NONE_EXISTING = "noneexisting@smartworks.net";

	private String nickName;
	private int	userLevel = USER_LEVEL_DEFAULT;
	private int role = USER_ROLE_MEMBER;
	private String position;
	private String locale = LocaleInfo.LOCALE_DEFAULT;
	private String timeZone = LocalDate.TIMEZONE_SEOUL; 
	private String departmentId;
	private String department;
	private String employeeId;
	private String password;
	private String phoneNo;
	private String cellPhoneNo;
	private String company;
	private String companyId;

	public String getNickName() {
		if(SmartUtil.isBlankObject(nickName)) return getName();
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCellPhoneNo() {
		return cellPhoneNo;
	}
	public void setCellPhoneNo(String cellPhoneNo) {
		this.cellPhoneNo = cellPhoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getLongName() {
		if(!position.equals("")) return position + " " + super.getName();
		else return super.getName();
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public User(){
		super();
	}
	public User(String id, String name){
		super(id, name);
	}
	
	public String getEmailAddressShown(){
		return this.getLongName() + " <" + super.getId() + ">";
	}
	
	public int getTimeOffsetInHour(){
		if(timeZone==null) return 0;
		return TimeZone.getTimeZone(timeZone).getRawOffset()/LocalDate.ONE_HOUR;
	}
	public UserInfo getUserInfo(){
		UserInfo userInfo = new UserInfo(getId(), getName());
		userInfo.setBigPictureName(getBigPictureName());
		userInfo.setSmallPictureName(getSmallPictureName());
		userInfo.setCellPhoneNo(getCellPhoneNo());
		userInfo.setDepartment(new DepartmentInfo(getDepartmentId(), getDepartment()));
		userInfo.setPhoneNo(getPhoneNo());
		userInfo.setPosition(getPosition());
		userInfo.setRole(getRole());
		return userInfo;
	}
}
