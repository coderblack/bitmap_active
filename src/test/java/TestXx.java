import org.roaringbitmap.RoaringBitmap;

import java.util.Arrays;

public class TestXx {

    public static void main(String[] args) {


        RoaringBitmap bm = RoaringBitmap.bitmapOf(2, 4, 5, 6, 8, 9, 10, 12, 14, 20, 33);

        RoaringBitmap mask = RoaringBitmap.bitmapOfRange(4, 14 + 1);
        bm.and(mask);

        System.out.println(Arrays.toString(bm.toArray()));

        System.exit(1);


        // 4~10之间1的个数
        long cnt = bm.rangeCardinality(4, 11);
        //System.out.println(cnt);

        int[] arr = bm.toArray();


        int tmp = 1;
        int max = tmp;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]+1 ==  arr[i+1]){
                tmp++;
            }else{
                if(tmp>max) max = tmp;
                tmp = 1;
            }
        }

        System.out.println(max);


    }
}
