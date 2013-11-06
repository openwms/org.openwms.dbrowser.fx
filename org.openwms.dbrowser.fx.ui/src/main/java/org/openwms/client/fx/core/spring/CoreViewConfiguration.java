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
package org.openwms.client.fx.core.spring;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.openwms.client.fx.core.FXMLDialog;
import org.openwms.client.fx.core.controller.AddCustomerController;
import org.openwms.client.fx.core.controller.CustomerDataScreenController;
import org.openwms.client.fx.core.controller.ErrorController;
import org.openwms.client.fx.core.controller.LoginController;
import org.openwms.client.fx.core.model.DossierBrowser;
import org.openwms.client.fx.core.view.CustomerDataScreen;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * A CoreViewConfiguration.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
@Configuration()
@Lazy
public class CoreViewConfiguration {

    private Stage primaryStage;

    @Bean
    public CustomerDataScreen customerDataScreen() {
        return new CustomerDataScreen(customerDataScreenController());
    }

    @Bean
    public CustomerDataScreenController customerDataScreenController() {
        return new CustomerDataScreenController(this);
    }

    @Bean
    @Scope("prototype")
    public FXMLDialog errorDialog() {
        return new FXMLDialog(errorController(), getClass().getResource("/client/Error.fxml"), getPrimaryStage(),
                StageStyle.UNDECORATED);
    }

    @Bean
    @Scope("prototype")
    public ErrorController errorController() {
        return new ErrorController();
    }

    @Bean
    @Scope("prototype")
    public FXMLDialog addCustomerDialog() {
        return new FXMLDialog(addCustomerController(), getClass().getResource("/client/AddCustomer.fxml"),
                getPrimaryStage());
    }

    @Bean
    @Scope("prototype")
    public AddCustomerController addCustomerController() {
        return new AddCustomerController();
    }

    /** --> DossierBrowser */
    @Bean
    @Scope("prototype")
    public FXMLDialog addDossierBrowser() {
        return new FXMLDialog(addDossierBrowserController(), getClass().getResource(
                "/client/DossierBrowser/ComplexApplication.fxml"), getPrimaryStage());
    }

    @Bean
    @Scope("prototype")
    public DossierBrowser addDossierBrowserController() {
        DossierBrowser ctrl = new DossierBrowser();
        ctrl.afterPostConstruct();
        return ctrl;
    }

    /** <-- DossierBrowser */

    @Bean
    @Scope("prototype")
    public FXMLDialog loginDialog() {
        return new FXMLDialog(loginController(), getClass().getResource("/client/Login.fxml"), getPrimaryStage(),
                StageStyle.UNDECORATED);
    }

    @Bean
    @Scope("prototype")
    public LoginController loginController() {
        return new LoginController(this);
    }

    /**
     * Get the primaryStage.
     * 
     * @return the primaryStage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Store the stage object.
     * 
     * @param primaryStage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showScreen(Parent screen) {
        primaryStage.setScene(new Scene(screen, 777, 500));
        primaryStage.show();
    }
}
