package com.revature.driver;

import com.revature.prompt.LoginPrompt;
import com.revature.prompt.Prompt;

public class BankDriver {
		
	public static void main(String[] args) {
		
		System.out.println(" *<:o)||  *<:o)||  *<:o)||  *<:o)||  *<:o)|| ");
		System.out.println("=========== Welcome To Clown Jail ===========");
		System.out.println(" ||(o:>*  ||(o:>*  ||(o:>*  ||(o:>*  ||(o:>* ");
		System.out.println();
		Prompt p = new LoginPrompt();
		
		while(true) {
			p = p.run();
		}
	}

}
