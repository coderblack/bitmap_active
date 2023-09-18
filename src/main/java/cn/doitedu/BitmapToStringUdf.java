package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.roaringbitmap.RoaringBitmap;

import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapToStringUdf extends UDF {

    public String evaluate(byte[] bitmapBytes) throws IOException {
        // 反序列化成bitmap对象
        RoaringBitmap bm = RoaringBitmap.bitmapOf();
        bm.deserialize(ByteBuffer.wrap(bitmapBytes));
        return bm.toString();
    }

}
