package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.roaringbitmap.RoaringBitmap;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapMaxContinuousActiveUdf extends UDF {

    public int evaluate(byte[] bitmapBytes, Integer start, Integer end) throws IOException {
        RoaringBitmap bm = RoaringBitmap.bitmapOf();
        bm.deserialize(ByteBuffer.wrap(bitmapBytes));

        RoaringBitmap maskBitmap = RoaringBitmap.bitmapOfRange(start, end + 1);
        bm.and(maskBitmap);

        // 求指定区间中最大的连续1个数
        int[] arr = bm.toArray();
        int tmp = 1;
        int max = tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + 1 == arr[i + 1]) {
                tmp++;
                if (tmp > max ) max = tmp;
            } else {
                if (tmp > max) max = tmp;
                tmp = 1;
            }
        }

        return max;
    }
}
