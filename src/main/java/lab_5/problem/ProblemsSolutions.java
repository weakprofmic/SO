package lab_5.problem;
import java.util.Arrays;
import java.util.Comparator;

import static lab_5.utils.ProblemsUtils.*;

public class ProblemsSolutions {

  public static void mergeKArrays(Number[][] arr) {
    OptimizedHeapPriorityQueue<ArrElement<Double>> opq = new OptimizedHeapPriorityQueue<ArrElement<Double>>();
    for (var i = 0; i < arr.length; ++i) {
      opq.enqueue(new ArrElement<Double>(arr[i][0].doubleValue(), i, 1));
    }

    while (!opq.isEmpty()) {
      ArrElement<Double> minElem = opq.dequeue();
      int arrInd = minElem.arrIndex;
      int ind = minElem.nextIndex;
      if (ind < arr[arrInd].length)
        opq.enqueue(new ArrElement<>(arr[arrInd][ind].doubleValue(), arrInd, ind + 1));
      System.out.print(minElem.value + " ");

    }
  }

  /**
   * Учитывая последовательность S = {1, 2, 3 принадлежащей N} найдите
   * лексикографически наименьшее (самое раннее по порядку в словаре)
   * отклонение из S.
   * Перестановкой S называется любая перестановка S, при которой ни один из
   * двух элементов S и ее перестановки не встречаются в одной и той же позиции.
   */
  public static void firstTrans(Number[] arr) {
    OptimizedHeapPriorityQueue opq = new OptimizedHeapPriorityQueue(Arrays.asList(arr));

    int i = 0;
    int memo = 0;
    boolean haveMemo = false;
    while (!opq.isEmpty()) {
      if (opq.peek() != arr[i]) {
        System.out.print(opq.dequeue() + " ");
        ++i;
        if (haveMemo) {
          haveMemo = false;
          System.out.print(memo + " ");
          ++i;
        }
      } else {
        haveMemo = true;
        memo = (int) opq.dequeue();
      }

    }

    if (haveMemo)
      System.out.print(memo);
  }

  public static void mergeMaxHeaps(Number[] arr1, Number[] arr2) {
    int i = 0, j = 0;
    boolean ivis = false, jvis = false;
    int n = arr1.length, m = arr2.length;

    while (i < n && j < m) {
      int maxi = i;
      int maxj = j;

      if (i < n - 1 && !ivis && (arr1[i + 1].doubleValue() > arr1[i].doubleValue()))
        maxi = i + 1;

      if (j < m - 1 && !jvis && arr2[j + 1].doubleValue() > arr2[j].doubleValue())
        maxj = j + 1;

      if (arr1[maxi].doubleValue() > arr2[maxj].doubleValue()) {
        System.out.print(arr1[maxi] + " ");
        if (maxi == i + 1) {
          ivis = true;
        } else {
          if (ivis) {
            ivis = false;
            i += 2;
          } else
            ++i;

        }
      } else {
        System.out.print(arr2[maxj] + " ");
        if (maxj == j + 1) {
          jvis = true;
        } else {
          if (jvis) {
            jvis = false;
            j += 2;
          } else
            ++j;

        }
      }

      if (i == n) {
        if (jvis) {
          System.out.print(arr2[j += 2] + " ");
        }
        while (j < m) {
          System.out.print(arr2[j++] + " ");
        }
      }

      else if (j == m) {
        if (ivis) {
          System.out.print(arr1[i += 2] + " ");
        }
        while (i < n) {
          System.out.print(arr1[i++] + " ");
        }
      }
    }
  }

  public static void kthSum(Number[] array, int k) {
    System.out.println(array.length + " " + array[0].doubleValue());
    OptimizedHeapPriorityQueue<Double> pq = new OptimizedHeapPriorityQueue<>();
    Number[] prefixSums = new Double[array.length + 1];
    int i = 0, j;
    prefixSums[0] = 0d;
    while (++i <= array.length) {
      prefixSums[i] = prefixSums[i - 1].doubleValue() + array[i - 1].doubleValue();
    }
    System.out.println();
    for (var n : prefixSums)
      System.out.print(n + " ");

    i = 0;
    while (++i <= array.length) {
      j = i;
      while (j <= array.length) {
        double sum = prefixSums[j++].doubleValue() - prefixSums[i - 1].doubleValue();

        if (pq.size() < k) {
          pq.enqueue(sum);
        }

        else if (sum > pq.peek()) {
          pq.dequeue();
          pq.enqueue(sum);
        }
      }
    }
    System.out.println();
    System.out.println(pq.peek());
    for (var s : pq) {
      System.out.println(s);
    }
  }

  public static void CompOfK(Number[] array, int k) {
    OptimizedHeapPriorityQueue<Double> pq = new OptimizedHeapPriorityQueue<>(new Comparator<>() {

      @Override
      public int compare(Double i1, Double i2) {
        return (int) (i2 - i1);
      }

    });
    int i = -1;
    while (++i < k)
      pq.enqueue(array[i].doubleValue());

    while (i < array.length) {
      if (pq.peek() > array[i].doubleValue()) {
        pq.dequeue();
        pq.enqueue(array[i].doubleValue());
      }
      ++i;
    }

    int comp = 1;
    for (var s : pq) {
      comp *= s;
    }
    System.out.println(comp);
  }
}