package io.quarkiverse.quarkus.elasticsearch.reactive.it;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import io.reactiverse.elasticsearch.client.mutiny.RestHighLevelClient;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class FruitService {
    @Inject
    RestHighLevelClient restHighLevelClient;

    public Uni<IndexResponse> index(Fruit fruit) throws IOException {
        IndexRequest request = new IndexRequest("fruits");
        request.id(fruit.id);
        request.source(JsonObject.mapFrom(fruit).toString(), XContentType.JSON);
        return restHighLevelClient.indexAsync(request, RequestOptions.DEFAULT);
    }

    public Uni<Fruit> get(String id) throws IOException {
        GetRequest getRequest = new GetRequest("fruits", id);
        return restHighLevelClient.getAsync(getRequest, RequestOptions.DEFAULT)
                .map(getResponse -> {
                    if (getResponse.isExists()) {
                        String sourceAsString = getResponse.getSourceAsString();
                        JsonObject json = new JsonObject(sourceAsString);
                        return json.mapTo(Fruit.class);
                    }
                    return null;
                });

    }

    public Uni<List<Fruit>> searchByColor(String color) throws IOException {
        return search("color", color);
    }

    public Uni<List<Fruit>> searchByName(String name) throws IOException {
        return search("name", name);
    }

    private Uni<List<Fruit>> search(String term, String match) throws IOException {
        SearchRequest searchRequest = new SearchRequest("fruits");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(term, match));
        searchRequest.source(searchSourceBuilder);

        return restHighLevelClient.searchAsync(searchRequest, RequestOptions.DEFAULT)
                .map(searchResponse -> {
                    SearchHits hits = searchResponse.getHits();
                    List<Fruit> results = new ArrayList<>(hits.getHits().length);
                    for (SearchHit hit : hits.getHits()) {
                        String sourceAsString = hit.getSourceAsString();
                        JsonObject json = new JsonObject(sourceAsString);
                        results.add(json.mapTo(Fruit.class));
                    }
                    return results;
                });
    }
}
