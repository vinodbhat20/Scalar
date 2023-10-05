import java.util.ArrayList;
import java.util.Arrays;

public class PaintersProblem {
    public static void main(String[] args) {
//        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(904,531,64,992,522,850,639));
        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(1000000,1000000));
        System.out.println(paint( 1,1000000, A));
    }


    public static int paint(int A, int B, ArrayList<Integer> C) {
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (Integer integer : C) {
            if (max < integer) {
                max = integer;
            }
            sum += integer;
        }
        long start = max * B;
        long end = sum * B;
        return (int)findTime(C, A, B, start, end);
    }

    private static long findTime(ArrayList<Integer> A, int painters, int timePerUnit, long start, long end) {
        long mid = (start + end) / 2;
        if (findMinPainters(A, mid, timePerUnit) <=painters) {
            end = mid  -1;
        } else {
            start = mid + 1;
        }
        if (end < start) {
            if (findMinPainters(A, mid, timePerUnit) <= painters) {
                return mid % 10000003;
            } else {
                return (mid + 1)%10000003;
            }
        }
        return findTime(A, painters, timePerUnit, start, end);
    }

    public static int findMinPainters(ArrayList<Integer> A, long totalTime, int timePerUnit) {
        int painters = 1;
        long timeLeft = totalTime;

        for (Integer board : A) {
            long timeToPaintBoard = (long) board * timePerUnit;
            if (timeToPaintBoard > totalTime) {
                return -1;
            }
            if (timeToPaintBoard <= timeLeft) {
                timeLeft -= timeToPaintBoard;
            } else {
                painters++;
                timeLeft = totalTime - timeToPaintBoard;
            }
        }
        return painters;

    }

}