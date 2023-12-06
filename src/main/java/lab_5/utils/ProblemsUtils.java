package lab_5.utils;

public class ProblemsUtils {
  public static class ArrElement<E extends Comparable<? super E>>
      implements Comparable<ArrElement<E>> {
    public E value;

    public int arrIndex;
    public int nextIndex;

    public ArrElement(E v, int ai, int ni) {
      value = v;
      arrIndex = ai;
      nextIndex = ni;
    }

    @Override
    public int compareTo(ArrElement<E> o) {
      return this.value.compareTo(o.value);
    }

  }
}