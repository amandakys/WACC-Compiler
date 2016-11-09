import java.util.Objects;

public class ARRAY extends TYPE {
  private TYPE elementType;
  private int elements;

  public ARRAY(String typeName, TYPE elementType, int elements) {
    super(typeName);
    this.elementType = elementType;
    this.elements = elements;
  }

  @Override
  public boolean equals(Object object) {
    if(object instanceof ARRAY) {
      ARRAY obj = (ARRAY) object;
      return this.elementType.equals(obj.elementType)
              && this.elements == obj.elements;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return elementType.hashCode() + elements * 100;
  }
}
