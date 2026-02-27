import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DNSCacheTest {
    private DNSCache dnsCache;

    @BeforeEach
    public void setUp() {
        dnsCache = new DNSCache();
    }

    @Test
    public void testAddEntry() {
        dnsCache.addEntry("example.com", "192.168.1.1");
        assertEquals("192.168.1.1", dnsCache.getEntry("example.com"));
    }

    @Test
    public void testGetEntry_NotFound() {
        assertNull(dnsCache.getEntry("notfound.com"));
    }

    @Test
    public void testRemoveEntry() {
        dnsCache.addEntry("example.com", "192.168.1.1");
        dnsCache.removeEntry("example.com");
        assertNull(dnsCache.getEntry("example.com"));
    }

    @Test
    public void testCacheExpiry() throws InterruptedException {
        dnsCache.addEntry("temp.com", "192.168.1.10", 1); // expires in 1 second
        Thread.sleep(2000);
        assertNull(dnsCache.getEntry("temp.com"));
    }

    @Test
    public void testOverwriteEntry() {
        dnsCache.addEntry("example.com", "192.168.1.1");
        dnsCache.addEntry("example.com", "192.168.1.2");
        assertEquals("192.168.1.2", dnsCache.getEntry("example.com"));
    }
}