// CacheControl.java
package practise.Chapter_5_URL;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class CacheControl {
    private boolean noStore;
    private final long maxAge;
    private final Date creationTime;
    private final Map<String, List<String>> headers;

    public CacheControl(String cacheControlHeader) {
        this.headers = new HashMap<>();
        this.creationTime = new Date();
        this.noStore = cacheControlHeader != null && cacheControlHeader.contains("no-store");
        this.maxAge = parseMaxAge(cacheControlHeader);
    }

    private long parseMaxAge(String cacheControlHeader) {
        if (cacheControlHeader != null && cacheControlHeader.contains("max-age")) {
            try {
                String[] directives = cacheControlHeader.split(",");
                for (String directive : directives) {
                    directive = directive.trim();
                    if (directive.startsWith("max-age")) {
                        String[] parts = directive.split("=");
                        if (parts.length > 1) {
                            return TimeUnit.SECONDS.toMillis(Long.parseLong(parts[1].trim()));
                        }
                    }
                }
            } catch (Exception ignored) {}
        }
        return 0;
    }

    public boolean noStore() {
        return noStore;
    }

    public Date getMaxAge() {
        return new Date(creationTime.getTime() + maxAge);
    }

    public boolean isExpired() {
        return maxAge > 0 && new Date().after(getMaxAge());
    }

    public CacheControl getControl() {
        return this;
    }
}