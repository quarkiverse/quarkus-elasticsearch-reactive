package io.quarkiverse.quarkus.elasticsearch.reactive.runtime.health;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.cluster.health.ClusterHealthStatus;

import io.reactiverse.elasticsearch.client.mutiny.RestHighLevelClient;
import io.smallrye.health.api.AsyncHealthCheck;
import io.smallrye.mutiny.Uni;

@Readiness
@ApplicationScoped
public class ElasticsearchReactiveHealthCheck implements AsyncHealthCheck {
    private static final String CHECK_NAME = "Elasticsearch cluster health check";
    @Inject
    RestHighLevelClient restHighLevelClient;

    @Override
    public Uni<HealthCheckResponse> call() {
        ClusterHealthRequest request = new ClusterHealthRequest();
        return restHighLevelClient.cluster().healthAsync(request, RequestOptions.DEFAULT).map(
                response -> {
                    HealthCheckResponseBuilder builder = HealthCheckResponse.named(CHECK_NAME).up();
                    ClusterHealthStatus status = response.getStatus();
                    if (status.equals(ClusterHealthStatus.RED)) {
                        builder.down().withData("status", status.toString());
                    } else {
                        builder.up().withData("status", status.toString());
                    }
                    return builder.build();
                })
                .onFailure()
                .recoverWithItem(e -> HealthCheckResponse.named(CHECK_NAME).down()
                        .withData("reason", e.getMessage()).build());
    }
}
