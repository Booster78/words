package fr.boost;

import android.os.CountDownTimer;
import android.widget.TextView;
  public class MyCount extends CountDownTimer{
    	
    	 private TextView compteur;
    	
	    public MyCount(long millisInFuture, long countDownInterval, TextView compteur) {
	    	super(millisInFuture, countDownInterval);
	    	this.compteur = compteur;
	    }
	
	    @Override
	    public void onFinish() {
	    	compteur.setText("done!");
	    }
	
	    @Override
	    public void onTick(long millisUntilFinished) {
	    	compteur.setText("Left: " + millisUntilFinished/1000);
	
	    }

    }