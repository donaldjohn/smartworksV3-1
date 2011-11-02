/*	
 * $Id$
 * created by    : hsshin
 * creation-date : 2011. 11. 2.
 * =========================================================
 * Copyright (c) 2011 ManinSoft, Inc. All rights reserved.
 */

package net.smartworks.server.engine.organization.model;

public class SwoObject extends SmartObject {

	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(SwoObject.class);
	
	protected static final String PREFIX = "Swo";
	private static final String NAME = CommonUtil.toName(SwoObject.class, PREFIX);

	public static final String A_ID = "id";
	public static final String A_NAME = "name";
	public static final String A_DOMAINID = "domainId";
	public static final String A_CREATIONUSER = "creationUser";
	public static final String A_CREATIONDATE = "creationDate";
	public static final String A_MODIFICATIONUSER = "modificationUser";
	public static final String A_MODIFICATIONDATE = "modificationDate";

	private String id;
	private String name;
	private String domainId;
	private String creationUser;
	private Date creationDate;
	private String modificationUser;
	private Date modificationDate;

	public SwoObject() {
		super();
	}
	public String toString(String name, String tab){
		if(name == null || name.trim().length() == 0)
			name = NAME;
		return super.toString(name, tab);
	}
	public String toAttributesString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toAttributesString());
		appendAttributeString(A_ID, id, buf);
		appendAttributeString(A_NAME, name, true, buf);
		appendAttributeString(A_DOMAINID, domainId, buf);
		appendAttributeString(A_CREATIONUSER, creationUser, buf);
		appendAttributeString(A_CREATIONDATE, creationDate, buf);
		appendAttributeString(A_MODIFICATIONUSER, modificationUser, buf);
		appendAttributeString(A_MODIFICATIONDATE, modificationDate, buf);
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		SwoObject obj = null;
		if (baseObj == null || !(baseObj instanceof SwoObject))
			obj = new SwoObject();
		else
			obj = (SwoObject)baseObj;
		
		SwdObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node name = attrMap.getNamedItem(A_NAME);
			if (name != null)
				obj.setName(name.getNodeValue());
		}
		return obj;
	}
	public static BaseObject toObject(String str) throws Exception {
		if (str == null)
			return null;
		Document doc = XmlUtil.toDocument(str);
		if (doc == null)
			return null;
		return toObject(doc.getDocumentElement(), null);
	}
	public static SwoObject[] add(SwoObject[] objs, SwoObject obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		SwoObject[] newObjs = new SwoObject[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static SwoObject[] remove(SwoObject[] objs, SwoObject obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		SwoObject[] newObjs = new SwoObject[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static SwoObject[] left(SwoObject[] objs, SwoObject obj) {
		if (objs == null || objs.length == 0 || obj == null)
			return objs;
		int idx = -1;
		for (int i=0; i<objs.length; i++) {
			if (!objs[i].equals(obj))
				continue;
			idx = i;
			break;
		}
		if (idx < 1)
			return objs;
		SwoObject[] newObjs = new SwoObject[objs.length];
		for (int i=0; i<objs.length; i++) {
			if (i == idx) {
				newObjs[i] = objs[idx-1];
				continue;
			} else if (i == idx-1) {
				newObjs[i] = objs[idx];
				continue;
			}
			newObjs[i] = objs[i];
		}
		return newObjs;
	}
	public static SwoObject[] right(SwoObject[] objs, SwoObject obj) {
		if (objs == null || objs.length == 0 || obj == null)
			return objs;
		int idx = -1;
		for (int i=0; i<objs.length; i++) {
			if (!objs[i].equals(obj))
				continue;
			idx = i;
			break;
		}
		if (idx == -1 || idx+1 == objs.length)
			return objs;
		SwoObject[] newObjs = new SwoObject[objs.length];
		for (int i=0; i<objs.length; i++) {
			if (i == idx) {
				newObjs[i] = objs[idx+1];
				continue;
			} else if (i == idx+1) {
				newObjs[i] = objs[idx];
				continue;
			}
			newObjs[i] = objs[i];
		}
		return newObjs;
	}
	public Object clone() throws CloneNotSupportedException {
		try {
			return toObject(this.toString());
		} catch (Exception e) {
			logger.warn(e, e);
			return null;
		}
	}

	
	
	
	
	
	
	
	
	



	public String toAttributesString() {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toAttributesString());
		appendAttributeString(A_ID, id, buf);
		appendAttributeString(A_DOMAINID, domainId, buf);
		appendAttributeString(A_CREATIONUSER, creationUser, buf);
		appendAttributeString(A_CREATIONDATE, creationDate, buf);
		appendAttributeString(A_MODIFICATIONUSER, modificationUser, buf);
		appendAttributeString(A_MODIFICATIONDATE, modificationDate, buf);
		return buf.toString();
	}
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		SwdObject obj = null;
		if (baseObj == null || !(baseObj instanceof SwdObject))
			obj = new SwdObject();
		else
			obj = (SwdObject)baseObj;
		
		BaseObject.toObject(node, obj);
		
		NamedNodeMap attrMap = node.getAttributes();
		if (attrMap != null) {
			Node id = attrMap.getNamedItem(A_ID);
			Node domainId = attrMap.getNamedItem(A_DOMAINID);
			Node creationUser = attrMap.getNamedItem(A_CREATIONUSER);
			Node creationDate = attrMap.getNamedItem(A_CREATIONDATE);
			Node modificationUser = attrMap.getNamedItem(A_MODIFICATIONUSER);
			Node modificationDate = attrMap.getNamedItem(A_MODIFICATIONDATE);
			if (id != null)
				obj.setId(id.getNodeValue());
			if (domainId != null)
				obj.setDomainId(domainId.getNodeValue());
			if (creationUser != null)
				obj.setCreationUser(creationUser.getNodeValue());
			if (creationDate != null)
				obj.setCreationDate(DateUtil.toDate(creationDate.getNodeValue()));
			if (modificationUser != null)
				obj.setModificationUser(modificationUser.getNodeValue());
			if (modificationDate != null)
				obj.setModificationDate(DateUtil.toDate(modificationDate.getNodeValue()));
		}
		return obj;
	}
	public static BaseObject toObject(String str) throws Exception {
		if (str == null)
			return null;
		Document doc = XmlUtil.toDocument(str);
		if (doc == null)
			return null;
		return toObject(doc.getDocumentElement(), null);
	}
	public static SwdObject[] add(SwdObject[] objs, SwdObject obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		SwdObject[] newObjs = new SwdObject[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static SwdObject[] remove(SwdObject[] objs, SwdObject obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		SwdObject[] newObjs = new SwdObject[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static SwdObject[] left(SwdObject[] objs, SwdObject obj) {
		if (objs == null || objs.length == 0 || obj == null)
			return objs;
		int idx = -1;
		for (int i=0; i<objs.length; i++) {
			if (!objs[i].equals(obj))
				continue;
			idx = i;
			break;
		}
		if (idx < 1)
			return objs;
		SwdObject[] newObjs = new SwdObject[objs.length];
		for (int i=0; i<objs.length; i++) {
			if (i == idx) {
				newObjs[i] = objs[idx-1];
				continue;
			} else if (i == idx-1) {
				newObjs[i] = objs[idx];
				continue;
			}
			newObjs[i] = objs[i];
		}
		return newObjs;
	}
	public static SwdObject[] right(SwdObject[] objs, SwdObject obj) {
		if (objs == null || objs.length == 0 || obj == null)
			return objs;
		int idx = -1;
		for (int i=0; i<objs.length; i++) {
			if (!objs[i].equals(obj))
				continue;
			idx = i;
			break;
		}
		if (idx == -1 || idx+1 == objs.length)
			return objs;
		SwdObject[] newObjs = new SwdObject[objs.length];
		for (int i=0; i<objs.length; i++) {
			if (i == idx) {
				newObjs[i] = objs[idx+1];
				continue;
			} else if (i == idx+1) {
				newObjs[i] = objs[idx];
				continue;
			}
			newObjs[i] = objs[i];
		}
		return newObjs;
	}
	public Object clone() throws CloneNotSupportedException {
		try {
			return toObject(this.toString());
		} catch (Exception e) {
			logger.warn(e, e);
			return null;
		}
	}


}