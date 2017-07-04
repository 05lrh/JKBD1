import com.squareup.picasso.Picasso;

import java.util.List;
 import java.util.Timer;
 import java.util.TimerTask;

import cn.ucai.jkbd.ExamApplication;
import cn.ucai.jkbd.R;
@@ -34,7 +36,7 @@

public class RandomTest extends Activity {
    private TextView subject,limitTime,questionCount,tv_loading;
    private TextView tv_id,tv_question,q1,q2,q3,q4;
    private TextView tv_id,tv_question,q1,q2,q3,q4,tv_time;
    private ImageButton img_pic;
    private LinearLayout linearLayout,ll_C,ll_D;
    private ProgressBar pb_loading;
    @@ -109,6 +111,8 @@ private void initData(){
        subject.setText(""+exam.getSubjectTitle());
        limitTime.setText(""+exam.getLimitTime());
        questionCount.setText(""+exam.getQuestionCount());

                                  initTimer(exam);
    }else{
        Log.e("exam","exam为空！");
    }
    @@ -161,6 +165,88 @@ public void onClick(View v) {

    }

    private void initExam(){
                exam= ExamApplication.getInstance().getExam();
                if(exam!=null){
                        subject.setText(""+exam.getSubjectTitle());
                        limitTime.setText(""+exam.getLimitTime());
                        questionCount.setText(""+exam.getQuestionCount());
                    }else{
                        Log.e("initExam:exam","exam为空！");
                   }
            }

             private void initQuestion(){
                question=iExamBiz.getQuestion();
                if(question!=null){
                        tv_id.setText(iExamBiz.getIndex()+1+".");
                        tv_question.setText(""+question.getQuestion());
                        q1.setText(""+question.getItem1());
                        q2.setText(""+question.getItem2());
                        q3.setText(""+question.getItem3());
                        q4.setText(""+question.getItem4());

                                ll_C.setVisibility(question.getItem3().equals("")?View.GONE:View.VISIBLE);
                        ll_D.setVisibility(question.getItem4().equals("")?View.GONE:View.VISIBLE);
                        cb_C.setVisibility(question.getItem3().equals("")?View.GONE:View.VISIBLE);
                        cb_D.setVisibility(question.getItem4().equals("")?View.GONE:View.VISIBLE);

                                if(question.getUrl()!=null&&!question.getUrl().equals("")){
                                img_pic.setVisibility(View.VISIBLE);
                                Picasso.with(this).load(question.getUrl()).into(img_pic);
                            }else{
                                img_pic.setVisibility(View.GONE);
                            }

                                initRadioButton();

                                String userAnswer=question.getUserAnswer();
                        if(userAnswer!=null&&!userAnswer.equals("")){
                                int a=Integer.parseInt(userAnswer)-1;
                                rb[a].setChecked(true);
                            }

                            }else{
                        Log.e("initQuestion:question","questionList为空！");
                    }
            }

             private void initTimer(Exam exam) {
                int sumTime=exam.getLimitTime()*60*1000;
        //        int sumTime=1*60*1000;

                                final long overTime=sumTime+System.currentTimeMillis();
                final Timer timer=new Timer();
                timer.schedule(new TimerTask() {
             @Override
             public void run() {
                                long l=overTime-System.currentTimeMillis();
                               final long min=l/1000/60;
                                final long sec=l/1000%60;
                                runOnUiThread(new Runnable() {
                    @Override
                     public void run() {
                                                tv_time.setText("剩余时间:"+min+"分"+sec+"秒");
                                            }
                 });
                            }
         },0,1000);

                        timer.schedule(new TimerTask() {
             @Override
             public void run() {
                                        timer.cancel();
                                        runOnUiThread(new Runnable() {
                   @Override
                     public void run() {
                                                       commit(null);
                                                    }
                 });
                                    }
         },sumTime);

                    }


    private void initView(){
        subject= (TextView) findViewById(R.id.subject);
        @@ -173,6 +259,7 @@ private void initView(){
            q2= (TextView) findViewById(R.id.tv_B);
            q3= (TextView) findViewById(R.id.tv_C);
            q4= (TextView) findViewById(R.id.tv_D);
                    tv_time= (TextView) findViewById(R.id.tv_time);
            img_pic= (ImageButton) findViewById(R.id.img_pic);

            linearLayout= (LinearLayout) findViewById(R.id.ll_loading);
            @@ -213,13 +300,17 @@ public void onClick(View v) {
                public void pre(View view) {
                    setUserAnswer();
                    iExamBiz.Pre();
                           initData();
                            //initData();
                                    initExam();
                            initQuestion();
                }

            public void next(View view) {
                setUserAnswer();
                iExamBiz.Next();
                       initData();
                        //initData();
                                initExam();
                        initQuestion();
            }