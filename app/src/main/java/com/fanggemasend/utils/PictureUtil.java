package com.fanggemasend.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 1363655717 on 2017/4/3.
 */

public class PictureUtil {
    private PictureUtilListener pictureUtilListener;
    public interface PictureUtilListener {

        void onSucces(File[] files);

    }
    public static void uploadpictures(Context context, File[] files, String[] pic_name, PictureUtilListener pictureUtilListener){
        Log.e("图片大小4",files[0].length() / 1024 +"k");
        for (int i=0;i<files.length;i++) {
            Log.e("图片大小5",files[0].length() / 1024 +"k");
//            Toast.makeText(context, i+"", Toast.LENGTH_LONG).show();
            File file=files[i];

            if (file.length() / 1024 > 500||file.length()==0) {
                Log.e("图片大小1",files[0].length() / 1024 +"k");
                String targetPath = context.getExternalFilesDir("img").getPath();
                String compressImage = compressImage(file.getPath(), targetPath + "/" + pic_name[i]+".jpg", 30);
                File compressedPic = new File(compressImage);
                if (compressedPic.exists()) {
//                    Toast.makeText(context, "cehngggong"+i, Toast.LENGTH_LONG).show();
                    files[i]=compressedPic;
                } else
                    files[i]=file;
            } else {
                files[i]=file;
            }
        }
        Log.e("图片大小2",files[0].length() / 1024 +"k");
        Log.e("图片大小3",files[0].length() / 1024 +"k");
        pictureUtilListener.onSucces(files);
    }
    public  static  String[] shuzu(){
        String[] strings=new String[]{"1","2","3"};
        strings[0]="4";
        strings[1]="5";
        strings[2]="6";
        return strings;
    }

    public static String compressImage(String filePath, String targetPath, int quality)  {
        Bitmap bm = getSmallBitmap(filePath);//获取一定尺寸的图片
        int degree = readPictureDegree(filePath);//获取相片拍摄角度
        if(degree!=0){//旋转照片角度，防止头像横着显示
            bm=rotateBitmap(bm,degree);
        }
        File outputFile=new File(targetPath);
        try {
            if (!outputFile.exists()) {
                outputFile.getParentFile().mkdirs();
                //outputFile.createNewFile();
            }else{
                outputFile.delete();
            }
            FileOutputStream out = new FileOutputStream(outputFile);
            bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
        }catch (Exception e){}
        return outputFile.getPath();
    }
    /**
     * 根据路径获得图片信息并按比例压缩，返回bitmap
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//只解析图片边沿，获取宽高
        BitmapFactory.decodeFile(filePath, options);
        // 计算缩放比
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // 完整解析图片返回bitmap
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
    /**
     * 获取照片角度
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
    /**
     * 旋转照片
     * @param bitmap
     * @param degress
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                    bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
