// SimpleCacheResponse.java
package practise.Chapter_5_URL;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleCacheResponse extends CacheResponse {
    private final Map<String, List<String>> headers;
    private final SimpleCacheRequest request;
    private final Date expires;
    private final CacheControl control;

    public SimpleCacheResponse(SimpleCacheRequest request, URLConnection uc, CacheControl control) throws IOException {
        this.request = request;
        this.control = control;

        // Handle expiration
        long expiration = uc.getExpiration();
        this.expires = expiration == 0 ? null : new Date(expiration);

        // Copy headers
        Map<String, List<String>> tempHeaders = new HashMap<>();
        uc.getHeaderFields().forEach(tempHeaders::put);
        this.headers = Collections.unmodifiableMap(tempHeaders);
    }

    @Override
    public InputStream getBody() throws IOException {
        byte[] data = request.getData();
        return data != null ? new ByteArrayInputStream(data) : new ByteArrayInputStream(new byte[0]);
    }

    @Override
    public Map<String, List<String>> getHeaders() throws IOException {
        return headers;
    }

    public CacheControl getControl() {
        return control;
    }

    public boolean isExpired() {
        Date now = new Date();
        if (control.getMaxAge().before(now)) {
            return true;
        }
        return expires != null && expires.before(now);
    }
}