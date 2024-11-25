package Framework.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.bytebuddy.asm.Advice.Return;

public class DataReader {
	
	@Test
	public static List<HashMap<String,String>> getJsonDatatoMap() throws IOException {
		
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir") +"//src//test//java//Framework//Data//data.json"),"UTF-8");
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
	
}
