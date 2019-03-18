package cn.cbapay.ympay.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Environment;
import android.view.SurfaceView;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.cbapay.ympay.R;
import cn.cbapay.ympay.bean.WrapCameraSize;
import cn.cbapay.ympay.mvp.presenter.OnCaptureCallback;


/**
 * Created by Administrator on 2016/9/19.
 * wangkezheng
 */
public class CaremaUtils {

    public static String TAG = "CaremaUtils";



    //	闪光灯模式
    public static String flashlightStatus = Camera.Parameters.FLASH_MODE_OFF;
    private static Camera.Size pictureSize;

    //    public static int mIndex = 0;
    public static Camera.CameraInfo cameraInfo = new Camera.CameraInfo();



    /**
     * 开始预览
     */
    public static void startPreview(Camera camera){
        if(camera!=null){
            camera.startPreview();
            camera.autoFocus(null);
        }
    }
    /**
     * 闪光灯开关
     */
    public static void changelight(Camera camera, SurfaceView surfaceView , ImageButton img, Boolean isReserved){
        if(flashlightStatus == Camera.Parameters.FLASH_MODE_OFF){
            flashlightStatus = Camera.Parameters.FLASH_MODE_ON;
            img.setImageResource(R.mipmap.flash_default);
        }else{
            flashlightStatus = Camera.Parameters.FLASH_MODE_OFF;
            img.setImageResource(R.mipmap.flash);
        }
        camera.stopPreview();
        setCameraPreView(camera,surfaceView,isReserved);
    }

    /**
     *
     * 切换摄像头
     */
    public static int changeCamera(Camera mCamera, int mIndex) {
//        更换相机   1 为前置摄像头，0为后置摄像头
        int index = mIndex;

        int cameraCount = Camera.getNumberOfCameras();//得到摄像头的个数


        for(int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);//得到每一个摄像头的信息

            if (mIndex == 0) {
                //现在是后置，变更为前置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                    index = 1;
                    break;
                }
            } else {
                //现在是前置， 变更为后置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//代表摄像头的方位，CAMERA_FACING_FRONT前置
                    index = 0;
                    break;
                }
            }

        }
        mCamera.stopPreview();//停掉原来摄像头的预览
        mCamera.release();//释放资源
        mCamera = null;//取消原来摄像头

        return  index;
    }

    /**
     * 开启相机
     * @param surfaceView
     * @param mCamera
     * @param isReserved
     */
    public static void openCamera(SurfaceView surfaceView, Camera mCamera, Boolean isReserved) {
        try {
            mCamera.setPreviewDisplay(surfaceView.getHolder());//关联surfaceview，成为相机的预览界面
            setCamra(mCamera,surfaceView,isReserved);
            mCamera.startPreview();//开启预览
            mCamera.autoFocus(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setCameraPreView(Camera mCamera, SurfaceView surfaceView, Boolean isReserved){
        if(mCamera!=null){
            setCamra(mCamera,surfaceView,isReserved);
            try {
                mCamera.setPreviewDisplay(surfaceView.getHolder());
                mCamera.startPreview();
            } catch (IOException e) {
                LogUtil.e(TAG,"相机转换失败!"+e.getMessage());
            }
        }else{
            LogUtil.e(TAG,"相机初始化失败!");
        }
    }

    /**
     * 设置相机参数
     * @param mCamera
     * @param surfaceView
     * @param isReserved
     */
    public static void setCamra(Camera mCamera, SurfaceView surfaceView, Boolean isReserved) {

        Camera.Parameters parameters = mCamera.getParameters();

        //			照片质量
        parameters.set("jpeg-quality", 100);

//			设置照片格式
        parameters.setPictureFormat(PixelFormat.JPEG);

//			设置闪光灯
        parameters.setFlashMode(flashlightStatus);
        if (parameters.getSupportedFocusModes().contains(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
        }
        //        设置分辨率
        setCameraSize(parameters,surfaceView.getWidth(),surfaceView.getHeight());

        mCamera.setParameters(parameters);
        if(surfaceView.getHeight()>surfaceView.getWidth()) {
//            竖屏
            mCamera.setDisplayOrientation(90);//预览结果屏幕非正常显示 需要旋转屏幕
        }else{
            if(isReserved){
                mCamera.setDisplayOrientation(180);
            }
        }

    }

    /**
     * 拍照调用的方法
     * @param callback 回调
     * @param camera    相机
     * @param surfaceView
     * @param context
     * @param flag  是否是手持身份证
     * @param isReserved 横屏时手机屏幕是不是反的
     * @param mIndex
     */
    public static void takePhotos(final OnCaptureCallback callback, Camera camera, final SurfaceView surfaceView, final Context context, final Boolean flag, final boolean isReserved, final int mIndex) {
//        进行拍照
        camera.takePicture(null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                String filepath = savePicture(data,surfaceView,context,flag,isReserved,mIndex);
                boolean success = false;
                if(filepath != null){
                    success = true;
                }
                camera.stopPreview();
                callback.onCapture(success,filepath);
            }
        });
    }


    /**
     * 裁剪身份证照片
     * @param data  拍完后用于生成照片的数组
     * @return
     */
    public static Bitmap cutImage(byte[] data, SurfaceView surfaceView, Context context, boolean isReserved,int mIndex){
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        int height;
        Matrix matrix = new Matrix();
        if(surfaceView.getWidth() < surfaceView.getHeight()){
//			竖屏旋转照片
            height = 2*surfaceView.getHeight()/5;
            matrix.reset();

            rotate(mIndex, matrix);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if(surfaceView == null){
                return bitmap;
            }else{

                int w = bitmap.getWidth();
                int h = bitmap.getHeight();
                int x = 0;
                int y = (h-height)/2;
                return Bitmap.createBitmap(bitmap, x, y, w, height);
            }
        }else{
//                横屏时手机屏幕是反的
            if(isReserved){
                matrix.reset();
                matrix.setRotate(180);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }

            height = 2*surfaceView.getWidth()/5;
            if(surfaceView == null){
                return bitmap;
            }else{
//                int w = bitmap.getWidth();
                int h = bitmap.getHeight();
                int y = (h-height)/2;
                return Bitmap.createBitmap(bitmap, (surfaceView.getWidth()-surfaceView.getHeight())/2-dip2px(context,20), y-dip2px(context,20), surfaceView.getHeight(), height);
            }
        }


    }

    /**
     * 预览结果屏幕非正常显示 旋转屏幕
     * @param mIndex
     * @param matrix
     */
    private static void rotate(int mIndex, Matrix matrix) {
        int cameraCount = Camera.getNumberOfCameras();//得到摄像头的个数
        for(int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, cameraInfo);//得到每一个摄像头的信息
            if (mIndex == 0) {
                //现在是后置，变更为前置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {//代表摄像头的方位，CAMERA_FACING_FRONT前置      CAMERA_FACING_BACK后置
                    matrix.setRotate(90);
                    break;
                }
            } else {
                //现在是前置， 变更为后置
                if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {//代表摄像头的方位，CAMERA_FACING_FRONT前置
                    matrix.setRotate(270);
                    break;
                }
            }

        }
    }
    /**
     * 手持身份证拍摄裁剪
     * @param data  拍完后用于生成照片的数组
     * @param surfaceView
     * @param context
     * @param isReserved 横屏时手机屏幕是不是反的
     * @param mIndex
     * @return
     */
    private static Bitmap cutImageHold(byte[] data, SurfaceView surfaceView, Context context, boolean isReserved,int mIndex) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        Matrix matrix = new Matrix();
        if(surfaceView.getWidth() < surfaceView.getHeight()){
//			竖屏旋转照片
            matrix.reset();

            rotate(mIndex, matrix);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }else{
            if(isReserved){
//                横屏时手机屏幕是反的
                matrix.reset();
                matrix.setRotate(180);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
            if(surfaceView == null){
                return bitmap;
            }else{
                int w = surfaceView.getWidth();
                int h = surfaceView.getHeight();

                return Bitmap.createBitmap(bitmap,3*w/10 ,0,2*w/5,h);
            }
        }
    }

    /**
     * 拍完保存照片
     * @param data
     * @param surfaceView
     * @param context
     * @param flag  是否是手持身份证
     * @param isReserved 横屏时手机屏幕是不是反的
     * @param mIndex
     * @return
     */
    public static String savePicture(byte[] data, SurfaceView surfaceView, Context context, Boolean flag, boolean isReserved,int mIndex){
        File fileFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                + "/Camera/");
        if (!fileFolder.exists() && !fileFolder.mkdirs()) {
            return null;
        }
//		文件路径路径
        String imgFilePath = fileFolder.getPath() + File.separator + generateFileName();
        Bitmap b;
        if(flag) {
//            不是手持身份证
            b = cutImage(data, surfaceView, context,isReserved,mIndex);
        }else{
//            手持身份证
            b = cutImageHold(data,surfaceView,context,isReserved,mIndex);

        }
        saveBitmap(b,imgFilePath);

        return imgFilePath;
    }



    /**
     * 生成图片名称
     * @return
     */
    public static String generateFileName(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault());
        String strDate = dateFormat.format(new Date());
        return "img_" + strDate + ".jpg";
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 删除图片文件
     */
    public static void deleteFile(String filePath) {
        if (filePath == null || filePath.equals("")) {
            return;
        }
        File f = new File(filePath);
        if (f.exists()) {
            f.delete();
        }
    }



    /**
     * 设置相机Preview Size 和 Picture Size，找到设备所支持的最匹配的相机预览和图片大小
     */
    public static void setCameraSize(Camera.Parameters parameters, int width, int height) {
        Map<String, List<Camera.Size>> allSizes = new HashMap<>();
        String typePreview = "typePreview";
        String typePicture = "typePicture";
        allSizes.put(typePreview, parameters.getSupportedPreviewSizes());
        allSizes.put(typePicture, parameters.getSupportedPictureSizes());
        Iterator iterator = allSizes.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<Camera.Size>> entry = (Map.Entry<String, List<Camera.Size>>) iterator.next();
            List<Camera.Size> sizes = entry.getValue();
            if (sizes == null || sizes.isEmpty()) continue;
            ArrayList<WrapCameraSize> wrapCameraSizes = new ArrayList<>(sizes.size());
            for (Camera.Size size : sizes) {
                WrapCameraSize wrapCameraSize = new WrapCameraSize();
                wrapCameraSize.setWidth(size.width);
                wrapCameraSize.setHeight(size.height);
                wrapCameraSize.setD(Math.abs((size.width - width)) + Math.abs((size.height - height)));
                if (size.width == width && size.height == height) {
                    if (typePreview.equals(entry.getKey())) {
                        parameters.setPreviewSize(size.width, size.height);
                    } else if (typePicture.equals(entry.getKey())) {
                        parameters.setPictureSize(size.width, size.height);
                    }
                    LogUtil.d(TAG, "best size: width=" + size.width + ";height=" + size.height);
                    break;
                }
                wrapCameraSizes.add(wrapCameraSize);
            }
            LogUtil.d(TAG, "wrapCameraSizes.size()=" + wrapCameraSizes.size());
            Camera.Size resultSize = null;
            if (typePreview.equals(entry.getKey())) {
                resultSize = parameters.getPreviewSize();
            } else if (typePicture.equals(entry.getKey())) {
                resultSize = parameters.getPictureSize();
            }
            if (resultSize != null) {
                if (resultSize.width != width && resultSize.height != height) {
                    //找到相机Preview Size 和 Picture Size中最适合的大小
                    WrapCameraSize minCameraSize = Collections.min(wrapCameraSizes);
                    while (!(minCameraSize.getWidth() >= width || minCameraSize.getHeight() >= height)) {
                        wrapCameraSizes.remove(minCameraSize);
//                        minCameraSize = null;
                        minCameraSize = Collections.min(wrapCameraSizes);
                    }
                    LogUtil.d(TAG, "best min size: width=" + minCameraSize.getWidth() + ";height=" + minCameraSize.getHeight());
                    if (typePreview.equals(entry.getKey())) {
                        parameters.setPreviewSize(minCameraSize.getWidth(), minCameraSize.getHeight());
                    } else if (typePicture.equals(entry.getKey())) {
                        parameters.setPictureSize(minCameraSize.getWidth(), minCameraSize.getHeight());
                    }
                }
            }
            iterator.remove();
        }
    }
    /**
     * 压缩bitmap并保存
     *
     * @param
     * @return
     */

    public static void saveBitmap(Bitmap bitmap,String imgFilePath) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>500) { //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        File imgFile = new File(imgFilePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imgFile);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String substring(String s){
        String file = s.substring(s.length()-22,s.length());
        return file;
    }

}
