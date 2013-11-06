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
package org.openwms.client.fx.core.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.ToolBarBuilder;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBoxBuilder;

import org.openwms.client.fx.core.controller.CustomerDataScreenController;
import org.openwms.client.fx.core.model.Customer;
import org.springframework.security.access.AccessDeniedException;

/**
 * A CustomerDataScreen.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public class CustomerDataScreen extends StackPane {

    CustomerDataScreenController controller;
    private TableView<Customer> tableView = new TableView<Customer>();

    /**
     * Create a new CustomerDataScreen.
     * 
     * @param controller
     */
    public CustomerDataScreen(CustomerDataScreenController controller) {
        this.controller = controller;
        getChildren().add(VBoxBuilder.create().children(createHeader(), createToolbar(), createDataTable()).build());
    }

    private Node createHeader() {
        return new ImageView(getClass().getResource("/client/header.jpg").toString());
    }

    private Node createToolbar() {
        Button removeButton;
        ToolBar toolBar = ToolBarBuilder
                .create()
                .items(ButtonBuilder.create().text("Add Customer").onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        controller.addCustomer();
                    }
                }).build(),
                        removeButton = ButtonBuilder.create().text("Remove Customer")
                                .onAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        try {
                                            controller.removeCustomer(tableView.getSelectionModel().getSelectedItem());
                                            tableView.getSelectionModel().select(
                                                    Math.min(tableView.getSelectionModel().getSelectedIndex(),
                                                            tableView.getItems().size() - 1));
                                        } catch (AccessDeniedException e) {
                                            controller.showErrorDialog();
                                        }
                                    }
                                }).build()).build();
        removeButton.disableProperty().bind(tableView.getSelectionModel().selectedItemProperty().isNull());
        return toolBar;
    }

    @SuppressWarnings("unchecked")
    private Node createDataTable() {
        StackPane dataTableBorder = new StackPane();
        dataTableBorder.getChildren().add(tableView);
        dataTableBorder.setPadding(new Insets(8));
        dataTableBorder.setStyle("-fx-background-color: lightgray");
        tableView.setItems(controller.getCustomers());
        tableView.getColumns().setAll(
                TableColumnBuilder.<Customer, String> create().text("First Name")
                        .cellValueFactory(new PropertyValueFactory<Customer, String>("firstName")).prefWidth(204)
                        .build(),
                TableColumnBuilder.<Customer, String> create().text("Last Name")
                        .cellValueFactory(new PropertyValueFactory<Customer, String>("lastName")).prefWidth(204)
                        .build(),
                TableColumnBuilder.<Customer, String> create().text("Sign-up Date")
                        .cellValueFactory(new PropertyValueFactory<Customer, String>("signupDate")).prefWidth(351)
                        .build());
        tableView.setPrefHeight(500);
        return dataTableBorder;
    }
}
