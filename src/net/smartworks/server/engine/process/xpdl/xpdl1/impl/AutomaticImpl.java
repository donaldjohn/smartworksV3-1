/**
 * <copyright>
 * </copyright>
 *
 * $Id: AutomaticImpl.java,v 1.1 2009/12/22 06:17:20 kmyu Exp $
 */
package net.smartworks.server.engine.process.xpdl.xpdl1.impl;

import net.smartworks.server.engine.process.xpdl.xpdl1.Automatic;
import net.smartworks.server.engine.process.xpdl.xpdl1.Xpdl1Factory;

import org.apache.tuscany.sdo.impl.DataObjectBase;

import commonj.sdo.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Automatic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class AutomaticImpl extends DataObjectBase implements Automatic
{

  public final static int SDO_PROPERTY_COUNT = 0;

  public final static int EXTENDED_PROPERTY_COUNT = 0;


  /**
   * The number of properties for this type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public final static int INTERNAL_PROPERTY_COUNT = 0;

  protected int internalConvertIndex(int internalIndex)
  {
    switch (internalIndex)
    {
    }
    return super.internalConvertIndex(internalIndex);
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AutomaticImpl()
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
    return ((Xpdl1FactoryImpl)Xpdl1Factory.INSTANCE).getAutomatic();
  }

} //AutomaticImpl
