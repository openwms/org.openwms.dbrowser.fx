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

import java.io.IOException;
import java.util.Collections;

import org.openwms.client.fx.core.model.CustomerModel;
import org.openwms.client.fx.core.model.DossierBrowser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * A AppConfiguration.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * 
 */
@Configuration
@Import(CoreViewConfiguration.class)
@ImportResource("classpath:spring/applicationContext-security.xml")
public class AppConfiguration {

    /**
     * FIXME [scherrer] Comment this
     * 
     * @return
     * @throws IOException
     */
    @Bean
    CustomerModel customerModel() throws IOException {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setRestTemplate(restTemplate());
        // customerModel.loadData();
        return customerModel;
    }

    @Bean
    DossierBrowser dossierBrowser() {
        return new DossierBrowser();
    }

    /**
     * FIXME [scherrer] Comment this
     * 
     * @return
     */
    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections
                .<HttpMessageConverter<?>> singletonList(new MappingJacksonHttpMessageConverter()));
        return restTemplate;
    }
}
