package facade;

import easyaccept.EasyAccept;

public class Main {

	public static void main(String[] args) {
		
		args = new String[] {"facade.Facade", "easyaccept/use_case_1.txt", "easyaccept/use_case_2.txt",
				"easyaccept/use_case_3.txt", "easyaccept/use_case_4.txt", "easyaccept/use_case_5.txt",
				"easyaccept/use_case_6.txt", "easyaccept/use_case_7.txt", "easyaccept/use_case_8.txt", "easyaccept/use_case_9.txt", "easyaccept/use_case_10.txt",
				"easyaccept/use_case_11.txt"};
		EasyAccept.main(args);
	}
}