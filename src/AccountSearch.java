class AccountSearch {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // ================= LINEAR SEARCH =================
    public static int linearFirst(String[] arr, String target) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static int linearLast(String[] arr, String target) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i].equals(target)) {
                index = i;
            }
        }
        return index;
    }

    // ================= BINARY SEARCH =================
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ================= COUNT OCCURRENCES =================
    public static int countOccurrences(String[] arr, String target) {
        int index = binarySearch(arr, target);
        if (index == -1) return 0;

        int count = 1;

        // Left side
        int i = index - 1;
        while (i >= 0 && arr[i].equals(target)) {
            count++;
            i--;
        }

        // Right side
        i = index + 1;
        while (i < arr.length && arr[i].equals(target)) {
            count++;
            i++;
        }

        return count;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        // NOTE: Binary search requires sorted array
        String[] logs = {"accA", "accB", "accB", "accC"};

        String target = "accB";

        // Linear Search
        int first = linearFirst(logs, target);
        int last = linearLast(logs, target);

        System.out.println("Linear First Occurrence: " + first);
        System.out.println("Linear Last Occurrence: " + last);
        System.out.println("Linear Comparisons: " + linearComparisons);

        // Binary Search
        int index = binarySearch(logs, target);
        int count = countOccurrences(logs, target);

        System.out.println("\nBinary Search Index: " + index);
        System.out.println("Binary Comparisons: " + binaryComparisons);
        System.out.println("Count of Occurrences: " + count);
    }
}
