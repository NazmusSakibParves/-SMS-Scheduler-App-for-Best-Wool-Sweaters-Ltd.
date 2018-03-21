package com.somitsolutions.android.smsscheduler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nazmus Sakib Parves on 8/21/2017.
 */

public class AboutUs extends Activity {

    private TextView textViewtitle,textViewvirsion,textViewWebsite;
    private Button buttonFacebook,buttontwiter,buttonyoutube,buttonMoreApps;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        textViewtitle=(android.widget.TextView)findViewById(R.id.titleApp);
        textViewvirsion=(TextView)findViewById(R.id.appVirsion);
        textViewWebsite=(TextView)findViewById(R.id.tvWebSite);
        imageView=(ImageView)findViewById(R.id.imageViewlogo);

       // buttonFacebook=(Button)findViewById(R.id.btfacebook);
        //buttontwiter=(Button)findViewById(R.id.bttwitter);
       // buttonyoutube=(Button)findViewById(R.id.btyoutube);
        buttonMoreApps=(Button)findViewById(R.id.moreApps);


        textViewWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.vistasoftit.org/"));
                startActivity(intent);
            }
        });


        buttonMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=Vintage%20Studioes&hl=en"));
                startActivity(intent);

            }
        });

        buttontwiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://twitter.com/vistasoftit"));
                startActivity(intent);
            }
        });

        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.facebook.com/vistasoftit/"));
                startActivity(intent);
            }
        });

        buttonyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.youtube.com/channel/UCPkFAJEssuNbeZTCbihi7aA"));
                startActivity(intent);
            }
        });

    }
}
