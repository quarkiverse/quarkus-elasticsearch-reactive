package io.quarkiverse.quarkus.elasticsearch.reactive.deployment;

import org.jboss.jandex.DotName;

import io.quarkiverse.quarkus.elasticsearch.reactive.runtime.ElasticsearchReactiveProducer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanDefiningAnnotationBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.elasticsearch.restclient.lowlevel.ElasticsearchClientConfig;

class ElasticsearchReactiveProcessor {

    private static final DotName ELASTICSEARCH_CLIENT_CONFIG = DotName.createSimple(ElasticsearchClientConfig.class.getName());

    private static final String FEATURE = "elasticsearch-reactive";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    AdditionalBeanBuildItem build() {
        return AdditionalBeanBuildItem.unremovableOf(ElasticsearchReactiveProducer.class);
    }

    @BuildStep
    void elasticsearchClientConfigSupport(BuildProducer<AdditionalBeanBuildItem> additionalBeans,
            BuildProducer<BeanDefiningAnnotationBuildItem> beanDefiningAnnotations) {
        // add the @ElasticsearchClientConfig class otherwise it won't be registered as a qualifier
        additionalBeans.produce(AdditionalBeanBuildItem.builder().addBeanClass(ElasticsearchClientConfig.class).build());

        beanDefiningAnnotations
                .produce(new BeanDefiningAnnotationBuildItem(ELASTICSEARCH_CLIENT_CONFIG, DotNames.APPLICATION_SCOPED, false));
    }
}
