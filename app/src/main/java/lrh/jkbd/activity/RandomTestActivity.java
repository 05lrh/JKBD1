@@ -348,9 +348,12 @@ public void setUserAnswer(){
        for(int i=0;i<rb.length;i++){
        if(rb[i].isChecked()){
        iExamBiz.getQuestion().setUserAnswer(String.valueOf(i+1));
        +                questionAdapter.notifyDataSetChanged();
        return;
        }
        }
        +        iExamBiz.getQuestion().setUserAnswer("");
        +        questionAdapter.notifyDataSetChanged();
        }

public void commit(View view) {
        View
        6  app/src/main/java/cn/ucai/jkbd/view/QuestionAdapter.java
@@ -46,6 +46,12 @@ public View getView(int position, View convertView, ViewGroup parent) {
        View view=View.inflate(mContext, R.layout.item_question,null);
        ImageView imv_question= (ImageView) view.findViewById(R.id.imv_question);
        TextView tv_no= (TextView) view.findViewById(R.id.tv_no);
        +        String ua=questionList.get(position).getUserAnswer();
        +        if(ua!=null&&!ua.equals("")){
        +            imv_question.setImageResource(R.mipmap.answer24x24);
        +        }else{
        +            imv_question.setImageResource(R.mipmap.ques24x24);
        +        }
        tv_no.setText("第"+(position+1)+"题");
        return view;
        }