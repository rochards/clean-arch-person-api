package com.rochards.registerapi.person.infrastructure.configuration;

import com.rochards.registerapi.person.core.gateways.PersonGateway;
import com.rochards.registerapi.person.core.usecases.CreatePersonUseCase;
import com.rochards.registerapi.person.core.usecases.CreatePersonUseCaseImpl;
import com.rochards.registerapi.person.core.usecases.FindAllPersonUseCase;
import com.rochards.registerapi.person.core.usecases.FindAllPersonUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    /*
     * PersonGateway have an implementation: PersonRepositoryGateway. This class already has a Spring annotation,
     * ensuring that we'll have an available object for the methods parameter below.
     * */
    @Bean
    public CreatePersonUseCase createCreatePersonUseCaseBean(PersonGateway personGateway) {
        return new CreatePersonUseCaseImpl(personGateway);
    }

    @Bean
    public FindAllPersonUseCase createFindAllPersonUseCaseBean(PersonGateway personGateway) {
        return new FindAllPersonUseCaseImpl(personGateway);
    }
}
