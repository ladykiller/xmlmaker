package com.fitweber.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.fitweber.util.CommonUtils;
import com.fitweber.util.ExecelUtils;

/**
 * <pre>
 * 行政区划XML文件分割器
 * </pre>
 * @author wheatmark  hajima11@163.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public class XmlCreateService {
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static void main(String[] argc){
		String resource = "META-INF/conf/mybatis-config.xml";
		String root = "";
		InputStream inputStream;
		
		try {
			//拿到数据库连接
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = sqlSessionFactory.openSession();
			//拿到查询参数
			List requestList = ExecelUtils.readExecelSimple("xmlmaker.xls");
			//定义变量
			int i,j,listSize;
			String filename,sqlstament,temp;;
			HashMap requestMap = new HashMap();
			Map map;
			StringBuffer buf = new StringBuffer();
			
			for(Object l:requestList){
				List list = (List)l;
				listSize = list.size();
				filename =(String)list.get(1);
				sqlstament =(String)list.get(2);
				requestMap.put("sql", sqlstament);
				List result = session.selectList("com.fitweber.dao.XmlCreateDao.xmlDataQuery",requestMap);
				for(Object r:result){
					buf.append("<option>");
					map=(Map)r;
					temp = (String) map.get("DM");
					if(temp!=null){
						buf.append("<dm>"+temp+"</dm>");
					}
					temp = (String) map.get("MC");
					if(temp!=null){
						buf.append("<mc>"+temp+"</mc>");
					}
					temp = (String) map.get("PC");
					if(temp!=null){
						buf.append("<pc>"+temp+"</pc>");
					}
					temp = (String) map.get("ITEM");
					if(temp!=null){
						buf.append("<item>"+temp+"</item>");
					}
					buf.append("</option>");
				}
				CommonUtils.saveFile(null, (System.getProperty("user.dir")+"\\xml\\").replace("\\", "/")+filename, ("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root><select>"+buf.toString()+"</select></root>"),false);
				buf.setLength(0);
			}
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
