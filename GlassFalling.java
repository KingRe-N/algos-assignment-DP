/**
 * Glass Falling
 * Author: Jen Chung Liew
 */
public class GlassFalling {

    // Do not change the parameters!
    public int glassFallingRecur(int floors, int sheets) {

        // base cases
        if (floors == 0 || floors == 1) return floors;
        if (sheets == 1) return floors;

        int minDrops = Integer.MAX_VALUE;
        int tempResult;

        for (int x = 1; x <= floors; x++) {
            tempResult = Math.max(glassFallingRecur(x - 1,sheets - 1),
                                  glassFallingRecur(floors - x, sheets));
            minDrops = Math.min(tempResult,minDrops);
        }

        return minDrops + 1;
    }

    // Optional:
    // Pick whatever parameters you want to, just make sure to return an int.
    public int glassFallingMemoized(int floors, int sheets, int memoArr[][]) {

        //base cases
        if (floors == 0 || floors == 1) return memoArr[floors][sheets] = floors;
        if (sheets == 1) return memoArr[floors][sheets] = floors;

        if (memoArr[floors][sheets] > 0) return memoArr[floors][sheets];

        int minDrops = floors + 1;
        int tempResult;

        for (int x = 1; x <= floors; x++) {
            tempResult = Math.max(glassFallingMemoized(x - 1,sheets - 1, memoArr),
                                  glassFallingMemoized(floors - x, sheets, memoArr));
            minDrops = Math.min(tempResult,minDrops);
        }

        memoArr[floors][sheets] = minDrops + 1;

        return memoArr[floors][sheets];
    }

    // Do not change the parameters!
    public int glassFallingBottomUp(int floors, int sheets) {
        int [][] numDrops = new int[sheets + 1][floors + 1];

        // base cases
        // 0 floor or 1 floor
        for (int i = 1; i <=sheets ; i++) {
            numDrops[i][0] = 0;
            numDrops[i][1] = 1;
        }
        // 1 egg
        for (int i = 1; i <=floors ; i++) {
            numDrops[1][i] = i;
        }

        for (int i = 2; i <= sheets; i++)
        {
            for(int j = 2; j <= floors; j++)
            {
                numDrops[i][j] = Integer.MAX_VALUE;
                int tempResult;
                for(int k = 1; k <= j; k++)
                {
                    tempResult = 1 + Math.max(numDrops[i-1][k-1], numDrops[i][j-k]);
                    numDrops[i][j] = Math.min(tempResult,numDrops[i][j]);
                }
            }
        }
        return numDrops[sheets][floors];
    }


    public static void main(String args[]){
        GlassFalling gf = new GlassFalling();

        //set values from email [floors + 1] [sheets + 1] thus,
        int memoArr[][] = new int[101][4];

        // Do not touch the below lines of code, and make sure
        // in your final turned-in copy, these are the only things printed
        int minTrials1Recur = gf.glassFallingRecur(27, 2);
        int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);

        int minTrials2Memo = gf.glassFallingMemoized(100, 3, memoArr);
        int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
        System.out.println(minTrials1Recur + " " + minTrials1Bottom);
        System.out.println(minTrials2Memo + " " + minTrials2Bottom);
    }
}
