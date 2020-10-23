package com.gbm.edu.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

@Component
public class CommonUtil {

	public static final String notNull(String str) {
		if ((null == str) || ("null".equals(str))) {
			return "";
		}
		return str;
	}

	public static String getCalsDate(int y, int z, String form) {
		Calendar cal = Calendar.getInstance();
		cal.add(y, z);
		Date currentTime = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat(form);
		String timestr = formatter.format(currentTime);

		return timestr;
	}

	public static StringBuffer makeFileMessage(Map<String, Object> temp) {

		StringBuffer msgLine = new StringBuffer();

		Iterator<String> titer = temp.keySet().iterator();
		while (titer.hasNext()) {
			String s = (String) titer.next();
			String v = stringEncoder("UTF-8", String.valueOf(temp.get(s)));
			if (v != null) {
				v = v.replace("\r\n", "∰");
				v = v.replace("\n", "∰");
				v = v.replace("\r", "∰");
			}
			temp.replace(s, v);
		}

		String[] s_filed = { "MSG_SEQ", "SERV_SEQ", "TALK_MSG", "MSG_TITLE", "MSG_CONT", "MSG_TYPE", "BACK_TYPE",
				"RECV_NO", "CALL_NO", "PROC_TYPE", "REQ_SEND_DT", "TMPL_CD", "MSG_IMG_PATH1", "MSG_IMG_PATH2",
				"MSG_IMG_PATH3", "IMG_PROC_FLAG", "TALK_BTN_NM", "TALK_BTN_URL", "TALK_BTN_LINK1", "TALK_BTN_LINK2",
				"TALK_BTN_LINK3", "TALK_BTN_LINK4", "TALK_BTN_LINK5", "TALK_IMG_PATH", "TALK_IMG_LINK",
				"AGENT_SEND_FLAG", "RESEND_FLAG", "MSG_IMG_SERV_PATH1", "MSG_IMG_SERV_PATH2", "MSG_IMG_SERV_PATH3",
				"TALK_IMG_SERV_PATH" };

		for(int i = 0; i < s_filed.length; i++) {
			msgLine.append(temp.get(s_filed[i]));
			if(i < s_filed.length - 1) {
				msgLine.append("ψ");
			}
		}
		msgLine.append("\r\n");

		return msgLine;
	}

	public static String stringEncoder(String encoding, String data) {
		try {
			if (!"".equals(notNull(data))) {
				Charset CS = Charset.forName(encoding);

				CharsetEncoder enc = CS.newEncoder();
				CharsetDecoder dec = CS.newDecoder();

				CharBuffer cb = CharBuffer.wrap(data);
				ByteBuffer bb = enc.encode(cb);

				CharBuffer cbuf = dec.decode(bb);

				return cbuf.toString();
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public static boolean deleteDirectory(File paramFile)
	{
	    boolean bool = false;
	    final char a = File.separatorChar;

	    if ((paramFile.exists()) && (paramFile.isDirectory()))
	    {
	      String[] arrayOfString = paramFile.list();

	      for (int i = 0; i < arrayOfString.length; i++)
	      {
	    	  File localFile = new File(paramFile.getAbsolutePath() + a + arrayOfString[i]);
	    	  if (localFile.isFile()) {
	    		  bool = localFile.delete();
	    	  } else {
	    		  deleteDirectory(localFile);
	    	  }
	      }

	      bool = paramFile.delete();
	    }
	    else
	    {
	    	throw new RuntimeException("deleteDirectory Error");
	    }
	    return bool;
	}

	public static boolean copyFiles(File[] paramArrayOfFile, File paramFile) throws Exception
	{
		boolean bool = true;
		File localFile1;

		for (int i = 0; i < paramArrayOfFile.length; i++)
		{
			localFile1 = paramArrayOfFile[i];
			if (!localFile1.exists()) {
				return bool;
			}
		}

		for (int j = 0; (j < paramArrayOfFile.length) && (bool); j++)
		{
			localFile1 = paramArrayOfFile[j];
			String str = paramFile.getAbsolutePath() + File.separator + localFile1.getName();
			File localFile2 = new File(str);
			copy(localFile1, localFile2);
			bool = true;
		}

		return bool;
	}

	public static void copy(File paramFile1, File paramFile2) throws Exception
	{
		try
		{
			copy(new BufferedInputStream(new FileInputStream(paramFile1)), new BufferedOutputStream(new FileOutputStream(paramFile2)));
		}
		catch (IOException localIOException)
		{
			throw new RuntimeException("copy(File in, File out) Error");
		}
	}

	public static void copy(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException
	{
		try
		{
			byte[] arrayOfByte = new byte[4096];
			int i = -1;

			while ((i = paramInputStream.read(arrayOfByte)) != -1) {
				paramOutputStream.write(arrayOfByte, 0, i);
			}
			paramOutputStream.flush();
		}
		finally
		{
			try
			{
				if (paramInputStream != null) {
					paramInputStream.close();
				}
			}
			catch (IOException localIOException1)
			{
				throw new RuntimeException("copy(InputStream in, OutputStream out) : " + localIOException1.getMessage());
			}
			try
			{
				if (paramOutputStream != null) {
					paramOutputStream.close();
				}
			}
			catch (IOException localIOException2)
			{
				throw new RuntimeException("copy(InputStream in, OutputStream out) : " + localIOException2.getMessage());
			}
		}
	}

	// Map을 json으로 변환한다.
	@SuppressWarnings("unchecked")
	public static JSONObject getJsonStringFromMap( Map<String, Object> map )
    {
        JSONObject jsonObject = new JSONObject();
        for( Map.Entry<String, Object> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            jsonObject.put(key, value);
        }

        return jsonObject;
    }

	// List<Map>을 jsonArray로 변환한다.
	@SuppressWarnings("unchecked")
	public static JSONArray getJsonArrayFromList( List<Map<String, Object>> list )
    {
        JSONArray jsonArray = new JSONArray();
        for( Map<String, Object> map : list ) {
            jsonArray.add(getJsonStringFromMap( map ));
        }

        return jsonArray;
    }

	// JsonObject를 Map<String, String>으로 변환한다.
	@SuppressWarnings("unchecked")
    public static Map<String, Object> getMapFromJsonObject( JSONObject jsonObj )
    {
        Map<String, Object> map = null;
        try {
            map = new ObjectMapper().readValue(jsonObj.toJSONString(), Map.class) ;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

	// JsonArray를 List<Map<String, String>>으로 변환한다.
	public static List<Map<String, Object>> getListMapFromJsonArray( JSONArray jsonArray )
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        if( jsonArray != null )
        {
            int jsonSize = jsonArray.size();
            for( int i = 0; i < jsonSize; i++ )
            {
                Map<String, Object> map = CommonUtil.getMapFromJsonObject( ( JSONObject ) jsonArray.get(i) );
                list.add( map );
            }
        }

        return list;
    }
}
