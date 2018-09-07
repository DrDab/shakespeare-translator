import java.io.IOException;
import java.util.Scanner;

public class Translation
{
	public static void main (String[] args) throws IOException
	{
		Translator t = new Translator("definitions.json", false);
		Scanner sc = new Scanner(System.in);
		for(;;)
		{
			System.out.println("Please enter English sentence: ");
			String s = sc.nextLine();
			System.out.println(t.getTranslated(s));
		}
	}
}
