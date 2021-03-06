/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph.arangodb.example;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import graph.arangodb.example.bean.SampleDocumentEntity;
import graph.arangodb.example.utils.FileUtils;

/**
 * Query Arango Graph data using simple HTTP post
 * request.(com.mashape.unirest.http)
 *
 * @see First Build Graph Data by running FillGraphDataToArangoDB class
 * @author ranjeet
 */
public class GraphQueryUsingHttpRequest {

    public static void main(String[] args) throws MalformedURLException, ExecutionException, InterruptedException {
        String resourceTestFile = "test.txt";
        String postUrl = "http://localhost:8529/_db/tagcloud/_api/cursor";
        long instantStart = 0;
        try {
            instantStart = System.nanoTime();
            List<String> skillClouds = FileUtils.getVertices(FileUtils.getResourceFile(resourceTestFile));
            List<String> skillCloudsSub = skillClouds.subList(0, 1);
            skillCloudsSub.stream().forEach(skill -> {
                String skill_key = new SampleDocumentEntity(skill).getKey();//for graph ID.
                try {
                    String bodyQuery = "{\"query\":\"\\n FOR v1,e1,p1 IN 1..2 OUTBOUND @vertices GRAPH  @graphName \\nFILTER (p1.edges[0].name !='r' and p1.edges[1].name != 'r') \\nFOR v2,e2,p2 IN 1..1 OUTBOUND v1._id GRAPH @graphName \\nFILTER (p2.edges[0].name == 'r') \\nRETURN {\\\"m\\\": p2.vertices[0].name,\\\"s\\\": v2.name,\\\"r\\\": e2.name,\\\"w\\\": e2.Weight}\\n\",\"batchSize\":1000,\"id\":\"currentFrontendQuery\",\"bindVars\":{\"graphName\":\"tagcloud\",\"vertices\":\"it-skill/" + skill_key + "\"}}";
                    com.mashape.unirest.http.HttpResponse<JsonNode> jsonResponse = Unirest.post(postUrl)
                            .header("accept", "application/json")
                            .body(bodyQuery)
                            .asJson();
                    System.out.println(jsonResponse);

                } catch (UnirestException ex) {
                    Logger.getLogger(GraphQueryUsingHttpRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(GraphQueryUsingArangoDriverApi.class.getName()).log(Level.SEVERE, null, ex);

        }
        long instantEnd = System.nanoTime();
        System.out.println("****************");
        System.out.println(
                "Diff :" + (instantEnd - instantStart) / 1000000);
        System.out.println("****************");
    }

}
