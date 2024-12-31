package com.aurionpro.loan.config;

import org.springframework.batch.item.ItemProcessor;

import com.aurionpro.loan.entity.NPA;

public class NpaProcessor implements ItemProcessor<NPA, NPA>{

	@Override
	public NPA process(NPA item) throws Exception {
		// TODO Auto-generated method stub
		return item;
	}

}
