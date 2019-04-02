package com.example.horizontalscrollviewsample;

import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout asthmaActionPlan, controlledMedication, asNeededMedication,
            rescueMedication, yourSymtoms, yourTriggers, wheezeRate, peakFlow, myNewSample, innerlayout;
    LinearLayout.LayoutParams params;
    LinearLayout next, prev;
    Button btn_add;

    int viewWidth;
    GestureDetector gestureDetector = null;
    HorizontalScrollView horizontalScrollView;
    ArrayList<LinearLayout> layouts;
    int parentLeft, parentRight;
    int mWidth;
    int currPosition, prevPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_backup);

        gestureDetector = new GestureDetector(new MyGestureDetector());

//        prev = (LinearLayout) findViewById(R.id.prev);
//        next = (LinearLayout) findViewById(R.id.next);

        layouts = new ArrayList<LinearLayout>();
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMoreView();
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        viewWidth = mWidth / 3;
        //innerlayout = (LinearLayout)findViewById(R.id.innerLay);



        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        innerlayout = (LinearLayout)horizontalScrollView.getChildAt(0);
        createMoreView();
        //Log.d("asd","asd");

        //layouts.add(new LinearLayout(this))


//        //asthmaActionPlan = (LinearLayout) findViewById(R.id.asthma_action_plan);
//        //controlledMedication = (LinearLayout) findViewById(R.id.controlled_medication);
//        asNeededMedication = (LinearLayout) findViewById(R.id.as_needed_medication);
//        //rescueMedication = (LinearLayout) findViewById(R.id.rescue_medication);
////        yourSymtoms = (LinearLayout) findViewById(R.id.your_symptoms);
//        yourTriggers = (LinearLayout) findViewById(R.id.your_triggers);
//        wheezeRate = (LinearLayout) findViewById(R.id.wheeze_rate);
//        peakFlow = (LinearLayout) findViewById(R.id.peak_flow);
//
//
//        Display display = getWindowManager().getDefaultDisplay();
//        mWidth = display.getWidth(); // deprecated
//        viewWidth = mWidth / 3;
//        layouts = new ArrayList<LinearLayout>();
//        params = new LinearLayout.LayoutParams(viewWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//        //asthmaActionPlan.setLayoutParams(params);
////        controlledMedication.setLayoutParams(params);
//        asNeededMedication.setLayoutParams(params);
//        //rescueMedication.setLayoutParams(params);
////        yourSymtoms.setLayoutParams(params);
//        yourTriggers.setLayoutParams(params);
//        wheezeRate.setLayoutParams(params);
//        peakFlow.setLayoutParams(params);
//
//        //layouts.add(asthmaActionPlan);
//        layouts.add(controlledMedication);
//        layouts.add(asNeededMedication);
//        layouts.add(rescueMedication);
//        layouts.add(yourSymtoms);
//        layouts.add(yourTriggers);
//        layouts.add(wheezeRate);
//        layouts.add(peakFlow);
//
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        horizontalScrollView.smoothScrollTo(
//                                (int) horizontalScrollView.getScrollX()
//                                        + viewWidth,
//                                (int) horizontalScrollView.getScrollY());
//                    }
//                }, 100L);
//            }
//        });
//
//        prev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Handler().postDelayed(new Runnable() {
//                    public void run() {
//                        horizontalScrollView.smoothScrollTo(
//                                (int) horizontalScrollView.getScrollX()
//                                        - viewWidth,
//                                (int) horizontalScrollView.getScrollY());
//                    }
//                }, 100L);
//            }
//        });
//
//        horizontalScrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (gestureDetector.onTouchEvent(event)) {
//                    return true;
//                }
//                return false;
//            }
//        });
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e1.getX() < e2.getX()) {
                currPosition = getVisibleViews("left");
            } else {
                currPosition = getVisibleViews("right");
            }

            horizontalScrollView.smoothScrollTo(layouts.get(currPosition)
                    .getLeft(), 0);
            return true;
        }
    }

    /**
     * 1. adapter를 통해서 view 추가(event 발생 -> 어댑터에 add->어댑터는 list에..)
     *
     *
     *
     * **/


    public int getVisibleViews(String direction) {
        Rect hitRect = new Rect();
        int position = 0;

        for (int i = 0; i < 4; i++) {
            if (direction.equals("left")) {
                position = 0;
            } else if (direction.equals("right")) {
                position = 4;
            }
        }
        return position;
    }

    public void createMoreView(){
        params = new LinearLayout.LayoutParams(viewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        for (int i=0; i<10; i++){

            TextView tv = new TextView(this);
            tv.setText(""+i);
            tv.setTextSize(20f);

            LinearLayout ll = new LinearLayout(this);
            ll.setLayoutParams(params);
            ll.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            ll.addView(tv);
            innerlayout.addView(ll);
            layouts.add(ll);
        }
    }



//    public int getVisibleViews(String direction) {
//        Rect hitRect = new Rect();
//        int position = 0;
//        int rightCounter = 0;
//        for (int i = 0; i < layouts.size(); i++) {
//            if (layouts.get(i).getLocalVisibleRect(hitRect)) {
//                if (direction.equals("left")) {
//                    position = i;
//                    break;
//                } else if (direction.equals("right")) {
//                    rightCounter++;
//                    position = i;
//                    if (rightCounter == 2)
//                        break;
//                }
//            }
//        }
//        return position;
//    }
}

