package br.com.test.question03;

public class ExceptionVowel extends Exception {
	
private static final long serialVersionUID = 1L;
	
	public ExceptionVowel(){
		super("Vogal não encontrada na String");
	}

}
