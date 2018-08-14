import java.io.IOException;

public class Translation
{
	public static void main (String[] args) throws IOException
	{
		Translator t = new Translator("definitions.json", false);
		System.out.println(t.getTranslated("I'm like bitch who is yo mans? "
				+ "Can't keep my dick in my pants! "
				+ "My bitch don't love me no more, aye "
				+ "She kick me out I'm like vro, aye "
				+ "That bitch don't wanna be friends, aye "
				+ "I gave hre dick, she got mad, aye "
				+ "She put her tongue on my dick, aye "
				+ "Look at my wrist, about 10, aye "
				+ "Just got a pound of that boof, aye "
				+ "Brought that shit straight to the boof, aye "
				+ "Tommy my Hilfiger boots, aye "
				+ "She said want fuck bitch, I do, aye"));
	}
}
