
package com.fitweber.dao;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * <pre>
 * 程序的中文名称。
 * </pre>
 * @author wheatmark  hajima11@163.com
 * @version 1.00.00
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容: 
 * </pre>
 */
public interface XmlCreateDao {
	/**
	 * 
	 * @param requestMap
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List xmlDataQuery(HashMap<String,String> requestMap);
}
