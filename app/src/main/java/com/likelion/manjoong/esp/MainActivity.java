package com.likelion.manjoong.esp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {
    TextView texx;
    TextView bigtitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        bigtitle = (TextView)findViewById(R.id.suject);


        toolbar.findViewById(R.id.toolbar_like).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "총 389명이 이 말씀을 좋아합니다.", Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout bible = (LinearLayout)findViewById(R.id.bible);
        LinearLayout question = (LinearLayout)findViewById(R.id.question);
        LinearLayout help = (LinearLayout)findViewById(R.id.help);
        LinearLayout impression = (LinearLayout)findViewById(R.id.impression);

        texx = (TextView)findViewById(R.id.text_test);
        texx.setMovementMethod(ScrollingMovementMethod.getInstance());





        bible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new bible_view().execute();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim,R.animator.exit_anim).replace(R.id.main_frame,new MyFragment1());

            }
        });


        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new question_view().execute();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim,R.animator.exit_anim).replace(R.id.main_frame,new MyFragment2());

            }
        });


        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new help_view().execute();
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim,R.animator.exit_anim).replace(R.id.main_frame,new MyFragment3());

            }
        });


        impression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim,R.animator.exit_anim).replace(R.id.main_frame,new MyFragment4());

            }
        });
    }
//////////////////////////////////////////////////////////////////////////////////////////
    public class bible_view extends AsyncTask<Void,Void,Void>{
        String words;
        String goodtitle;

        @Override
        protected Void doInBackground(Void... params) {
            try
            {

                Document doc = Jsoup.connect("http://www.esf21.com/daily/daily_main.php").get();

                Element elements = doc.select("#sub_daily_contents_box #center_box #bonaem_view_box #daily_contents_box").get(0);
                Element title = doc.select("#sub_daily_contents_box #center_box #bonaem_view_box #impress_title_box #impress_bigtitle").get(0);



                doc.outputSettings(new Document.OutputSettings().prettyPrint(false));


                //select all <br> tags and append \n after that
                doc.select("br").after("<pre>\n\n\n\n</pre>");
                doc.select("8").after("<pre>\n\n\n\n</pre>");



                //select all <p> tags and prepend \n before that
                doc.select("p").before("<pre>\n\n</pre>");

                //get the HTML from the document, and retaining original new lines
//                String str = doc.html().replaceAll("\\\\n", "\n");

                words = elements.text();
                goodtitle= title.text();


            }catch(Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            texx.setText(words);
            bigtitle.setText(goodtitle);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////
    public class question_view extends AsyncTask<Void,Void,Void>{
        String words;
        String words1;
        String space;

        @Override
        protected Void doInBackground(Void... params) {
            try
            {

                Document doc = Jsoup.connect("http://www.esf21.com/daily/daily_main.php").get();

                Element elements = doc.select("#sub_daily_contents_box #center_box #bonaem_view_box #daily_contents_box").get(1);
                Element elements1 = doc.select("#sub_daily_contents_box #center_box #bonaem_view_box #daily_contents_box").get(3);

                doc.outputSettings(new Document.OutputSettings().prettyPrint(false));

                //select all <br> tags and append \n after that
                doc.select("br").after("<pre>\n\n</pre>");


                //select all <p> tags and prepend \n before that
                doc.select("p").before("<pre>\n\n</pre>");


                //get the HTML from the document, and retaining original new lines
//                String str = doc.html().replaceAll("\\\\n", "\n");

                words = elements.text();
                space ="\n\n\n";
                words1= elements1.text();


            }catch(Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            texx.setText(words+space+words1);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
    public class help_view extends AsyncTask<Void,Void,Void>{
        String words;

        @Override
        protected Void doInBackground(Void... params) {
            try
            {

                Document doc = Jsoup.connect("http://www.esf21.com/daily/daily_main.php").get();

                Element elements = doc.select("#sub_daily_contents_box #center_box #bonaem_view_box #daily_contents_box").get(2);

                doc.outputSettings(new Document.OutputSettings().prettyPrint(false));

                //select all <br> tags and append \n after that
                doc.select("br").after("<pre>\n</pre>");

                //select all <p> tags and prepend \n before that
                doc.select("p").before("<pre>\n\n</pre>");

                //get the HTML from the document, and retaining original new lines
//                String str = doc.html().replaceAll("\\\\n", "\n");

                words = elements.text();


            }catch(Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            texx.setText(words);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////
    public class impression_view extends AsyncTask<Void,Void,Void>{
        String words;

        @Override
        protected Void doInBackground(Void... params) {
            try
            {

                Document doc = Jsoup.connect("http://www.esf21.com/daily/daily_main.php").get();

                Element elements = doc.select("#sub_daily_contents_box #center_box #bonaem_view_box #daily_contents_box #daily_txt").get(1);

                doc.outputSettings(new Document.OutputSettings().prettyPrint(false));

                //select all <br> tags and append \n after that
                doc.select("br").after("<pre>\n\n\n\n</pre>");

                //select all <p> tags and prepend \n before that
                doc.select("p").before("<pre>\n\n\n\n</pre>");

                //get the HTML from the document, and retaining original new lines
//                String str = doc.html().replaceAll("\\\\n", "\n");

                words = elements.text();


            }catch(Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            texx.setText(words);
        }
    }
}
