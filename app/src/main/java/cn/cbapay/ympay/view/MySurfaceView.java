package cn.cbapay.ympay.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.io.InputStream;

import cn.cbapay.ympay.R;

/**
 * Created by Administrator on 2016/9/21.
 * wangkezheng
 */
public class MySurfaceView extends SurfaceView {
    private Paint paint;
    private int padding;
    private int bitpadding;
    private int src;
    private boolean index,isLand;
    int bitWidth = 0;
    int bitHeight = 0;
    float textWidth = 0;
    Bitmap mBitmap;

    public MySurfaceView(Context context) {
        super(context);
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setLand(boolean land) {
        isLand = land;
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.FILL); //绘制
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MySurfaceView);

        int paddings = ta.getInteger(R.styleable.MySurfaceView_padding,20);
        padding = dip2px(context,paddings);
        int bitpad = ta.getInteger(R.styleable.MySurfaceView_bitpadd,30);
        bitpadding = dip2px(context,bitpad);
        index = ta.getBoolean(R.styleable.MySurfaceView_index0,true);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerWidth = getWidth()/2;
        int centerHeight = getHeight()/2;
//        字体属性
        paint.setStrokeWidth(0);
        paint.setColor(Color.WHITE);//设置字体颜色
        paint.setTextSize(50);//设置字体大小
        textWidth =  paint.measureText("将身份证正面置于此区域，拍摄身份证");   //测量字体宽度
        Path path = new Path();
        RectF rect;
        if(isLand){
//            如果横屏
            int heights = getWidth()*2/5;
            //用矩形表示SurfaceView宽高
            rect = new RectF(centerWidth-centerHeight, centerHeight-heights/2, centerWidth + centerHeight, centerHeight+heights/2);
//            截取圆角矩形
            drawRoundRect(canvas, path, rect);
            if(index){
                setSrc(R.mipmap.frontimg);
//                设置图片属性
                setBitmap();
//                绘制文字
                canvas.drawText("将身份证正面置于此区域，拍摄身份证", centerWidth - textWidth / 2, centerHeight + heights / 2 + 80, paint);
//              绘制图片
                canvas.drawBitmap(mBitmap, centerWidth+centerHeight-bitpadding-bitWidth, centerHeight - bitHeight / 2-30, paint);
            }else{
                setSrc(R.mipmap.backimg);
//                设置图片属性
                setBitmap();
//              绘制文字
                canvas.drawText("将身份证背面置于此区域，拍摄身份证", centerWidth - textWidth / 2, centerHeight + heights / 2 + 80, paint);
//              绘制图片
                canvas.drawBitmap(mBitmap, centerWidth-centerHeight+bitpadding, centerHeight -heights/2+ bitpadding, paint);

            }

        }else {
//            竖屏
            int heights = getHeight()*2/5;
//          用矩形表示SurfaceView宽高
            rect = new RectF(padding, centerHeight - heights / 2, centerWidth * 2 - padding, centerHeight + heights / 2);
//            截取圆角矩形
            drawRoundRect(canvas, path, rect);
            if (index) {
                setSrc(R.mipmap.frontimg);
//                设置图片属性
                setBitmap();
//                绘制文字
                canvas.drawText("将身份证正面置于此区域，拍摄身份证", centerWidth - textWidth / 2, centerHeight + heights / 2 + 100, paint);
//              绘制图片
                canvas.drawBitmap(mBitmap, getWidth() - padding - bitWidth - bitpadding, centerHeight - bitHeight / 2, paint);
            } else {
                setSrc(R.mipmap.backimg);
//                设置图片属性
                setBitmap();
//                绘制文字
                canvas.drawText("将身份证背面置于此区域，拍摄身份证", centerWidth - textWidth / 2, centerHeight + heights / 2 + 100, paint);
//              绘制图片
                canvas.drawBitmap(mBitmap, padding + bitpadding, centerHeight - heights / 2 + bitpadding, paint);
            }
        }


    }

    /**
     * 设置图片属性
     *
     */
    private  void setBitmap(){
        InputStream is = getResources().openRawResource(src);
        mBitmap = BitmapFactory.decodeStream(is);
        bitWidth = mBitmap.getWidth();
        bitHeight = mBitmap.getHeight();

    }

    /**
     * 截取圆角矩形
     * @param canvas
     * @param path
     * @param rect
     */

    private void drawRoundRect(Canvas canvas, Path path, RectF rect) {
        //25.0f即是圆角半径
        path.addRoundRect(rect, 25.0f, 25.0f, Path.Direction.CCW);
        //裁剪画布，并设置其填充方式
        canvas.clipPath(path, Region.Op.XOR);
        canvas.drawARGB(136, 0, 0, 0);
        canvas.restore();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
