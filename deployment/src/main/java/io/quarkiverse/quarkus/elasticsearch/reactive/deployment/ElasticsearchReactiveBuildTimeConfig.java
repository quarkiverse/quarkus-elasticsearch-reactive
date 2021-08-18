package io.quarkiverse.quarkus.elasticsearch.reactive.deployment;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

@ConfigRoot(name = "elasticsearch.reactive", phase = ConfigPhase.BUILD_TIME)
public class ElasticsearchReactiveBuildTimeConfig {
    /**
     * Whether or not an health check is published in case the smallrye-health extension is present.
     * When enabled, you must disable quarkus.elasticsearch.health.enabled to avoid having two checks.
     */
    @ConfigItem(name = "health.enabled", defaultValue = "false")
    public boolean healthEnabled;
}
