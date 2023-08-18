package com.atc.opportunity_management_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.atc.opportunity_management_system.entity.Company;
import com.atc.opportunity_management_system.entity.Country;
import com.atc.opportunity_management_system.entity.DealStage;
import com.atc.opportunity_management_system.entity.DeliveryModel;
import com.atc.opportunity_management_system.entity.Industry;
import com.atc.opportunity_management_system.entity.Location;
import com.atc.opportunity_management_system.entity.Opportunity;
import com.atc.opportunity_management_system.entity.Role;
import com.atc.opportunity_management_system.entity.Transaction;
import com.atc.opportunity_management_system.entity.UseCase;
import com.atc.opportunity_management_system.entity.User;
@SpringBootApplication
public class OpportunityManagementSystemApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(OpportunityManagementSystemApplication.class, args);
	}

	@Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Country.class);
        config.exposeIdsFor(Company.class);
        config.exposeIdsFor(DealStage.class);
        config.exposeIdsFor(DeliveryModel.class);
        config.exposeIdsFor(Industry.class);
        config.exposeIdsFor(Location.class);
        config.exposeIdsFor(Opportunity.class);
        config.exposeIdsFor(Role.class);
        config.exposeIdsFor(Transaction.class);
        config.exposeIdsFor(UseCase.class);
        config.exposeIdsFor(User.class);
    }

}
