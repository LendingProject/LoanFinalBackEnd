package com.aurionpro.loan.config;

import org.springframework.batch.item.ItemProcessor;

import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.NPA;

public class MissedPaymentProcessor  implements ItemProcessor<MissedPayment, MissedPayment>{

	@Override
	public MissedPayment process(MissedPayment item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}



}
