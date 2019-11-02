package facade;

import easyaccept.EasyAccept;

public class Main {

	public static void main(String[] args) {
		
		args = new String[] {"facade.Facade", "TestEasyAccept/use_case_1.txt", "TestEasyAccept/use_case_2.txt",
				"TestEasyAccept/use_case_3.txt", "TestEasyAccept/use_case_4.txt", "TestEasyAccept/use_case_5.txt", "TestEasyAccept/use_case_6.txt", "TestEasyAccept/use_case_7.txt", "TestEasyAccept/use_case_8.txt"};
		EasyAccept.main(args);
	}
}
