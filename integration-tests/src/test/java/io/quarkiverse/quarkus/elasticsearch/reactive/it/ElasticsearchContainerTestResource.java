package io.quarkiverse.quarkus.elasticsearch.reactive.it;

import java.util.Map;

import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.utility.DockerImageName;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class ElasticsearchContainerTestResource implements QuarkusTestResourceLifecycleManager {

    static ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer(
            DockerImageName.parse("docker.elastic.co/elasticsearch/elasticsearch-oss")
                    // Aiven managed version
                    .withTag("7.9.3"));

    @Override
    public Map<String, String> start() {
        elasticsearchContainer.withEnv("action.auto_create_index", "true");
        elasticsearchContainer.start();
        return Map.of(
                "quarkus.elasticsearch.hosts", elasticsearchContainer.getHttpHostAddress());
    }

    @Override
    public void stop() {
        elasticsearchContainer.stop();
    }
}
