/*
 * Copyright (c) 2007, 2018 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * @(#)TestBeanEJB.java	1.17 03/05/16
 */

package com.sun.ts.tests.ejb.ee.bb.entity.cmp.multiclienttest;

import com.sun.ts.lib.util.*;
import com.sun.ts.lib.porting.*;

import java.util.*;
import javax.ejb.*;
import java.rmi.*;
import java.sql.*;

public class TestBeanEJB implements EntityBean {
  private EntityContext ectx = null;

  // Entity instance data
  public String BRAND_NAME;

  public Integer KEY_ID;

  public float PRICE;

  public Integer ejbCreate(Properties p, int KEY_ID, String BRAND_NAME,
      float PRICE) throws CreateException {
    TestUtil.logTrace("ejbCreate");
    try {
      TestUtil.logMsg("Initialize remote logging");
      TestUtil.init(p);
      this.KEY_ID = new Integer(KEY_ID);
      this.BRAND_NAME = BRAND_NAME;
      this.PRICE = PRICE;
    } catch (RemoteLoggingInitException e) {
      TestUtil.printStackTrace(e);
      throw new CreateException(e.getMessage());
    } catch (Exception e) {
      TestUtil.printStackTrace(e);
      throw new CreateException("Exception occurred: " + e);
    }
    return this.KEY_ID;
  }

  public void ejbPostCreate(Properties p, int KEY_ID, String BRAND_NAME,
      float PRICE) {
    TestUtil.logTrace("ejbPostCreate");
  }

  public void setEntityContext(EntityContext c) {
    TestUtil.logTrace("setEntityContext");
    ectx = c;
  }

  public void unsetEntityContext() {
    TestUtil.logTrace("unsetEntityContext");
  }

  public void ejbRemove() throws RemoveException {
    TestUtil.logTrace("ejbRemove");
  }

  public void ejbActivate() {
    TestUtil.logTrace("ejbActivate");
  }

  public void ejbPassivate() {
    TestUtil.logTrace("ejbPassivate");
  }

  public void ejbLoad() {
    TestUtil.logTrace("ejbLoad");
  }

  public void ejbStore() {
    TestUtil.logTrace("ejbStore");
  }

  // ===========================================================
  // TestBean interface (our business methods)

  public void updatePrice(float newPrice) {
    TestUtil.logTrace("updatePrice");
    PRICE = newPrice;
  }

  public float getPrice() {
    TestUtil.logTrace("getPrice");
    return PRICE;
  }

  public void initLogging(Properties p) {
    TestUtil.logTrace("initLogging");
    try {
      TestUtil.init(p);
    } catch (RemoteLoggingInitException e) {
      TestUtil.printStackTrace(e);
      throw new EJBException(e.getMessage());
    }
  }
}
