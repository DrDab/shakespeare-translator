import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Translator
{
	private JSONObject obj;
	public Translator() throws IOException
	{
		// Translator.class.getResourceAsStream("/definitions.json");
		String tmpJSON = "";
		BufferedReader br = new BufferedReader(new FileReader("definitions.json"));
		String inputLine;
		while ((inputLine = br.readLine()) != null)
		{
			tmpJSON += inputLine;
		}
		obj = new JSONObject(tmpJSON);
		br.close();
	}

	
	public String getTranslated(String input)
	{
		ArrayList<String> accum = new ArrayList<String>();
		String toReturn = "";
		String[] prefurry = input.toLowerCase().split(" ");
		String[] furry = new String[prefurry.length];
		String[] furrydiff = new String[prefurry.length];
		for(int i = 0; i < prefurry.length; i++)
		{
			furry[i] = prefurry[i].replaceAll("[,.!?-]", "").toLowerCase();
			furrydiff[i] = StringUtils.difference(furry[i], prefurry[i]);
		}
		int tmpIdx = 0;
		int max = furry.length;
		while(tmpIdx < max)
		{
			int cnt = 0;
			String comparison = "";
			String bestComparison = "";
			int bestIdx = tmpIdx;
			for(int i = tmpIdx; i < max; i++)
			{
				if (cnt > 0)
				{
					cnt--;
					comparison += " ";
				}
				String cmp = furry[i];
				comparison += cmp;
				cnt++;
				if (obj.has(comparison))
				{
					String s = obj.getString(comparison);
					if (s.length() > bestComparison.length())
					{
						bestComparison = s;
						bestIdx = i;
					}
				}
			}
			
			if (bestComparison.matches(""))
			{
				accum.add(furry[tmpIdx] + furrydiff[tmpIdx]);
				tmpIdx++;
			}
			else
			{
				accum.add(bestComparison + furrydiff[tmpIdx]);
				tmpIdx = (bestIdx + 1);
			}
			/*
			System.out.println("B:" + bestComparison);
			System.out.println("C:" + compared);
			System.out.println("R:" + toReturn);
			System.out.println("F:" + Arrays.toString(furry));
			System.out.println("A:" + accum.toString());
			System.out.println("D:" + Arrays.toString(furrydiff));
			System.out.println("I:" + tmpIdx + " B:" + bestIdx);
			*/
		}
		for(String yiff : accum)
		{
			toReturn += (yiff + " "); 
		}
		return toReturn;
	}
}