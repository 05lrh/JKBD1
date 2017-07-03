import android.os.Bundle;
import android.util.Log;
import android.view.View;
 +import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
@@ -32,8 +33,9 @@
private TextView subject,limitTime,questionCount,tv_loading;
private TextView tv_id,tv_question,q1,q2,q3,q4;
private ImageButton img_pic;
        -    private LinearLayout linearLayout;
        +    private LinearLayout linearLayout,ll_C,ll_D;
private ProgressBar pb_loading;
        +    private CheckBox cb_C,cb_D;

        Exam exam;
        Question question;
@@ -107,7 +109,6 @@ private void initData(){
        Log.e("exam","exam为空！");
        }

        -//                questionList=ExamApplication.getInstance().getQuestionList();
        question=iExamBiz.getQuestion();
        if(question!=null){
        tv_id.setText(iExamBiz.getIndex()+1+".");
@@ -116,6 +117,12 @@ private void initData(){
        q2.setText(""+question.getItem2());
        q3.setText(""+question.getItem3());
        q4.setText(""+question.getItem4());
        +
        +                    ll_C.setVisibility(question.getItem3().equals("")?View.GONE:View.VISIBLE);
        +                    ll_D.setVisibility(question.getItem4().equals("")?View.GONE:View.VISIBLE);
        +                    cb_C.setVisibility(question.getItem3().equals("")?View.GONE:View.VISIBLE);
        +                    cb_D.setVisibility(question.getItem4().equals("")?View.GONE:View.VISIBLE);
        +
        if(question.getUrl()!=null&&!question.getUrl().equals("")){
        img_pic.setVisibility(View.VISIBLE);
        Picasso.with(this).load(question.getUrl()).into(img_pic);
@@ -158,6 +165,11 @@ private void initView(){
        linearLayout= (LinearLayout) findViewById(R.id.ll_loading);
        tv_loading= (TextView) findViewById(R.id.tv_loading);
        pb_loading= (ProgressBar) findViewById(R.id.pb_loading);
        +
        +        ll_C= (LinearLayout) findViewById(R.id.ll_C);
        +        ll_D= (LinearLayout) findViewById(R.id.ll_D);
        +        cb_C= (CheckBox) findViewById(R.id.cb_C);
        +        cb_D= (CheckBox) findViewById(R.id.cb_D);
        }

public void pre(View view) {
        View
        4  app/src/main/res/layout/activity_random.xml
@@ -175,6 +175,7 @@
</LinearLayout>

<LinearLayout
 +            android:id="@+id/ll_C"
         android:layout_width="match_parent"
         android:layout_height="wrap_content">
<TextView
@@ -192,6 +193,7 @@
</LinearLayout>

<LinearLayout
 +            android:id="@+id/ll_D"
         android:layout_width="match_parent"
         android:layout_height="wrap_content">
<TextView
@@ -229,11 +231,13 @@
        android:layout_marginRight="30dp"
        android:text="B"/>
<CheckBox
 +                android:id="@+id/cb_C"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:layout_marginRight="30dp"
         android:text="C"/>
<CheckBox
 +                android:id="@+id/cb_D"
         android:layout_width="wrap_content"
         android:layout_height="wrap_content"
         android:layout_marginRight="30dp"