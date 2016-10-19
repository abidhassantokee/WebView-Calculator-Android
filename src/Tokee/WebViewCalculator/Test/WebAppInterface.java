package Tokee.WebViewCalculator.Test;

import android.content.Context;

public class WebAppInterface {
	
	MyWebviewCalculatorActivity myActivity = null;
	
	int operator = 0, eql = 0;
    double ans=0;
    String text="",error="0",exp;
    Boolean point = false;
	
    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
    	myActivity=(MyWebviewCalculatorActivity)c;
    }
    
    public void reload(){
    	myActivity.mHandler.post(new Runnable() {
			public void run() {
				WebAppInterface.this.myActivity.myWebView.reload();
			}
		});
	}
    
    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public String btnNumEvent(String num) {
        if(eql==1){
            text = "";
            eql = 0;
            operator=0;
        }
        text+=num;
        return text;
    }
    
    public String btnOperatorEvent(String operatorSign) {
		error="0";
        if(operator!=0 && eql==0){
            if(isDouble(text))
            {
                if(operator==1){
                    ans = ans + Double.parseDouble(text);
                }
                if(operator==2){
                    ans = ans - Double.parseDouble(text);
                }
                if(operator==3){
                    ans = ans * Double.parseDouble(text);
                }
                if(operator==4){
                    if (Double.parseDouble(text) == 0) {
                        error = "Error:Divided by Zero";
                        ans = 0;
                    } else {
                        ans = ans / Double.parseDouble(text);
                    }
                }
            }
        }
        else{
            if(eql==0)
            {
                if(!isDouble(text)) text = "0";
                ans = Double.parseDouble(text);
            }
            else{
                eql=0;
            }
        }
        if(operatorSign.equals("+"))
        {
            operator = 1;
        }
        if(operatorSign.equals("-"))
        {
            operator = 2;
        }
        if(operatorSign.equals("*"))
        {
            operator = 3;
        }
        if(operatorSign.equals("/"))
        {
            operator = 4;
        }
        text = "";
        if(error.equals("0"))
            return String.valueOf(ans);
        else{
            return error;
        }
        
    }
    
    public String btnEqualEvent() {
    	error="0";
        if(operator!=0){
            if(isDouble(text))
            {
                if(operator==1){
                    ans = ans + Double.parseDouble(text);
                }
                if(operator==2){
                    ans = ans - Double.parseDouble(text);
                }
                if(operator==3){
                    ans = ans * Double.parseDouble(text);
                }
                if(operator==4){
                    if (Double.parseDouble(text) == 0) {
                        error = "Error:Divided by Zero";
                        ans = 0;
                    } else {
                        ans = ans / Double.parseDouble(text);
                    }
                }
            }
        }else
        {
            if(!isDouble(text)) text = "0";
            ans = Double.parseDouble(text);
        }
        eql = 1;
        if(error.equals("0"))
            return String.valueOf(ans);
        else
        {
            return error;            
        }
    }
    
    public String btnPointEvent() {
        if(eql==1){
            text = "";
            eql = 0;
            operator=0;
        }
        if(isDouble(text + '.')){
            text+= '.';
        }else if(text.length()==1 && text.charAt(0)=='-'){
            text+='.';
        }else if(text.length()==0) text = ".";
        return text;
    }
    
    public String btnSignedEvent() {
        if(eql == 0)
        {
            if (text.length() > 0) {
                if(text.charAt(0)!='-') text = "-"+text;
                else
                    text = text.substring(1, text.length());
            }else{
                text = "-";
            }
        }
        else {
            text = "-";
            eql = 0;
            operator = 0;
        }
        return text;
    }
    public String btnDelEvent() {
        if(eql==0)
        {
            if (text.length() > 0) {
                text = text.substring(0, text.length()-1);
                return text;
            }
        }
        return String.valueOf(ans);
    }
    public String btnClearEvent() {
        operator = 0;
        ans = 0;
        text = "";
        eql=0;
        return text;
    }
}
