/**
 * <copyright>
 * </copyright>
 *
 * $Id: DataFieldsImpl.java,v 1.1 2009/12/22 06:17:18 kmyu Exp $
 */
package net.smartworks.server.engine.process.xpdl.xpdl1.impl;

import java.util.Collection;
import java.util.List;

import net.smartworks.server.engine.process.xpdl.xpdl1.DataField;
import net.smartworks.server.engine.process.xpdl.xpdl1.DataFields;
import net.smartworks.server.engine.process.xpdl.xpdl1.Xpdl1Factory;

import org.apache.tuscany.sdo.impl.DataObjectBase;

import commonj.sdo.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Fields</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.smartworks.server.engine.process.xpdl.xpdl1.impl.DataFieldsImpl#getDataField <em>Data Field</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataFieldsImpl extends DataObjectBase implements DataFields
{

  public final static int DATA_FIELD = 0;

  public final static int SDO_PROPERTY_COUNT = 1;

  public final static int EXTENDED_PROPERTY_COUNT = 0;


  /**
   * The internal feature id for the '<em><b>Data Field</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */ 
  public final static int INTERNAL_DATA_FIELD = 0;

  /**
   * The number of properties for this type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public final static int INTERNAL_PROPERTY_COUNT = 1;

  protected int internalConvertIndex(int internalIndex)
  {
    switch (internalIndex)
    {
      case INTERNAL_DATA_FIELD: return DATA_FIELD;
    }
    return super.internalConvertIndex(internalIndex);
  }


  /**
   * The cached value of the '{@link #getDataField() <em>Data Field</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataField()
   * @generated
   * @ordered
   */
  
  protected List dataField = null;
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataFieldsImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Type getStaticType()
  {
    return ((Xpdl1FactoryImpl)Xpdl1Factory.INSTANCE).getDataFields();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getDataField()
  {
    if (dataField == null)
    {
      dataField = createPropertyList(ListKind.CONTAINMENT, DataField.class, DATA_FIELD, 0);
    }
    return dataField;
  }
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChangeContext inverseRemove(Object otherEnd, int propertyIndex, ChangeContext changeContext)
  {
    switch (propertyIndex)
    {
      case DATA_FIELD:
        return removeFromList(getDataField(), otherEnd, changeContext);
    }
    return super.inverseRemove(otherEnd, propertyIndex, changeContext);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object get(int propertyIndex, boolean resolve)
  {
    switch (propertyIndex)
    {
      case DATA_FIELD:
        return getDataField();
    }
    return super.get(propertyIndex, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void set(int propertyIndex, Object newValue)
  {
    switch (propertyIndex)
    {
      case DATA_FIELD:
        getDataField().clear();
        getDataField().addAll((Collection)newValue);
        return;
    }
    super.set(propertyIndex, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unset(int propertyIndex)
  {
    switch (propertyIndex)
    {
      case DATA_FIELD:
        getDataField().clear();
        return;
    }
    super.unset(propertyIndex);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSet(int propertyIndex)
  {
    switch (propertyIndex)
    {
      case DATA_FIELD:
        return dataField != null && !dataField.isEmpty();
    }
    return super.isSet(propertyIndex);
  }

} //DataFieldsImpl
