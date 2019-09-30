public class SpojnoscSasiedztwa {

    int licznosc;
    float spojnosc;
    public SpojnoscSasiedztwa(int licznosc, float spojnosc)
    {
        this.licznosc = licznosc;
        this.spojnosc = spojnosc;
    }

    public int[] zweryfikujSpojnosc(float[][] featuresforA, float[][] featuresforB,int [] pairs,int[][] pairsforA,int [][] pairsforB)
    {
            int[] returnArray = new int[pairs.length];
            int[][] closestforA;
            int[][] closestforB;

            for(int i=0;i<pairs.length;i++)
            {
                if(pairs[i] ==1)
                {
                    closestforA = new int[licznosc][2];
                    for (int w= 0 ;w<licznosc;w++)
                    {
                        closestforA[w] = new int []{0,1000000};
                    }
                    for(int j =0;j<featuresforA.length;j++)
                    {
                        if(j!=i)
                        {
                            for(int k =0;k< closestforA.length;k++)
                            {
                                int temp = Difference.difference(featuresforA[j],featuresforA[i]);
                                if(temp < closestforA[k][1])
                                {
                                    closestforA[k][0]=j;
                                    closestforA[k][1] = temp;
                                    break;
                                }
                            }
                        }
                    }
                    /*for(int z = 0;z<4;z++)
                    {
                        System.out.println(closestforA[z][0]);
                    }*/

                    closestforB = new int[licznosc][2];
                    for (int w= 0 ;w<licznosc;w++)
                    {
                        closestforB[w] = new int []{0,1000000};
                    }
                    for(int j =0;j<featuresforB.length;j++)
                    {
                        if(j!=pairsforA[i][0])
                        {
                            for(int k =0;k< closestforB.length;k++)
                            {
                                int temp = Difference.difference(featuresforB[j],featuresforB[pairsforA[i][0]]);
                                if(temp < closestforB[k][1])
                                {
                                    closestforB[k][0]=j;
                                    closestforB[k][1] = temp;
                                    break;
                                }
                            }
                        }
                    }
                    /*for(int z = 0;z<4;z++)
                    {
                        System.out.println(closestforB[z]);
                    }*/

                    int matched = 0;

                    for(int p = 0;p<closestforA.length;p++)
                    {
                        for(int q = 0;q<closestforB.length;q++) {
                            if (pairsforA[closestforA[p][0]][0] == closestforB[q][0])
                            {
                                matched++;
                            }
                        }
                    }
                    if(licznosc*spojnosc <= matched)
                    {
                        returnArray[i] = 1;
                    }
                    else
                    {
                        returnArray[i]=0;
                    }
                }
            }


            return returnArray;
    }
}
