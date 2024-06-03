package SubSystems;

import Enums.Language;

// TODO: Auto-generated Javadoc
/**
 * The Class testTrans.
 */
public class testTrans {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception{

		Translator.setToLanguage(Language.KZ);
//		Translator.printing(" 0) MAIN PAGE \n 1) News \n"
//				+ " 2) Change password \n 3) Change language \n 4) Subscribe journal \n 5) Messenger \n 6) Log out ");
		Translator.printing("\n 1) News ");



	}

}