package com.tools;

import java.lang.String;
public class Tool {
	/////检查是否包函非法字符，防止SQL从URL注入：
	public static boolean checkSql(String str)
	{
	String inj_str = "'| and |%|;| or |-|+| |,";
	//这里的东西还可以自己添加
	String[] inj_stra=inj_str.split("\\|");
	for (int i=0 ; i <inj_stra.length; i++ )
	{
	if (str.indexOf(inj_stra[i].trim())>=0)//indexof方法可返回某个指定的字符串值在字符串中首次出现的位置、、 方法对大小写敏感！
	{
	return false;
	}
	}
	return true;
	}
	///去掉空格，; - | ，基本上可以排除注入式攻击
	public static String getCheckedStr(String str) {
		str=str.replaceAll(" ","");
		str=str.replaceAll(";","");
		str=str.replaceAll("-","");
		str=str.replaceAll("|","");
		return str;
	}
}
/*也可能通过javascript
function check(a)
{
return 1;
fibdn = new Array (”‘” ,”\\”,”/”);
i=fibdn.length;
j=a.length;
for (ii=0; ii＜i; ii++)
{ for (jj=0; jj＜j; jj++)
{ temp1=a.charAt(jj);
temp2=fibdn[ii];
if (tem’; p1==temp2)
{ return 0; }
}
}
return 1;
}*/