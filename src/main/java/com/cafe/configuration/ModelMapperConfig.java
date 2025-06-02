package com.cafe.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper.
 * Provides a singleton instance of ModelMapper with strict matching strategy.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures a ModelMapper bean.
     *
     * @return configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true);
        return modelMapper;
    }
}
