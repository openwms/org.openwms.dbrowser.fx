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

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.openwms.client.fx.core.FXMLDialog;
import org.openwms.client.fx.core.model.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A AddCustomerController.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public class AddCustomerController implements DialogController {

    @Autowired
    private CustomerModel model;
    private FXMLDialog dialog;

    @FXML
    TextField firstName;
    @FXML
    TextField lastName;

    /**
     * @see org.openwms.client.fx.core.controller.DialogController#setDialog(org.openwms.client.fx.core.FXMLDialog)
     */
    @Override
    public void setDialog(FXMLDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * FIXME [scherrer] Comment this
     */
    @FXML
    public void add() {
        model.addCustomer(firstName.getText(), lastName.getText());
        dialog.close();
    }

    /**
     * FIXME [scherrer] Comment this
     */
    @FXML
    public void cancel() {
        dialog.close();
    }
}
