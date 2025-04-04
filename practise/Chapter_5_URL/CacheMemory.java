package practise.Chapter_5_URL;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CacheMemory extends ResponseCache {
    private final Map<URI, SimpleCacheResponse> responses = new ConcurrentHashMap<>();
    private final int maxEntries;

    public CacheMemory() {
        this(100);
    }

    public CacheMemory(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        if (responses.size() >= maxEntries) {
            return null;
        }

        // Only handle HTTP connections
        if (!(conn instanceof HttpURLConnection)) {
            return null;
        }

        HttpURLConnection httpConn = (HttpURLConnection) conn;
        String requestMethod = httpConn.getRequestMethod();
        
        String cacheControlHeader = conn.getHeaderField("Cache-Control");
        CacheControl control = new CacheControl(cacheControlHeader);
        
        if (control.noStore()) {
            return null;
        }
        
        if (!"GET".equalsIgnoreCase(requestMethod)) {
            return null;
        }
        
        SimpleCacheRequest request = new SimpleCacheRequest();
        SimpleCacheResponse response = new SimpleCacheResponse(request, conn, control);
        responses.put(uri, response);
        return request;
    }

    @Override
    public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) throws IOException {
        if (!"GET".equalsIgnoreCase(requestMethod)) {
            return null;
        }
        
        SimpleCacheResponse response = responses.get(uri);
        if (response != null && response.isExpired()) {
            responses.remove(uri);
            return null;
        }
        return response;
    }
}