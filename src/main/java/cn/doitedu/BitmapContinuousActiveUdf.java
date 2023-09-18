package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.roaringbitmap.RoaringBitmap;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapContinuousActiveUdf extends UDF {

    public boolean evaluate(byte[] bitmapBytes, Integer start, Integer end) throws IOException {

        // 反序列化成bitmap对象
        RoaringBitmap bm = RoaringBitmap.bitmapOf();
        bm.deserialize(ByteBuffer.wrap(bitmapBytes));

        // 求在指定范围内的基数，是否等于范围长度
        return bm.rangeCardinality(start, end + 1) == (end - start + 1);

    }

}
