import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Main {
    public static void main(String[] args) {
        float[][]  imageAFeatures=LoadFeatures.load("zdjecie1/image1.png.haraff.sift");
        float[][]  imageBFeatures=LoadFeatures.load("zdjecie2/image2.png.haraff.sift");

        int[][] pairsForImageA = new int[imageAFeatures.length][2];
        int[][] pairsForImageB = new int[imageBFeatures.length][2];

        for(int i=0;i<pairsForImageA.length;i++)
        {
            pairsForImageA[i][0] = 0;
            pairsForImageA[i][1] = Difference.difference(imageAFeatures[i], imageBFeatures[0]);
        }
        for(int i=0;i<pairsForImageB.length;i++)
        {
            pairsForImageB[i][0] = 0;
            pairsForImageB[i][1] = Difference.difference(imageAFeatures[0], imageBFeatures[i]);
        }



        //find closest for image A
        for (int i=0;i<pairsForImageA.length;i++)
        {
            for(int j=0;j<imageBFeatures.length;j++)
            {
                int difference = Difference.difference(imageAFeatures[i],imageBFeatures[j]);
                if(difference < pairsForImageA[i][1])
                {
                    pairsForImageA[i][0] = j;
                    pairsForImageA[i][1] = difference;
                }
            }
        }
        //find closest for image B
        for (int i=0;i<pairsForImageB.length;i++)
        {
            for(int j=0;j<imageAFeatures.length;j++)
            {
                int difference = Difference.difference(imageAFeatures[j],imageBFeatures[i]);
                if(difference < pairsForImageB[i][1])
                {
                    pairsForImageB[i][0] = j;
                    pairsForImageB[i][1] = difference;
                }
            }
        }


        //finding pairs where closest is the same
        int[] foundPairs;

        foundPairs = new int[imageAFeatures.length];
        for (int i =0;i<foundPairs.length;i++)
        {
            if(pairsForImageB[pairsForImageA[i][0]][0] == i)
            {
                foundPairs[i] = 1; }
        }


        int sum = 0;
        for (int i = 0;i<foundPairs.length;i++)
        {
            if(foundPairs[i]==1) sum++;
        }
        System.out.println(sum);
        System.out.println();
        /*for (int z =0;z<;z++)
        {
            System.out.println(foundPairs[z]);
        }*/

        SpojnoscSasiedztwa ss = new SpojnoscSasiedztwa(4,0.0f);
        int[] poSpojnosci = ss.zweryfikujSpojnosc(imageAFeatures,imageBFeatures,foundPairs,pairsForImageA,pairsForImageB);
        int sum2 = 0;
        for (int i = 0;i<poSpojnosci.length;i++)
        {
            if(poSpojnosci[i]==1) sum2++;
        }
        System.out.println(sum2);
        System.load("C://opencv/opencv/build/java/x64/opencv_java341.dll");
        Mat img = Imgcodecs.imread("image1.png");
        for(int i =0;i<poSpojnosci.length;i++)
        {
            if(poSpojnosci[i]== 1)
            {
                Point p1 = new Point(imageAFeatures[i][0], imageAFeatures[i][1]);
                Point p2 = new Point(imageBFeatures[pairsForImageA[i][0]][0] + 540, imageBFeatures[pairsForImageA[i][0]][1]);
                Imgproc.line(img, p1, p2, new Scalar(0,255,0), 1);
            }
        }
        Imgcodecs.imwrite("image2.png",img);
    }
}
