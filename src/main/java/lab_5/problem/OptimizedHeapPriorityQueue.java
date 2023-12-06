package lab_5.problem;
import java.util.Arrays;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.AbstractQueue;
import java.util.NoSuchElementException;

public class OptimizedHeapPriorityQueue<E extends Comparable<? super E>> extends AbstractQueue<E>
    implements java.io.Serializable {

  public static final int MAX_SIZE = Integer.MAX_VALUE - 8;
  private static final int DEFAULT_INITIAL_CAPACITY = 10;
  private static final int ROOT_INDEX = 0;
  private Object[] elements;

  private Comparator<? super E> comparator = new Comparator<>() {
    @Override
    public int compare(E o1, E o2) {
      return o1.compareTo(o2);
    }
  };

  private int numberOfElements;

  public OptimizedHeapPriorityQueue() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  public OptimizedHeapPriorityQueue(int capacity) {
    if (capacity < 1) {
      throw new IllegalArgumentException("Capacity must be 1 or higher");
    }

    elements = new Object[capacity];
  }

  public OptimizedHeapPriorityQueue(Collection<? extends E> c) {
    int newCapacity = calculateNewCapacity(c.size());
    elements = Arrays.copyOf(c.toArray(), newCapacity);
    numberOfElements = c.size();
    heapify();
  }

  public OptimizedHeapPriorityQueue(Comparator<? super E> cmp) {
    this(DEFAULT_INITIAL_CAPACITY);
    comparator = cmp;
  }

  public void enqueue(E newElement) {
    if (numberOfElements == elements.length) {
      grow();
    }

    siftUp(newElement);
    numberOfElements++;
  }

  private void grow() {
    int newCapacity = calculateNewCapacity(elements.length);
    elements = Arrays.copyOf(elements, newCapacity);
  }

  static int calculateNewCapacity(int currentCapacity) {
    if (currentCapacity == MAX_SIZE) {
      throw new IllegalStateException("Can't grow further");
    }

    int newCapacity = currentCapacity + calculateIncrement(currentCapacity);

    if (newCapacity > MAX_SIZE || newCapacity < 0 /* overflow */ ) {
      newCapacity = MAX_SIZE;
    }

    return newCapacity;
  }

  private static int calculateIncrement(int currentCapacity) {
    return currentCapacity < 64 ? currentCapacity : currentCapacity / 2;
  }

  private void siftUp(E newElement) {
    int insertIndex = numberOfElements;

    while (isNotRoot(insertIndex)) {
      int parentIndex = parentOf(insertIndex);

      if (!isParentGreater(parentIndex, newElement)) {
        break;
      }

      copyParentDownTo(parentIndex, insertIndex);
      insertIndex = parentIndex;
    }
    elements[insertIndex] = newElement;
  }

  private boolean isNotRoot(int index) {
    return index != ROOT_INDEX;
  }

  private boolean isParentGreater(int parentIndex, E element) {
    E parent = elementAt(parentIndex);
    return comparator.compare(parent, element) > 0;
  }

  private void copyParentDownTo(int parentIndex, int insertIndex) {
    elements[insertIndex] = elements[parentIndex];
  }

  private int parentOf(int insertIndex) {
    return (insertIndex - 1) / 2;
  }

  public E dequeue() {
    E result = elementAtHead();
    E lastElement = removeLastElement();
    siftDown(lastElement);
    return result;
  }

  private E removeLastElement() {
    numberOfElements--;
    E lastElement = elementAt(numberOfElements);
    elements[numberOfElements] = null;
    return lastElement;
  }

  protected void heapify() {
    int firstElementWithoutChildren = numberOfElements / 2;
    int currentElementIndex = firstElementWithoutChildren;
    E currentElement = elementAt(currentElementIndex);

    while (true) {
      siftDown(currentElement, currentElementIndex);

      if (currentElementIndex == ROOT_INDEX)
        break;

      --currentElementIndex;
      currentElement = elementAt(currentElementIndex);
    }
  }

  private void siftDown(E lastElement) {
    siftDown(lastElement, ROOT_INDEX);
  }

  private void siftDown(E element, int treeRoot) {
    int elementInsertIndex = treeRoot;
    int firstElementWithoutChildren = numberOfElements / 2;

    while (elementInsertIndex < firstElementWithoutChildren) {
      int smallestChildIndex = smallestChildOf(elementInsertIndex);
      E smallestChild = elementAt(smallestChildIndex);
      boolean elementGreaterThanSmallestChild = comparator.compare(element, smallestChild) > 0;
      
      if (!elementGreaterThanSmallestChild) {
        break;
      }

      moveSmallestChildUpTo(smallestChildIndex, elementInsertIndex);
      elementInsertIndex = smallestChildIndex;
    }
    elements[elementInsertIndex] = element;
  }

  private int smallestChildOf(int index) {
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = leftChildIndex + 1;
    if (!exists(rightChildIndex)) {
      return leftChildIndex;
    }
    return smallerOf(leftChildIndex, rightChildIndex);
  }

  private boolean exists(int index) {
    return index < numberOfElements;
  }

  private int smallerOf(int leftChildIndex, int rightChildIndex) {
    E leftChild = elementAt(leftChildIndex);
    E rightChild = elementAt(rightChildIndex);
    return comparator.compare(leftChild, rightChild) < 0 ? leftChildIndex : rightChildIndex;
  }

  private void moveSmallestChildUpTo(int smallestChildIndex,
      int parentIndex) {
    elements[parentIndex] = elements[smallestChildIndex];

  }

  @Override
  public E peek() {
    return elementAtHead();
  }

  private E elementAtHead() {
    E element = elementAt(0);
    if (element == null) {
      throw new NoSuchElementException();
    }
    return element;
  }

  private E elementAt(int child) {
    @SuppressWarnings("unchecked")
    E element = (E) elements[child];
    return element;
  }

  @Override
  public boolean isEmpty() {
    return numberOfElements == 0;
  }

  @Override
  public int size() {
    return numberOfElements;
  }

  @Override
  public boolean contains(Object o) {
    throw new UnsupportedOperationException("Unimplemented method 'contains'");
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      int cursor = 0;
      public boolean hasNext() {
        return !isEmpty() && cursor < size();
      }

      public E next() {
        if (cursor < size())
          return (E) elements[cursor++];
        throw new NoSuchElementException();
      }

    };

  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(elements, size());
  }

  @Override
  public <T> T[] toArray(T[] a) {
    final int size = numberOfElements;
    if (a.length < size)
      return (T[]) Arrays.copyOf(elements, size, a.getClass());
    System.arraycopy(elements, 0, a, 0, size);
    if (a.length > size)
      a[size] = null;
    return a;
  }

  @Override
  public boolean offer(E e) {
    enqueue(e);
    return true;
  }

  @Override
  public E poll() {
    return dequeue();
  }

  // @Override
  // public boolean remove(Object o) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'remove'");

  // }

  // @Override
  // public boolean containsAll(Collection<?> c) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method
  // 'containsAll'");

  // }

  // @Override
  // public boolean addAll(Collection<? extends E> c) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'addAll'");

  // }

  // @Override
  // public boolean removeAll(Collection<?> c) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'removeAll'");

  // }

  // @Override
  // public boolean retainAll(Collection<?> c) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'retainAll'");

  // }

  // @Override
  // public void clear() {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'clear'");

  // }

  // @Override
  // public boolean add(E e) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'add'");

  // }

  // @Override
  // public boolean offer(E e) {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'offer'");

  // }

  // @Override
  // public E remove() {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'remove'");

  // }

  // @Override
  // public E poll() {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'poll'");

  // }

  // @Override
  // public E element() {

  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method 'element'");

  // }
}