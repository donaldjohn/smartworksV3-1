/**
 * <copyright>
 * </copyright>
 *
 * $Id: TransitionRestrictionImpl.java,v 1.1 2009/12/22 06:17:22 kmyu Exp $
 */
package net.smartworks.server.engine.process.xpdl.xpdl1.impl;

import net.smartworks.server.engine.process.xpdl.xpdl1.Join;
import net.smartworks.server.engine.process.xpdl.xpdl1.Split;
import net.smartworks.server.engine.process.xpdl.xpdl1.TransitionRestriction;
import net.smartworks.server.engine.process.xpdl.xpdl1.Xpdl1Factory;

import org.apache.tuscany.sdo.impl.DataObjectBase;

import commonj.sdo.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition Restriction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.smartworks.server.engine.process.xpdl.xpdl1.impl.TransitionRestrictionImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link net.smartworks.server.engine.process.xpdl.xpdl1.impl.TransitionRestrictionImpl#getSplit <em>Split</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionRestrictionImpl extends DataObjectBase implements TransitionRestriction
{

  public final static int JOIN = 0;

  public final static int SPLIT = 1;

  public final static int SDO_PROPERTY_COUNT = 2;

  public final static int EXTENDED_PROPERTY_COUNT = 0;


  /**
   * The internal feature id for the '<em><b>Join</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */ 
  public final static int INTERNAL_JOIN = 0;

  /**
   * The internal feature id for the '<em><b>Split</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */ 
  public final static int INTERNAL_SPLIT = 1;

  /**
   * The number of properties for this type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public final static int INTERNAL_PROPERTY_COUNT = 2;

  protected int internalConvertIndex(int internalIndex)
  {
    switch (internalIndex)
    {
      case INTERNAL_JOIN: return JOIN;
      case INTERNAL_SPLIT: return SPLIT;
    }
    return super.internalConvertIndex(internalIndex);
  }


  /**
   * The cached value of the '{@link #getJoin() <em>Join</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJoin()
   * @generated
   * @ordered
   */
  
  protected Join join = null;
  
  /**
   * This is true if the Join containment reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean join_set_ = false;

  /**
   * The cached value of the '{@link #getSplit() <em>Split</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSplit()
   * @generated
   * @ordered
   */
  
  protected Split split = null;
  
  /**
   * This is true if the Split containment reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean split_set_ = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TransitionRestrictionImpl()
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
    return ((Xpdl1FactoryImpl)Xpdl1Factory.INSTANCE).getTransitionRestriction();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Join getJoin()
  {
    return join;
  }
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChangeContext basicSetJoin(Join newJoin, ChangeContext changeContext)
  {
    Join oldJoin = join;
    join = newJoin;
    boolean oldJoin_set_ = join_set_;
    join_set_ = true;
    if (isNotifying())
    {
      addNotification(this, ChangeKind.SET, INTERNAL_JOIN, oldJoin, newJoin, !oldJoin_set_, changeContext);
    }
    return changeContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJoin(Join newJoin)
  {
    if (newJoin != join)
    {
      ChangeContext changeContext = null;
      if (join != null)
        changeContext = inverseRemove(join, this, OPPOSITE_FEATURE_BASE - INTERNAL_JOIN, null, changeContext);
      if (newJoin != null)
        changeContext = inverseAdd(newJoin, this, OPPOSITE_FEATURE_BASE - INTERNAL_JOIN, null, changeContext);
      changeContext = basicSetJoin(newJoin, changeContext);
      if (changeContext != null) dispatch(changeContext);
    }
    else
    {
      boolean oldJoin_set_ = join_set_;
      join_set_ = true;
      if (isNotifying())
        notify(ChangeKind.SET, INTERNAL_JOIN, newJoin, newJoin, !oldJoin_set_);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChangeContext basicUnsetJoin(ChangeContext changeContext)
  {
    Join oldJoin = join;
    join = null;
    boolean oldJoin_set_ = join_set_;
    join_set_ = false;
    if (isNotifying())
    {
      addNotification(this, ChangeKind.UNSET, INTERNAL_JOIN, oldJoin, null, !oldJoin_set_, changeContext);
    }
    return changeContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetJoin()
  {
    if (join != null)
    {
      ChangeContext changeContext = null;
      changeContext = inverseRemove(join, this, EOPPOSITE_FEATURE_BASE - INTERNAL_JOIN, null, changeContext);
      changeContext = basicUnsetJoin(changeContext);
      if (changeContext != null) dispatch(changeContext);
    }
    else
    	{
      boolean oldJoin_set_ = join_set_;
      join_set_ = false;
      if (isNotifying())
        notify(ChangeKind.UNSET, INTERNAL_JOIN, null, null, oldJoin_set_);
    	}
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetJoin()
  {
    return join_set_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Split getSplit()
  {
    return split;
  }
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChangeContext basicSetSplit(Split newSplit, ChangeContext changeContext)
  {
    Split oldSplit = split;
    split = newSplit;
    boolean oldSplit_set_ = split_set_;
    split_set_ = true;
    if (isNotifying())
    {
      addNotification(this, ChangeKind.SET, INTERNAL_SPLIT, oldSplit, newSplit, !oldSplit_set_, changeContext);
    }
    return changeContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSplit(Split newSplit)
  {
    if (newSplit != split)
    {
      ChangeContext changeContext = null;
      if (split != null)
        changeContext = inverseRemove(split, this, OPPOSITE_FEATURE_BASE - INTERNAL_SPLIT, null, changeContext);
      if (newSplit != null)
        changeContext = inverseAdd(newSplit, this, OPPOSITE_FEATURE_BASE - INTERNAL_SPLIT, null, changeContext);
      changeContext = basicSetSplit(newSplit, changeContext);
      if (changeContext != null) dispatch(changeContext);
    }
    else
    {
      boolean oldSplit_set_ = split_set_;
      split_set_ = true;
      if (isNotifying())
        notify(ChangeKind.SET, INTERNAL_SPLIT, newSplit, newSplit, !oldSplit_set_);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChangeContext basicUnsetSplit(ChangeContext changeContext)
  {
    Split oldSplit = split;
    split = null;
    boolean oldSplit_set_ = split_set_;
    split_set_ = false;
    if (isNotifying())
    {
      addNotification(this, ChangeKind.UNSET, INTERNAL_SPLIT, oldSplit, null, !oldSplit_set_, changeContext);
    }
    return changeContext;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetSplit()
  {
    if (split != null)
    {
      ChangeContext changeContext = null;
      changeContext = inverseRemove(split, this, EOPPOSITE_FEATURE_BASE - INTERNAL_SPLIT, null, changeContext);
      changeContext = basicUnsetSplit(changeContext);
      if (changeContext != null) dispatch(changeContext);
    }
    else
    	{
      boolean oldSplit_set_ = split_set_;
      split_set_ = false;
      if (isNotifying())
        notify(ChangeKind.UNSET, INTERNAL_SPLIT, null, null, oldSplit_set_);
    	}
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetSplit()
  {
    return split_set_;
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
      case JOIN:
        return basicUnsetJoin(changeContext);
      case SPLIT:
        return basicUnsetSplit(changeContext);
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
      case JOIN:
        return getJoin();
      case SPLIT:
        return getSplit();
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
      case JOIN:
        setJoin((Join)newValue);
        return;
      case SPLIT:
        setSplit((Split)newValue);
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
      case JOIN:
        unsetJoin();
        return;
      case SPLIT:
        unsetSplit();
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
      case JOIN:
        return isSetJoin();
      case SPLIT:
        return isSetSplit();
    }
    return super.isSet(propertyIndex);
  }

} //TransitionRestrictionImpl
