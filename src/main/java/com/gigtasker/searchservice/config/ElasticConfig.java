package com.gigtasker.searchservice.config;

import com.gigtasker.searchservice.config.components.DateConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.util.List;

@Configuration
public class ElasticConfig extends ElasticsearchConfigurationSupport {

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
                List.of(new DateConverters.StringToLocalDateTimeConverter())
        );
    }
}
