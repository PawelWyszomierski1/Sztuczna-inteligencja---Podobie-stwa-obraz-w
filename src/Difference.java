public class Difference {
    public static int difference(float[] arrayA, float[] arrayB){
        int difference = 0;
        for(int i =5;i<arrayA.length;i++)
        {
            difference+=Math.abs(arrayA[i]-arrayB[i]);
        }
        return difference;
    }
}
