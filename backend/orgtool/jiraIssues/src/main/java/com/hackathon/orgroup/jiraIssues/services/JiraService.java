package com.hackathon.orgroup.jiraIssues.services;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class JiraService {

    private static final String CONSUMER_KEY = "<your-consumer-key>";
    private static final String CONSUMER_SECRET = "<your-consumer-secret>";
    private static final String ACCESS_TOKEN_URL = "<your-jira-base-url>/plugins/servlet/oauth/access-token";

    public String getRequestToken() throws OAuthException, IOException, URISyntaxException {
        OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);

        String requestTokenEndpoint = "<your-jira-base-url>/plugins/servlet/oauth/request-token";
        HttpGet getRequestToken = new HttpGet(requestTokenEndpoint);
        consumer.sign(getRequestToken);
        HttpResponse response = new DefaultHttpClient().execute(getRequestToken);
        String responseBody = EntityUtils.toString(response.getEntity());
        String[] requestTokenResponse = responseBody.split("&");
        String requestToken = requestTokenResponse[0].split("=")[1];
        String requestTokenSecret = requestTokenResponse[1].split("=")[1];

        // Store the request token and secret in a database or other persistent storage
        // You'll need them later to exchange the request token for an access token
        // ...

        return requestToken;
    }

    public String getAccessToken(String requestToken, String requestTokenSecret) throws OAuthException, IOException {
        OAuthConsumer consumer = new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        consumer.setTokenWithSecret(requestToken, requestTokenSecret);

        HttpGet getAccessToken = new HttpGet(ACCESS_TOKEN_URL);
        consumer.setTokenWithSecret(requestToken, requestTokenSecret);
        consumer.sign(getAccessToken);
        HttpResponse response = new DefaultHttpClient().execute(getAccessToken);
        String responseBody = EntityUtils.toString(response.getEntity());
        String[] accessTokenResponse = responseBody.split("&");
        String accessToken = accessTokenResponse[0].split("=")[1];
        String accessTokenSecret = accessTokenResponse[1].split("=")[1];

        return accessToken;

    }
}
