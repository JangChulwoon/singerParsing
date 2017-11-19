package org.karaoke.restcontroller;


import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.karaoke.domain.Argument;
import org.karaoke.service.KaraokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class KaraokeController {

    @Autowired
    KaraokeService parser;

    @Autowired
    GraphQL graphQL;

    @GetMapping("/{company}/{category}/{word}")
    public List<?> selectKaraoke(@ModelAttribute Argument argument, @RequestParam(required = false, defaultValue = "1") int page) throws IOException {
        return  parser.parseKaraoke(argument, page);
    }

    @PostMapping("/karaokeGraphiQL")
    public CompletableFuture<ExecutionResult> selectByGraphiQL(@RequestBody Map query)
            throws InterruptedException {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query.get("query").toString()).variables((Map) query.get("variables")).build();
        return  graphQL.executeAsync(executionInput);
    }

    @PostMapping("/karaokeGraphQL")
    public CompletableFuture<ExecutionResult> selectByGraphQL(@RequestBody Map query) throws InterruptedException {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query.get("query").toString()).variables((Map) query.get("variables")).operationName(query.getOrDefault("operationName","").toString()).build();
        return graphQL.executeAsync(executionInput);
    }
}
