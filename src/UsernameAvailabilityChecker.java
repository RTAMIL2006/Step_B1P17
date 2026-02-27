import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class UsernameAvailabilityChecker {
    // Map to hold usernames and their frequency counts
    private ConcurrentHashMap<String, AtomicInteger> userFrequencyMap;

    public UsernameAvailabilityChecker() {
        userFrequencyMap = new ConcurrentHashMap<>();
    }

    // Check if the username is available
    public boolean isUsernameAvailable(String username) {
        return !userFrequencyMap.containsKey(username);
    }

    // Register a username
    public void registerUsername(String username) throws Exception {
        if (isUsernameAvailable(username)) {
            userFrequencyMap.put(username, new AtomicInteger(1));
        } else {
            throw new Exception("Username is already taken.");
        }
    }

    // Track usage frequency
    public void trackUsernameUsage(String username) {
        userFrequencyMap.get(username).incrementAndGet();
    }

    // Suggest alternatives for taken usernames
    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();
        String base = username;
        int count = 1;
        while (suggestions.size() < 5) {
            String newSuggestion = base + count;
            if (isUsernameAvailable(newSuggestion)) {
                suggestions.add(newSuggestion);
            }
            count++;
        }
        return suggestions;
    }

    public static void main(String[] args) {
        UsernameAvailabilityChecker checker = new UsernameAvailabilityChecker();
        try {
            // Example usage:
            String username = "user123";
            if (checker.isUsernameAvailable(username)) {
                checker.registerUsername(username);
                System.out.println(username + " is registered.");
            } else {
                System.out.println(username + " is taken. Suggestions: " + checker.suggestAlternatives(username));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}