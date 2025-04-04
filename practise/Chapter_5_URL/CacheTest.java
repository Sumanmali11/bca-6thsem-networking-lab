// CacheTest.java
package practise.Chapter_5_URL;

import java.net.*;
import java.util.*;

public class CacheTest {
    public static void main(String[] args) {
        try {
            // Set the cache
            ResponseCache.setDefault(new CacheMemory(10));
            
            // Create URI first, then convert to URL
            URI uri = new URI("http://example.com");
            URL url = uri.toURL();
            
            // Make a request - must use HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Cache-Control", "max-age=60");
            
            try {
                // Read response
                conn.connect();
                int responseCode = conn.getResponseCode();
                System.out.println("Response Code: " + responseCode);
                
                // Try to get from cache
                ResponseCache cache = ResponseCache.getDefault();
                CacheResponse cachedResponse = cache.get(
                    uri, 
                    "GET", 
                    conn.getRequestProperties()
                );
                
                if (cachedResponse != null) {
                    System.out.println("Found in cache!");
                    Map<String, List<String>> headers = cachedResponse.getHeaders();
                    System.out.println("Headers: " + headers);
                } else {
                    System.out.println("Not in cache or expired");
                }
            } finally {
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}