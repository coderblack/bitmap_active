package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.roaringbitmap.RoaringBitmap;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class BitmapCreateUdf extends UDF {

   public byte[] evaluate(Integer dateInt) throws IOException {
      // 反序列化成bitmap对象
      RoaringBitmap bm = RoaringBitmap.bitmapOf();
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
