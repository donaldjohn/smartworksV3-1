package net.smartworks.server.engine.common.collection.model;

import net.smartworks.server.engine.common.model.BaseObject;
import net.smartworks.server.engine.common.model.MisObjectCond;
import net.smartworks.server.engine.common.util.CommonUtil;
import net.smartworks.server.engine.common.util.XmlUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ColListCond extends MisObjectCond {
	private static final long serialVersionUID = 1L;
	private static Log logger = LogFactory.getLog(ColListCond.class);

	protected static final String PREFIX = "Col";
	private static final String NAME = CommonUtil.toName(ColListCond.class, PREFIX);
	public static final String A_CORRELATION = "correlation";
	public static final String A_TYPE = "type";
	public static final String A_TYPELIKE = "typeLike";
	public static final String A_ITEM = "item";
	public static final String A_ITEMS = "items";

	private String correlation;
	private String type;
	private String typeLike;
	
	private ColObject[] items;
	
	public ColListCond() {
		super();
	}
	public Object clone() throws CloneNotSupportedException {
		try {
			return toObject(this.toString());
		} catch (Exception e) {
			logger.warn(e, e);
			return null;
		}
	}
	public static ColListCond[] add(ColListCond[] objs, ColListCond obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		ColListCond[] newObjs = new ColListCond[size+1];
		int i;
		for (i=0; i<size; i++)
			newObjs[i] = objs[i];
		newObjs[i] = obj;
		return newObjs;
	}
	public static ColListCond[] remove(ColListCond[] objs, ColListCond obj) {
		if (obj == null)
			return objs;
		int size = 0;
		if (objs != null)
			size = objs.length;
		if (size == 0)
			return objs;
		ColListCond[] newObjs = new ColListCond[size-1];
		int i;
		int j = 0;
		for (i=0; i<size; i++) {
			if (objs[i].equals(obj))
				continue;
			newObjs[j++] = objs[i];
		}
		return newObjs;
	}
	public static ColListCond[] left(ColListCond[] objs, ColListCond obj) {
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
		ColListCond[] newObjs = new ColListCond[objs.length];
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
	public static ColListCond[] right(ColListCond[] objs, ColListCond obj) {
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
		ColListCond[] newObjs = new ColListCond[objs.length];
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
	public static BaseObject toObject(Node node, BaseObject baseObj) throws Exception {
		if (node == null)
			return null;
		
		ColListCond obj = null;
		if (baseObj == null || !(baseObj instanceof ColListCond))
			obj = new ColListCond();
		else
			obj = (ColListCond)baseObj;
		
		MisObjectCond.toObject(node, obj);
		
		NodeList childNodeList = node.getChildNodes();
		if (childNodeList == null || childNodeList.getLength() == 0)
			return obj;
		for (int i=0; i<childNodeList.getLength(); i++) {
			Node childNode = childNodeList.item(i);
			if (childNode.getNodeType() != Node.ELEMENT_NODE || childNode.getNodeName() == null)
				continue;
			if (childNode.getNodeName().equals(A_CORRELATION)) {
				obj.setCorrelation(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_TYPE)) {
				obj.setType(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_TYPELIKE)) {
				obj.setTypeLike(getNodeValue(childNode));
			} else if (childNode.getNodeName().equals(A_ITEMS)) {
				Node[] nodes = getNodes(childNode);
				if (nodes == null || nodes.length == 0)
					continue;
				ColObject[] objs = new ColObject[nodes.length];
				for (int j=0; j<nodes.length; j++)
					objs[j] = (ColObject)ColObject.toObject(nodes[j], null);
				obj.setItems(objs);
			} else if (childNode.getNodeName().equals(A_ITEM)) {
				obj.addItem((ColObject)ColObject.toObject(childNode, null));
			}
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
	public String toString(String name, String tab) {
		if (name == null || name.trim().length() == 0)
			name = NAME;
		return super.toString(name, tab);
	}
	public String toElementsString(String tab) {
		StringBuffer buf = new StringBuffer();
		buf.append(super.toElementsString(tab));
		appendElementString(A_CORRELATION, correlation, tab, buf);
		appendElementString(A_TYPE, type, tab, buf);
		appendElementString(A_TYPELIKE, typeLike, tab, buf);
		appendElementsString(A_ITEMS, A_ITEM, getItems(), tab, buf);
		return buf.toString();
	}
	public String getCorrelation() {
		return correlation;
	}
	public void setCorrelation(String correlation) {
		this.correlation = correlation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ColObject[] getItems() {
		return items;
	}
	public void setItems(ColObject[] items) {
		this.items = items;
	}
	public void addItem(ColObject item) {
		if (item == null)
			return;
		this.setItems(ColObject.add(this.getItems(), item));
	}
	public String getTypeLike() {
		return typeLike;
	}
	public void setTypeLike(String typeLike) {
		this.typeLike = typeLike;
	}
}
