
public class Temp {
    public static void main(String[] args) {
        // calculate averate of 100 numbers
        // get current time
        long startTime = System.currentTimeMillis();
        int [] list = new int[10000];
        for(int i = 0; i < list.length; i++) {
            // random number
            list[i] = (int)(Math.random() * 1000);
        }

        int sum = 0;
        for(int i = 0; i < list.length; i++) {
            sum += list[i];
        }


        System.out.println("Average: " + sum / list.length);

        long endTime = System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

    }
}
