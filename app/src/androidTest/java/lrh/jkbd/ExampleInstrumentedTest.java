import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
 +import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
@@ -35,7 +36,7 @@
private ImageButton img_pic;
private LinearLayout linearLayout,ll_C,ll_D;
private ProgressBar pb_loading;
        -    private CheckBox cb_C,cb_D;
        +    private RadioButton cb_A,cb_B,cb_C,cb_D;

        Exam exam;
        Question question;
@@ -168,20 +169,32 @@ private void initView(){

        ll_C= (LinearLayout) findViewById(R.id.ll_C);
        ll_D= (LinearLayout) findViewById(R.id.ll_D);
        -        cb_C= (CheckBox) findViewById(R.id.cb_C);
        -        cb_D= (CheckBox) findViewById(R.id.cb_D);
        +
        +        cb_A= (RadioButton) findViewById(R.id.cb_A);
        +        cb_B= (RadioButton) findViewById(R.id.cb_B);
        +        cb_C= (RadioButton) findViewById(R.id.cb_C);
        +        cb_D= (RadioButton) findViewById(R.id.cb_D);
        }

public void pre(View view) {
        +        //this.initRadioButton();
        iExamBiz.Pre();
        initData();
        }

public void next(View view) {
        +        //this.initRadioButton();
        iExamBiz.Next();
        initData();
        }

        +    public void initRadioButton(){
        +        cb_A.setChecked(false);
        +        cb_B.setChecked(false);
        +        cb_C.setChecked(false);
        +        cb_D.setChecked(false);
        +    }
        +
class LoadExamBroadcast extends BroadcastReceiver{

    @Override
