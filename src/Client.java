import java.util.Arrays;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    public String toString() {
        return name + ":" + riskScore;
    }
}

public class TrainConsistManagementApp {

    // Bubble Sort (Ascending by riskScore)
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break; // optimization
        }

        System.out.println("Bubble Sort (Ascending): " + Arrays.toString(arr));
        System.out.println("Swaps: " + swaps);
    }

    // Insertion Sort (Descending by riskScore + accountBalance)
    public static void insertionSort(Client[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Client key = arr[i];
            int j = i - 1;

            while (j >= 0 &&
                  (arr[j].riskScore < key.riskScore ||
                  (arr[j].riskScore == key.riskScore &&
                   arr[j].accountBalance < key.accountBalance))) {

                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println("Insertion Sort (Descending): " + Arrays.toString(arr));
    }

    // Top N highest risk clients
    public static void printTopRisk(Client[] arr, int topN) {
        System.out.print("Top " + topN + " risks: ");
        for (int i = 0; i < topN && i < arr.length; i++) {
            System.out.print(arr[i].name + "(" + arr[i].riskScore + ")");
            if (i < topN - 1 && i < arr.length - 1) System.out.print(", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        // Input
        Client[] clients = {
            new Client("clientC", 80, 5000),
            new Client("clientA", 20, 2000),
            new Client("clientB", 50, 3000)
        };

        System.out.println("Input: " + Arrays.toString(clients));
        System.out.println();

        // Bubble Sort (Ascending)
        Client[] bubbleArr = Arrays.copyOf(clients, clients.length);
        bubbleSort(bubbleArr);

        System.out.println();

        // Insertion Sort (Descending)
        Client[] insertionArr = Arrays.copyOf(clients, clients.length);
        insertionSort(insertionArr);

        System.out.println();

        // Top risks
        printTopRisk(insertionArr, 3);
    }
}
