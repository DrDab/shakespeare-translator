import java.io.IOException;

public class Translation
{
	public static void main (String[] args) throws IOException
	{
		Translator t = new Translator("definitions.json");
		System.out.println(t.getTranslated("I'm like bitch who is yo mans?\n"
				+ "Can't keep my dick in my pants!\n"
				+ "My bitch don't love me no more, aye\n"
				+ "She kick me out I'm like vro, aye\n"
				+ "That bitch don't wanna be friends, aye\n"
				+ "I gave hre dick, she got mad, aye\n"
				+ "She put her tongue on my dick, aye\n"
				+ "Look at my wrist, about 10, aye\n"
				+ "Just got a pound of that boof, aye\n"
				+ "Brought that shit straight to the boof, aye\n"
				+ "Tommy my Hilfiger boots, aye\n"
				+ "She said want fuck bitch, I do, aye\n"));
	}
}
