package lrh.jkbd.activity;

import android.app.Activity;
 +import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
 +import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
@@ -37,6 +39,7 @@
private LinearLayout linearLayout,ll_C,ll_D;
private ProgressBar pb_loading;
private RadioButton cb_A,cb_B,cb_C,cb_D;
        +    private RadioButton[] rb=new RadioButton[4];

        Exam exam;
        Question question;
@@ -131,6 +134,14 @@ private void initData(){
        img_pic.setVisibility(View.GONE);
        }

        +                    initRadioButton();
        +
        +                    String userAnswer=question.getUserAnswer();
        +                    if(userAnswer!=null&&!userAnswer.equals("")){
        +                        int a=Integer.parseInt(userAnswer)-1;
        +                        rb[a].setChecked(true);
        +                    }
        +
        }else{
        Log.e("onCreate:question","questionList为空！");
        }
@@ -150,6 +161,7 @@ public void onClick(View v) {

        }

        +
private void initView(){
        subject= (TextView) findViewById(R.id.subject);
        limitTime= (TextView) findViewById(R.id.limitTime);
@@ -174,16 +186,38 @@ private void initView(){
        cb_B= (RadioButton) findViewById(R.id.cb_B);
        cb_C= (RadioButton) findViewById(R.id.cb_C);
        cb_D= (RadioButton) findViewById(R.id.cb_D);
        +        rb[0]=cb_A;
        +        rb[1]=cb_B;
        +        rb[2]=cb_C;
        +        rb[3]=cb_D;
        +
        +        cb_A.setOnClickListener(listener);
        +        cb_B.setOnClickListener(listener);
        +        cb_C.setOnClickListener(listener);
        +        cb_D.setOnClickListener(listener);
        }

        +    View.OnClickListener listener=new View.OnClickListener() {
        +        @Override
 +        public void onClick(View v) {
        +           v.getId();
        +            for(int i=0;i<4;i++){
        +                if(rb[i].getId()!=v.getId()){
        +                    rb[i].setChecked(false);
        +                }
        +            }
        +        }
        +    };
        +
        +
public void pre(View view) {
        -        //this.initRadioButton();
        +        setUserAnswer();
        iExamBiz.Pre();
        initData();
        }

public void next(View view) {
        -        //this.initRadioButton();
        +        setUserAnswer();
        iExamBiz.Next();
        initData();
        }
@@ -195,6 +229,36 @@ public void initRadioButton(){
        cb_D.setChecked(false);
        }

        +    public void setUserAnswer(){
        +        for(int i=0;i<rb.length;i++){
        +            if(rb[i].isChecked()){
        +                iExamBiz.getQuestion().setUserAnswer(String.valueOf(i+1));
        +                return;
        +            }
        +        }
        +    }
        +
        +    public void commit(View view) {
        +        setUserAnswer();
        +        int score=iExamBiz.commit();
        +        View inflate=View.inflate(this,R.layout.layout_result,null);
        +        TextView tvResult= (TextView) inflate.findViewById(R.id.tv_result);
        +        tvResult.setText("你的分数为\n"+score+"分！");
        +
        +        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        +        builder.setIcon(R.mipmap.exam_commit32x32)
        +                .setTitle("交卷")
        +                .setView(inflate)
        +                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        +                    @Override
 +                    public void onClick(DialogInterface dialog, int which) {
        +                        finish();
        +                    }
        +                });
        +
        +        builder.create().show();
        +    }
