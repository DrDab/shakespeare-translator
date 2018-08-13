import java.io.IOException;

public class Translation
{
	public static void main (String[] args) throws IOException
	{
		Translator t = new Translator();
		System.out.println(t.getTranslated("Your mom gay! WTF!"));
	}
}
