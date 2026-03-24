import java.util.ArrayList;
import java.util.List;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TrainConsistManagementApp {

    // Bubble Sort (by fee)
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // early termination
        }

        System.out.println("BubbleSort (fees): " + formatList(list));
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // Insertion Sort (by fee + timestamp)
    public static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            while (j >= 0 &&
                  (list.get(j).fee > key.fee ||
                  (list.get(j).fee == key.fee &&
                   list.get(j).timestamp.compareTo(key.timestamp) > 0))) {

                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("InsertionSort (fee+ts): " + formatList(list));
    }

    // Format output
    public static String formatList(List<Transaction> list) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // High-fee detection
    public static void findHighFee(List<Transaction> list) {
        List<Transaction> high = new ArrayList<>();

        for (Transaction t : list) {
            if (t.fee > 50) {
                high.add(t);
            }
        }

        if (high.isEmpty()) {
            System.out.println("High-fee outliers: none");
        } else {
            System.out.println("High-fee outliers: " + formatList(high));
        }
    }

    public static void main(String[] args) {

        // Input data
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Input transactions:");
        for (Transaction t : transactions) {
            System.out.println(t.id + ", fee=" + t.fee + ", ts=" + t.timestamp);
        }

        System.out.println();

        // Bubble Sort (small batch)
        List<Transaction> bubbleList = new ArrayList<>(transactions);
        bubbleSort(bubbleList);

        System.out.println();

        // Insertion Sort (medium batch)
        List<Transaction> insertionList = new ArrayList<>(transactions);
        insertionSort(insertionList);

        System.out.println();

        // High fee detection
        findHighFee(transactions);
    }
}
