import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

@SuppressWarnings("all")
public class Translator
{
	private JSONObject obj;
	private boolean debug;
	
	public Translator(String fileName, boolean debug) throws IOException
	{
		this.debug = debug;
		String tmpJSON = "";
		BufferedReader br = new BufferedReader(new FileReader(fileName));
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
		String[] prefurry2 = input.split(" ");
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
				accum.add(prefurry2[tmpIdx]);
				tmpIdx++;
			}
			else
			{
				if (accum.size() != 0)
				{
					String chkEnd = accum.get(accum.size() - 1).substring(accum.get(accum.size() - 1).length() - 1);
					if (chkEnd.charAt(0) == '.' || chkEnd.charAt(0) == '!' || chkEnd.charAt(0) == '?' || chkEnd.charAt(0) == '-')
					{
						bestComparison = (bestComparison.substring(0, 1).toUpperCase() + bestComparison.substring(1));
						accum.add(bestComparison + furrydiff[tmpIdx]);
						tmpIdx = (bestIdx + 1);
					}
					else
					{
						accum.add(bestComparison + furrydiff[tmpIdx]);
						tmpIdx = (bestIdx + 1);
					}
				}
				else
				{
					bestComparison = (bestComparison.substring(0, 1).toUpperCase() + bestComparison.substring(1));
					accum.add(bestComparison + furrydiff[tmpIdx]);
					tmpIdx = (bestIdx + 1);
				}
			}
			
			if (debug)
			{
				System.out.println("B:" + bestComparison);
				System.out.println("R:" + toReturn);
				System.out.println("F:" + Arrays.toString(furry));
				System.out.println("A:" + accum.toString());
				System.out.println("D:" + Arrays.toString(furrydiff));
				System.out.println("I:" + tmpIdx + " B:" + bestIdx);
			}
		}
		for(String yiff : accum)
		{
			toReturn += (yiff + " "); 
		}
		return toReturn;
	}
}