/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.client.fx.core.controller;

import org.openwms.client.fx.core.FXMLDialog;

/**
 * A AbstractSceneController.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public abstract class AbstractSceneController implements DialogController {

    /**
     * Create a new AbstractSceneController.
     * 
     */
    public AbstractSceneController() {}

    public void afterPostConstruct() {

    }

    /**
     * @see org.openwms.client.fx.core.controller.DialogController#setDialog(org.openwms.client.fx.core.FXMLDialog)
     */
    @Override
    public void setDialog(FXMLDialog dialog) {
        // TODO [scherrer] Auto-generated method stub

    }

}
