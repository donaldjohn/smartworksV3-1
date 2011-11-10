/**
 * <copyright>
 * </copyright>
 *
 * $Id: OutputSets.java,v 1.1 2009/12/22 06:18:38 kmyu Exp $
 */
package net.smartworks.server.engine.process.xpdl.xpdl2;

import commonj.sdo.Sequence;

import java.io.Serializable;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Output Sets</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.smartworks.server.engine.process.xpdl.xpdl2.OutputSets#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link net.smartworks.server.engine.process.xpdl.xpdl2.OutputSets#getAny <em>Any</em>}</li>
 *   <li>{@link net.smartworks.server.engine.process.xpdl.xpdl2.OutputSets#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @extends Serializable
 * @generated
 */
public interface OutputSets extends Serializable
{
  /**
   * Returns the value of the '<em><b>Output Set</b></em>' containment reference list.
   * The list contents are of type {@link net.smartworks.server.engine.process.xpdl.xpdl2.OutputSet}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * BPMN
   * <!-- end-model-doc -->
   * @return the value of the '<em>Output Set</em>' containment reference list.
   * @generated
   */
  List getOutputSet();

  /**
   * Returns the value of the '<em><b>Any</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any</em>' attribute list.
   * @generated
   */
  Sequence getAny();

  /**
   * Returns the value of the '<em><b>Any Attribute</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Any Attribute</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Any Attribute</em>' attribute list.
   * @generated
   */
  Sequence getAnyAttribute();

} // OutputSets