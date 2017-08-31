package com.appkfz.imageloadertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   // private final static String imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504172817844&di=6e98320f43cf019a8b42139a1a514570&imgtype=0&src=http%3A%2F%2Fimg02.tooopen.com%2Fimages%2F20160428%2Ftooopen_sy_157231277582.jpg";

    private final static String imageUrl = "encrypt_https://raw.githubusercontent.com/chenyangcun/ImageLoaderTest/master/app/src/main/raw/ic_launcher_encrypt.png";

    //private final static String imageUrl = "file:///sdcard/ic_launcher.png";

    ImageView ivImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ivImage = (ImageView) findViewById(R.id.ivImage);

        findViewById(R.id.picassoeLoad).setOnClickListener(this);
        findViewById(R.id.glideLoad).setOnClickListener(this);
        findViewById(R.id.encrypt).setOnClickListener(this);




    }

    private void loadImageWithGlide() {
        ivImage.setImageBitmap(null);
        Glide.with(this).load(imageUrl).into(ivImage);

    }


    private void loadImageWithPicasso() {
        ivImage.setImageBitmap(null);
        Picasso.with(this).load(imageUrl).into(ivImage);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.picassoeLoad:
                loadImageWithPicasso();
                break;


            case R.id.glideLoad:
                loadImageWithGlide();
                break;


            case R.id.encrypt:
//                EncryptUtil.encryptImageFile("/sdcard/ic_launcher.png",
//                        "/sdcard/ic_launcher_encrypt.png");
//                try {
//                    File file = new File("/sdcard/ic_launcher_encrypt.png");
//                    int length = (int) file.length();
//                    byte[] buffer = new byte[length];
//                    FileInputStream fis = new FileInputStream(file);
//                    IOUtils.readFully(fis,buffer);
//                   // byte[] edata = EncryptUtil.encrypt(data, data.length);
//                    byte[] result = EncryptUtil.decrypt(buffer);
//                    FileOutputStream fos = new FileOutputStream("/sdcard/out.png");
//                    IOUtils.write(result, fos);
//                    Log.d("TAG", "result="+result[0]);
//                    fis.close();
//                    fos.close();
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
                break;

        }

    }
}
