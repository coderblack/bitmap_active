package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.roaringbitmap.RoaringBitmap;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapUpdateUdf extends UDF {

    //  update_bm(bm.active_bitmap,'2023-09-14')
   public byte[] evaluate(byte[] active_bitmap_bytes, Integer dateInt) throws IOException {
       // 反序列化成bitmap对象
      RoaringBitmap bm = RoaringBitmap.bitmapOf();
      bm.deserialize(ByteBuffer.wrap(active_bitmap_bytes));

      // 然后给bitmap添加一天
      bm.add(dateInt);

      // 将更新好的bitmap序列化成byte[]
      ByteArrayOutputStream baOut = new ByteArrayOutputStream();
      DataOutputStream dataOut = new DataOutputStream(baOut);
      bm.serialize(dataOut);

      byte[] bmBytes = baOut.toByteArray();
      return bmBytes;
   }



}
