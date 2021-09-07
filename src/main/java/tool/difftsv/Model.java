package tool.difftsv;

public class Model {
  private String key;
  private String value = "値が取得できない";
  
  public Model(String line) {
    
    String[] splited = line.split("\t");
    
    int index = 0;
    this.key = splited[index++];
    
    String tmpValue = "";
    if(splited.length >= 2) {
      for( ; index<splited.length ; index++) {
        tmpValue = splited[index];
        if(index != splited.length) {
          tmpValue += "\t";
        }
      }
      this.value = tmpValue;
    }
    
    // System.out.println("key : " + this.key + " , value : " + this.value);
  }
  
  public String getKey() {
    return this.key;
  }
  public String getValue() {
    return this.value;
  }
}