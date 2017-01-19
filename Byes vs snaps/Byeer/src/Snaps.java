import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Snaps {
	
	public static void main(String[] args) throws Exception {
		Map<Integer, List<String>> map = new LinkedHashMap<>();
		for(int i = 2; i < 7; i++){
			File file = new File("D:\\NFL byes\\snaps201"+i+".csv");
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();
			List<String> list = new LinkedList<>();
			while((line = reader.readLine())!=null){
				list.add(line);
			}
			map.put(2010+i, list);
			reader.close();
		}
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File("D:\\NFL byes\\snaps.csv")));
		wr.write("Year,Team,Players\n");
		for(Integer year : map.keySet()){
			for(String team : map.get(year)){
				wr.write(year+","+team+"\n");
			}
		}
		wr.close();
	}
	
}
