@@ -226,8 +226,12 @@ private void initQuestion(){
        if(userAnswer!=null&&!userAnswer.equals("")){
        int a=Integer.parseInt(userAnswer)-1;
        rb[a].setChecked(true);
        +                setRadioButtonEnable(false);
        +            }else{
        +                setRadioButtonEnable(true);
        }

        +
        }else{
        Log.e("initQuestion:question","questionList为空！");
        }
@@ -345,6 +349,7 @@ public void initRadioButton(){
        }

public void setUserAnswer(){
        +
        for(int i=0;i<rb.length;i++){
        if(rb[i].isChecked()){
        iExamBiz.getQuestion().setUserAnswer(String.valueOf(i+1));
@@ -356,6 +361,12 @@ public void setUserAnswer(){
        questionAdapter.notifyDataSetChanged();
        }

        +    public void setRadioButtonEnable(boolean b){
        +        for(int i=0;i<rb.length;i++){
        +            rb[i].setEnabled(b);
        +        }
        +    }
        +
public void commit(View view) {
        setUserAnswer();
        int score=iExamBiz.commit();