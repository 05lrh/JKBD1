initView();

        -        loadDate();
        +        iExamBiz=new ExamBiz();
        +        loadData();
        }

@Override
@@ -79,8 +80,10 @@ private void setListener() {
        registerReceiver(loadQuestionBroadcast,new IntentFilter(ExamApplication.LOAD_QUESTION));
        }

        -    private  void loadDate(){
        -        iExamBiz=new ExamBiz();
        +    private  void loadData(){
        +        linearLayout.setEnabled(false);
        +        pb_loading.setVisibility(View.VISIBLE);
        +        tv_loading.setText("Loading...");
        new Thread(new Runnable() {
@Override
public void run() {
@@ -116,8 +119,16 @@ private void initData(){
        Log.e("onCreate:question","questionList为空！");
        }
        }else{
        +                linearLayout.setEnabled(true);
        pb_loading.setVisibility(View.GONE);
        tv_loading.setText("加载失败，点击屏幕重新加载！");
        +
        +                linearLayout.setOnClickListener(new View.OnClickListener() {
        +                    @Override
 +                    public void onClick(View v) {
        +                        loadData();
        +                    }
        +                });
        }
        }


