package cn.doitedu;

import org.apache.hadoop.hive.ql.exec.UDF;

public class BitmapActiveDaysUdf extends UDF {

   public Integer evaluate(byte[] bitmapBytes,Integer startDiff,Integer endDiff){
       // 反序列化成bitmap对象

      // 调用roaringBitmap的api，去计算指定脚标范围内的1的个数


      // 返回计算出来的1的个数



      return 0;
   }

}
