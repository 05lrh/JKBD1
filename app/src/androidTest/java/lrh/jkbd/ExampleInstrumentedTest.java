private ProgressBar pb_loading;

        Exam exam;
        +    Question question;
        List<Question> questionList;

        IExamBiz iExamBiz;
@@ -106,15 +107,22 @@ private void initData(){
        Log.e("exam","exam为空！");
        }

        -                questionList=ExamApplication.getInstance().getQuestionList();
        -                if(questionList!=null){
        -                    tv_id.setText(questionList.get(0).getId()+".");
        -                    tv_question.setText(""+questionList.get(0).getQuestion());
        -                    q1.setText(""+questionList.get(0).getItem1());
        -                    q2.setText(""+questionList.get(0).getItem2());
        -                    q3.setText(""+questionList.get(0).getItem3());
        -                    q4.setText(""+questionList.get(0).getItem4());
        -                    Picasso.with(this).load(questionList.get(0).getUrl()).into(img_pic);
        +//                questionList=ExamApplication.getInstance().getQuestionList();
        +                question=iExamBiz.getQuestion();
        +                if(question!=null){
        +                    tv_id.setText(iExamBiz.getIndex()+1+".");
        +                    tv_question.setText(""+question.getQuestion());
        +                    q1.setText(""+question.getItem1());
        +                    q2.setText(""+question.getItem2());
        +                    q3.setText(""+question.getItem3());
        +                    q4.setText(""+question.getItem4());
        +                    if(question.getUrl()!=null&&!question.getUrl().equals("")){
        +                        img_pic.setVisibility(View.VISIBLE);
        +                        Picasso.with(this).load(question.getUrl()).into(img_pic);
        +                    }else{
        +                        img_pic.setVisibility(View.GONE);
        +                    }
        +
        }else{
        Log.e("onCreate:question","questionList为空！");
        }
@@ -152,6 +160,16 @@ private void initView(){
        pb_loading= (ProgressBar) findViewById(R.id.pb_loading);
        }

        +    public void pre(View view) {
        +        iExamBiz.Pre();
        +        initData();
        +    }
        +
        +    public void next(View view) {
        +        iExamBiz.Next();
        +        initData();
        +    }
        +
class LoadExamBroadcast extends BroadcastReceiver{

    @Override
    View
30  app/src/main/java/cn/ucai/jkbd/biz/ExamBiz.java
    @@ -1,5 +1,9 @@
            package cn.ucai.jkbd.biz;
  
 +import java.util.List;
 +
         +import cn.ucai.jkbd.ExamApplication;
 +import cn.ucai.jkbd.bean.Question;
  import cn.ucai.jkbd.dao.ExamDao;
  import cn.ucai.jkbd.dao.IExamDao;

    @@ -10,28 +14,50 @@
    public class ExamBiz implements IExamBiz {
        IExamDao dao;
  
 +    int index=0;
 +    List<Question> questionList;
 +
        public ExamBiz() {
            this.dao = new ExamDao();
        }
 +    @Override
 +    public int getIndex() {
            +        return index;
            +    }

        @Override
        public void ExamStart() {
            +        index=0;
            dao.loadExamInfo();
            dao.loadQuestionLists();
        }

        @Override
        public void Pre() {
            -
                    +        if(index>0){
                +            index--;
                +        }
        }

        @Override
        public void Next() {
            -
                    +        this.getQuestion();
            +        if(index>=0&&index<questionList.size()-1){
                +            index++;
                +        }
        }

        @Override
        public void commit() {

        }
 +
         +    @Override
 +    public Question getQuestion() {
            +        questionList=ExamApplication.instance.getQuestionList();
            +        if(questionList.get(index)!=null){
                +            return questionList.get(index);
                +        }
            +        return null;
            +    }
    }
    View
3  app/src/main/java/cn/ucai/jkbd/biz/IExamBiz.java
    @@ -1,5 +1,6 @@
            package cn.ucai.jkbd.biz;
  
 +import cn.ucai.jkbd.bean.Question;
  import cn.ucai.jkbd.dao.IExamDao;

/**
 @@ -11,4 +12,6 @@
 public void Pre();
 public void Next();
 public void commit();
 +    public Question getQuestion();
 +    public int getIndex();
 }
 View
 2  app/src/main/res/layout/activity_random.xml
 @@ -252,12 +252,14 @@
 android:layout_height="wrap_content"
 android:gravity="center">
 <Button
 +                android:onClick="pre"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:drawableLeft="@mipmap/exam_pre32x32"
 android:text="上一题"
 android:background="@null"/>
 <Button
 +                android:onClick="next"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content"
 android:drawableRight="@mipmap/exam_next32x32"