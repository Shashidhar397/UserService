package com.messanger.userService.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author shashidhar
 */
@Configuration
public class ESConfig {

    public RestClient restClient() {
        return RestClient
                .builder(HttpHost.create("http://localhost:9200"))
                .build();
    }

    public ElasticsearchTransport elasticsearchTransport(){
        return new RestClientTransport(
                restClient(), new JacksonJsonpMapper());
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(){
        return new ElasticsearchClient(elasticsearchTransport());
    }
}
