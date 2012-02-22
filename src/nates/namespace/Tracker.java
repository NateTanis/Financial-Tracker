package nates.namespace;

import java.math.*;
import java.sql.Date;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Tracker extends Activity {
	
	BigDecimal bd_balance = new BigDecimal("200.15");
	//double balance = bd_balance.doubleValue();
	Date today = new Date(0);
	TextView t;
	EditText mEdit;
	ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	String desc;
	TextView new_bal;
		TextView pending;
		String str_amount;
		EditText editor;
		TextView tv;
		
		String type;

		BigDecimal amount;
		BigDecimal total = new BigDecimal("0.00");
		
	RadioButton check;
	RadioButton debit;
	RadioButton atm;
	RadioButton other;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        t=(TextView)findViewById(R.id.tv_balance); 
        t.setText("$ " +  bd_balance);

        mEdit = (EditText)findViewById(R.id.ed_amount);
        mEdit.setFilters(new DecimalDigitsInputFilter[] {new DecimalDigitsInputFilter(2)});

    }
    
    // This method is called at button click because we assigned the name to the
   	// "On Click property" of the button
   	public void myClickHandler(View view) {
   		
   		
   		
   		
   		//RelativeLayout layout = (RelativeLayout) findViewById(R.id.relativeLayout1);
   		switch (view.getId()) {
   		case R.id.btn_deposit:
   			
   			makeDeposit();

            
   			break;
   			
   		case R.id.btn_payment:
   			
   			
   			makePayment();
   			check = (RadioButton)findViewById(R.id.rb_check);
   			debit = (RadioButton)findViewById(R.id.rb_debit);
   			atm = (RadioButton)findViewById(R.id.rb_atm);
   			other = (RadioButton)findViewById(R.id.rb_other);
   			
   			
            
   			break;
   			
   		case R.id.btn_reports:
   			setContentView(R.layout.reports1);
   			TextView report = (TextView)findViewById(R.id.tv_report);
   			String items = "";
   			for(int i = 0; i < transactions.size(); i++)
   			{
   				items += transactions.get(i).toString() + "\n";
   			}
   			report.setText(items);
   			break;
   			
   		case R.id.btn_cancel:
   			setContentView(R.layout.main);
   			tv=(TextView)findViewById(R.id.tv_balance); 
   	        tv.setText("$ " +  bd_balance);
   			break;
   			
   		case R.id.btn_confirm:
   			
   			bd_balance = total;
   			transactions.add(new Transaction(today, type, desc, "-" + str_amount, bd_balance));
   			str_amount = "";
   			setContentView(R.layout.main);
   			tv =(TextView)findViewById(R.id.tv_balance); 
   	        tv.setText("$ " +  bd_balance);
   			break;
   			
   		case R.id.btn_back:
   			
   			setContentView(R.layout.main);
   			tv =(TextView)findViewById(R.id.tv_balance); 
   	        tv.setText("$ " +  bd_balance);
   			break;
   		
   		
   		case R.id.rb_check:
   			
   			EditText check_num = (EditText)findViewById(R.id.ed_check_num);
   			desc = (String) check.getText();
   			
   			if(!check_num.getText().equals(""))
   				desc += check_num.getText();
   			check.setChecked(true);
   			debit.setChecked(false);
   			atm.setChecked(false);
   			other.setChecked(false);
   			
   			break;
   			
   		case R.id.rb_debit:
   			
   			desc = (String) debit.getText();
   			check.setChecked(false);
   			debit.setChecked(true);
   			atm.setChecked(false);
   			other.setChecked(false);
   			break;
   		case R.id.rb_atm:
   			
   			desc = (String) atm.getText();
   			
   			check.setChecked(false);
   			debit.setChecked(false);
   			atm.setChecked(true);
   			other.setChecked(false);
   			
   			break;	
   		case R.id.rb_other:
   			
   			desc = (String) other.getText();
   			check.setChecked(false);
   			debit.setChecked(false);
   			atm.setChecked(false);
   			other.setChecked(true);
   			
   			break;	

   		}
   	}
   	
   	private void makeDeposit()
   	{
   		mEdit = (EditText)findViewById(R.id.ed_amount);
	        //mEdit.setFilters(new DecimalDigitsInputFilter[] {new DecimalDigitsInputFilter(2)});
			
			// Create transaction for reports
			type = "D";
			desc = "";
			
		 	setContentView(R.layout.deposit);
			new_bal = (TextView)findViewById(R.id.tv_balance);
			new_bal.setText("$ " +  bd_balance);
			pending =(TextView)findViewById(R.id.tv_update_bal);
			str_amount = mEdit.getText().toString();
        if(!str_amount.equals(""))
        {
        	amount = new BigDecimal(str_amount.trim());
        	total = bd_balance.add(amount);
        	
        	transactions.add(new Transaction(today, type, desc, str_amount, bd_balance));
        }
        editor = (EditText)findViewById(R.id.ed_amount);
        editor.setText(str_amount);
        pending.setText("$ " +  total);
   	}
   	
   	private void makePayment()
   	{
   		mEdit = (EditText)findViewById(R.id.ed_amount);
	        //mEdit.setFilters(new DecimalDigitsInputFilter[] {new DecimalDigitsInputFilter(2)});
			
			// Create transaction for reports
			type = "P";
			
			setContentView(R.layout.payment);
			
			
			new_bal = (TextView)findViewById(R.id.tv_balance);
			new_bal.setText("$ " +  bd_balance);
			pending =(TextView)findViewById(R.id.tv_new_bal);
			str_amount = mEdit.getText().toString();
        if(!str_amount.equals(""))
        {
        	amount = new BigDecimal(str_amount.trim());
        	total = bd_balance.subtract(new BigDecimal(str_amount.trim()));
        	
        	
        }
        editor = (EditText)findViewById(R.id.ed_amount);
        editor.setText(str_amount);
        pending.setText("$ " +  total);
        
   	}
   	
}