package dns_cache;

/**
 * Represents a single DNS cache entry with TTL support
 */
public class DNSEntry {
    private String domain;
    private String ipAddress;
    private long timestamp;
    private long ttl; // Time to live in seconds

    /**
     * Constructor for DNS entry
     * @param domain Domain name
     * @param ipAddress IP address
     * @param ttl Time to live in seconds
     */
    public DNSEntry(String domain, String ipAddress, long ttl) {
        this.domain = domain;
        this.ipAddress = ipAddress;
        this.timestamp = System.currentTimeMillis();
        this.ttl = ttl;
    }

    /**
     * Check if the entry has expired
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        long elapsedSeconds = (System.currentTimeMillis() - timestamp) / 1000;
        return elapsedSeconds >= ttl;
    }

    /**
     * Get the remaining time to live in seconds
     * @return remaining TTL in seconds
     */
    public long getRemainingTTL() {
        long elapsedSeconds = (System.currentTimeMillis() - timestamp) / 1000;
        return Math.max(0, ttl - elapsedSeconds);
    }

    // Getters
    public String getDomain() {
        return domain;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getTTL() {
        return ttl;
    }

    @Override
    public String toString() {
        return "DNSEntry{ " +
                "domain='" + domain + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", remainingTTL=" + getRemainingTTL() + "s" +
                '}';
    }
}