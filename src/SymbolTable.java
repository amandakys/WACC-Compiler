public class SymbolTable {
	SymbolTable encSymbolTable; //Ref to enclosing symbol table
	HashMap dict;               //Maps names to objects

	public SymbolTable(SymbolTable st) {
		dict = new HashMap();
		encSymbolTable = st;
	}

	public void add(string name, IDENTIFIER object) {
		return dict.put(name, object);
	}

	public IDENTIFIER lookUp(string name) {
		return dict.get(name);
	}

	public IDENTIFIER lookUpAll(string name) {
		SymbolTable S = this;
		while (S != Null) {
			IDENTIFIER obj = S.lookUp(name);
			if (obj != Null) return obj;
			S = encSymbolTable;
		}
		return Null;
	}
}
