package edu.gatech.seclass.crypto6300.Model;
import java.util.Random;
public class encryption{
    public static String process(String input)
    {
        String lowchars = "abcdefghijklmnopqrstuvwxyz";
        String capchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int shifts[]=new int [26];
        for (int i=0;i<26;i++)
            shifts[i]=i;
        int j,temp;
        for (int i=25;i>0;i--)
        {
            j= (int)Math.round(Math.random()*i);
            while ((shifts[j]==i)) j=(int)Math.round(Math.random()*i);
            temp=shifts[i];
            shifts[i]=shifts[j];
            shifts[j]=temp;
        };
        if (shifts[0]==0)
        {j=(int)Math.round(Math.random()*24);
         j++;
         shifts[0]=shifts[j];
         shifts[j]=0;
        };

        String result="";
        char[] charArray = input.toCharArray();
        for (int i=0;i<charArray.length;i++)
        {
           char origin_c = charArray[i];
           char new_c=origin_c;
           int pos;
            if ((origin_c>='a')&&(origin_c<='z')){
                pos=(int)(origin_c-'a'+0);
                new_c=lowchars.charAt(shifts[pos]);
            };
            if ((origin_c>='A')&&(origin_c<='Z')){
                pos=(int)(origin_c-'A'+0);
                new_c=capchars.charAt(shifts[pos]);
            };
            result+=new_c;
        };

        return result;
    }
}
