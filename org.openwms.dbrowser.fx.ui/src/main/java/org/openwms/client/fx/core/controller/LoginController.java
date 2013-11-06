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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import org.openwms.client.fx.core.FXMLDialog;
import org.openwms.client.fx.core.spring.CoreViewConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * A LoginController.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public class LoginController implements DialogController {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final CoreViewConfiguration screens;
    private FXMLDialog dialog;

    @FXML
    Label header;
    @FXML
    TextField username;
    @FXML
    TextField password;

    /**
     * @see org.openwms.client.fx.core.controller.DialogController#setDialog(org.openwms.client.fx.core.FXMLDialog)
     */
    @Override
    public void setDialog(FXMLDialog dialog) {
        this.dialog = dialog;
    }

    /**
     * Create a new LoginController.
     * 
     * @param screens
     */
    public LoginController(CoreViewConfiguration screens) {
        this.screens = screens;
    }

    /**
     * FIXME [scherrer] Comment this
     * 
     */
    @FXML
    public void login() {
        Authentication authToken = new UsernamePasswordAuthenticationToken(username.getText(), password.getText());
        try {
            authToken = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } catch (AuthenticationException e) {
            header.setText("Login failure, please try again:");
            header.setTextFill(Color.DARKRED);
            return;
        }
        dialog.close();
        screens.showScreen(screens.customerDataScreen());
    }

    /**
     * FIXME [scherrer] Comment this
     * 
     */
    @FXML
    public void employee() {
        username.setText("employee");
        password.setText("lol");
    }

    /**
     * FIXME [scherrer] Comment this
     * 
     */
    @FXML
    public void manager() {
        username.setText("manager");
        password.setText("password");
    }
}
