import org.jetbrains.annotations.NotNull;

public class SortDemo {

    static void bubbleSortDesc(int @NotNull [] a) {
        for (int i = 0; i < a.length - 1; i++)
            for (int j = 0; j < a.length - i - 1; j++)
                if (a[j] < a[j + 1]) swap(a, j, j + 1);
    }

    static void selectionSortDesc(int @NotNull [] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < a.length; j++)
                if (a[j] > a[max]) max = j;
            swap(a, i, max);
        }
    }

    static void selectionSortSmallestToEnd(int @NotNull [] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int min = 0;
            for (int j = 1; j <= i; j++)
                if (a[j] < a[min]) min = j;
            swap(a, i, min);
        }
    }

    // ---------- Helpers ----------
    static void swap(int @NotNull [] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    static void printArray(String msg, @org.jetbrains.annotations.NotNull int @NotNull [] a) {
        System.out.print(msg + " ");
        for (int n : a) System.out.print(n + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr1 = {9, 3, 2, 1, 7};
        int[] arr2 = arr1.clone();
        int[] arr3 = arr1.clone();

    }
}
