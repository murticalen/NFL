import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Byes {
	
	public static void main(String[] args) throws Exception {
		
		Map<String, String> allTeams = new HashMap<>();
		fillMap(allTeams);
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\NFL byes\\byes.txt")));
		writer.write("Year,Week,Team\n");
		writer.flush();
		for(int i = 2; i < 7; i++){
			File file = new File("D:\\NFL byes\\201"+i+" byes.txt");
			BufferedReader reader = new BufferedReader
					(new InputStreamReader(new FileInputStream(file)));
			String line = reader.readLine();
			Map<Integer, List<String>> byes = new LinkedHashMap<>(32);
			while((line = reader.readLine()) != null){
				int pos = line.indexOf(' ');
				String week = line.substring(0, pos);
				String teams = line.substring(pos);
				Integer wk = Integer.parseInt(week.replaceAll("[^0-9]", ""));
				teams = teams.replaceAll("\\s", "");
				String[] tms = teams.split(",");
				List<String> list = new LinkedList<>();
				for(String tm : tms){
					String shortName = getShortName(allTeams, tm, console);
					list.add(shortName);
				}
				byes.put(wk, list);
			}
			reader.close();
			for(Integer week : byes.keySet()){
				for(String team : byes.get(week)){
					writer.write("201"+i+","+week+","+team+"\n");
					writer.flush();
				}
			}
			
		}
		writer.close();
		console.close();
		BufferedWriter wr = new BufferedWriter(new FileWriter(new File("D:\\NFL byes\\teams.txt")));
		for(String key : allTeams.keySet()){
			wr.write(key+";"+allTeams.get(key)+"\n");
		}
		wr.close();
	}

	private static String getShortName(Map<String, String> allTeams, String team, BufferedReader console) throws Exception {
		if(allTeams.containsKey(team)){
			return allTeams.get(team);
		}
		else{
			System.out.println("Navedite ime za momčad: " + team);
			String tm = console.readLine();
			allTeams.put(team, tm);
			return tm;
		}
	}
	
	private static void fillMap(Map<String, String> allTeams) throws Exception{
		File file = new File("D:\\NFL byes\\teams.txt");
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(new FileInputStream(file)));
		String line;
		while((line = reader.readLine()) != null){
			String[] ln = line.split(";");
			allTeams.put(ln[0], ln[1]);
		}
		reader.close();
	}
}
