package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.roaringbitmap.RoaringBitmap;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapActiveDaysUdf extends UDF {

    public Integer evaluate(byte[] bitmapBytes, Integer startDiff, Integer endDiff) throws IOException {
        // 反序列化成bitmap对象
        RoaringBitmap bm = RoaringBitmap.bitmapOf();
        bm.deserialize(ByteBuffer.wrap(bitmapBytes));

        // 调用roaringBitmap的api，去计算指定脚标范围内的1的个数
        // 返回计算出来的1的个数
        return (int)bm.rangeCardinality(startDiff,endDiff+1);
    }

}
