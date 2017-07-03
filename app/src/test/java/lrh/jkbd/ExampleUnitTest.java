import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
 +import android.widget.ImageButton;
import android.widget.TextView;
  
 +import com.squareup.picasso.Picasso;
 +
import java.util.List;

import cn.ucai.jkbd.ExamApplication;
@@ -20,6 +23,7 @@
private TextView subject,limitTime,questionCount;

private TextView tv_id,tv_question,q1,q2,q3,q4;
        +    private ImageButton img_pic;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
@@ -41,6 +45,7 @@ protected void onCreate(Bundle savedInstanceState) {
        q2= (TextView) findViewById(R.id.tv_B);
        q3= (TextView) findViewById(R.id.tv_C);
        q4= (TextView) findViewById(R.id.tv_D);
        +        img_pic= (ImageButton) findViewById(R.id.img_pic);


//        subject.setText(""+exam.getSubjectTitle());
@@ -57,6 +62,7 @@ protected void onCreate(Bundle savedInstanceState) {
        q2.setText(""+questionList.get(0).getItem2());
        q3.setText(""+questionList.get(0).getItem3());
        q4.setText(""+questionList.get(0).getItem4());
        +        Picasso.with(this).load(questionList.get(0).getUrl()).into(img_pic);

        }
        }
        View
        2  app/src/main/res/layout/activity_random.xml
@@ -112,8 +112,10 @@


<ImageButton
 +            android:id="@+id/img_pic"
         android:layout_width="100dp"
         android:layout_height="100dp"
         +            android:background="@null"
         android:src="@mipmap/btn" />
<TextView
          android:layout_width="match_parent"