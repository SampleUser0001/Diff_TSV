package tool.difftsv;

import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;

import tool.difftsv.Model;

import java.io.IOException;

/**
 * tsvファイルのdiffを取得する
 */
public class App {
  
  public static void main( String[] args ) throws IOException {
    System.out.println("args[0] : " + args[0]);
    System.out.println("args[1] : " + args[1]);
    
    Map<String, String> file1Map = readFile(args[0]);
    Map<String, String> file2Map = readFile(args[1]);
    

    try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(args[2]), Charset.forName("UTF-8"))) {
      final String format = "%s\t%s\t%s\r\n";
      writer.write(String.format(format, "key", args[0], args[1]));
      Stream.concat(file1Map.keySet().stream(), file2Map.keySet().stream())
            .distinct()
            .forEach(key -> {
              try {
                writer.write(String.format(format,
                  key,
                  file1Map.containsKey(key) ? file1Map.get(key) : "キーが指定されていない",
                  file2Map.containsKey(key) ? file2Map.get(key) : "キーが指定されていない"));
              } catch(IOException e) {
                e.printStackTrace();
              }
            });
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  
  public static Map<String, String> readFile(String filepath) throws IOException{
    return Files.lines(Paths.get(filepath))
         .map(line -> new Model(line))
         .collect(Collectors.toMap(Model::getKey, Model::getValue));
  }
  
}

