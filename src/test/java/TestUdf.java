import cn.doitedu.BitmapCreateUdf;
import cn.doitedu.BitmapMaxContinuousActiveUdf;
import cn.doitedu.BitmapUpdateUdf;
import org.roaringbitmap.RoaringBitmap;

import java.io.IOException;
import java.nio.ByteBuffer;

public class TestUdf {
    public static void main(String[] args) throws IOException {

        BitmapCreateUdf bitmapCreateUdf = new BitmapCreateUdf();
        BitmapUpdateUdf bitmapUpdateUdf = new BitmapUpdateUdf();
        BitmapMaxContinuousActiveUdf maxActUdf = new BitmapMaxContinuousActiveUdf();

        // 模拟生成一个人的bitmap
        byte[] originBitmap = bitmapCreateUdf.evaluate(100);


        // 模拟添加一个活跃日
        byte[] bitmap2 = bitmapUpdateUdf.evaluate(originBitmap, 101);
        byte[] bitmap3 = bitmapUpdateUdf.evaluate(bitmap2, 102);
        byte[] bitmap4 = bitmapUpdateUdf.evaluate(bitmap3, 103);
        byte[] bitmap5 = bitmapUpdateUdf.evaluate(bitmap4, 105);
        byte[] bitmap6 = bitmapUpdateUdf.evaluate(bitmap5, 106);
        byte[] bitmap7 = bitmapUpdateUdf.evaluate(bitmap6, 107);
        byte[] bitmap8 = bitmapUpdateUdf.evaluate(bitmap7, 108);
        byte[] bitmap9 = bitmapUpdateUdf.evaluate(bitmap8, 110);
        byte[] bitmap10 = bitmapUpdateUdf.evaluate(bitmap9, 112);


        RoaringBitmap tmpBm = RoaringBitmap.bitmapOf();
        tmpBm.deserialize(ByteBuffer.wrap(bitmap10));
        System.out.println(tmpBm);


        // 求最大连续活跃天数
        int max = maxActUdf.evaluate(bitmap10, 101, 120);
        System.out.println(max);


    }

}
