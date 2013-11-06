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

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.openwms.client.fx.core.spring.CoreViewConfiguration;

/**
 * A StageController.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public class StageController {

    private Stage primaryStage;
    private CoreViewConfiguration config;

    /**
     * Set the stage.
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

    /**
     * Get the primaryStage.
     * 
     * @return the primaryStage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Create a new StageController.
     */
    public StageController(CoreViewConfiguration configuration) {
        config = configuration;
    }
}
