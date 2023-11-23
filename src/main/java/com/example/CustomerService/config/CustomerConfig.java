package com.example.CustomerService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate();
      /*  List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor(oAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository)));
        restTemplate.setInterceptors(interceptors);*/
        return new RestTemplate();
    }

    @Bean
    public OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(ClientRegistrationRepository clientRegistrationRepository,
                                                                       OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

        OAuth2AuthorizedClientProvider oAuth2AuthorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository);
        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(oAuth2AuthorizedClientProvider);
        return defaultOAuth2AuthorizedClientManager;

    }

    @Primary
    @Bean
    @LoadBalanced
    public RestTemplate restTemplateBuilderBean() {
        return new RestTemplateBuilder().interceptors(restTemplateInterceptor()).build();
    }


    public RestTemplateInterceptor restTemplateInterceptor() {
        return new RestTemplateInterceptor(oAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientRepository));
    }

}
