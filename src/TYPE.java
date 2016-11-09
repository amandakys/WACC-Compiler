public class TYPE extends IDENTIFIER {
  private final String typeName;

  public TYPE(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  @Override
  public boolean equals(Object object) {
    if(object instanceof TYPE) {
      TYPE obj = (TYPE) object;
      return this.typeName.equals(obj.typeName);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return typeName.hashCode();
  }
}
