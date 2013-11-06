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
package org.openwms.client.fx.core.model;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.client.RestTemplate;

/**
 * A CustomerModel.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
public class CustomerModel {

    private final ObservableList<Customer> customers = FXCollections.observableArrayList();
    public static final String CONTEXT_ROOT = "openwms/";

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void loadData() {
        Customer[] customers = restTemplate.getForObject("http://localhost:8280/" + CONTEXT_ROOT + "customers",
                Customer[].class);
        this.customers.setAll(customers);
    }

    @Secured("ROLE_MANAGER")
    public void remove(Customer customer) {
        restTemplate.delete("http://localhost:8280/" + CONTEXT_ROOT + "customer/" + customer.getId());
        customers.remove(customer);
    }

    public void addCustomer(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setSignupDate(new Date());
        Integer id = restTemplate.postForObject("http://localhost:8280/" + CONTEXT_ROOT + "customers", customer,
                Integer.class);
        customer.setId(id);
        customers.add(customer);
    }
}
