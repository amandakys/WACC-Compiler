public class FUNCTION extends IDENTIFIER {
  TYPE returnType;
  PARAM formals[];
//  SymbolTable symtab;

  public FUNCTION(TYPE returnType, PARAM[] formals) {
    this.returnType = returnType;
    this.formals = formals;
    //this. sb = sb;
  }
}
