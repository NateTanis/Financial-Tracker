package nates.namespace;

import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {

	private Date date;
	private String type;
	private String desc;
	private String dbl_amount;
	private BigDecimal bd_balance;

	public Transaction(Date d, String type, String description, String amount, BigDecimal balance)
	{
		this.date = d;
		this.type = type;
		this.desc = description;
		this.dbl_amount = amount;
		this.bd_balance = balance;
	}

	public Date getDate() {
		return date;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public String getDbl_amount() {
		return dbl_amount;
	}

	public BigDecimal getDbl_balance() {
		return bd_balance;
	}
	
	@Override
	public String toString()
	{
		return date.getMonth() + "/" + date.getDate() + "\t " + type + "  " + dbl_amount + "\t " + bd_balance + "\n\t\t " + desc;
	}
}
