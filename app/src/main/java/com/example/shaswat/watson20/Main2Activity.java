package com.example.shaswat.watson20;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {
    private TextView voiceInput;
    private TextToSpeech mtts;
    private ImageView speakimage;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        voiceInput = (TextView) findViewById(R.id.voiceinput);
        speakimage = (ImageView) findViewById(R.id.logoImageView);
        speakimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askSpeechInput();
            }
        });
        mtts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result1 = mtts.setLanguage(Locale.ENGLISH);
                    if (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "language not supported", Toast.LENGTH_SHORT).show();
                    } else {
                        speakimage.setEnabled(true);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Initialization failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void speak() {
        String text = voiceInput.getText().toString();
        mtts.setPitch(0.5f);
        mtts.setSpeechRate(1.0f);
        mtts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (mtts != null) {
            mtts.stop();
            mtts.shutdown();
        }
        super.onDestroy();
    }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hii say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    public String test(String something) {
        System.out.print(something);
        return something;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> resultdata = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String word = "";
                    for (String s : resultdata)
                    {
                        word += s ;
                    }
                    if (word.toLowerCase().contains("sum")) {
                        String retrieve1=resultdata.get(0);
                        int sum = 0;
                        int i;
                        Main2Activity sd = new Main2Activity();
                        String numbers = word.replaceAll("[^0-9]","");
                        String[] arr = numbers.split("");
                        for (i = 0;i < arr.length ;i++ ) {
                            sum += Integer.parseInt(arr[i]);
                        }
                        voiceInput.setText("The sum of "+ arr[0] +" and "+ arr[1] +" is "+ String.valueOf(sum) +"");
                    }else if (word.toLowerCase().contains("product") || word.toLowerCase().contains("multiply") || word.toLowerCase().contains("multiplication")) {
                        Main2Activity sd = new Main2Activity();
                        String numbers = word.replaceAll("[^0-9]","");
                        String[] arr = numbers.split("");
                        int result = 1;
                        for (String element: arr) {
                            result *= Integer.parseInt(element);
                        }
                        sd.test("The product of "+ arr[0] +" and "+ arr[1] +" is "+ String.valueOf(result) +"");
                    }
                    if (word.toLowerCase().contains("hey")|| word.toLowerCase().contains("hi")||word.toLowerCase().contains("hello")||word.toLowerCase().contains("watson")){
                        voiceInput.setText("hey, how are you");
                        speak();
                    }
                    if (word.toLowerCase().contains("how are you")||word.toLowerCase().contains("how you doing")||word.contains("how you doin")) {
                        voiceInput.setText("I am great");
                        speak();
                    }
                    if (word.toLowerCase().contains("who are you")) {
                        voiceInput.setText("I am Watson,your very " +
                                "own artificial " +
                                "linguistic Business financial consultant");
                        speak();
                    }
                    if (word.toLowerCase().contains("Merger")) {
                        voiceInput.setText("The combination of one or more corporations, LLCs," +
                                " or other business entities into a single business entity; the joining of two or more" +
                                " companies to achieve greater efficiencies of scale and productivity.");
                        speak();
                    }
                    if (word.toLowerCase().contains("created")) {
                        voiceInput.setText("Shaswat vashistha");
                        speak();
                    }
                    if (word.contains("what is the sum of 1 and 1")) {
                        voiceInput.setText("the answer is 2");
                        speak();
                    }
                    if (word.contains("headlines")) {
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indiatoday.in/top-stories?page=1"));
                        startActivity(intent);
                    }
                    if(word.contains("how is the weather")){
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.accuweather.com/en/in/jaipur/205617/hourly-weather-forecast/205617"));
                        startActivity(intent);
                    }
                    if(word.contains("stock price of Apple ")) {
                        voiceInput.setText("204.59 USD +0.73 (0.36%)");
                        speak();
                    }
                    if(word.contains("stock price of Microsoft ")) {
                        voiceInput.setText("123.68 USD +0.31 (0.26%)");
                        speak();
                    }
                    if(word.contains("price of gold")) {
                        voiceInput.setText("10g of 24k gold (99.9%) in Jaipur is \n" +
                                "32,640.00 Indian Rupee");
                        speak();
                    }
                    if(word.contains("share market")) {
                        voiceInput.setText("Trade setup: Nifty may attempt a pullback, but outlook is bearish\n" +
                                "Hold your horses! Large midcap rally may only come in 2020-21\n"+"Oil price spike to hit rupee hard as unit may soon test 70.50 level");
                        speak();
                    }
                    if(word.contains("acquisitions")) {
                        voiceInput.setText("A Joint Venture (JV) is a business entity created by two or more parties, generally characterized by shared ownership, shared returns and risks, and shared governance. Companies typically pursue joint ventures for one of four reasons: to access a new market, particularly emerging markets; to gain scale efficiencies by combining assets and operations; to share risk for major investments or projects; or to access skills and capabilities");
                        speak();
                    }
                    if(word.toLowerCase().contains("you single")) {
                        voiceInput.setText("Don't you have anything better to do");
                        speak();
                    }
                }

                break;
            }
        }
    }
}
