package file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.*;
public class FileRead {
	
	public static final String HEADER = "aaa,bbb,cccc,ddddd"; //JDK1.7以降
	
	public static void main(String[] args) throws IOException{
		FileRead fr = new FileRead();
		//fr.readDir(Paths.get("./csv"));
		fr.readLine2(Paths.get("./csv/Sample2.txt"));
	}
	
	public void readLine2(Path path) {
		Path dst = Paths.get("./result.txt");
		try(Stream<String> stream = Files.lines(Paths.get(path.toUri()),StandardCharsets.UTF_8);
			BufferedWriter bw = Files.newBufferedWriter(dst, StandardCharsets.UTF_8, CREATE, TRUNCATE_EXISTING, WRITE)){
			writeFile(HEADER,bw);			
			stream.forEach(text -> {
				String edit = edit(text);
				if(!"".equals(edit)){
					writeFile(edit,bw);
				}
			});		
		} catch (IOException e) {
			throw new UncheckedIOException(this.getClass().getName(), e);
		}
	}
	
	public void writeFile(String s, BufferedWriter bw){
		try{
			bw.write(s);
			bw.newLine();
		} catch (IOException e) {
			throw new UncheckedIOException(this.getClass().getName(), e);
		}
	}

	public String edit(String s){
		
		return "add["+s + "]";
	}

	public String edit(String s, BufferedWriter bw){
		//System.out.println(s);
		String[] strArray = { "abc", "def", "123", "456", "xyz" };
		String separator = ",";
		StringJoiner sj = new StringJoiner(separator);
		for (String str : strArray) {
			sj.add(str);
		}
		return sj.toString();
	}

	public void readLine(Path path) {
		Path dst = Paths.get("./result.txt");
		try(Stream<String> stream = Files.lines(Paths.get(path.toUri()),StandardCharsets.UTF_8);
			BufferedWriter bw = Files.newBufferedWriter(dst, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING)){
			stream.map(s->edit(s)).forEach(s -> writeFile(s,bw));		
		} catch (IOException e) {
			throw new UncheckedIOException(this.getClass().getName(), e);
		}
	}

	public void readDir(Path path){
		try (Stream<Path> stream = Files.list(Paths.get(path.toUri()))) {
			stream
		    .filter(entry -> entry.getFileName().toString().endsWith("Sample2.txt"))
		    .forEach(s->readLine2(s));
		} catch (IOException e) {
			throw new UncheckedIOException(this.getClass().getName(), e);
		} 
	}

}
