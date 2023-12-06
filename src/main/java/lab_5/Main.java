package lab_5;

import lab_5.menu.ProgrammMenu;

public class Main {
    // static ProgrammMenu pm = new ProgrammMenu();
    public static void test() {
        while (ProgrammMenu.isNotClosed()) {
            // ConsoleUtils.clearConsole();
            System.out.println("Please choose an option.");
            ProgrammMenu.getMainMenu().show();
        }
    }

/*     public static void testMethods() {
        Integer[] array = new Integer[] { 198, 76, 544, 123, 154, 675 };

        OptimizedHeapPriorityQueue<Integer> opq = new OptimizedHeapPriorityQueue<Integer>(new Comparator<>() {

            @Override
            public int compare(Integer i1, Integer i2) {
                return i2 - i1;
            }

        });

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(
                new Comparator<>() {

                    @Override
                    public int compare(Integer i1, Integer i2) {
                        return i2 - i1;
                    }

                });

        pq.addAll(Arrays.asList(array));

        opq.enqueue(new Integer(5));
        opq.enqueue(new Integer(15));
        opq.enqueue(new Integer(25));
        opq.enqueue(new Integer(-5));
        opq.enqueue(new Integer(1));
        for (Integer n : opq)

            System.out.print(n + " ");

        System.out.println();

        for (Integer n : pq)

            System.out.print(n + " ");

        ProblemsSolutions.kthSum(array, 4);
        System.out.println();

        // ProblemsSolutions.mergeMaxHeaps(new int[] { 10, 5, 6, 2 }, new int[] { 12, 7, 9 });
        // System.out.println();

        ProblemsSolutions.firstTrans(new Integer[] { 10, 5, 12, 7, 96, 2 });

        System.out.println();

        Integer[][] arr5 = new Integer[][] { { 1, 6, 8 }, { 2, 4, 5, 12 }, { 11 }, { 2, 5, 8, 13, 13, 18, 21 } };
        ProblemsSolutions.mergeKArrays(arr5);

        System.out.println();
        ProblemsSolutions.CompOfK(array, 2);

    }

 */
    public static void main(String[] args) {
        test();
    }
}
