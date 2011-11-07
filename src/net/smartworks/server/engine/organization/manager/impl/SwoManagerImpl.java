/*	
 * $Id$
 * created by    : maninsoft
 * creation-date : 2011. 11. 2.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.server.engine.organization.manager.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.smartworks.server.engine.common.manager.AbstractManager;
import net.smartworks.server.engine.common.model.SmartServerConstant;
import net.smartworks.server.engine.common.util.CommonUtil;
import net.smartworks.server.engine.common.util.id.IDCreator;
import net.smartworks.server.engine.domain.model.SwdObject;
import net.smartworks.server.engine.organization.exception.SwoException;
import net.smartworks.server.engine.organization.manager.ISwoManager;
import net.smartworks.server.engine.organization.model.SwoAuthority;
import net.smartworks.server.engine.organization.model.SwoAuthorityCond;
import net.smartworks.server.engine.organization.model.SwoCompany;
import net.smartworks.server.engine.organization.model.SwoCompanyCond;
import net.smartworks.server.engine.organization.model.SwoConfig;
import net.smartworks.server.engine.organization.model.SwoConfigCond;
import net.smartworks.server.engine.organization.model.SwoContact;
import net.smartworks.server.engine.organization.model.SwoContactCond;
import net.smartworks.server.engine.organization.model.SwoDepartment;
import net.smartworks.server.engine.organization.model.SwoDepartmentCond;
import net.smartworks.server.engine.organization.model.SwoTeam;
import net.smartworks.server.engine.organization.model.SwoTeamCond;
import net.smartworks.server.engine.organization.model.SwoUser;
import net.smartworks.server.engine.organization.model.SwoUserCond;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

@Service
public class SwoManagerImpl extends AbstractManager implements ISwoManager {

	public SwoManagerImpl() {
		super();
		if (logger.isInfoEnabled())
			logger.info(this.getClass().getName() + " created");
	}
	
	protected void fill(String user, SwdObject obj) throws Exception {
		if (obj == null)
			return;
		Date date = new Date();
		obj.setModificationUser(user);
		obj.setModificationDate(date);
		if (obj.getCreationDate() == null) {
			obj.setCreationUser(user);
			obj.setCreationDate(date);
		}
	}

	@Override
	public SwoContact getContact(String userId, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoContact obj = (SwoContact)this.get(SwoContact.class, id);
				return obj;
			} else {
				SwoContactCond cond = new SwoContactCond();
				cond.setId(id);
				SwoContact[] objs = this.getContacts(userId, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoContact getContact(String userId, SwoContactCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoContact[] contacts = getContacts(userId, cond, level);
		if (CommonUtil.isEmpty(contacts))
			return null;
		try {
			if (contacts.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return contacts[0];
	}
	@Override
	public void setContact(String userId, SwoContact obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(userId, obj);
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoContact set");
				buf.append(" name=:name, companyId=:companyId, deptId=:deptId, position=:position,");
				buf.append(" email=:email, telephone=:telephone, domainId=:domainId,");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser, modificationUser=:modificationUser,");
				buf.append(" modificationDate=:modificationDate where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoContact.A_NAME, obj.getName());
				query.setString(SwoContact.A_COMPANYID, obj.getCompanyId());
				query.setString(SwoContact.A_DEPTID, obj.getDeptId());
				query.setString(SwoContact.A_POSITION, obj.getPosition());
				query.setString(SwoContact.A_EMAIL, obj.getEmail());
				query.setString(SwoContact.A_TELEPHONE, obj.getTelephone());
				query.setString(SwoContact.A_DOMAINID, obj.getDomainId());
				query.setTimestamp(SwoContact.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoContact.A_CREATIONUSER, obj.getCreationUser());
				query.setString(SwoContact.A_MODIFICATIONUSER, obj.getModificationUser());
				query.setTimestamp(SwoContact.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(SwdObject.A_ID, obj.getId());
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void createContact(String userId, SwoContact obj) throws SwoException {
		try {
			fill(userId, obj);
			create(obj);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeContact(String userId, String id) throws SwoException {
		try {
			remove(SwoContact.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeContact(String userId, SwoContactCond cond) throws SwoException {
		SwoContact obj = getContact(userId, cond, null);
		if (obj == null)
			return;
		removeContact(userId, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoContactCond cond) throws Exception {
		String id = null;
		String name = null;
		String companyId = null;
		String deptId = null;
		String position = null;
		String email = null;
		String telephone = null;
		String domainId = null;
		String creationUser = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
		
		if (cond != null) {
			id = cond.getId();
			name = cond.getName();
			companyId = cond.getCompanyId();
			deptId = cond.getDeptId();
			position = cond.getPosition();
			email = cond.getEmail();
			telephone = cond.getTelephone();
			domainId = cond.getDomainId();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
		}
		buf.append(" from SwoContact obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (companyId != null)
				buf.append(" and obj.companyId = :companyId");
			if (deptId != null)
				buf.append(" and obj.deptId = :deptId");
			if (position != null)
				buf.append(" and obj.position = :position");
			if (email != null)
				buf.append(" and obj.email = :email");
			if (telephone != null)
				buf.append(" and obj.telephone = :telephone");
			if (domainId != null)
				buf.append(" and obj.domainId = :domainId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (name != null)
				query.setString("name", name);
			if (companyId != null)
				query.setString("companyId", companyId);
			if (deptId != null)
				query.setString("deptId", deptId);
			if (position != null)
				query.setString("position", position);
			if (email != null)
				query.setString("email", email);
			if (telephone != null)
				query.setString("telephone", telephone);
			if (domainId != null)
				query.setString("domainId", domainId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
		}
		return query;
	}
	@Override
	public long getContactSize(String userId, SwoContactCond cond) throws SwoException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List<?> list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoContact[] getContacts(String userId, SwoContactCond cond, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.id, obj.name, obj.companyId, obj.deptId, obj.position,");
				buf.append(" obj.email, obj.telephone, obj.domainId,");
				buf.append(" obj.creationUser, obj.creationDate, obj.modificationUser,");
				buf.append(" obj.modificationDate");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoContact obj = new SwoContact();
					int j = 0;
					obj.setId((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setCompanyId((String)fields[j++]);
					obj.setDeptId(((String)fields[j++]));
					obj.setPosition(((String)fields[j++]));
					obj.setEmail(((String)fields[j++]));
					obj.setTelephone(((String)fields[j++]));
					obj.setDomainId(((String)fields[j++]));
					obj.setCreationUser(((String)fields[j++]));
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoContact[] objs = new SwoContact[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}

	@Override
	public SwoCompany getCompany(String userId, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoCompany obj = (SwoCompany)this.get(SwoCompany.class, id);
				return obj;
			} else {
				SwoCompanyCond cond = new SwoCompanyCond();
				cond.setId(id);
				SwoCompany[] objs = this.getCompanys(userId, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoCompany getCompany(String userId, SwoCompanyCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoCompany[] companys = getCompanys(userId, cond, level);
		if (CommonUtil.isEmpty(companys))
			return null;
		try {
			if (companys.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return companys[0];
	}
	@Override
	public void setCompany(String userId, SwoCompany obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(userId, obj);
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoCompany set");
				buf.append(" name=:name, address=:address, domainId=:domainId,");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser, modificationUser=:modificationUser,");
				buf.append(" modificationDate=:modificationDate where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoCompany.A_NAME, obj.getName());
				query.setString(SwoCompany.A_ADDRESS, obj.getAddress());
				query.setString(SwoCompany.A_DOMAINID, obj.getDomainId());
				query.setTimestamp(SwoCompany.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoCompany.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(SwoCompany.A_MODIFICATIONUSER, obj.getModificationDate());
				query.setTimestamp(SwoCompany.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(SwdObject.A_ID, obj.getId());
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void createCompany(String userId, SwoCompany obj) throws SwoException {
		try {
			fill(userId, obj);
			create(obj);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeCompany(String userId, String id) throws SwoException {
		try {
			remove(SwoCompany.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeCompany(String userId, SwoCompanyCond cond) throws SwoException {
		SwoCompany obj = getCompany(userId, cond, null);
		if (obj == null)
			return;
		removeCompany(userId, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoCompanyCond cond) throws Exception {
		String id = null;
		String name = null;
		String address = null;
		String domainId = null;
		String creationUser = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
				
		if (cond != null) {
			id = cond.getId();
			name = cond.getName();
			address = cond.getAddress();
			domainId = cond.getDomainId();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
		}
		buf.append(" from SwoCompany obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (address != null)
				buf.append(" and obj.address = :address");
			if (domainId != null)
				buf.append(" and obj.domainId = :domainId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (name != null)
				query.setString("name", name);
			if (address != null)
				query.setString("address", address);
			if (domainId != null)
				query.setString("domainId", domainId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
		}
		return query;
	}
	@Override
	public long getCompanySize(String userId, SwoCompanyCond cond) throws SwoException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoCompany[] getCompanys(String userId, SwoCompanyCond cond, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.id, obj.name, obj.address, obj.domainId,");
				buf.append(" obj.creationUser, obj.creationDate, obj.modificationUser,");
				buf.append(" obj.modificationDate");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoCompany obj = new SwoCompany();
					int j = 0;
					obj.setId((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setAddress((String)fields[j++]);
					obj.setDomainId(((String)fields[j++]));
					obj.setCreationUser(((String)fields[j++]));
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoCompany[] objs = new SwoCompany[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoAuthority getAuthority(String userId, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoAuthority obj = (SwoAuthority)this.get(SwoAuthority.class, id);
				return obj;
			} else {
				SwoAuthorityCond cond = new SwoAuthorityCond();
				cond.setId(id);
				SwoAuthority[] objs = this.getAuthoritys(userId, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoAuthority getAuthority(String userId, SwoAuthorityCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoAuthority[] authoritys = getAuthoritys(userId, cond, level);
		if (CommonUtil.isEmpty(authoritys))
			return null;
		try {
			if (authoritys.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return authoritys[0];
	}
	@Override
	public void setAuthority(String userId, SwoAuthority obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(userId, obj);
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoAuthority set");
				buf.append(" companyId=:companyId, name=:name, description=:description, domainId=:domainId,");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser, modificationUser=:modificationUser,");
				buf.append(" modificationDate=:modificationDate where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoAuthority.A_COMPANYID, obj.getCompanyId());
				query.setString(SwoAuthority.A_NAME, obj.getName());
				query.setString(SwoAuthority.A_DESCRIPTION, obj.getDescription());
				query.setString(SwoAuthority.A_DOMAINID, obj.getDomainId());
				query.setTimestamp(SwoAuthority.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoAuthority.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(SwoAuthority.A_MODIFICATIONUSER, obj.getModificationDate());
				query.setTimestamp(SwoAuthority.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(SwoAuthority.A_ID, obj.getId());
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	
	@Override
	public void createAuthority(String userId, SwoAuthority obj) throws SwoException {
		try {
			fill(userId, obj);
			create(obj);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeAuthority(String userId, String id) throws SwoException {
		try {
			remove(SwoAuthority.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeAuthority(String userId, SwoAuthorityCond cond) throws SwoException {
		SwoAuthority obj = getAuthority(userId, cond, null);
		if (obj == null)
			return;
		removeAuthority(userId, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoAuthorityCond cond) throws Exception {
		String id = null;
		String companyId = null;
		String name = null;
		String description = null;
		String domainId = null;
		String creationUser = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
				
		if (cond != null) {
			id = cond.getId();
			companyId = cond.getCompanyId();
			name = cond.getName();
			description = cond.getDescription();
			domainId = cond.getDomainId();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
		}
		buf.append(" from SwoAuthority obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (companyId != null)
				buf.append(" and obj.companyId = :companyId");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (description != null)
				buf.append(" and obj.description = :description");
			if (domainId != null)
				buf.append(" and obj.domainId = :domainId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (companyId != null)
				query.setString("companyId", companyId);
			if (name != null)
				query.setString("name", name);
			if (description != null)
				query.setString("description", description);
			if (domainId != null)
				query.setString("domainId", domainId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
		}
		return query;
	}
	@Override
	public long getAuthoritySize(String userId, SwoAuthorityCond cond) throws SwoException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoAuthority[] getAuthoritys(String userId, SwoAuthorityCond cond, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.id, obj.companyId, obj.name, obj.description, obj.domainId,");
				buf.append(" obj.creationUser, obj.creationDate, obj.modificationUser,");
				buf.append(" obj.modificationDate");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoAuthority obj = new SwoAuthority();
					int j = 0;
					obj.setId((String)fields[j++]);
					obj.setCompanyId((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setDescription((String)fields[j++]);
					obj.setDomainId(((String)fields[j++]));
					obj.setCreationUser(((String)fields[j++]));
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoAuthority[] objs = new SwoAuthority[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	
	@Override
	public SwoDepartment getDepartment(String userId, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoDepartment obj = (SwoDepartment)this.get(SwoDepartment.class, id);
				return obj;
			} else {
				SwoDepartmentCond cond = new SwoDepartmentCond();
				cond.setId(id);
				SwoDepartment[] objs = this.getDepartments(userId, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoDepartment getDepartment(String userId, SwoDepartmentCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoDepartment[] departments = getDepartments(userId, cond, level);
		if (CommonUtil.isEmpty(departments))
			return null;
		try {
			if (departments.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return departments[0];
	}
	@Override
	public void setDepartment(String userId, SwoDepartment obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(userId, obj);
				if(obj.getId() == null)
					obj.setId(IDCreator.createId(SmartServerConstant.DEPT_ABBR));
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoDepartment set");
				buf.append(" companyId=:companyId, parentId=:parentId, type=:type, name=:name, description=:description,");
				buf.append(" domainId=:domainId,");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser,");
				buf.append(" modificationUser=:modificationUser, modificationDate=:modificationDate");
				buf.append(" where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoDepartment.A_COMPANYID, obj.getCompanyId());
				query.setString(SwoDepartment.A_PARENTID, obj.getParentId());
				query.setString(SwoDepartment.A_TYPE, obj.getType());
				query.setString(SwoDepartment.A_NAME, obj.getName());
				query.setString(SwoDepartment.A_DESCRIPTION, obj.getDescription());
				query.setString(SwoDepartment.A_DOMAINID, obj.getDomainId());
				query.setTimestamp(SwoDepartment.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoDepartment.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(SwoDepartment.A_MODIFICATIONUSER, obj.getModificationDate());
				query.setTimestamp(SwoDepartment.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(SwoDepartment.A_ID, obj.getId());
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void createDepartment(String userId, SwoDepartment obj) throws SwoException {
		try {
			fill(userId, obj);
			if(obj.getId() == null)
				obj.setId(IDCreator.createId(SmartServerConstant.DEPT_ABBR));
			create(obj);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeDepartment(String userId, String id) throws SwoException {
		try {
			remove(SwoDepartment.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeDepartment(String userId, SwoDepartmentCond cond) throws SwoException {
		SwoDepartment obj = getDepartment(userId, cond, null);
		if (obj == null)
			return;
		removeDepartment(userId, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoDepartmentCond cond) throws Exception {
		String id = null;
		String companyId = null;
		String parentId = null;
		boolean isParentNull = false;
		String type = null;
		String name = null;
		String description = null;
		String domainId = null;
		String creationUser = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
				
		if (cond != null) {
			id = cond.getId();
			companyId = cond.getCompanyId();
			parentId = cond.getParentId();
			isParentNull = cond.isParentNull();
			type = cond.getType();
			name = cond.getName();
			description = cond.getDescription();
			domainId = cond.getDomainId();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
		}
		buf.append(" from SwoDepartment obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (companyId != null)
				buf.append(" and obj.companyId = :companyId");
			if (parentId != null)
				buf.append(" and obj.parentId = :parentId");
			if (isParentNull)
				buf.append(" and obj.parentId is null");
			if (type != null)
				buf.append(" and obj.type = :type");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (description != null)
				buf.append(" and obj.description = :description");
			if (domainId != null)
				buf.append(" and obj.domainId = :domainId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (companyId != null)
				query.setString("companyId", companyId);
			if (parentId != null)
				query.setString("parentId", parentId);
			if (type != null)
				query.setString("type", type);
			if (name != null)
				query.setString("name", name);
			if (description != null)
				query.setString("description", description);
			if (domainId != null)
				query.setString("domainId", domainId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
		}
		return query;
	}
	@Override
	public long getDepartmentSize(String userId, SwoDepartmentCond cond) throws SwoException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoDepartment[] getDepartments(String userId, SwoDepartmentCond cond, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.id, obj.companyId, obj.parentId, obj.type, obj.name, obj.description,");
				buf.append(" obj.domainId,");
				buf.append(" obj.creationUser, obj.creationDate,");
				buf.append(" obj.modificationUser, obj.modificationDate");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoDepartment obj = new SwoDepartment();
					int j = 0;
					obj.setId((String)fields[j++]);
					obj.setCompanyId((String)fields[j++]);
					obj.setParentId((String)fields[j++]);
					obj.setType((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setDescription((String)fields[j++]);
					obj.setDomainId(((String)fields[j++]));
					obj.setCreationUser(((String)fields[j++]));
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoDepartment[] objs = new SwoDepartment[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	
	@Override
	public SwoUser getUser(String userId, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoUser obj = (SwoUser)this.get(SwoUser.class, id);
				return obj;
			} else {
				SwoUserCond cond = new SwoUserCond();
				cond.setId(id);
				SwoUser[] objs = this.getUsers(userId, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoUser getUser(String userId, SwoUserCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoUser[] users = getUsers(userId, cond, level);
		if (CommonUtil.isEmpty(users))
			return null;
		try {
			if (users.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return users[0];
	}
	@Override
	public void setUser(String userId, SwoUser obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(userId, obj);
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoUser set");
				buf.append(" companyId=:companyId, deptId=:deptId, roleId=:roleId, authId=:authId, empNo=:empNo,");
				buf.append(" name=:name, type=:type, position=:position, email=:email, password=:password,");
				buf.append(" lang=:lang, stdTime=:stdTime, picture=:picture,");
				buf.append(" domainId=:domainId,");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser,");
				buf.append(" modificationUser=:modificationUser, modificationDate=:modificationDate, retiree=:retiree");
				buf.append(" mobileNo=:mobileNo, internalNo=:internalNo");
				buf.append(" where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoUser.A_COMPANYID, obj.getCompanyId());
				query.setString(SwoUser.A_DEPTID, obj.getDeptId());
				query.setString(SwoUser.A_ROLEID, obj.getRoleId());
				query.setString(SwoUser.A_AUTHID, obj.getAuthId());
				query.setString(SwoUser.A_EMPNO, obj.getEmpNo());
				query.setString(SwoUser.A_NAME, obj.getName());
				query.setString(SwoUser.A_TYPE, obj.getType());
				query.setString(SwoUser.A_POSITION, obj.getPosition());
				query.setString(SwoUser.A_EMAIL, obj.getEmail());
				query.setString(SwoUser.A_PASSWORD, obj.getPassword());
				query.setString(SwoUser.A_LANG, obj.getLang());
				query.setString(SwoUser.A_STDTIME, obj.getStdTime());
				query.setString(SwoUser.A_PICTURE, obj.getPicture());
				query.setString(SwoUser.A_DOMAINID, obj.getDomainId());
				query.setTimestamp(SwoUser.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoUser.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(SwoUser.A_MODIFICATIONUSER, obj.getModificationDate());
				query.setTimestamp(SwoUser.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(SwoUser.A_RETIREE, obj.getRetiree());
				query.setString(SwoUser.A_MOBILENO, obj.getMobileNo());
				query.setString(SwoUser.A_MOBILENO, obj.getInternalNo());
				query.setString(SwoUser.A_ID, obj.getId());
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void createUser(String userId, SwoUser obj) throws SwoException {
		try {
			fill(userId, obj);
			create(obj);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeUser(String userId, String id) throws SwoException {
		try {
			remove(SwoUser.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeUser(String userId, SwoUserCond cond) throws SwoException {
		SwoUser obj = getUser(userId, cond, null);
		if (obj == null)
			return;
		removeUser(userId, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoUserCond cond) throws Exception {
		String id = null;
		String companyId = null;
		String deptId = null;
		String roleId = null;
		String authId = null;
		String empNo = null;
		String name = null;
		String nameLike = null;
		String type = null;
		String position = null;
		String email = null;
		String password = null;
		String lang = null;
		String stdTime = null;
		String picture = null;
		String domainId = null;
		String creationUser = null;
		String retiree = null;
		String mobileNo = null;
		String internalNo = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
		String[] typeNotIns = null;
				
		if (cond != null) {
			id = cond.getId();
			companyId = cond.getCompanyId();
			deptId = cond.getDeptId();
			roleId = cond.getRoleId();
			authId = cond.getAuthId();
			empNo = cond.getEmpNo();
			name = cond.getName();
			nameLike = cond.getNameLike();
			type = cond.getType();
			position = cond.getPosition();
			email = cond.getEmail();
			password = cond.getPassword();
			lang = cond.getLang();
			stdTime = cond.getStdTime();
			picture = cond.getPicture();
			domainId = cond.getDomainId();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
			typeNotIns = cond.getTypeNotIns();
			retiree = cond.getRetiree();
			mobileNo = cond.getMobileNo();
			internalNo = cond.getInternalNo();
		}
		buf.append(" from SwoUser obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (companyId != null)
				buf.append(" and obj.companyId = :companyId");
			if (deptId != null)
				buf.append(" and obj.deptId = :deptId");
			if (roleId != null)
				buf.append(" and obj.roleId = :roleId");
			if (authId != null)
				buf.append(" and obj.authId = :authId");
			if (empNo != null)
				buf.append(" and obj.empNo = :empNo");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (nameLike != null)
				buf.append(" and obj.name like :nameLike");
			if (type != null)
				buf.append(" and obj.type = :type");
			if (position != null)
				buf.append(" and obj.position = :position");
			if (email != null)
				buf.append(" and obj.email = :email");
			if (password != null)
				buf.append(" and obj.password = :password");
			if (lang != null)
				buf.append(" and obj.lang = :lang");
			if (stdTime != null)
				buf.append(" and obj.stdTime = :stdTime");
			if (picture != null)
				buf.append(" and obj.picture = :picture");
			if (domainId != null)
				buf.append(" and obj.domainId = :domainId");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (retiree != null)
				buf.append(" and obj.retiree = :retiree");
			if (mobileNo != null)
				buf.append(" and obj.mobileNo = :mobileNo");
			if (internalNo != null)
				buf.append(" and obj.internalNo = :internalNo");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
			if (typeNotIns != null && typeNotIns.length != 0) {
				buf.append(" and obj.type not in (");
				for (int i=0; i<typeNotIns.length; i++) {
					if (i != 0)
						buf.append(", ");
					buf.append(":typeNotIn").append(i);
				}
				buf.append(")");
			}
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (companyId != null)
				query.setString("companyId", companyId);
			if (deptId != null)
				query.setString("deptId", deptId);
			if (roleId != null)
				query.setString("roleId", roleId);
			if (authId != null)
				query.setString("authId", authId);
			if (empNo != null)
				query.setString("empNo", empNo);
			if (name != null)
				query.setString("name", name);
			if (nameLike != null)
				query.setString("nameLike", CommonUtil.toLikeString(nameLike));
			if (type != null)
				query.setString("type", type);
			if (position != null)
				query.setString("position", position);
			if (email != null)
				query.setString("email", email);
			if (password != null)
				query.setString("password", password);
			if (lang != null)
				query.setString("lang", lang);
			if (stdTime != null)
				query.setString("stdTime", stdTime);
			if (picture != null)
				query.setString("picture", picture);
			if (domainId != null)
				query.setString("domainId", domainId);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
			if (retiree != null)
				query.setString("retiree", retiree);
			if (mobileNo != null)
				query.setString("mobileNo", mobileNo);
			if (internalNo != null)
				query.setString("internalNo", internalNo);
			if (typeNotIns != null && typeNotIns.length != 0) {
				for (int i=0; i<typeNotIns.length; i++) {
					query.setString("typeNotIn"+i, typeNotIns[i]);
				}
			}
		}
		return query;
	}
	@Override
	public long getUserSize(String userId, SwoUserCond cond) throws SwoException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	//사원찾기
	@Override
	public SwoUser[] getSearchUsers(String userId, SwoUserCond cond, String level) throws SwoException {
		String name = cond.getName();
		StringBuffer sqlBuf = new StringBuffer();
		try {
			sqlBuf.append(" select id, name, pos, retiree  from SwOrgUser where name like '%"+name+"%'");	
			Query query = this.getSession().createSQLQuery(sqlBuf.toString());	
			List<String> list = query.list();
			
			if (list == null || list.isEmpty())
				return null;
			
			List objList = new ArrayList();	
//			for(String str:list){
//				SwoUser obj = new SwoUser();
//				obj.setId(str);
//				obj.setName(str);
//				objList.add(obj);
//			}
			for (Iterator itr = list.iterator(); itr.hasNext();) {
				Object[] fields = (Object[]) itr.next();
				SwoUser obj = new SwoUser();
				int j = 0;
				obj.setId((String)fields[j++]);
				obj.setName((String)fields[j++]);
				obj.setPosition((String)fields[j++]);
				obj.setRetiree((String)fields[j++]);
				objList.add(obj);
			}
			list = objList;
			SwoUser[] objs = new SwoUser[list.size()];
			list.toArray(objs);
		return objs;
	} catch (Exception e) {
		logger.error(e, e);
		throw new SwoException(e);
	}
}
	@Override
	public SwoUser[] getUsers(String userId, SwoUserCond cond, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.id, obj.companyId, obj.deptId, obj.roleId, obj.authId, obj.empNo,");
				buf.append(" obj.name, obj.type, obj.position, obj.email, obj.password,");
				buf.append(" obj.lang, obj.stdTime, obj.picture,");
				buf.append(" obj.domainId,");
				buf.append(" obj.creationUser, obj.creationDate,");
				buf.append(" obj.modificationUser, obj.modificationDate, obj.retiree, obj.mobileNo, obj.internalNo");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoUser obj = new SwoUser();
					int j = 0;
					obj.setId((String)fields[j++]);
					obj.setCompanyId((String)fields[j++]);
					obj.setDeptId((String)fields[j++]);
					obj.setRoleId((String)fields[j++]);
					obj.setAuthId((String)fields[j++]);
					obj.setEmpNo((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setType((String)fields[j++]);
					obj.setPosition((String)fields[j++]);
					obj.setEmail((String)fields[j++]);
					obj.setPassword((String)fields[j++]);
					obj.setLang((String)fields[j++]);
					obj.setStdTime((String)fields[j++]);
					obj.setPicture((String)fields[j++]);
					obj.setDomainId(((String)fields[j++]));
					obj.setCreationUser(((String)fields[j++]));
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					obj.setRetiree(((String)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoUser[] objs = new SwoUser[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}

	@Override
	public String getDefaultLogo() throws SwoException {
		String sql = "select logo from SWConfig where id = 'maninsoft'";
		Query query = this.getSession().createSQLQuery(sql);
		Object logo = query.uniqueResult();
		return (String)logo;
	}
	
	@Override
	public String getLogo(String user, String companyId ) throws SwoException {
		String sql = "select logo from SWConfig where id = '" + companyId + "'";
		Query query = this.getSession().createSQLQuery(sql);
		Object logo = query.uniqueResult();
		return (String)logo;
	}

	@Override
	public void setLogo(String user, String companyId, String pictureName) throws SwoException {
		String sql = "update SWConfig set logo = '" + pictureName + "' where id = '" + companyId + "'";
		Query query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
	}

	@Override
	public void createLogo(String user, String companyId, String pictureName) throws SwoException {
		String sql = "insert into SWConfig (id, logo) values ('"+ companyId +"', '"+ pictureName +"')";
		Query query = this.getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public SwoConfig getConfig(String user, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoConfig obj = (SwoConfig)this.get(SwoConfig.class, id);
				return obj;
			} else {
				SwoConfigCond cond = new SwoConfigCond();
				cond.setId(id);
				SwoConfig[] objs = this.getConfigs(user, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}

	@Override
	public SwoConfig getConfig(String user, SwoConfigCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoConfig[] configs = getConfigs(user, cond, level);
		if (CommonUtil.isEmpty(configs))
			return null;
		try {
			if (configs.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return configs[0];
	}
	@Override
	public void setConfig(String user, SwoConfig obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(user, obj);
				if (obj.getId() == null) {
					if (!CommonUtil.isEmpty(obj.getCompanyId())) {
						obj.setId(obj.getCompanyId());
					} else {
						throw new SwoException("companyId is null");
					}
				}
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoConfig set");
				buf.append(" companyId=:companyId, name=:name, domainId=:domainId, smtpAddress=:smtpAddress, userId=:userId, password=:password,");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser,");
				buf.append(" modificationUser=:modificationUser, modificationDate=:modificationDate, isActivity=:isActivity");
				buf.append(" where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoConfig.A_COMPANYID, obj.getCompanyId());
				query.setString(SwoConfig.A_NAME, obj.getName());
				query.setString(SwoConfig.A_DOMAINID, obj.getDomainId());
				query.setString(SwoConfig.A_SMTPADDRESS, obj.getSmtpAddress());
				query.setString(SwoConfig.A_USERID,obj.getUserId());
				query.setString(SwoConfig.A_PASSWORD, obj.getPassword());
				query.setTimestamp(SwoConfig.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoConfig.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(SwoConfig.A_MODIFICATIONUSER, obj.getModificationDate());
				query.setTimestamp(SwoConfig.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setBoolean(SwoConfig.A_ISACTIVITY, obj.isActivity());
				query.setString(SwoConfig.A_ID, obj.getId());
				
			}				
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void createConfig(String user, SwoConfig obj) throws SwoException {
		try {
			fill(user, obj);
			if (obj.getId() == null) {
				if (!CommonUtil.isEmpty(obj.getCompanyId())) {
					obj.setId(obj.getCompanyId());
				} else {
					throw new SwoException("companyId is null");
				}
			}
			create(obj);
		} catch (Exception e) {
			logger.error(e,e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeConfig(String user, String id) throws SwoException {
		try {
			remove(SwoConfig.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeConfig(String user, SwoConfigCond cond) throws SwoException {
		SwoConfig obj = getConfig(user, cond, null);
		if (obj == null)
			return;
		removeConfig(user, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoConfigCond cond) throws Exception {
		String id = null;
		String companyId = null;
		String name = null;
		String smtpAddress = null;
		String userId = null;
		String password = null;
		String creationUser = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
				
		if (cond != null) {
			id = cond.getId();
			companyId = cond.getCompanyId();
			name = cond.getName();
			smtpAddress = cond.getSmtpAddress();
			userId = cond.getUserId();
			password = cond.getPassword();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
		}
		buf.append(" from SwoConfig obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (companyId != null)
				buf.append(" and obj.companyId = :companyId");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (smtpAddress != null)
				buf.append(" and obj.smtpAddress = :smtpAddress");
			if (userId != null)
				buf.append(" and obj.userId = :userId");
			if (password != null)
				buf.append(" and obj.password = :password");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
		}
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (companyId != null)
				query.setString("companyId", companyId);
			if (name != null)
				query.setString("name", name);
			if (smtpAddress != null)
				query.setString("smtpAddress", smtpAddress);
			if (userId != null)
				query.setString("userId", userId);
			if (password != null)
				query.setString("password", password);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
		}
		return query;
	}
	@Override
	public long getConfigSize(String user, SwoConfigCond cond) throws SwoException{
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoConfig[] getConfigs(String user, SwoConfigCond cond, String level) throws SwoException {
		// TODO Auto-generated method stub
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.userId, obj.smtpAddress, obj.password, obj.isActivity");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoConfig obj = new SwoConfig();
					int j = 0;
					obj.setSmtpAddress((String)fields[j++]);
					obj.setUserId((String)fields[j++]);
					obj.setPassword((String)fields[j++]);
					obj.setActivity(CommonUtil.toBoolean(fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoConfig[] objs = new SwoConfig[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e,e);
			throw new SwoException(e);
		}
	}

	@Override
	public SwoTeam getTeam(String user, String id, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_ALL;
			if (level.equals(LEVEL_ALL)) {
				SwoTeam obj = (SwoTeam)this.get(SwoTeam.class, id);
				return obj;
			} else {
				SwoTeamCond cond = new SwoTeamCond();
				cond.setId(id);
				SwoTeam[] objs = this.getTeams(user, cond, level);
				if (objs == null || objs.length == 0)
					return null;
				return objs[0];
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public SwoTeam getTeam(String user, SwoTeamCond cond, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		cond.setPageSize(2);
		SwoTeam[] teams = getTeams(user, cond, level);
		if (CommonUtil.isEmpty(teams))
			return null;
		try {
			if (teams.length != 1)
				throw new SwoException("More than 1 Object");
		} catch (SwoException e) {
			logger.error(e, e);
			throw e;
		}
		return teams[0];
	}
	@Override
	public void setTeam(String user, SwoTeam obj, String level) throws SwoException {
		if (level == null)
			level = LEVEL_ALL;
		try {
			if (level.equals(LEVEL_ALL)) {
				fill(user, obj);
				if(obj.getId() == null)
					obj.setId(IDCreator.createId(SmartServerConstant.TEAM_APPR));
				set(obj);
			} else {
				StringBuffer buf = new StringBuffer();
				buf.append("update SwoTeam set");
				buf.append(" companyId=:companyId, name=:name, teamLeader=:teamLeader, dept=:dept, member=:member, accessLevel=:accessLevel,");
				buf.append(" state=:state, description=:description");
				buf.append(" creationDate=:creationDate, creationUser=:creationUser,");
				buf.append(" modificationUser=:modificationUser, modificationDate=:modificationDate");
				buf.append(" where id=:id");
				Query query = this.getSession().createQuery(buf.toString());
				query.setString(SwoTeam.A_COMPANYID, obj.getCompanyId());
				query.setString(SwoTeam.A_NAME, obj.getName());
				query.setString(SwoTeam.A_TEAMLEADER, obj.getTeamLeader());
				query.setString(SwoTeam.A_DEPT, obj.getDept());
				query.setString(SwoTeam.A_MEMBER, obj.getMember());
				query.setString(SwoTeam.A_ACCESSLEVEL, obj.getAccessLevel());
				query.setString(SwoTeam.A_STATE, obj.getState());
				query.setString(SwoTeam.A_DESCRIPTION, obj.getDescription());
				query.setTimestamp(SwoTeam.A_CREATIONDATE, obj.getCreationDate());
				query.setString(SwoTeam.A_CREATIONUSER, obj.getCreationUser());
				query.setTimestamp(SwoTeam.A_MODIFICATIONUSER, obj.getModificationDate());
				query.setTimestamp(SwoTeam.A_MODIFICATIONDATE, obj.getModificationDate());
				query.setString(SwoTeam.A_ID, obj.getId());
			}
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void createTeam(String user, SwoTeam obj) throws SwoException {
		try {
			fill(user, obj);
			if(obj.getId() == null)
				obj.setId(IDCreator.createId(SmartServerConstant.TEAM_APPR));
			create(obj);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeTeam(String user, String id) throws SwoException {
		try {
			remove(SwoTeam.class, id);
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}
	@Override
	public void removeTeam(String user, SwoTeamCond cond) throws SwoException {
		SwoTeam obj = getTeam(user, cond, null);
		if (obj == null)
			return;
		removeTeam(user, obj.getId());
	}
	private Query appendQuery(StringBuffer buf, SwoTeamCond cond) throws Exception {
		String id = null;
		String companyId = null;
		String name = null;
		String teamLeader = null;
		String dept = null;
		String accessLevel = null;
		String state = null;
		String creationUser = null;
		Date creationDate = null;
		String modificationUser = null;
		Date modificationDate = null;
		String nameLike = null;
				
		if (cond != null) {
			id = cond.getId();
			companyId = cond.getCompanyId();
			name = cond.getName();
			teamLeader = cond.getTeamLeader();
			dept = cond.getDept();
			accessLevel = cond.getAccessLevel();
			state = cond.getState();
			creationUser = cond.getCreationUser();
			creationDate = cond.getCreationDate();
			modificationUser = cond.getModificationUser();
			modificationDate = cond.getModificationDate();
			nameLike = cond.getNameLike();
		}
		buf.append(" from SwoTeam obj");
		buf.append(" where obj.id is not null");
		//TODO 시간 검색에 대한 확인 필요
		if (cond != null) {
			if (id != null)
				buf.append(" and obj.id = :id");
			if (companyId != null)
				buf.append(" and obj.companyId = :companyId");
			if (name != null)
				buf.append(" and obj.name = :name");
			if (nameLike != null)
				buf.append(" and obj.name like :nameLike");
			if (teamLeader != null)
				buf.append(" and obj.teamLeader = :teamLeader");
			if (dept != null)
				buf.append(" and obj.dept = :dept");
			if (accessLevel != null)
				buf.append(" and obj.accessLevel = :accessLevel");
			if (state != null)
				buf.append(" and obj.state = :state");
			if (creationUser != null)
				buf.append(" and obj.creationUser = :creationUser");
			if (creationDate != null)
				buf.append(" and obj.creationDate = :creationDate");
			if (modificationUser != null)
				buf.append(" and obj.modificationUser = :modificationUser");
			if (modificationDate != null)
				buf.append(" and obj.modificationDate = :modificationDate");
		}
		
		this.appendOrderQuery(buf, "obj", cond);
		
		Query query = this.createQuery(buf.toString(), cond);
		if (cond != null) {
			if (id != null)
				query.setString("id", id);
			if (companyId != null)
				query.setString("companyId", companyId);
			if (name != null)
				query.setString("name", name);
			if (nameLike != null)
				query.setString("nameLike", CommonUtil.toLikeString(nameLike));
			if (teamLeader != null)
				query.setString("teamLeader", teamLeader);
			if (dept != null)
				query.setString("dept", dept);
			if (accessLevel != null)
				query.setString("accessLevel", accessLevel);
			if (state != null)
				query.setString("state", state);
			if (creationUser != null)
				query.setString("creationUser", creationUser);
			if (creationDate != null)
				query.setTimestamp("creationDate", creationDate);
			if (modificationUser != null)
				query.setString("modificationUser", modificationUser);
			if (modificationDate != null)
				query.setTimestamp("modificationDate", modificationDate);
		}
		return query;
	}
	
	@Override
	public long getTeamSize(String user, SwoTeamCond cond) throws SwoException {
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			buf.append(" count(obj)");
			Query query = this.appendQuery(buf,cond);
			List list = query.list();
			long count = ((Long)list.get(0)).longValue();
			return count;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}

	@Override
	public SwoTeam[] getTeams(String user, SwoTeamCond cond, String level) throws SwoException {
		try {
			if (level == null)
				level = LEVEL_LITE;
			StringBuffer buf = new StringBuffer();
			buf.append("select");
			if (level.equals(LEVEL_ALL)) {
				buf.append(" obj");
			} else {
				buf.append(" obj.id, obj.companyId, obj.name, obj.teamLeader, obj.dept, obj.member,");
				buf.append(" obj.accessLevel, obj.state, obj.description,");
				buf.append(" obj.creationUser, obj.creationDate,");
				buf.append(" obj.modificationUser, obj.modificationDate");
			}
			Query query = this.appendQuery(buf, cond);
			List list = query.list();
			if (list == null || list.isEmpty())
				return null;
			if (!level.equals(LEVEL_ALL)) {
				List objList = new ArrayList();
				for (Iterator itr = list.iterator(); itr.hasNext();) {
					Object[] fields = (Object[]) itr.next();
					SwoTeam obj = new SwoTeam();
					int j = 0;
					obj.setId((String)fields[j++]);
					obj.setCompanyId((String)fields[j++]);
					obj.setName((String)fields[j++]);
					obj.setTeamLeader((String)fields[j++]);
					obj.setDept((String)fields[j++]);
					obj.setMember((String)fields[j++]);
					obj.setAccessLevel((String)fields[j++]);
					obj.setState((String)fields[j++]);
					obj.setDescription((String)fields[j++]);
					obj.setCreationUser(((String)fields[j++]));
					obj.setCreationDate(((Timestamp)fields[j++]));
					obj.setModificationUser(((String)fields[j++]));
					obj.setModificationDate(((Timestamp)fields[j++]));
					objList.add(obj);
				}
				list = objList;
			}
			SwoTeam[] objs = new SwoTeam[list.size()];
			list.toArray(objs);
			return objs;
		} catch (Exception e) {
			logger.error(e, e);
			throw new SwoException(e);
		}
	}

	@Override
	public List getOrganization(String deptId) throws SwoException {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" select id, deptId, name, 'u' as type from sworguser where deptId = '"+ deptId +"' ");
		sqlBuf.append(" union ");
		sqlBuf.append(" select id, parentId as deptId, name, 'd' as type from sworgDept where parentId = '"+ deptId +"'");
		
		Query query = getSession().createSQLQuery(sqlBuf.toString());
		List list = query.list();
		List objList = new ArrayList();
		if (list.size() != 0) {
			for (Iterator itr = list.iterator(); itr.hasNext();) {
				Object[] fields = (Object[]) itr.next();
				SwoUser obj = new SwoUser();
				int j = 0;
				obj.setId((String)fields[j++]);
				obj.setDeptId((String)fields[j++]);
				obj.setName((String)fields[j++]);
				obj.setType((String)fields[j++].toString());
				objList.add(obj);
			}
			list = objList;
		} else {
			list = new ArrayList();
		}
		return list;
	}

}