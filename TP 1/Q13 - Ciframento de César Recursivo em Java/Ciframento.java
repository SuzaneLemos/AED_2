public class Ciframento 
{
	public static void main(String[] args) 
	{
		char[] entradaChar = new char[1000];
		int tam;		
		String entradaString = new String();
		entradaString = "";

		while (!entradaString.equals("FIM")) 
		{
			entradaString = MyIO.readLine();

			if (!entradaString.equals("FIM")) 
			{
				str2char(entradaString, entradaChar);
				tam = entradaString.length();

				encriptar(entradaChar, tam);
			}
		}
	}

	/*
	 * Converte de String para array de caracteres
	 */
	public static void str2char(String entradaString, char[] entradaChar) 
	{
		for (int i = 0; i < entradaString.length(); i++) 
		{
			entradaChar[i] = entradaString.charAt(i);
		}
	}

	/*
	 * Retorna a mensagem encriptada com a cifra de césar
	 * somar 0x0 a um valor inteiro pega o valor char referente a
	 * tal inteiro na tabela ASCII
	 */
	public static void encriptar(char[] entradaChar, int tam) 
	{
		for (int i = 0; i < tam; i++) 
		{
			MyIO.print((char) (entradaChar[i] + 3 + 0x0));
		}
		
		MyIO.println("");
	}
}