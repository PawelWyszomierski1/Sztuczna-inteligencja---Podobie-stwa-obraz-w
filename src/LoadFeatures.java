import java.io.BufferedReader;
import java.io.FileReader;

public class LoadFeatures {
    public static float[][] load(String fileName)
    {
        float[][] returnMatrix = null;
        int sizeDim1;
        int sizeDim2;
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            sizeDim1 = Integer.parseInt(br.readLine());
            sizeDim2 = Integer.parseInt(br.readLine());
            returnMatrix = new float[sizeDim2][sizeDim1+5];
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split(" ");
                for(int j= 0;j<splitLine.length;j++)
                {
                    returnMatrix[i][j] = Float.parseFloat(splitLine[j]);
                }
                i++;
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnMatrix;
    }
}
