class RiskLookup {

    static int linearComparisons = 0;
    static int binaryComparisons = 0;

    // ================= LINEAR SEARCH =================
    public static int linearSearch(int[] arr, int target) {
        linearComparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            linearComparisons++;
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // ================= BINARY SEARCH =================
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        binaryComparisons = 0;

        while (low <= high) {
            binaryComparisons++;
            int mid = (low + high) / 2;

            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }

    // ================= LOWER BOUND (first ≥ target) =================
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // ================= UPPER BOUND (first > target) =================
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // ================= FLOOR =================
    public static Integer floor(int[] arr, int target) {
        int idx = lowerBound(arr, target);
        if (idx == 0) return null; // no smaller element
        return arr[idx - 1];
    }

    // ================= CEILING =================
    public static Integer ceiling(int[] arr, int target) {
        int idx = lowerBound(arr, target);
        if (idx == arr.length) return null; // no larger element
        return arr[idx];
    }

    // ================= INSERTION POINT =================
    public static int insertionPoint(int[] arr, int target) {
        return lowerBound(arr, target);
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100}; // sorted

        int target = 30;

        // Linear Search
        int linearIndex = linearSearch(risks, target);
        System.out.println("Linear Search Index: " + linearIndex);
        System.out.println("Linear Comparisons: " + linearComparisons);

        // Binary Search
        int binaryIndex = binarySearch(risks, target);
        System.out.println("\nBinary Search Index: " + binaryIndex);
        System.out.println("Binary Comparisons: " + binaryComparisons);

        // Floor & Ceiling
        Integer floorVal = floor(risks, target);
        Integer ceilVal = ceiling(risks, target);

        System.out.println("\nFloor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);

        // Insertion Point
        int pos = insertionPoint(risks, target);
        System.out.println("Insertion Point: " + pos);
    }
}
