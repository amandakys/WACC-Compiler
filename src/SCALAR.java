public class SCALAR extends TYPE {
  final private int min;
  final private int max;

  public SCALAR(String typeName,  int min, int max) {
    super(typeName);
    this.min = min;
    this.max = max;
  }

}
